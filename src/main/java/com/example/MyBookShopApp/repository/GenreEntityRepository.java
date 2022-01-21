package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.Dto.TreeGenreDto;
import com.example.MyBookShopApp.data.genre.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreEntityRepository extends JpaRepository<GenreEntity, Integer>, PagingAndSortingRepository<GenreEntity, Integer> {


    List<TreeGenreDto> findAllDtoedBy();

}
