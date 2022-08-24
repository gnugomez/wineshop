package com.group3.wineshop.controllers;

import com.group3.wineshop.entities.Wine;
import com.group3.wineshop.exceptions.NotFoundException;
import com.group3.wineshop.services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/wines")
public class WineController {

    @Autowired
    WineService wineService;

    @GetMapping("")
    List<Wine> findAll(){
        return wineService.getAll();
    }

    @GetMapping("/{id}")
    Wine findOne(@PathVariable Long id) throws NotFoundException {
        return wineService.getById(id);
    }

    @PostMapping("")
    ResponseEntity<Wine> save(@RequestBody Wine wine) {
        return new ResponseEntity<>(wineService.save(wine), null, HttpStatus.CREATED);
    }

    @PatchMapping("")
    Wine update(@RequestBody Wine wine) {
        return  wineService.save(wine);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteOne(@PathVariable Long id){
        wineService.delete(id);
        return new ResponseEntity<>("Wine deleted successfully", null, HttpStatus.ACCEPTED);
    }
}
