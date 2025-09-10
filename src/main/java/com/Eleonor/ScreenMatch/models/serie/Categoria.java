package com.Eleonor.ScreenMatch.models.serie;

public enum Categoria {

    ACCION("Action", "Acción"),
    ROMANCE("Romance","Romance"),
    COMEDIA("Comedy","Comedia"),
    DRAMA("Drama","Drama"),
    CRIMEN("Crime","Crimen"),
    HISTORY("History","Historia"),
    AVENTURA("adventure","Aventura"),
    ANIMACION("Animation","Animación"),
    DOCUMENTAL("Documentary","Documental"),
    FANTASIA("Fantasy","Fantasia");
    private String categoriaOmdb;
    private String categoriaEspanol;

    Categoria (String categoriaOmdb, String categoriaEspanol){
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaEspanol = categoriaEspanol;
    }

    public static Categoria fromString(String text){
        for (Categoria categoria : Categoria.values()){
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)){
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }
    public static Categoria fromEspanol(String text){
        for (Categoria categoria : Categoria.values()){
            if (categoria.categoriaEspanol.equalsIgnoreCase(text)){
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }

    public static Categoria fromName(String text){
        try {
            return Categoria.valueOf(text.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
        }
    }
}
