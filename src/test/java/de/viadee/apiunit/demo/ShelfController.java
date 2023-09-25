package de.viadee.apiunit.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/shelves")
class ShelfController {

    @GetMapping()
    List<Shelf> getAllShelves() {
        return null;
    }

    @GetMapping("/{shelfId}")
    Shelf getShelf(@PathVariable Long shelfId) {
        return null;
    }

    @PatchMapping("/{shelfId}")
    public Shelf patchShelf(Long shelfId, @RequestBody Set<Book> books) {
        return null;
    }

    @PutMapping("/{shelfId}")
    public Shelf putShelf(Long shelfId, @RequestBody Set<Book> books) {
        return null;
    }

    @PostMapping
    public Shelf postShelf(@RequestBody Shelf shelf) {
        return null;
    }

    @DeleteMapping("/{shelfId}")
    public Shelf deleteShelf(@RequestBody Shelf shelf) {
        return null;
    }
}