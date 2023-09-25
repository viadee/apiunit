package de.viadee.apiunit.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelves/{shelfId}/dvd")
class DvdController{

    @RequestMapping(method = RequestMethod.GET)
    public List<Dvd> getAllDvds(@PathVariable Long shelfId) {
        return null;
    }

    @GetMapping("/{dvdId}")
    public Dvd getDvd(@PathVariable Long shelfId, @PathVariable Long dvdId) {
        return null;
    }

    @PatchMapping("/{dvdId}")
    public Dvd patchDvd(@PathVariable Long shelfId, @PathVariable Long dvdId, @RequestParam String title, @RequestParam String director) {
        return null;
    }

    @PutMapping("/{dvdId}")
    public Dvd putDvd(@PathVariable Long shelfId, @PathVariable Long dvdId, @RequestParam String title, @RequestParam String director) {
        return null;
    }

    @PostMapping
    public Dvd postDvd(@RequestParam String title, @RequestParam String director, @RequestParam Long shelfId) {
        return null;
    }

    @DeleteMapping("/{dvdId}")
    public Dvd deleteDvd(@PathVariable Long shelfId, @PathVariable Long dvdId) {
        return null;
    }
}