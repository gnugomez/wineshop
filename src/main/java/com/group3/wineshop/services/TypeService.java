package com.group3.wineshop.services;

import com.group3.wineshop.entities.Type;
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
    public Optional<Type> getById(Long id){
        return typeRepository.findById(id);
    }
    public Type save(Type type){
        return typeRepository.save(type);
    }
    public void deleteById(Long id){
        typeRepository.deleteById(id);
    }
    public Type update(Type type){
        return typeRepository.save(type);
    }
}
