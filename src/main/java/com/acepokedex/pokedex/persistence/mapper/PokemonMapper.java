package com.acepokedex.pokedex.persistence.mapper;

import com.acepokedex.pokedex.domain.Pokemon;
import com.acepokedex.pokedex.persistence.entity.EntityPokemon;
import org.mapstruct.*;

import java.util.List;

/**
 * @Title Make to Mapper
 * In this part was made the mapping the attributes of the system with the attributes of the databases
 *
 */
@Mapper(componentModel = "Spring")
public interface PokemonMapper {
    @Mappings({

            @Mapping(source = "name", target = "name"),
            @Mapping(source = "idPokemon", target = "id"),
            @Mapping(source = "type1", target = "type1"),
            @Mapping(source = "type2", target = "type2"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "weight", target = "weight"),
            @Mapping(source = "height", target = "height"),
            @Mapping(source = "megaEvolves", target = "megaEvolves"),
            @Mapping(source = "evolves", target = "evolves"),
    })
    Pokemon toPokemon(EntityPokemon entityPokemon);


    @InheritConfiguration
    @Mapping(source ="id",target = "idPokemon")
    EntityPokemon toEntityPokemon(Pokemon pokemon);

    List<Pokemon> toPokemons(List<EntityPokemon> entityPokemons);
    List<EntityPokemon> toEntityPokemons(List<Pokemon> pokemons);
}
