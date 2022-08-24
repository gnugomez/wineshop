package com.group3.wineshop.controllers;

import com.group3.wineshop.entities.Type;
import com.group3.wineshop.exceptions.NotFoundException;
import com.group3.wineshop.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")

public class TypeController {
    @Autowired
    private TypeService typeService;
    @GetMapping("")
    public List<Type> getAll(){
        return typeService.getAll();
    }
    @GetMapping("/{id}")
    public Type getById(@PathVariable Long id) throws NotFoundException {
        return typeService.getById(id);
    }
    @PostMapping("")
    public Type save(@RequestBody Type type){
        return typeService.save(type);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        typeService.deleteById(id);

    }

    @PatchMapping("/{id}")
    public Type update(@PathVariable Long id, @RequestBody Type type){
        type.setId(id);
        return typeService.update(type);

    }
}
