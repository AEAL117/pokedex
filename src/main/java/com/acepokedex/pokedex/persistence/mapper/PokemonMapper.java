package com.acepokedex.pokedex.persistence.mapper;

import com.acepokedex.pokedex.domain.Pokemon;
import com.acepokedex.pokedex.persistence.entity.EntityPokemon;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface PokemonMapper {
    @Mappings({
            @Mapping(source = "idPokemon", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "type1", target = "type1"),
            @Mapping(source = "type2", target = "type2"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "weight", target = "weight"),
            @Mapping(source = "height", target = "height"),
            @Mapping(source = "megaEvolves", target = "megaEvolves"),
            @Mapping(source = "evolves", target = "evolves"),
    })
    Pokemon toPokemon(EntityPokemon entityPokemon);
    List<Pokemon> toPokemons(List<EntityPokemon> entityPokemons);

    @InheritConfiguration
    EntityPokemon toEntityPokemon(Pokemon pokemon);
}
