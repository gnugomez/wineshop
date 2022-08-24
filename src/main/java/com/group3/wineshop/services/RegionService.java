package com.group3.wineshop.services;

import com.group3.wineshop.entities.Region;
import com.group3.wineshop.exceptions.NotFoundException;
import com.group3.wineshop.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    RegionRepository regionRepository;

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    
    public Region findById(long id) throws NotFoundException {
        return regionRepository.findById(id).orElseThrow(() -> new NotFoundException("Region not found"));

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
