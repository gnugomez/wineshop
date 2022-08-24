package com.group3.wineshop.services;

import com.group3.wineshop.entities.Wine;
import com.group3.wineshop.exceptions.NotFoundException;
import com.group3.wineshop.repositories.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WineService {
    @Autowired
    WineRepository wineRepository;

    public List<Wine> getAll() {
        return wineRepository.findAll();
    }
    public Wine getById(Long id) throws NotFoundException{
        return wineRepository.findById(id).orElseThrow(() -> new NotFoundException("Wine not found"));
    }
    public Wine save(Wine wine) {
        return wineRepository.save(wine);
    }
    public boolean delete(Long id){
        wineRepository.deleteById(id);
        return true;
    }


}
