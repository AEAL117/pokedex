package com.acepokedex.pokedex.persistence;

import com.acepokedex.pokedex.domain.Pokemon;
import com.acepokedex.pokedex.domain.repository.PokemonRepository;
import com.acepokedex.pokedex.persistence.crud.PokemonCrudRepository;
import com.acepokedex.pokedex.persistence.entity.EntityPokemon;
import com.acepokedex.pokedex.persistence.mapper.PokemonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Title Repository of the Pokemon
 * the function of this class is use the reference of the class PokemonCrudRepository
 * and implements PokemonRepository
 */
@Repository
public class PokemonRepositoryPersistence implements PokemonRepository {
    @Autowired
    private PokemonCrudRepository pokemonCrudRepository;
    @Autowired
    private PokemonMapper pokemonMapper;

    @Override
    public List<Pokemon> getAll() {
        List<EntityPokemon> pokemons = (List<EntityPokemon>) pokemonCrudRepository.findAll();
        return pokemonMapper.toPokemons(pokemons);
    }

    @Override
    public Optional<Pokemon> getPokemonByName(String name) {

        return pokemonCrudRepository
                .findPokemonByName(name)
                .map(entityPokemons -> pokemonMapper
                        .toPokemon( entityPokemons));
    }

    @Override
    public Optional<Pokemon> getPokemonById(int idPokemon) {
        return pokemonCrudRepository
                .findPokemonByIdPokemon(idPokemon).map(pokemon -> pokemonMapper.toPokemon(pokemon));
    }

    @Override
    public Pokemon save(Pokemon pokemon) {
        EntityPokemon entityPokemon = pokemonMapper.toEntityPokemon(pokemon);
        return pokemonMapper.toPokemon(pokemonCrudRepository.save(entityPokemon));
    }

    @Override
    public Pokemon update(Pokemon pokemon) {
        EntityPokemon entityPokemon = pokemonCrudRepository.findPokemonByIdPokemon(pokemon.getId()).get();
        return pokemonMapper.toPokemon(pokemonCrudRepository.save(entityPokemon));
    }

    @Override
    public void delete(int idPokemon) {
        pokemonCrudRepository.deleteById(idPokemon);
    }


}

