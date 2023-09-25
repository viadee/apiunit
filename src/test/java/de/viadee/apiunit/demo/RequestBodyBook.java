package de.viadee.apiunit.demo;

import java.util.Objects;

public class RequestBodyBook {

    private String author;

    private String title;

    private Genre genre;

    public RequestBodyBook(String author, String title, Genre genre) {
        this.author = author;
        this.title = title;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof RequestBodyBook))
            return false;
        RequestBodyBook book = (RequestBodyBook) o;
        return Objects.equals(this.author, book.getAuthor()) && Objects.equals(this.title, book.getTitle()) && Objects.equals(this.getGenre(), book.getGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title,author,genre);
    }

    @Override
    public String toString() {
        return "RequestBodyBook{title= " + this.getTitle() + "; author= " + this.getAuthor() + '}';
    }


}
