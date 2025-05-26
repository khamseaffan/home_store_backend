package com.khamse.homestore.product.util;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface for file storage services
 */
public interface StorageService {
    
    /**
     * Upload multiple files and return their URLs
     */
    List<String> uploadFiles(List<MultipartFile> files) throws IOException;
    
    /**
     * Delete files by their URLs
     */
    void deleteImages(List<String> fileUrls);
}
