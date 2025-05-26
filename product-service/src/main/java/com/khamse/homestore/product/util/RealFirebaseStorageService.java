package com.khamse.homestore.product.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.Blob; // Re-add import for Blob
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;

/**
 * Real Firebase Storage Service implementation.
 * This will be activated when firebase.enabled=true in application.yml
 */
@Service
@ConditionalOnProperty(name = "firebase.enabled", havingValue = "true")
public class RealFirebaseStorageService implements StorageService {

    private static final long MAX_SIZE = 5L * 1024L * 1024L; // 5MB
    
    @Value("${firebase.storage.bucket}") // Inject bucket name from properties
    private String bucketName;
    
    private final StorageClient storageClient;

    @Autowired
    public RealFirebaseStorageService(StorageClient storageClient) {
        this.storageClient = storageClient;
    }

    /**
     * Upload files to Firebase Storage
     */
    public List<String> uploadFiles(List<MultipartFile> files) throws IOException {
        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("Upload failed: no files provided.");
        }

        List<String> imageUrls = new ArrayList<>();
        Bucket bucket = storageClient.bucket(bucketName);

        for (MultipartFile file : files) {
            validateFile(file);
            
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                throw new IllegalArgumentException("One of the files has no name.");
            }

            // Generate unique filename
            String fileName = "products/" + UUID.randomUUID() + "-" + originalFilename;
            
            // Upload file to Firebase Storage
            bucket.create(fileName, file.getBytes(), file.getContentType());
            
            // Construct the public URL. This assumes objects are publicly readable by default 
            // or that Firebase security rules allow read access.
            String publicUrl = String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                bucketName, java.net.URLEncoder.encode(fileName, java.nio.charset.StandardCharsets.UTF_8.toString()));
            imageUrls.add(publicUrl);
        }

        return imageUrls;
    }

    /**
     * Delete files from Firebase Storage
     */
    public void deleteImages(List<String> fileUrls) {
        if (fileUrls == null || fileUrls.isEmpty()) {
            return;
        }

        Bucket bucket = storageClient.bucket(bucketName);

        for (String fileUrl : fileUrls) {
            try {
                // Extract file name from URL
                String fileName = extractFileNameFromUrl(fileUrl);
                if (fileName != null) {
                    Blob blob = bucket.get(fileName);
                    if (blob != null && blob.delete()) {
                        System.out.println("Deleted file: " + fileName);
                    } else {
                        System.out.println("File not found for deletion: " + fileName);
                    }
                }
            } catch (Exception e) {
                System.err.println("Error deleting file: " + fileUrl + " - " + e.getMessage());
            }
        }
    }
    
    private void validateFile(MultipartFile file) {
        if (file.getSize() > MAX_SIZE) {
            throw new IllegalArgumentException("File size exceeds the 5MB limit.");
        }
        
        // Validate file type
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Only image files are allowed.");
        }
    }
    
    private String extractFileNameFromUrl(String url) {
        // Extract the file path from Firebase Storage URL
        // This is a simplified implementation
        if (url.contains("products/")) {
            int startIndex = url.indexOf("products/");
            int endIndex = url.indexOf("?");
            if (endIndex == -1) endIndex = url.length();
            return url.substring(startIndex, endIndex);
        }
        return null;
    }
}
