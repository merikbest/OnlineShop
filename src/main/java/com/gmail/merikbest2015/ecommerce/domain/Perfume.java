package com.gmail.merikbest2015.ecommerce.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "perfumes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Perfume {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "perfume_title")
    private String perfumeTitle;

    @Column(name = "perfumer")
    private String perfumer;

    @Column(name = "year")
    private Integer year;

    @Column(name = "country")
    private String country;

    @Column(name = "perfume_gender")
    private String perfumeGender;

    @Column(name = "fragrance_top_notes")
    private String fragranceTopNotes;

    @Column(name = "fragrance_middle_notes")
    private String fragranceMiddleNotes;

    @Column(name = "fragrance_base_notes")
    private String fragranceBaseNotes;

    @Column(name = "description")
    private String description;

    @Column(name = "filename")
    private String filename;

    @Column(name = "price")
    private Integer price;

    @Column(name = "volume")
    private String volume;

    @Column(name = "type")
    private String type;
}
