package com.example.cloudinary.service.impl;

import com.cloudinary.Cloudinary;
import com.example.cloudinary.repository.PictureRepository;
import com.example.cloudinary.service.CloudinaryImage;
import com.example.cloudinary.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";
    private static final String PUBLIC_ID = "public_id";

    private final Cloudinary cloudinary;
    private final PictureRepository pictureRepository;

    @Autowired
    public CloudinaryServiceImpl(Cloudinary cloudinary, PictureRepository pictureRepository) {
        this.cloudinary = cloudinary;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public CloudinaryImage upload(MultipartFile file) throws IOException {
        File tempFile = File.createTempFile(TEMP_FILE, file.getOriginalFilename());
        file.transferTo(tempFile);

        try {
            @SuppressWarnings("unchecked")
            Map<String, String> uploadResult = cloudinary
                    .uploader()
                    .upload(tempFile, Map.of());

            String url = uploadResult.getOrDefault(URL, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pinterest.com%2Fpin%2F98516310568877692%2F&psig=AOvVaw2WYW6gTQCJX5Mi5kYo_mah&ust=1649700113561000&source=images&cd=vfe&ved=0CAcQjRxqFwoTCMCvoPyJivcCFQAAAAAdAAAAABAG");
            String publicId = uploadResult.getOrDefault(PUBLIC_ID, "");

            CloudinaryImage cloudinaryImage = new CloudinaryImage();
            cloudinaryImage.setUrl(url);
            cloudinaryImage.setPublicID(publicId);

            return cloudinaryImage;

        } finally {
            tempFile.delete();
        }
    }

    @Override
    public boolean delete(String publicId) {

        try {
            cloudinary
                    .uploader()
                    .destroy(publicId, Map.of());

            pictureRepository.deleteAllByPublicId(publicId);

        } catch (IOException e) {
            return false;
        }

        return true;
    }
}
