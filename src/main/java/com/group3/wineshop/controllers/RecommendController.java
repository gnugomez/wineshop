package com.group3.wineshop.controllers;

import com.group3.wineshop.entities.Wine;
import com.group3.wineshop.services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/recommend")
public class RecommendController {
    @Autowired
    WineService wineService;

    @GetMapping("/vintage")
    Map<String, List<Wine>> getVintage(@RequestParam("top") Optional<Integer> top) {
        return top.map(integer -> wineService.getYearsWithBestRatedWines().entrySet()
                .stream()
                .limit(integer)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)))
                .orElseGet(() -> wineService.getYearsWithBestRatedWines());
    }

    @GetMapping("/best")
    List<Wine> getBest() {
        return wineService.getBest();
    }


}
