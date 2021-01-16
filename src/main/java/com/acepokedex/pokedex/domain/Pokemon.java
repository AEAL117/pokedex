package com.acepokedex.pokedex.domain;

/**
 * @Title Create Class of the Pokemon
 * Here have all attributes and methods of the pokemon
 */
public class Pokemon {
    private int id;
    private String type1;
    private String type2;
    private String name;
    private String description;
    private double weight;
    private int height;
    private boolean megaEvolves;
    private int evolves;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isMegaEvolves() {
        return megaEvolves;
    }

    public void setMegaEvolves(boolean megaEvolves) {
        this.megaEvolves = megaEvolves;
    }

    public int getEvolves() {
        return evolves;
    }

    public void setEvolves(int evolves) {
        this.evolves = evolves;
    }
}
