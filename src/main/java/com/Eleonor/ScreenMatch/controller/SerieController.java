package com.Eleonor.ScreenMatch.controller;

import com.Eleonor.ScreenMatch.dto.EpisodioDTO;
import com.Eleonor.ScreenMatch.dto.SerieDTO;

import com.Eleonor.ScreenMatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {
    @Autowired
    private SerieService service;

    @GetMapping()
    public List<SerieDTO> mostrarTodasLasSeries(){
        return service.obtenerTodasLasSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> topfive(){
        return service.obtenertop5();
    }
    @GetMapping("/lanzamientos")
    public List<SerieDTO> nuevosLanzamientos(){
        return service.obtenerNuevosLanzamientos();
    }
    @GetMapping("{id}")
    public SerieDTO obtenerPorId(@PathVariable Long id){
        return service.obtenerSeriePorId(id);
    }
    @GetMapping("{id}/temporadas/todas")
    public List<EpisodioDTO> todasLasTemporadas(@PathVariable Long id){
        return service.obtenerTodosLosEpisodios(id);
    }
    @GetMapping("{id}/temporadas/{numero}")
    public List<EpisodioDTO> episodiosPorTemporadas(@PathVariable Long id,@PathVariable Integer numero){
        return service.obtenerEpisodiosPorTemporada(id,numero);
    }
    @GetMapping("/categoria/{nombreGenero}")
    public List<SerieDTO> obtenerSeriesPorGenero(@PathVariable String nombreGenero){
        return service.obtenerSeriesPorGenero(nombreGenero);
    }
}
