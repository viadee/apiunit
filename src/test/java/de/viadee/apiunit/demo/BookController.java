package de.viadee.apiunit.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelves/{shelfId}/book`s")
class BookController {

    @GetMapping()
    public List<Book> getAllBooks(@PathVariable Long shelfId, @RequestBody FilterObject filterObject) {
        return null;
    }

    @GetMapping("{bookId}")
    public Book getBook(@PathVariable Long shelfId, @PathVariable Long bookId) {
        return null;
    }

    @PatchMapping("/{bookId}")
    public Book patchBook(@PathVariable Long shelfId, @PathVariable Long bookId, @RequestBody(required = false) RequestBodyBook newBook) {
        return null;
    }

    @PutMapping("/{bookId}")
    public Book putBook(@PathVariable Long shelfId, @PathVariable Long bookId, @RequestBody(required = false) RequestBodyBook newBook) {
        return null;
    }

    @PostMapping
    public Book postBook(@RequestBody(required = false) RequestBodyBook newBook) {
        return null;
    }

    @DeleteMapping("/{bookId}")
    public Book deleteBook(@PathVariable Long shelfId, @PathVariable Long bookId, @RequestBody(required = false) RequestBodyBook newBook) {
        return null;
    }
}