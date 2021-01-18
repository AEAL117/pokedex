package com.acepokedex.pokedex.domain.service;

import com.acepokedex.pokedex.domain.Pokemon;
import com.acepokedex.pokedex.domain.repository.PokemonRepository;
import com.acepokedex.pokedex.persistence.PokemonRepositoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Title Pokemon Service
 * this Class use PokemonRepository and its methods for create one service
 * Example getAll,getPokemonById.etc.
 * For indacate what is a service you will use @Service
 * use @Autowired to make the process automatic
 */
@Service
public class PokemonService {
    @Autowired
    private PokemonRepository pokemonRepository;
    @Autowired
    private PokemonRepositoryPersistence pokemonRepositoryPersistence;

    public List<Pokemon> getAll(){
        return pokemonRepository.getAll();
    }
    public Optional<Pokemon> getPokemonById(int id)
    {
        return pokemonRepository.getPokemonById(id);
    }
    public Optional<Pokemon>getPokemonByName(String name){
        return pokemonRepository.getPokemonByName(name);
    }
    public Pokemon save(Pokemon pokemon){
        return pokemonRepository.save(pokemon);
    }
    public Pokemon update(Pokemon pokemon){
        System.out.println("update de pokemon repository");
        System.out.println(pokemon.getId());
        return pokemonRepository.update(pokemon);
    }

    public boolean delete(int id){
        return getPokemonById(id).map(pokemon -> {
            System.out.println("yes");
            pokemonRepository.delete(id);
            return true;
        }).orElse(false);
    }




}
