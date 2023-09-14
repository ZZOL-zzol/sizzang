package com.zzol.sizzang.s3.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    String saveFile(MultipartFile file);

    byte[] downloadFile(String filename);

    String deleteFile(String filename);

    List<String> listAllFiles();
}
