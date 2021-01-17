package com.acepokedex.pokedex.domain.repository;

import com.acepokedex.pokedex.domain.Pokemon;

import java.util.List;
import java.util.Optional;

/**
 * @Title Repository Pokemon
 * This interface return a list of Pokemon,
 * return One pokemon with the name or ID
 * save and delete pokemons
 */
public interface PokemonRepository {
    public List<Pokemon> getAll();
  //  Optional<List<Pokemon>> getById(int idPokemon);
    Optional<Pokemon>getPokemonByName(String name);
    Optional<Pokemon>getPokemonById(int id);
    Pokemon save(Pokemon pokemon);
    Pokemon update(Pokemon pokemon);
    void delete(int idPokemon);

}
