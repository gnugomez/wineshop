package com.group3.wineshop.controllers;

import com.group3.wineshop.entities.Winery;
import com.group3.wineshop.services.WineryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wineries")
public class WineryController {

    @Autowired
    WineryService wineryService;

    @GetMapping("")
    public List<Winery> getAll() {
        return wineryService.getAll();
    }

    @GetMapping("/{id}")
    public Winery getById(@PathVariable Long id) {
        return wineryService.getById(id).orElse(null);
    }

    @PostMapping("")
    public ResponseEntity<Winery> save(@RequestBody Winery winery) {
        return new ResponseEntity<>(wineryService.save(winery), null, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Winery> update(@PathVariable Long id ,@RequestBody Winery winery) {
        winery.setId(id);
        return new ResponseEntity<>(wineryService.update(winery), null, 204);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        wineryService.delete(id);
        return new ResponseEntity<>("Winery successfully deleted", null, 204);
    }
}
