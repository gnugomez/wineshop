package com.group3.wineshop.repositories;

import com.group3.wineshop.entities.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WineRepository extends JpaRepository<Wine, Long> {

}
