package com.example.__general_question.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.__general_question.entity.Position;

public interface PositionsRepository extends JpaRepository<Position, String>{

}
