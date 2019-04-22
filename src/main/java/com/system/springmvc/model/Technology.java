package com.system.springmvc.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "technologies")
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Getter
    @Setter
    private String name;

    public Technology() {
    }

    public Technology(String name) {
        this.name = name;
    }

}
