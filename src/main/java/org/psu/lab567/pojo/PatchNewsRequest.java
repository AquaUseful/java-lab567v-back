package org.psu.lab567.pojo;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PatchNewsRequest {
    @NotEmpty(message = "Пустое имя новости")
    private String title;
    @NotEmpty(message = "Пустое содержание")
    private String content;
    private MultipartFile picture;
    private String removePicture;
}
