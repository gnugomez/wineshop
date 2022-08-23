package com.group3.wineshop;

import com.group3.wineshop.repositories.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class command implements CommandLineRunner {
    @Autowired
    private WineRepository wineRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(wineRepository.findAll());
    }
}
