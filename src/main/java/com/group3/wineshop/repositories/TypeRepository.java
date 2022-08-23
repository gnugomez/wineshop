package com.group3.wineshop.repositories;

import com.group3.wineshop.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface TypeRepository extends JpaRepository<Type, Long> {
}
