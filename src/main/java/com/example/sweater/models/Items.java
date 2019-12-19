package com.example.sweater.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Items {
    public Items() {
    }

    public Items(Integer id, String name, Integer cost, String desk) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.desk = desk;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private Integer cost;
    private String desk;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getDesk() {
        return desk;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }

    @Override
    public String toString()
    {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost='" + cost + '\'' +
                ", desk='" + desk + '\'' +
                '}';
    }
}
