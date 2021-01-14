package com.acepokedex.pokedex.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name="pokemons")
public class EntityPokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idPokemon;

    @Column(name = "type_1")
    private String type1;

    @Column(name = "type_2")
    private String type2;
    private String name;
    private String description;
    private Double weight;
    private Double height;

    @Column(name = "mega_evolves")
    private Boolean megaEvolves;
    private int evolves;

    public int getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon) {
        this.idPokemon = idPokemon;
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Boolean getMegaEvolves() {
        return megaEvolves;
    }

    public void setMegaEvolves(Boolean megaEvolves) {
        this.megaEvolves = megaEvolves;
    }

    public int getEvolves() {
        return evolves;
    }

    public void setEvolves(int evolves) {
        this.evolves = evolves;
    }
}
