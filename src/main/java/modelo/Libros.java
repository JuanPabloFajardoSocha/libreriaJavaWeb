package modelo;

import java.io.Serializable;

public class Libros implements Serializable {

    private String isbn;
    private String titulo;
    private String descripcion;
    private String nombreAutor;
    private String publicacion;
    private String fechaRegistro;
    private int codigoCategoria;
    private String nitEditorial;
    private String codigoCategoriaText;

    public Libros() {
        this.isbn = "";
        this.titulo = "";
        this.descripcion = "";
        this.nombreAutor = "";
        this.publicacion = "";
        this.fechaRegistro = "";
        this.codigoCategoria = 0;
        this.nitEditorial = "";
        this.codigoCategoriaText="";
    }

    public Libros(String isbn, String titulo, String descripcion, String nombreAutor, String publicacion, String fechaRegistro, int codigoCategoria, String nitEditorial) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.nombreAutor = nombreAutor;
        this.publicacion = publicacion;
        this.fechaRegistro = fechaRegistro;
        this.codigoCategoria = codigoCategoria;
        this.nitEditorial = nitEditorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(int codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public String getNitEditorial() {
        return nitEditorial;
    }

    public void setNitEditorial(String nitEditorial) {
        this.nitEditorial = nitEditorial;
    }

    public String getCodigoCategoriaText() {
        return codigoCategoriaText;
    }

    public void setCodigoCategoriaText(String codigoCategoriaText) {
        this.codigoCategoriaText = codigoCategoriaText;
    }  
    

}
