package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.Bestseller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BestsellerRepository extends JpaRepository<Bestseller, Integer> {
}
