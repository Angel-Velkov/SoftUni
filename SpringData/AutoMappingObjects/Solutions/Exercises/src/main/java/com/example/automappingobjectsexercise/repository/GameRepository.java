package com.example.automappingobjectsexercise.repository;

import com.example.automappingobjectsexercise.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

}
