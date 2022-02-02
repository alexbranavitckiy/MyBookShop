package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.other.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer>, PagingAndSortingRepository<Tag, Integer> {


    @Query("from Tag order by listBook.size desc")
    List<Tag> getTagsOrderByDesc();

   Optional<Tag> getTagById(Integer id);



}
