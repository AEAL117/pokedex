package com.acepokedex.pokedex.web.controller;

import com.acepokedex.pokedex.domain.Pokemon;
import com.acepokedex.pokedex.domain.service.PokemonService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {

@Autowired
    private PokemonService pokemonService;

    @RequestMapping("/all")
    public ResponseEntity<List<Pokemon>> getAll(){
        return new ResponseEntity<>(pokemonService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/SearchById/{id}")
    public ResponseEntity<Pokemon>getPokemonById(@Param("id") int id)
    {
        return  pokemonService.getPokemonById(id)
                .map(pokemon -> new ResponseEntity<>(pokemon,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @RequestMapping("/listado")
    public String listado(Model model){
        List<Pokemon> pokemons= pokemonService.getAll();
        model.addAttribute("pokemons", pokemons);
        model.addAttribute("pokemon",new Pokemon());
        model.addAttribute("poke",new Pokemon());

        return "listado";
    }

    @GetMapping("/save")
    public String save(Pokemon pokemon){
        pokemonService.save(pokemon);
        System.out.println(pokemon.getName());
        return "redirect:/pokemon/listado";
    }
    @GetMapping("/SearchByName/{name}")
    public ResponseEntity<Pokemon>getPokemonByName(@Param("name") String name){
        return pokemonService.getPokemonByName(name)
                .map((pokemon -> new ResponseEntity<>(pokemon,HttpStatus.OK)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Checar response entity del false
    @GetMapping("/delete")
    public String delete(Pokemon pokemon){

        if(pokemonService.delete(pokemon.getId())){
            System.out.println("exito");
            return  "redirect:/pokemon/listado";
        }else{
            System.out.println("fracaso");
            return "redirect:/pokemon/listado";
        }

    }


}
