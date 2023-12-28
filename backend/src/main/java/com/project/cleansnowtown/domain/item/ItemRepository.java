package com.project.cleansnowtown.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsByNameAndSize(String name, String size);
}
