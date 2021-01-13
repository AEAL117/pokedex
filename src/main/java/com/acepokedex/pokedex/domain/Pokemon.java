package com.acepokedex.pokedex.domain;

public class Pokemon {
    private int id;
    private String name;
    private String type;
    //private String Type2;
    private String description;
    private double weitg;
    private double heigth;
    private String evolves;
    private String megaEnvolves;

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

   /* public String getType2() {
        return Type2;
    }

    public void setType2(String type2) {
        Type2 = type2;
    }*/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeitg() {
        return weitg;
    }

    public void setWeitg(double weitg) {
        this.weitg = weitg;
    }

    public double getHeigth() {
        return heigth;
    }

    public void setHeigth(double heigth) {
        this.heigth = heigth;
    }

    public String getEvolves() {
        return evolves;
    }

    public void setEvolves(String evolves) {
        this.evolves = evolves;
    }

    public String getMegaEnvolves() {
        return megaEnvolves;
    }

    public void setMegaEnvolves(String megaEnvolves) {
        this.megaEnvolves = megaEnvolves;
    }
}
