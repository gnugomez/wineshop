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

    public ResponseEntity<String> delete(Integer id) {
        try {
            wineryRepository.deleteById(id);
            return new ResponseEntity<>("Winery deleted successfully", null, HttpStatus.NO_CONTENT);
        } catch (NotFoundException e){
            return new ResponseEntity<>("Winery not found", null, HttpStatus.NOT_FOUND);
        }
    }
}
