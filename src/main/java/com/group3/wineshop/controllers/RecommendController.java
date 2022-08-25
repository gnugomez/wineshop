package com.group3.wineshop.controllers;

import com.group3.wineshop.entities.Wine;
import com.group3.wineshop.services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/recommend")
public class RecommendController {
    @Autowired
    WineService wineService;

    @GetMapping("/bang")
    List<Wine> getBang(@RequestParam("top") Optional<Integer> top){
        return top.map(rank -> wineService.getBang()
                        .stream()
                        .limit(rank)
                        .collect(Collectors.toList()))
                .orElseGet(() -> wineService.getBang());
    }

    @GetMapping("/expensive")
    List<Wine> getExpensive(@RequestParam("top") Optional<Integer> top) {
        return top.map(integer -> wineService.getExpensive()
                        .stream()
                        .limit(integer)
                        .collect(Collectors.toList()))
                .orElseGet(() -> wineService.getExpensive());
    }

    @GetMapping("/vintage")
    Map<Integer, List<Wine>> getVintage(@RequestParam("top") Optional<Integer> top) {
        return top.map(integer -> wineService.getYearsWithBestRatedWines().entrySet()
                .stream()
                .limit(integer)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
                .orElseGet(() -> wineService.getYearsWithBestRatedWines());

    }

    @GetMapping("/best")
    List<Wine> getBest(@RequestParam("top") Optional<Integer> top){
        return top.map(rank -> wineService.getBest()
                        .stream()
                        .limit(rank)
                        .collect(Collectors.toList()))
                .orElseGet(() -> wineService.getBest());
    }

}
