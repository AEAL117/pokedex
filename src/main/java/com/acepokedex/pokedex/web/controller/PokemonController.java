package com.acepokedex.pokedex.web.controller;

import com.acepokedex.pokedex.domain.Pokemon;
import com.acepokedex.pokedex.domain.service.PokemonService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @RequestMapping("/all")
    public ResponseEntity<List<Pokemon>> getAll() {
        return new ResponseEntity<>(pokemonService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/SearchById/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable("id") int id) {
        return pokemonService.getPokemonById(id)
                .map(pokemon -> new ResponseEntity<>(pokemon, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/SearchByN/")
    public ResponseEntity<Pokemon> getPokemonByName(@PathVariable("name") String name) {
        return pokemonService.getPokemonByName(name)
                .map((pokemon -> new ResponseEntity<>(pokemon, HttpStatus.OK)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

/*
    @RequestMapping("/greeting")
    public String greeting(Model model){
        model.addAttribute("pokemon",new Pokemon());
        return "greeting";
    }
    */

    @RequestMapping("/detailsByName")
    public String showPokemonByName(Model model, @Param("name") String name) {
        Optional<Pokemon> pokemons = pokemonService.getPokemonByName(name);
        if (pokemons.isPresent()) {
            model.addAttribute("pokemons", pokemons);
            return "/detailsByName";
        } else {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return "redirect:/pokemon/listado";
        }
    }

    @RequestMapping("/listado")
    public String listado(Model model) {
        List<Pokemon> pokemons = pokemonService.getAll();
        model.addAttribute("pokemons",pokemons);
        model.addAttribute("pokemon", new Pokemon());
        model.addAttribute("poke", new Pokemon());
        model.addAttribute("pok",new Pokemon());
        return "listado";
    }


    @GetMapping("/save")
    public String save(Pokemon pokemon){
        pokemonService.save(pokemon);
        System.out.println(pokemon.getName());
        return "redirect:/pokemon/listado";
    }

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

    //inicia la vista detailsbyid con los datos de un pokemon
    @GetMapping("/detailsbyname")
    public String pokemonDetails(Model model,Pokemon poke){
        Optional<Pokemon> pokemon = pokemonService.getPokemonByName(poke.getName());
        String ruta=pokemon.get().getName().toLowerCase();
        model.addAttribute("ruta",ruta);
        model.addAttribute("pokemon",pokemon.get());
        return "findbyname";
    }


    //manda los cambios a la BD
    @GetMapping("/update")
    public String update(Pokemon pokemon){
        System.out.println(pokemon.getName());
        pokemonService.update(pokemon);
        return "redirect:/pokemon/listado";
    }
    //inicia la vista cambios donde hay un formulario para modificar el pokemon
    @GetMapping("/cambios")
    public String initCambios(Model model,Pokemon pokemon){
        System.out.println(pokemon.getName());
        Optional<Pokemon> poke = pokemonService.getPokemonByName(pokemon.getName());

        model.addAttribute("pokemon",poke.get());
        return "cambio";
    }
}
