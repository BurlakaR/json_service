package com.jsonservice.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "message")
public class JMessage implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Getter(AccessLevel.PUBLIC) @Setter (AccessLevel.PUBLIC) private int id;

    @Column(name = "text")
    @Getter(AccessLevel.PUBLIC) @Setter (AccessLevel.PUBLIC)private String text;


    public JMessage(String text) {
        this.text = text;
    }



    @Override
    public String toString() {
        return "JMessage{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
