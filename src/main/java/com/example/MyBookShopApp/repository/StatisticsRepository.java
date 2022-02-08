package com.example.MyBookShopApp.repository;


import com.example.MyBookShopApp.data.other.Statistics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticsRepository extends CrudRepository<Statistics,Integer>, PagingAndSortingRepository<Statistics, Integer> {


    Optional<Statistics> getStatisticsBySlug(String slug);

}
