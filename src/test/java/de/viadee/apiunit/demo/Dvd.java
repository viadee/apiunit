package de.viadee.apiunit.demo;

import java.util.Objects;

public class Dvd {

    private Long dvdId;
    private String title;
    private String director;
    private Shelf shelf;

    public Long getDvdId() {
        return dvdId;
    }

    public void setDvdId(Long dvdId) {
        this.dvdId = dvdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Dvd{" + "id=" + this.getDvdId() + "; title= " + this.getTitle() + "; director= " + this.getDirector() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Dvd))
            return false;
        Dvd dvd = (Dvd) o;
        return Objects.equals(this.dvdId, dvd.getDvdId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(dvdId);
    }
}
