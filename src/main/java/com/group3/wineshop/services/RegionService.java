package com.group3.wineshop.services;

import com.group3.wineshop.entities.Region;
import com.group3.wineshop.exceptions.NotFoundException;
import com.group3.wineshop.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {
    @Autowired
    RegionRepository regionRepository;

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Region findById(Integer id) throws NotFoundException {
        return regionRepository.findById(id).orElseThrow(() -> new NotFoundException("Region not found"));

    }

    public Region create(Region region) {
        return regionRepository.save(region);
    }

    public Region update(Region region) {
        return regionRepository.save(region);
    }

    public ResponseEntity<String> delete(Integer id) {
        try {
            regionRepository.deleteById(id);
            return new ResponseEntity<>("Region deleted successfully", null, HttpStatus.NO_CONTENT);
        } catch (NotFoundException e){
            return new ResponseEntity<>("Region not found", null, HttpStatus.NOT_FOUND);
        }
    }
}
