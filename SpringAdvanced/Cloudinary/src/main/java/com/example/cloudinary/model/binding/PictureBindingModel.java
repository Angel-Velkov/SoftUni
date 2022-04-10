package com.example.cloudinary.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
@Setter
public class PictureBindingModel {
    private String title;
    private MultipartFile picture;
}
