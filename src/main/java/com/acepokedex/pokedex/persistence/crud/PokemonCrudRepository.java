package com.acepokedex.pokedex.persistence.crud;

import com.acepokedex.pokedex.persistence.entity.EntityPokemon;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Title CrudRepository
 * This interface  search pokemons in the base date with id or pokemon name
 * this interface extends of the CrudRepository
 */

public interface PokemonCrudRepository extends CrudRepository<EntityPokemon,Integer> {
Optional<EntityPokemon> findPokemonByIdPokemon(int idPokemon);
Optional<EntityPokemon> findPokemonByName(String name);
}
