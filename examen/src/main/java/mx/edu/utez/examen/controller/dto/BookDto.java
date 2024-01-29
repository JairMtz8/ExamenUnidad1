package mx.edu.utez.examen.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.examen.models.Book;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {

    Long id;
    String title;
    String isnb;
    String author;
    String pages;
    String category;
    String date;
    String folio;

    public Book toEntity(){
        return new Book(title, isnb, author, pages, category, date, folio);
    }

}
