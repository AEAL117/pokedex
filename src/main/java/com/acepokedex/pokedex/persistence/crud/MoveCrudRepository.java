package com.acepokedex.pokedex.persistence.crud;

import com.acepokedex.pokedex.persistence.entity.Move;
import org.springframework.data.repository.CrudRepository;

public interface MoveCrudRepository extends CrudRepository<Move,Integer> {

}
