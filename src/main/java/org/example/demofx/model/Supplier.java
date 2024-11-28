package org.example.demofx.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Supplier {
    private int id;
    private String type;
    private String name;
    private String director;
    private String phone;
    private int rating;

    public Supplier(String type, String name, String director, String phone, int rating) {
        this.type = type;
        this.name = name;
        this.director = director;
        this.phone = phone;
        this.rating = rating;
    }
}
