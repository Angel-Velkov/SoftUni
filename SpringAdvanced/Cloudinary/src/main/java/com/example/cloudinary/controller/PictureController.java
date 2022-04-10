package com.example.cloudinary.controller;

import com.example.cloudinary.model.PictureViewModel;
import com.example.cloudinary.model.binding.PictureBindingModel;
import com.example.cloudinary.model.entity.PictureEntity;
import com.example.cloudinary.repository.PictureRepository;
import com.example.cloudinary.service.CloudinaryImage;
import com.example.cloudinary.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PictureController {

    private final CloudinaryService cloudinaryService;
    private final PictureRepository pictureRepository;

    @Autowired
    public PictureController(CloudinaryService cloudinaryService, PictureRepository pictureRepository) {
        this.cloudinaryService = cloudinaryService;
        this.pictureRepository = pictureRepository;
    }

    @GetMapping("/add")
    public String getAddPicture() {
        return "add";
    }

    @PostMapping("/add")
    public String postPicture(PictureBindingModel pictureBindingModel) throws IOException {

        PictureEntity picture = createPictureEntity(pictureBindingModel.getPicture(), pictureBindingModel.getTitle());

        this.pictureRepository.save(picture);

        return "redirect:/all";
    }

    @Transactional
    @DeleteMapping("/delete")
    public String deletePicture(@RequestParam("public_id") String publicId){
        cloudinaryService
                .delete(publicId);

        return "redirect:all";
    }

    @GetMapping("/all")
    public String getAllPictures(Model model) {

        List<PictureViewModel> pictures = pictureRepository
                .findAll()
                .stream()
                .map(this::mapASViewModel)
                .collect(Collectors.toList());

        model.addAttribute("pictures", pictures);

        return "all";
    }

    private PictureViewModel mapASViewModel(PictureEntity picture) {
        return new PictureViewModel(picture.getTitle(), picture.getPublicId(), picture.getUrl());
    }

    private PictureEntity createPictureEntity(MultipartFile file, String title) throws IOException {
        CloudinaryImage uploaded = cloudinaryService.upload(file);

        return new PictureEntity(title, uploaded.getUrl(), uploaded.getPublicID());
    }
}
