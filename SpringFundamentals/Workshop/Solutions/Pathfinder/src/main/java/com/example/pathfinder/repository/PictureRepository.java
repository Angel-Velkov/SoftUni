package com.example.pathfinder.repository;

import com.example.pathfinder.model.entity.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PictureRepository extends JpaRepository<PictureEntity, Long> {

    @Query("SELECT p.url FROM PictureEntity AS p")
    Set<String> findAllUrls();
}
