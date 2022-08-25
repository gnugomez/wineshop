package com.group3.wineshop.services;

import com.group3.wineshop.entities.Winery;
import com.group3.wineshop.exceptions.NotFoundException;
import com.group3.wineshop.repositories.WineryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WineryService {

    @Autowired
    WineryRepository wineryRepository;

    public List<Winery> getAll() {
        return wineryRepository.findAll();
    }

    public Winery getById(Integer id) throws NotFoundException {
        return wineryRepository.findById(id).orElseThrow(() -> new NotFoundException("Winery not found"));
    }

    public Winery save(Winery winery) {
        return wineryRepository.save(winery);
    }

    public Winery update(Winery winery) {
        return wineryRepository.save(winery);
    }

    public void delete(Integer id) {
        wineryRepository.findById(id).
                map(region -> {
                    wineryRepository.delete(region);
                    return region;
                }).orElseThrow(() -> new NotFoundException("Winery not found"));
    }
}
