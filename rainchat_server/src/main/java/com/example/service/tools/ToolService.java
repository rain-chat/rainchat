package com.example.service.tools;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ToolService {
    Boolean deleteFile(String path);

    String uploadImage(MultipartFile file) throws IOException;
    String loadImage(String url);
}
