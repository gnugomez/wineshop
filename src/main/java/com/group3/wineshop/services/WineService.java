package com.group3.wineshop.services;

import com.group3.wineshop.entities.Wine;
import com.group3.wineshop.exceptions.NotFoundException;
import com.group3.wineshop.repositories.WineRepository;
import com.group3.wineshop.utilities.RatingToPriceRatioComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WineService {
    @Autowired
    WineRepository wineRepository;

    public List<Wine> getAll() {
        return wineRepository.findAll();
    }
    
    public Wine getById(Integer id) throws NotFoundException{
        return wineRepository.findById(id).orElseThrow(() -> new NotFoundException("Wine not found"));
    }
    
    public Wine save(Wine wine) {
        return wineRepository.save(wine);
    }

    public void delete(Integer id) {
        wineRepository.findById(id).
                map(region -> {
                    wineRepository.delete(region);
                    return region;
                }).orElseThrow(() -> new NotFoundException("Wine not found"));
    }

    //Recommendation endpoints
    public List<Wine> getExpensive(){
        List<Wine> wines = getAll();
        return wines.stream()
                .sorted(Comparator.comparingDouble(Wine::getPrice).reversed())
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Wine>> getYearsWithBestRatedWines(){
        List<Wine> wines = getAll();
        return wines.stream()
                .sorted(
                        Comparator.comparingInt(Wine::getNum_reviews).reversed()
                        .thenComparingDouble(Wine::getRating).reversed()
                )
                .collect(Collectors.groupingBy(Wine::getYear));
    }

    public List<Wine> getBest() {
        List<Wine> wines = getAll();
        return wines.stream()
                .sorted(Comparator.comparingDouble(Wine::getRating).reversed())
                .collect(Collectors.toList());
    }

    public List<Wine> getBang(){
        List<Wine> wines = getAll();
        return wines.stream()
                .sorted(new RatingToPriceRatioComparator().reversed())
                .collect(Collectors.toList());
    }

}
