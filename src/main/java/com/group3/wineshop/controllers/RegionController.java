package com.group3.wineshop.controllers;

import com.group3.wineshop.entities.Region;
import com.group3.wineshop.entities.Winery;
import com.group3.wineshop.exceptions.NotFoundException;
import com.group3.wineshop.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionController {
    @Autowired
    RegionService regionService;

    @GetMapping("")
    public List<Region> getAll() {
        return regionService.findAll();
    }

    @GetMapping("/{id}")
    public Region getById(@PathVariable Long id) throws NotFoundException {
        return regionService.findById(id);

    }

    @PostMapping("")
    public ResponseEntity<Region> save(@RequestBody Region region) {
        return new ResponseEntity<>(regionService.create(region), null, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Region> update(@RequestBody Region region, @PathVariable Long id) {
        region.setId(id);
        return new ResponseEntity<>(regionService.update(region), null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        regionService.delete(id);
    }
}
