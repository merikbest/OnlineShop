package com.gmail.merikbest2015.ecommerce.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Perfume {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Пожалуйсто заполните поле")
    @Length(max = 255)
    private String perfumeTitle;

    @NotBlank(message = "Пожалуйсто заполните поле")
    @Length(max = 255)
    private String perfumer;

    @NotNull(message = "Пожалуйсто заполните поле")
    private Integer year;

    @NotBlank(message = "Пожалуйсто заполните поле")
    @Length(max = 255)
    private String country;

    @NotBlank(message = "Пожалуйсто заполните поле")
    @Length(max = 255)
    private String perfumeGender;

    @NotBlank(message = "Пожалуйсто заполните поле")
    @Length(max = 255)
    private String fragranceTopNotes;

    @NotBlank(message = "Пожалуйсто заполните поле")
    @Length(max = 255)
    private String fragranceMiddleNotes;

    @NotBlank(message = "Пожалуйсто заполните поле")
    @Length(max = 255)
    private String fragranceBaseNotes;

    private String description;

    private String filename;

    @NotNull(message = "Пожалуйсто заполните поле")
    private Integer price;

    @NotBlank(message = "Пожалуйсто заполните поле")
    @Length(max = 255)
    private String volume;

    @NotBlank(message = "Пожалуйсто заполните поле")
    @Length(max = 255)
    private String type;

    public Perfume() {
    }

    public Perfume(String perfumeTitle, String perfumer, Integer year, String country, String perfumeGender,
                   String fragranceTopNotes, String fragranceMiddleNotes, String fragranceBaseNotes, String description,
                   Integer price, String volume, String type
    ) {
        this.perfumeTitle = perfumeTitle;
        this.perfumer = perfumer;
        this.year = year;
        this.country = country;
        this.perfumeGender = perfumeGender;
        this.fragranceTopNotes = fragranceTopNotes;
        this.fragranceMiddleNotes = fragranceMiddleNotes;
        this.fragranceBaseNotes = fragranceBaseNotes;
        this.description = description;
        this.price = price;
        this.volume = volume;
        this.type = type;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPerfumeTitle() {
        return perfumeTitle;
    }

    public void setPerfumeTitle(String perfumeTitle) {
        this.perfumeTitle = perfumeTitle;
    }

    public String getPerfumer() {
        return perfumer;
    }

    public void setPerfumer(String perfumer) {
        this.perfumer = perfumer;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPerfumeGender() {
        return perfumeGender;
    }

    public void setPerfumeGender(String gender) {
        this.perfumeGender = gender;
    }

    public String getFragranceTopNotes() {
        return fragranceTopNotes;
    }

    public void setFragranceTopNotes(String fragranceTopNotes) {
        this.fragranceTopNotes = fragranceTopNotes;
    }

    public String getFragranceMiddleNotes() {
        return fragranceMiddleNotes;
    }

    public void setFragranceMiddleNotes(String fragranceMiddleNotes) {
        this.fragranceMiddleNotes = fragranceMiddleNotes;
    }

    public String getFragranceBaseNotes() {
        return fragranceBaseNotes;
    }

    public void setFragranceBaseNotes(String fragranceBaseNotes) {
        this.fragranceBaseNotes = fragranceBaseNotes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perfume perfume = (Perfume) o;
        return Objects.equals(id, perfume.id) &&
                Objects.equals(perfumeTitle, perfume.perfumeTitle) &&
                Objects.equals(perfumer, perfume.perfumer) &&
                Objects.equals(year, perfume.year) &&
                Objects.equals(country, perfume.country) &&
                Objects.equals(perfumeGender, perfume.perfumeGender) &&
                Objects.equals(fragranceTopNotes, perfume.fragranceTopNotes) &&
                Objects.equals(fragranceMiddleNotes, perfume.fragranceMiddleNotes) &&
                Objects.equals(fragranceBaseNotes, perfume.fragranceBaseNotes) &&
                Objects.equals(description, perfume.description) &&
                Objects.equals(filename, perfume.filename) &&
                Objects.equals(price, perfume.price) &&
                Objects.equals(volume, perfume.volume) &&
                Objects.equals(type, perfume.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, perfumeTitle, perfumer, year, country, perfumeGender, fragranceTopNotes, fragranceMiddleNotes, fragranceBaseNotes, description, filename, price, volume, type);
    }
}