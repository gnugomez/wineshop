package com.group3.wineshop.controllers;

import com.group3.wineshop.entities.Wine;
import com.group3.wineshop.exceptions.NotFoundException;
import com.group3.wineshop.services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("save")
    Wine save(@RequestBody Wine wine) {
        return wineService.save(wine);
    }

    @PatchMapping("update")
    Wine update(@RequestBody Wine wine) {
        return wineService.save(wine);
    }

    @DeleteMapping("delete/{id}")
    void deleteOne(@PathVariable Long id){
        wineService.deleteById(id);
    }
}
