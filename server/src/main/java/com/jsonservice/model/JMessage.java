package com.jsonservice.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "message")
public class JMessage implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "text")
    private String text;

    public JMessage() {
    }

    public JMessage(String text) {
        this.text = text;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "JMessage{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
