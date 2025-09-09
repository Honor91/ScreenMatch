package com.Eleonor.ScreenMatch.controller;

import com.Eleonor.ScreenMatch.dto.SerieDTO;
import com.Eleonor.ScreenMatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {
    @Autowired
    private SerieService service;

    @GetMapping
    public List<SerieDTO> mostrarTodasLasSeries(){
        return service.obtenerTodasLasSeries();
    }

}
