package com.example.pathfinder.service.impl;

import com.example.pathfinder.repository.PictureRepository;
import com.example.pathfinder.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Set<String> findAllUrls() {
        return this.pictureRepository.findAllUrls();
    }
}
