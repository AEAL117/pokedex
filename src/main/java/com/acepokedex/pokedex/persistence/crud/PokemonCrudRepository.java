package com.acepokedex.pokedex.persistence.crud;

import com.acepokedex.pokedex.persistence.entity.EntityPokemon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PokemonCrudRepository extends CrudRepository<EntityPokemon,Integer> {
Optional<List<EntityPokemon>> findPokemonById(int id);
Optional<List<EntityPokemon>> findPokemonByName(String name);
}
