package mx.edu.utez.examen.controller;

import mx.edu.utez.examen.config.ApiResponse;
import mx.edu.utez.examen.controller.dto.BookDto;
import mx.edu.utez.examen.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
@CrossOrigin(origins = {"*"})
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return service.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> register(@RequestBody BookDto dto){
        return service.save(dto.toEntity());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody BookDto dto){
        return service.update(id, dto.toEntity());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        return service.delete(id);
    }




}
