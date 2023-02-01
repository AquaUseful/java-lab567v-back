package org.psu.lab567.service;

import java.io.IOException;

import org.psu.lab567.model.BinFile;
import org.psu.lab567.repository.BinFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.NonNull;

@Service
public class BinFileService {
    @Autowired
    private BinFileRepository binFileRepository;

    public BinFile createFromMultipart(@NonNull MultipartFile file) throws IOException {
        final BinFile binFile = new BinFile(file.getContentType(), file.getSize(), file.getBytes());
        binFileRepository.save(binFile);
        return binFile;
    }

    public BinFile updateFromMultipart(@NonNull BinFile binFile, @NonNull MultipartFile multipartFile)
            throws IOException {
        binFile.setMimeType(multipartFile.getContentType());
        binFile.setSize(multipartFile.getSize());
        binFile.setContent(multipartFile.getBytes());
        binFileRepository.save(binFile);
        return binFile;
    }
}
