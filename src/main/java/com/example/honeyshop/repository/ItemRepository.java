package com.example.honeyshop.repository;

import com.example.honeyshop.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findByNameContaining(String name, Pageable pageable);
}
