package com.acepokedex.pokedex.web.controller;

import com.acepokedex.pokedex.domain.Pokemon;
import com.acepokedex.pokedex.domain.service.PokemonService;
import io.swagger.annotations.*;
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
    @ApiOperation("Get all pokemons ")
    @ApiResponse(code = 200,message = "OK")

    public ResponseEntity<List<Pokemon>> getAll() {
        return new ResponseEntity<>(pokemonService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/SearchById/{id}")
    @ApiOperation("Search  pokemons by ID")
    @ApiResponses({
            @ApiResponse(code = 200,message = "Ok"),
            @ApiResponse(code = 404,message = "Pokemon Not Found")
    })
    public ResponseEntity<Pokemon> getPokemonById(@ApiParam(value = "Id of the pokemon",required = true,example = "2") @PathVariable("id") int id) {
        return pokemonService.getPokemonById(id)
                .map(pokemon -> new ResponseEntity<>(pokemon, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/SearchByN/")
    @ApiOperation("Search  pokemons by pokemon name ")
    @ApiResponses({
            @ApiResponse(code = 200,message = "Ok"),
            @ApiResponse(code = 404,message = "Pokemon Not Found")
    })
    public ResponseEntity<Pokemon> getPokemonByName(@ApiParam(value = "The name of the pokemon",required = true)@PathVariable("name") String name) {
        return pokemonService.getPokemonByName(name)
                .map((pokemon -> new ResponseEntity<>(pokemon, HttpStatus.OK)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping("/detailsByName")

    @ApiOperation("Get details of the  pokemons searching with the name ")
    @ApiResponses({
            @ApiResponse(code = 200,message = "Ok"),
            @ApiResponse(code = 404,message = "Pokemon Not Found")
    })
    public String showPokemonByName(Model model, @ApiParam(value = "The name of the pokemon", required = true) @Param("name") String name) {
        Optional<Pokemon> pokemons = pokemonService.getPokemonByName(name);
        if (pokemons.isPresent()) {
            model.addAttribute("pokemons", pokemons);
            return "/detailsByName";
        } else {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return "redirect:/pokemon/listado";
        }
    }

    //consulta general
    @RequestMapping("/listado")
    @ApiOperation("Get a list of the all pokemons ")
    @ApiResponses({
            @ApiResponse(code = 200,message = "Encontrado"),
            @ApiResponse(code = 404,message = "List Not Found")
    })
    public String listado(Model model) {
        List<Pokemon> pokemons = pokemonService.getAll();
        model.addAttribute("pokemons",pokemons);
        model.addAttribute("pokemon", new Pokemon());
        model.addAttribute("poke", new Pokemon());
        model.addAttribute("pok",new Pokemon());
        return "listado";
    }


    @PostMapping("/save")
    @ApiOperation("Save the  pokemons ")
    @ApiResponses({
            @ApiResponse(code = 200,message = "Ok"),
            @ApiResponse(code = 404,message = "Pokemon Not Save")
    })
    public String save(Pokemon pokemon){
        pokemonService.save(pokemon);
        System.out.println(pokemon.getId());
        return "redirect:/pokemon/listado";
    }

    @GetMapping("/delete")

    @ApiOperation("Delete  pokemons by ID")
    @ApiResponses({
            @ApiResponse(code = 200,message = "Ok"),
            @ApiResponse(code = 404,message = "Pokemon Not Delete")
    })

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

    @ApiOperation("Show the deteails  of the pokemons with the name of the pokemon")
    @ApiResponses({
            @ApiResponse(code = 200,message = "Ok"),
            @ApiResponse(code = 404,message = "Details Not Found")
    })
    public String pokemonDetails(Model model,Pokemon poke){
        Optional<Pokemon> pokemon = pokemonService.getPokemonByName(poke.getName());
        String ruta=pokemon.get().getName().toLowerCase();
        model.addAttribute("ruta",ruta);
        model.addAttribute("pokemon",pokemon.get());
        return "findbyname";
    }


    //manda los cambios a la BD
    @GetMapping("/update")
    @ApiOperation("Send the informatios of the   pokemons to the basedates")
    @ApiResponses({
            @ApiResponse(code = 200,message = "Ok"),
            @ApiResponse(code = 404,message = " Not Found")
    })
    public String update(Pokemon pokemon){
        pokemonService.update(pokemon);
        return "redirect:/pokemon/listado";
    }
    //inicia la vista cambios donde hay un formulario para modificar el pokemon


    @GetMapping("/cambios")
    @ApiOperation("Make changes in the pokemons with one list")
    @ApiResponses({
            @ApiResponse(code = 200,message = "Ok"),
            @ApiResponse(code = 404,message = " Not Found")
    })

    public String initCambios(Model model,Pokemon pokemon){
        Optional<Pokemon> poke = pokemonService.getPokemonByName(pokemon.getName());
        model.addAttribute("pokemon",poke.get());
        return "cambio";
    }
}
