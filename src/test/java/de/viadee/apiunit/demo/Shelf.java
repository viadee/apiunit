package de.viadee.apiunit.demo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Shelf {

    private Long shelfId;
    private Set<Book> books = new HashSet<>();
    private Set<Dvd> dvds = new HashSet<>();

    public Shelf() {
    }

    public Shelf(Long shelfId, Set<Book> books, Set<Dvd> dvds) {
        this.shelfId = shelfId;
        this.books = books;
        this.dvds = dvds;
    }

    public Shelf(Long shelfId) {
        this.shelfId = shelfId;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Set<Dvd> getDvds() {
        return dvds;
    }

    public void setDvds(Set<Dvd> dvds) {
        this.dvds = dvds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Book))
            return false;
        Shelf shelf = (Shelf) o;
        return Objects.equals(this.shelfId, shelf.getShelfId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(shelfId);
    }

    @Override
    public String toString() {
        return "Shelf{" + "id=" + this.getShelfId() + "; books= " + this.getBooks() + "; dvds= " + this.getDvds() + '}';
    }

}
