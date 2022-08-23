package com.group3.wineshop.services;

import com.group3.wineshop.entities.Winery;
import com.group3.wineshop.repositories.WineryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WineryService {

    @Autowired
    WineryRepository wineryRepository;

    public List<Winery> getAll() {
        return wineryRepository.findAll();
    }

    public Optional<Winery> getById(Long id) {
        return wineryRepository.findById(id);
    }

    public Winery save(Winery winery) {
        return wineryRepository.save(winery);
    }

    public Winery update(Winery winery) {
        return wineryRepository.save(winery);
    }

    public void delete(Long id) {
        wineryRepository.deleteById(id);
    }
}
