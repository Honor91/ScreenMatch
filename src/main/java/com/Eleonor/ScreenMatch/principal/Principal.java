package com.Eleonor.ScreenMatch.principal;

import com.Eleonor.ScreenMatch.models.episodios.DatosEpisodio;
import com.Eleonor.ScreenMatch.models.episodios.Episodio;
import com.Eleonor.ScreenMatch.models.serie.Categoria;
import com.Eleonor.ScreenMatch.models.serie.DatosSerie;
import com.Eleonor.ScreenMatch.models.serie.ISerieRepository;
import com.Eleonor.ScreenMatch.models.serie.Serie;
import com.Eleonor.ScreenMatch.models.temporadas.DatosTemporada;
import com.Eleonor.ScreenMatch.service.ConsumoAPI;
import com.Eleonor.ScreenMatch.service.ConvierteDatos;
import org.springframework.data.domain.PageRequest;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner scanner = new Scanner(System.in);
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=a59e1ae9";
    private ISerieRepository repository;
    private List<Serie> series;
    private Optional<Serie> serieBuscada;
    private String myTitle;
    private List<Episodio> episodios;
    private String movieTitle;


    public Principal(ISerieRepository repository) {
        this.repository = repository;
    }

    public void showMenu(){
        var selectedNumber = -1;
        while (selectedNumber != 0){
            System.out.println("""
                Please select any option
                
                1.- Buscar Serie
                2.- Listar Series de mi Base de datos
                3.- Buscar Series por nombre en BD
                4.- Top 5 based on evaluations
                5.- Buscar Series por categorias
                6.- filtrar series por maximo de temporadas y minima evealuacion.
                7.- Buscar Episodios por Serie
                8.- Buscar top 5 episodios
                9.- search2
                0.- Salir
                """);

            selectedNumber = scanner.nextInt();
            scanner.nextLine();

            switch (selectedNumber){
                case 1:
                    buscarSerie();
                    break;
                case 2:
                    listarSeriesEnBaseDeDatos();
                    break;
                case 3:
                    buscarSeriesEnBaseDeDatos();
                    break;
                case 4:
                    buscarTop5Series();
                    break;
                case 5:
                    buscarSeriesPorCategoria();
                    break;
                case 6:
                    filtrarSeriesPorMaximoTemporadasMinimoEvaluacion();
                    break;
                case 7:
                    buscarEpisodiosEnSerie();
                    break;
                case 8:
                    buscarTop5Episodios();
                    break;
                case 9:
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

    private void buscarTop5Episodios() {
        System.out.println("En que Serie quiere buscar los mejores episodios?");
        myTitle = scanner.nextLine();
        myTitle = myTitle.replace(" ","+");
        Optional<DatosSerie> datosSerie = datosSerie(myTitle);
        datosSerie.ifPresentOrElse( ds ->{
            Serie mySerie = new Serie(ds);
            episodios = repository.top5Episodios(mySerie.getTitulo(), PageRequest.of(0,5));
            episodios.forEach( e-> System.out.printf("Evaluacion: %s - Temporada: %s - Capitulo: %s - Titulo: %s \n",e.getEvaluacion(),e.getTemporada(),e.getNumeroEpisodio(),e.getTitulo()));
        },()->{
            System.out.println("La serie: " + myTitle + " No ha sido encontrada");
        });
    }

    private void buscarEpisodiosEnSerie() {
        System.out.println("Como se llama su episodio");
        var episodeTitle = scanner.nextLine();

        episodios = repository.episodiosPorNombre(episodeTitle);
        episodios.forEach( e -> System.out.printf("Serie: %s - Temporada: %s - Capitulo: %s - Titulo: %s \n",e.getSerie().getTitulo(),e.getTemporada(),e.getNumeroEpisodio(),e.getTitulo()));
    }
    private Optional<Serie> searchSerie(String text){
        text = text.replace(" ","+");
        try {
            var serieJson = consumoAPI.obtenerDatos(URL_BASE + text + API_KEY);
            DatosSerie datosSerie = conversor.obtenerDatos(serieJson, DatosSerie.class);
            if (datosSerie == null || "False".equalsIgnoreCase(datosSerie.response())){
                System.out.println("Lo sentimos la serie buscada no se encuentra disponible");
                if (datosSerie != null && datosSerie.error() != null){
                    System.out.println(" Detalle: " + datosSerie.error());
                }
                return Optional.empty();
            }
            return Optional.of(new Serie(datosSerie));
        } catch (Exception e){
            System.out.println("Error al consultar la API" + e.getMessage());
            return Optional.empty();
        }
    }

    private void filtrarSeriesPorMaximoTemporadasMinimoEvaluacion() {
        System.out.println("Seleccione el maximo numero de temporadas que desea ver");
        var temporadas = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Seleccione la minima evaluacion que desea ver");
        var minimaEvaluacion = scanner.nextDouble();
        scanner.nextLine();

        series = repository.seriesPorTemporadaYEvaluacion(temporadas,minimaEvaluacion);
        series.forEach( s -> System.out.println("Titulo: " + s.getTitulo() + ", Evaluacion:" + s.getEvaluacion()));

    }

    private void buscarSeriesPorCategoria() {
        System.out.println("Escriba una de las siguientes categorias");
        System.out.println(Arrays.toString(Categoria.values()));
        var myCategory = scanner.nextLine();
        myCategory = myCategory.toUpperCase();
        var cat = Categoria.fromName(myCategory);
        series = repository.findByGenero(cat);
        if ( series.isEmpty()){
            System.out.println("Lo sentimos no tenemos series en esa categoria");
        } else {
            System.out.println("Encontramos las siguientes series: ");
            series.forEach( s -> System.out.println(s.getTitulo()));
        }

    }

    private void buscarTop5Series() {
        series = repository.findTop5ByOrderByEvaluacionDesc();
        series.forEach( s -> System.out.println("Serie: " + s.getTitulo() + ", Evaluacion: " + s.getEvaluacion()));

    }

    private void buscarSeriesEnBaseDeDatos() {
        System.out.println("Ingrese el nombre de la serie a buscar en la base de datos");
        var myfavoriteSerie = scanner.nextLine();
        myfavoriteSerie = myfavoriteSerie.replace(" ","+");
        Optional<Serie> serie = repository.findByTituloContainsIgnoreCase(myfavoriteSerie);
        if (serie.isPresent()){
            System.out.println(serie);
        } else {
            System.out.println("La serie" + myfavoriteSerie + " no se encuentra en la base de datos");
        }
    }

    private void listarSeriesEnBaseDeDatos() {
        series = repository.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    public Optional<DatosSerie> datosSerie(String movieTitle){

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
        movieTitle = scanner.nextLine();
        movieTitle = movieTitle.replace(" ","+");
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

            serieBuscada = repository.findByTituloContainsIgnoreCase(movieTitle);
            if (serieBuscada.isPresent()){
                System.out.println("La serie ya se encuentra en la base de datos");
            } else {
                newSerie.setEpisodios(episodios);
                repository.save(newSerie);
                System.out.println("Serie: "+ movieTitle.replace("+"," ") + " fue agregada a la base de forma satisfactoria");
                System.out.println(newSerie);
            }

        },()->{
            System.out.println("No se encontro la serie: " + movieTitle);
        });

    }



}
