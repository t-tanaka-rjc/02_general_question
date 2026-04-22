package com.example.__general_question.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.__general_question.entity.Place;

public interface PlacesRepository extends JpaRepository<Place, String>{

}
