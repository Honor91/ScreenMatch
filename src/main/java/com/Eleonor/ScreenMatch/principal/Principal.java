package com.Eleonor.ScreenMatch.principal;

import com.Eleonor.ScreenMatch.models.episodios.DatosEpisodio;
import com.Eleonor.ScreenMatch.models.episodios.Episodio;
import com.Eleonor.ScreenMatch.models.serie.DatosSerie;
import com.Eleonor.ScreenMatch.models.serie.ISerieRepository;
import com.Eleonor.ScreenMatch.models.serie.Serie;
import com.Eleonor.ScreenMatch.models.temporadas.DatosTemporada;
import com.Eleonor.ScreenMatch.service.ConsumoAPI;
import com.Eleonor.ScreenMatch.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner scanner = new Scanner(System.in);
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=a59e1ae9";
    private ISerieRepository repository;
    private Optional<Serie> serie;

    public Principal(ISerieRepository repository) {
        this.repository = repository;
    }

    public void showMenu(){
        var selectedNumber = -1;
        while (selectedNumber != 0){
            System.out.println("""
                Please select any option
                
                1.- Buscar Serie
                0.- Salir
                """);

            selectedNumber = scanner.nextInt();
            scanner.nextLine();

            switch (selectedNumber){
                case 1:
                    buscarSerie();
                    break;
                case 0:
                    System.out.println("Closing app");
                    break;
                default:
                    System.out.println("Invalid code");
            }
            System.out.println(selectedNumber);
        }

    }






    public Optional<DatosSerie> datosSerie(String movieTitle){

        movieTitle = movieTitle.replace(" ","+");
        var serieJson = consumoAPI.obtenerDatos(URL_BASE + movieTitle + API_KEY);
        DatosSerie datosSerie = conversor.obtenerDatos(serieJson, DatosSerie.class);

        if (datosSerie.titulo() == null || datosSerie.titulo().isBlank()) {
            return Optional.empty();
        }
        return Optional.of(datosSerie);
    }

    public void buscarSerie(){
        List<DatosTemporada> temporadas = new ArrayList<>();
        System.out.println("Ingrese el titulo de su serie");
        var movieTitle = scanner.nextLine();
        Optional<DatosSerie> datosSerie = datosSerie(movieTitle);

        datosSerie.ifPresentOrElse(mySerie ->{
            Serie newSerie = new Serie(mySerie);
            for (int i = 1; i <= mySerie.totalTemporadas() ; i++) {
                var temporadaJson = consumoAPI.obtenerDatos(URL_BASE + movieTitle + "&season=" + i + API_KEY);
                DatosTemporada datosTemporada = conversor.obtenerDatos(temporadaJson, DatosTemporada.class);
                temporadas.add(datosTemporada);
            }
            List<Episodio> episodios = temporadas.stream()
                    .flatMap(dt->dt.episodios().stream()
                            .map(e->new Episodio(dt.numero(),e)))
                    .collect(Collectors.toList());

            newSerie.setEpisodios(episodios);
            repository.save(newSerie);
            System.out.println(newSerie);
        },()->{
            System.out.println("No se encontro la serie: " + movieTitle);
        });



    }




}
