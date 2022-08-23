package com.group3.wineshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.group3.wineshop.entities.Region;
import org.springframework.stereotype.Component;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
