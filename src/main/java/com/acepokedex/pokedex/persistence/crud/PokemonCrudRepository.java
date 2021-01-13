package com.acepokedex.pokedex.persistence.crud;

import com.acepokedex.pokedex.persistence.entity.Pokemon;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PokemonCrudRepository extends CrudRepository<Pokemon,Integer> {

}
