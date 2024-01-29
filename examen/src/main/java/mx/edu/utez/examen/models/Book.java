package mx.edu.utez.examen.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "book")
@NoArgsConstructor
@Setter
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 40, nullable = false)
    private String title;
    @Column(length = 40, nullable = false)
    private String isnb;
    @Column(length = 40, nullable = false)
    private String author;
    @Column(length = 40, nullable = false)
    private String pages;
    @Column(length = 40, nullable = false)
    private String category;
    @Column(columnDefinition = "DATE", length = 40, nullable = false)
    private String date;
    @Column(length = 30, nullable = false)
    private String folio;

    public Book(String title, String isnb, String author, String pages, String category, String date, String folio) {
        this.title = title;
        this.isnb = isnb;
        this.author = author;
        this.pages = pages;
        this.category = category;
        this.date = date;
        this.folio = folio;
    }

    /*
     * El folio del libro se debe componer de la primera letra del título, la primera letra del nombre del autor,
     * las primeras dos letras del apellido del autor, la fecha de publicación (yyyy-MM-dd)
     * las primeras 4 letras del ISBN y 2 dígitos random (letras y números).
     * */

    public String generarFolio(String title, String isnb, String author, String date) {

        String letraTitulo = title.substring(0, 1);
        String[] nombreAutor = author.split(" ");
        String letraNombreAutor = nombreAutor[0].substring(0, 1);
        String letraApellidoAutor = nombreAutor[1].substring(0, 2);
        String[] fechaPubli = date.split("-");
        String anio = fechaPubli[0].substring(0);
        String mes = fechaPubli[1].substring(0);
        String dia = fechaPubli[2].substring(0);
        String letrasISBN = isnb.substring(0, 4);

        String letraAleatoria1 = generarLetras();
        String letraAleatoria2 = generarLetras();

        String folio = letraTitulo + letraNombreAutor + letraApellidoAutor + anio + mes + dia + letrasISBN
                + letraAleatoria1 + letraAleatoria2;

        folio = folio.toUpperCase();

        return folio;
    }

    public static String generarLetras() {
        Random random = new Random();
        char letra = (char) ('A' + random.nextInt(26));
        return String.valueOf(letra);
    }

}
