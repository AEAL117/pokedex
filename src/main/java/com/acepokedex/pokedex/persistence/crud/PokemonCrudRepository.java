package com.acepokedex.pokedex.persistence.crud;

import com.acepokedex.pokedex.persistence.entity.EntityPokemon;
import org.springframework.data.repository.CrudRepository;

public interface PokemonCrudRepository extends CrudRepository<EntityPokemon,Integer> {

}
