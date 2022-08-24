package com.group3.wineshop.controllers;

import com.group3.wineshop.entities.Region;
import com.group3.wineshop.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/region")
public class RegionController {
    @Autowired
    RegionService regionService;

    @GetMapping("")
    public List<Region> getAll() {
        return regionService.findAll();
    }

    @GetMapping("/{id}")
    public Region getById(@PathVariable Long id) throws Exception {
        return regionService.findById(id);
    }

    @PostMapping("")
    public Region save(@RequestBody Region region) {
        return regionService.create(region);
    }

    @PatchMapping("/{id}")
    public Region update(@RequestBody Region region, @PathVariable Long id) {
        region.setId(id);
        return regionService.update(region);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        regionService.delete(id);
    }
}
