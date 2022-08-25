package com.group3.wineshop.services;

import com.group3.wineshop.entities.Type;
import com.group3.wineshop.exceptions.NotFoundException;
import com.group3.wineshop.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    TypeRepository typeRepository;

    public List<Type> getAll(){
        return typeRepository.findAll();
    }
    public Type getById(Integer id) throws NotFoundException{
        return typeRepository.findById(id).orElseThrow((() -> new NotFoundException("Type not found")));
    }
    public Type save(Type type){
        return typeRepository.save(type);
    }

    public Type update(Type type){
        return typeRepository.save(type);
    }

    public ResponseEntity<String> delete(Integer id) {
        try {
            typeRepository.deleteById(id);
            return new ResponseEntity<>("Type deleted successfully", null, HttpStatus.NO_CONTENT);
        } catch (NotFoundException e){
            return new ResponseEntity<>("Type not found", null, HttpStatus.NOT_FOUND);
        }
    }
}
