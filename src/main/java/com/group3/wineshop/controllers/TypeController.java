package com.group3.wineshop.controllers;

import com.group3.wineshop.entities.Type;
import com.group3.wineshop.exceptions.NotFoundException;
import com.group3.wineshop.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type")

public class TypeController {
    @Autowired
    private TypeService typeService;
    @GetMapping("")
    public List<Type> getAll(){
        return typeService.getAll();
    }
    @GetMapping("/{id}")
    public Type getById(@PathVariable Integer id) throws NotFoundException {
        return typeService.getById(id);
    }
    @PostMapping("")
    public ResponseEntity<Type> save(@RequestBody Type type) {
        return new ResponseEntity<>(typeService.save(type), null, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Type update(@PathVariable Integer id, @RequestBody Type type){
        type.setId(id);
        return typeService.update(type);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws NotFoundException {
       typeService.delete(id);
    }
}
