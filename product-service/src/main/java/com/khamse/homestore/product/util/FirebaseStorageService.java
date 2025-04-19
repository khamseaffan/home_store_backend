package com.khamse.homestore.product.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

@Service
public class FirebaseStorageService {

    private final Storage storage;
    private final String bucketName;

    public FirebaseStorageService(Storage storage,
                                   @Value("${spring.cloud.gcp.storage.bucket}") String bucketName) {
        this.storage = storage;
        this.bucketName = bucketName;
    }


    private static final long MAX_SIZE = 5L * 1024L * 1024L; // 5MB


    public List<String> uploadFiles(List<MultipartFile> files) throws IOException {
        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("Upload failed: no files provided.");
        }

        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.getSize() > MAX_SIZE) {
                throw new IllegalArgumentException("File size exceeds the 5MB limit.");
            }

            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                throw new IllegalArgumentException("One of the files has no name.");
            }

            String cleanedFilename = originalFilename.replaceAll("\\s+", "");
            String uniqueFilename = UUID.randomUUID() + "-" + cleanedFilename;

            BlobId blobId = BlobId.of(bucketName, uniqueFilename);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(file.getContentType())
                    .build();

            storage.create(blobInfo, file.getBytes());

            String imageUrl = String.format(
                "https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                bucketName,
                java.net.URLEncoder.encode(uniqueFilename, StandardCharsets.UTF_8)
            );

            imageUrls.add(imageUrl);
        }

        return imageUrls;
    }

    public void deleteImages(List<String> fileUrls) {
        if (fileUrls == null || fileUrls.isEmpty()) {
            throw new IllegalArgumentException("Delete failed: no file paths provided.");
        }

        for (String fileUrl : fileUrls) {
            try {
                String objectName = extractObjectName(fileUrl);
                boolean deleted = storage.delete(BlobId.of(bucketName, objectName));
                if (deleted) {
                    System.out.println("Deleted: " + fileUrl);
                } else {
                    System.out.println("Unable to delete: " + fileUrl);
                }
            } catch (Exception e) {
                System.err.println("Error deleting " + fileUrl + ":\n\t" + e.getMessage());
            }
        }
    }

    private String extractObjectName(String url) {
        if (url.contains("/o/") && url.contains("?")) {
            int startIndex = url.indexOf("/o/") + 3;
            int endIndex = url.indexOf("?", startIndex);
            String encodedPath = url.substring(startIndex, endIndex);
            return URLDecoder.decode(encodedPath, StandardCharsets.UTF_8);
        } else {
            // Raw object name
            return url;
        }
    }
}
