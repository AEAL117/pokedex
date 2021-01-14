package com.acepokedex.pokedex.domain.service;

import com.acepokedex.pokedex.domain.Pokemon;
import com.acepokedex.pokedex.domain.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {
    @Autowired
    private PokemonRepository pokemonRepository;
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
    public boolean delete(int id){
        return getPokemonById(id).map(pokemon -> {
            pokemonRepository.delete(id);
            return true;
        }).orElse(false);
    }



}
