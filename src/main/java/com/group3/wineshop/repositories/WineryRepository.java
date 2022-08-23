package com.group3.wineshop.repositories;

import com.group3.wineshop.entities.Winery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface WineryRepository extends JpaRepository<Winery, Long> {

}
