
package com.acepokedex.pokedex.domain.service;

public class Moves {

    private int id;
    private String  name;
    private String type;
    private String power;
    private int accurracy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public int getAccurracy() {
        return accurracy;
    }

    public void setAccurracy(int accurracy) {
        this.accurracy = accurracy;
    }
}


