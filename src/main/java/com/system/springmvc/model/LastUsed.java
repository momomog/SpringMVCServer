package com.system.springmvc.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "useds")
public class LastUsed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Getter
    @Setter
    private String name;

    public LastUsed() {
    }

    public LastUsed(String name) {
        this.name = name;
    }


}

