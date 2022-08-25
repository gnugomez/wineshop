package com.group3.wineshop.services;

import com.group3.wineshop.entities.Wine;
import com.group3.wineshop.exceptions.NotFoundException;
import com.group3.wineshop.repositories.WineRepository;
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
    public ResponseEntity<String> delete(Integer id){
        try {
            wineRepository.deleteById(id);
            return new ResponseEntity<>("Wine deleted successfully", null, HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>("Wine not found", null, HttpStatus.NOT_FOUND);
        }
    }

    //Recommendation endpoints
    public List<Wine> getBest(){
        List<Wine> wines = getAll();
        return wines.stream().filter((w)-> w.getRating()>=4.5)
                .sorted(Comparator.comparingInt(Wine::getNum_reviews).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }


    // this method returns a list of all years ordered by the ones with best rating wines
    public Map<String, List<Wine>> getYearsWithBestRatedWines(){
        List<Wine> wines = getAll();
        return wines.stream()
                .sorted(
                        Comparator.comparingInt(Wine::getNum_reviews).reversed()
                        .thenComparingDouble(Wine::getRating).reversed()
                )
                .collect(Collectors.groupingBy(Wine::getYear));
    }


}
