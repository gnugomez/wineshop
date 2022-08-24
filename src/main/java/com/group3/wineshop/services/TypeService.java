package com.group3.wineshop.services;

import com.group3.wineshop.entities.Type;
import com.group3.wineshop.exceptions.NotFoundException;
import com.group3.wineshop.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    @Autowired
    TypeRepository typeRepository;

    public List<Type> getAll(){
        return typeRepository.findAll();
    }
    public Type getById(Long id) throws NotFoundException{
        return typeRepository.findById(id).orElseThrow((() -> new NotFoundException("Type not found")));
    }
    public Type save(Type type){
        return typeRepository.save(type);
    }
    public boolean delete(Long id) {
        typeRepository.deleteById(id);
        return true;
    }
    public Type update(Type type){
        return typeRepository.save(type);
    }
}
