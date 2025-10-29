package com.example.ourlibrary.repository;

import com.example.ourlibrary.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GenreRepository extends JpaRepository<Category, UUID> {
}
