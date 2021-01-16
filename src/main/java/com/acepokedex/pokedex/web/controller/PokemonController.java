package com.acepokedex.pokedex.web.controller;

import com.acepokedex.pokedex.domain.Pokemon;
import com.acepokedex.pokedex.domain.service.PokemonService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @GetMapping("/all")
    @ApiOperation("Get all pokemons")
    @ApiResponse(code = 200,message = "OK")
    public ResponseEntity<List<Pokemon>> getAll(){
        return new ResponseEntity<>(pokemonService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/SearchById/{id}")
    @ApiOperation("Search pokemon by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND ")
    })
    public ResponseEntity<Pokemon>getPokemonById(@ApiParam(value = "The id of the pokemon",required = true,example = "7") @Param("id") int id)
    {
        return  pokemonService.getPokemonById(id)
                .map(pokemon -> new ResponseEntity<>(pokemon,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/SearchByName/{name}")
    @ApiOperation("Search pokemon by  pokemon name ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND ")
    })
    public ResponseEntity<Pokemon>getPokemonByName(@ApiParam(value = "The name of the pokemon",required = true,example = "Machop")@Param("name") String name){
        return pokemonService.getPokemonByName(name)
                .map((pokemon -> new ResponseEntity<>(pokemon,HttpStatus.OK)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping("/listado")
    @ApiOperation("Return a list of the pokemons")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND ")
    })
    public String listado(@ApiParam(value = "The model of the pokemon",required = false) Model model){
        List<Pokemon> pokemons= pokemonService.getAll();
        model.addAttribute("pokemons", pokemons);
        model.addAttribute("pokemon",new Pokemon());
        model.addAttribute("poke",new Pokemon());

        return "listado";
    }

    @GetMapping("/save")
    @ApiOperation("Search pokemon by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND ")
    })
    public String save( @ApiParam(value = "The pokemon will save in the bd ") Pokemon pokemon){
        pokemonService.save(pokemon);
        System.out.println(pokemon.getName());
        return "redirect:/pokemon/listado";
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
