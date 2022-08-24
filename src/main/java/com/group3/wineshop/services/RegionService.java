package com.group3.wineshop.services;

import com.group3.wineshop.entities.Region;
import com.group3.wineshop.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {
    @Autowired
    RegionRepository regionRepository;

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    
    public Region findById(long id) {
        return regionRepository.findById(id).orElse(null);
    }
    

    public Region create(Region region) {
        return regionRepository.save(region);
    }

    public Region update(Region region) {
        return regionRepository.save(region);
    }

    public void delete(Long id) {
        regionRepository.deleteById(id);
    }
}
