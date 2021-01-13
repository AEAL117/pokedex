package com.acepokedex.pokedex.domain.repository;

import com.acepokedex.pokedex.domain.Pokemon;

import java.util.List;
import java.util.Optional;

public interface PokemonRepository {
    public List<Pokemon> getAll();
    Optional<List<Pokemon>> getById(int idPokemon);
    Optional<Pokemon>getPokemon(String name);
    Pokemon save(Pokemon pokemon);
    void delete(int idPokemon);
}
