package com.jsonservice.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "message")
public class JMessage implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "text")
    private String text;


    public JMessage(String text) {
        this.text = text;
    }




}
