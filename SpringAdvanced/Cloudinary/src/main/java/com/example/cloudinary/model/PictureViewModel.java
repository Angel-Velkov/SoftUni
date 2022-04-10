package com.example.cloudinary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PictureViewModel {
    private String title;
    private String publicId;
    private String url;
}
