package mx.edu.utez.examen.service;

import mx.edu.utez.examen.config.ApiResponse;
import mx.edu.utez.examen.models.Book;
import mx.edu.utez.examen.models.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll() {
        return new ResponseEntity<>(new ApiResponse(repository.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> save(Book book) {

        Book bookFolio = new Book();
        String folio = bookFolio.generarFolio(book.getTitle(), book.getIsnb(),
                book.getAuthor(), book.getDate());

        book.setFolio(folio);

        book = repository.saveAndFlush(book);
        return new ResponseEntity<>(new ApiResponse(book, HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> update(Long id, Book book) {
        Optional<Book> updateBook = repository.findById(id);

        if (updateBook.isPresent()) {
            Book upBook = updateBook.get();

            upBook.setTitle(book.getTitle());
            upBook.setAuthor(book.getAuthor());
            upBook.setPages(book.getPages());
            upBook.setCategory(book.getCategory());
            upBook.setDate(book.getDate());
            upBook.setIsnb(book.getIsnb());
            upBook.setFolio(book.generarFolio(book.getTitle(), book.getIsnb(),
                    book.getAuthor(), book.getDate()));
            repository.saveAndFlush(upBook);

            return new ResponseEntity<>(new ApiResponse(repository.findAll(), HttpStatus.OK), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true,
                    "Libro no encontrado"), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(Long id) {
        Optional<Book> book = repository.findById(id);

        if (book.isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(new ApiResponse(repository.findAll(), HttpStatus.OK), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true,
                    "Libro no encontrado"), HttpStatus.BAD_REQUEST);
        }
    }

}
