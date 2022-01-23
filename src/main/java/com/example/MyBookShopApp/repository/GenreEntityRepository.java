package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.genre.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreEntityRepository extends JpaRepository<GenreEntity, Integer>, PagingAndSortingRepository<GenreEntity, Integer> {



    GenreEntity getBySlug(String slug);

    List<GenreEntity> findAllBySlug(String slag);

    List<GenreEntity> findAllByOrderByListBookDesc();





}
