package com.Eleonor.ScreenMatch.service;

import com.Eleonor.ScreenMatch.dto.SerieDTO;
import com.Eleonor.ScreenMatch.models.serie.ISerieRepository;
import com.Eleonor.ScreenMatch.models.serie.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private ISerieRepository repository;


    public List<SerieDTO> obtenerTodasLasSeries() {
        return convierteDatos(repository.findAll());
    }
    public List<SerieDTO> convierteDatos(List<Serie> serie){
        return serie.stream()
                .map( s -> new SerieDTO(
                        s.getId(),
                        s.getTitulo(),
                        s.getTotalTemporadas(),
                        s.getEvaluacion(),
                        s.getPoster(),
                        s.getGenero(),
                        s.getActores(),
                        s.getSinopsis()))
                .collect(Collectors.toList());

    }
}
