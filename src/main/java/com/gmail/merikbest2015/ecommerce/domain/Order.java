package com.gmail.merikbest2015.ecommerce.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "user", "perfumeList"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalPrice;
    private LocalDate date;

    @NotBlank(message = "Пожалуйста заполните поле")
    private String firstName;

    @NotBlank(message = "Пожалуйста заполните поле")
    private String lastName;

    @NotBlank(message = "Пожалуйста заполните поле")
    private String city;

    @NotBlank(message = "Пожалуйста заполните поле")
    private String address;

    @Email(message = "Некорректный email")
    @NotBlank(message = "Email не может быть пустым")
    private String email;

    @NotBlank(message = "Номер телефона не может быть пустым")
    private String phoneNumber;

    @NotNull(message = "Почтовый индекс не может быть пустым")
    @Min(value = 5, message = "Почтовый индекс должен содержать 5 цифр")
    private Integer postIndex;

    @OrderColumn
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfume> perfumeList;

    @ManyToOne
    private User user;

    public Order (User user) {
        this.date = LocalDate.now();
        this.user = user;
        this.perfumeList = new ArrayList<>();
    }
}
