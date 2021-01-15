package com.acepokedex.pokedex.persistence;

import com.acepokedex.pokedex.domain.Pokemon;
import com.acepokedex.pokedex.domain.repository.PokemonRepository;
import com.acepokedex.pokedex.persistence.crud.PokemonCrudRepository;
import com.acepokedex.pokedex.persistence.entity.EntityPokemon;
import com.acepokedex.pokedex.persistence.mapper.PokemonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
                        .toPokemon((EntityPokemon) entityPokemons));
    }

    @Override
    public Optional<Pokemon> getPokemonById(int idPokemon) {
        return pokemonCrudRepository
                .findPokemonByidPokemon(idPokemon).map(pokemon -> pokemonMapper.toPokemon((EntityPokemon) pokemon));
    }

    @Override
    public Pokemon save(Pokemon pokemon) {
        EntityPokemon entityPokemon = pokemonMapper.toEntityPokemon(pokemon);
        return pokemonMapper.toPokemon(pokemonCrudRepository.save(entityPokemon));
    }

    @Override
    public void delete(int idPokemon) {
        pokemonCrudRepository.deleteById(idPokemon);
    }
}
