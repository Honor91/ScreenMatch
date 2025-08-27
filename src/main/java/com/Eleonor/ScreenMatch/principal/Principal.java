package com.Eleonor.ScreenMatch.principal;

import com.Eleonor.ScreenMatch.models.episodios.DatosEpisodio;
import com.Eleonor.ScreenMatch.models.serie.DatosSerie;
import com.Eleonor.ScreenMatch.models.temporadas.DatosTemporada;
import com.Eleonor.ScreenMatch.service.ConsumoAPI;
import com.Eleonor.ScreenMatch.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner scanner = new Scanner(System.in);
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=a59e1ae9";


    public DatosSerie buscarSerie(){
        System.out.println("Ingrese el nombre de la Serie");
        var nombreSerie = scanner.nextLine();
        var serieJson = consumoAPI.obtenerDatos(URL_BASE+nombreSerie.replace(" ","+")+API_KEY);
        DatosSerie datosSerie = conversor.obtenerDatos(serieJson, DatosSerie.class);
        System.out.println(datosSerie);
        return datosSerie;
    }

    public DatosTemporada buscarEpisodio(){
        List<DatosTemporada> temporadas = new ArrayList<>();
        System.out.println("Ingrese el nombre de la serie");
        var nombreSerie = scanner.nextLine();
        nombreSerie = nombreSerie.replace(" ","+");
        var episodiosJson = consumoAPI.obtenerDatos(URL_BASE + nombreSerie + "&season=1" + API_KEY);
        DatosTemporada datosTemporada = conversor.obtenerDatos(episodiosJson, DatosTemporada.class);

        System.out.println(datosTemporada);
        return  datosTemporada;
    }

}
