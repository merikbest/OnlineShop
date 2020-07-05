package com.gmail.merikbest2015.ecommerce.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
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

    public Order() {
    }

    public Order (User user) {
        this.date = LocalDate.now();
        this.user = user;
        this.perfumeList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(Integer postIndex) {
        this.postIndex = postIndex;
    }

    public List<Perfume> getPerfumeList() {
        return perfumeList;
    }

    public void setPerfumeList(List<Perfume> perfumeList) {
        this.perfumeList = perfumeList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(totalPrice, order.totalPrice) &&
                Objects.equals(date, order.date) &&
                Objects.equals(firstName, order.firstName) &&
                Objects.equals(lastName, order.lastName) &&
                Objects.equals(city, order.city) &&
                Objects.equals(address, order.address) &&
                Objects.equals(email, order.email) &&
                Objects.equals(phoneNumber, order.phoneNumber) &&
                Objects.equals(postIndex, order.postIndex) &&
                Objects.equals(perfumeList, order.perfumeList) &&
                Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, date, firstName, lastName, city, address, email, phoneNumber, postIndex, perfumeList, user);
    }
}
