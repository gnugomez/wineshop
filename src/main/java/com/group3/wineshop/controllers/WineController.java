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
@RequestMapping("api/wine")
public class WineController {

    @Autowired
    WineService wineService;

    @GetMapping("")
    List<Wine> findAll(){
        return wineService.getAll();
    }

    @GetMapping("/{id}")
    Wine findOne(@PathVariable Integer id) throws NotFoundException {
        return wineService.getById(id);
    }

    @PostMapping("")
    ResponseEntity<Wine> save(@RequestBody Wine wine) {
        return new ResponseEntity<>(wineService.save(wine), null, HttpStatus.CREATED);
    }

    @PutMapping ("/{id}")
    Wine update(@PathVariable Integer id, @RequestBody Wine wine) {
        wine.setId(id);
        return  wineService.save(wine);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws NotFoundException {
        wineService.delete(id);
    }
}
