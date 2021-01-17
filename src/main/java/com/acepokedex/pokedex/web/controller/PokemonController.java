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

    @GetMapping(value = "/SearchByName/{name}", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String showPokemonByName(@PathVariable("name") String name) {
        Optional<Pokemon> pokemon = pokemonService.getPokemonByName(name);
        if (pokemon.isPresent()) {
            return "<html>\n" + "<header><title>Pokemon</title>" +
                    "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\'>" +
                    "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js'></script>" +
                    "<link rel='stylesheet' type='text/css' href='css/reset.css' th:href='@{/css/index.css}'>" +
                    "</header>\n" +
                        "<body>\n" +
                            "<div class=`container`>" +
                                "<div class='card-deck'>" +
                                    "<div class='card' style='width: 18rem;'>" +
                                        "<img class='card-img-top' src='https://img.pokemondb.net/artwork/large/"+pokemon.get().getName().toLowerCase(Locale.ROOT)+".jpg' alt='Card image cap' width=250 height=700>" +
                                            "<div class='card-body alert-primary'>" +
                                                "<p class='card-text  '>" + pokemon.get().getName() + "</p >" +
                                            "</div>" +
                                        "</div>" +
                                    "<div class='card' style = 'width: 18rem;'>" +
                                "<div class='card-header alert-primary'> Details " +
                            "</div >" +
                    "<div class='table-responsive'>" +
                        "<table class='table table-bordered table-hover table-striped'>" +
                            "<thead class='thread-transparent'>" +
                                "<tr>" +
                                    "<th scope='col'>Name</th>" +
                                "</tr>" +
                            "</thead>"+
                            "<tbody>" +
                                "<tr>" +
                                    "<td>" + pokemon.get().getName() + "</td>" +
                                "</tr>" +
                            "</tbody>" +
                            "<thead class='thread-transparent'>" +
                                "<tr>" +
                                    "<th scope='col'>Id</th>" +
                                "</tr>" +
                            "</thead>"+
                            "<tbody>" +
                                "<tr>" +
                                    "<td>" + pokemon.get().getId() + "</td>" +
                                "</tr>" +
                            "</tbody>" +
                            "<thead class='ransparent'>" +
                                "<tr>" +
                                    "<th scope='col'>Type 1</th>" +
                                "</tr>" +
                            "</thead>"+
                            "<tbody>" +
                                "<tr>" +
                                    "<td>" + pokemon.get().getType1() + "</td>" +
                                "</tr>" +
                            "</tbody>" +
                            "<thead class='thead-ransparent'>" +
                                "<tr>" +
                                    "<th scope='col'>Type 2</th>" +
                                "</tr>" +
                            "</thead>"+
                            "<tbody>" +
                                "<tr>" +
                                    "<td>" + pokemon.get().getType2() + "</td>" +
                                "</tr>" +
                            "</tbody>" +
                            "<thead class='ransparent'>" +
                                "<tr>" +
                                    "<th scope='col'>Description</th>" +
                                "</tr>" +
                            "</thead>"+
                            "<tbody>" +
                                "<tr>" +
                                    "<td>" + pokemon.get().getDescription() + "</td>" +
                                "</tr>" +
                            "</tbody>" +
                            "<thead class='thead-ransparent'>" +
                                "<tr>" +
                                    "<th scope='col'>Height</th>" +
                                "</tr>" +
                            "</thead>"+
                            "<tbody>" +
                                "<tr>" +
                                    "<td>" + pokemon.get().getHeight() + "</td>" +
                                "</tr>" +
                            "</tbody>" +
                            "<thead class='ransparent'>" +
                                "<tr>" +
                                    "<th scope='col'>Weight</th>" +
                                "</tr>" +
                            "</thead>"+
                            "<tbody>" +
                                "<tr>" +
                                    "<td>" + pokemon.get().getWeight() + "</td>" +
                                "</tr>" +
                            "</tbody>" +
                            "<thead class='thead-ransparent'>" +
                                "<tr>" +
                                    "<th scope='col'>Mega Evolves</th>" +
                                "</tr>" +
                            "</thead>"+
                            "<tbody>" +
                                "<tr>" +
                                    "<td>" + pokemon.get().isMegaEvolves() + "</td>" +
                                "</tr>" +
                            "</tbody>" +
                            "<thead class='ransparent'>" +
                                "<tr>" +
                                    "<th scope='col'>Evolves</th>" +
                                "</tr>" +
                            "</thead>"+
                            "<tbody>" +
                                "<tr>" +
                                    "<td>" + pokemon.get().getEvolves() + "</td>" +
                                "</tr>" +
                            "</tbody>" +
                        "</table>" +
                    "</div>"
                    +"</body>\n" + "</html>";
        } else {
            return "Pokemon not found :( ";
        }

    }

    @GetMapping(value = "/SearchById/{id}", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String showPokemonById(@PathVariable("id") int id) {
        Optional<Pokemon> pokemon = pokemonService.getPokemonById(id);
        if (pokemon.isPresent()) {
            return "<html>\n" + "<header><title>Pokemon</title>" +
                    "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\'>" +
                    "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js'></script>" +
                    "<link rel='stylesheet' type='text/css' href='css/reset.css' th:href='@{/css/index.css}'>" +
                    "</header>\n" +
                        "<body>\n" +
                            "<div class=`container`>" +
                                "<div class='card-deck'>" +
                                    "<div class='card' style='width: 18rem;'>" +
                                        "<img class='card-img-top' src='https://img.pokemondb.net/artwork/large/"+pokemon.get().getName().toLowerCase(Locale.ROOT)+".jpg' alt='Card image cap' width=250 height=700>" +
                                            "<div class='card-body alert-primary'>" +
                                                "<p class='card-text  '>" + pokemon.get().getName() + "</p >" +
                                            "</div>" +
                                        "</div>" +
                                    "<div class='card' style = 'width: 18rem;'>" +
                                "<div class='card-header alert-primary'> Details " +
                            "</div >" +
                        "<div class='table-responsive'>" +
                            "<table class='table table-bordered table-hover table-striped'>" +
                                "<thead class='thread-transparent'>" +
                                    "<tr>" +
                                        "<th scope='col'>Name</th>" +
                                    "</tr>" +
                                "</thead>"+
                                "<tbody>" +
                                    "<tr>" +
                                        "<td>" + pokemon.get().getName() + "</td>" +
                                    "</tr>" +
                                "</tbody>" +
                                "<thead class='thread-transparent'>" +
                                    "<tr>" +
                                        "<th scope='col'>Id</th>" +
                                    "</tr>" +
                                "</thead>"+
                                "<tbody>" +
                                    "<tr>" +
                                        "<td>" + pokemon.get().getId() + "</td>" +
                                    "</tr>" +
                                "</tbody>" +
                                "<thead class='ransparent'>" +
                                    "<tr>" +
                                        "<th scope='col'>Type 1</th>" +
                                    "</tr>" +
                                "</thead>"+
                                "<tbody>" +
                                    "<tr>" +
                                        "<td>" + pokemon.get().getType1() + "</td>" +
                                    "</tr>" +
                                "</tbody>" +
                                "<thead class='thead-ransparent'>" +
                                    "<tr>" +
                                        "<th scope='col'>Type 2</th>" +
                                    "</tr>" +
                                "</thead>"+
                                "<tbody>" +
                                    "<tr>" +
                                        "<td>" + pokemon.get().getType2() + "</td>" +
                                    "</tr>" +
                                "</tbody>" +
                                "<thead class='ransparent'>" +
                                    "<tr>" +
                                        "<th scope='col'>Description</th>" +
                                    "</tr>" +
                                "</thead>"+
                                "<tbody>" +
                                    "<tr>" +
                                        "<td>" + pokemon.get().getDescription() + "</td>" +
                                    "</tr>" +
                                "</tbody>" +
                                "<thead class='thead-ransparent'>" +
                                    "<tr>" +
                                        "<th scope='col'>Height</th>" +
                                    "</tr>" +
                                "</thead>"+
                                "<tbody>" +
                                    "<tr>" +
                                        "<td>" + pokemon.get().getHeight() + "</td>" +
                                    "</tr>" +
                                "</tbody>" +
                                    "<thead class='ransparent'>" +
                                        "<tr>" +
                                    "<th scope='col'>Weight</th>" +
                                "</tr>" +
                                "</thead>"+
                                "<tbody>" +
                                    "<tr>" +
                                        "<td>" + pokemon.get().getWeight() + "</td>" +
                                    "</tr>" +
                                "</tbody>" +
                                "<thead class='thead-ransparent'>" +
                                    "<tr>" +
                                        "<th scope='col'>Mega Evolves</th>" +
                                    "</tr>" +
                                "</thead>"+
                                "<tbody>" +
                                    "<tr>" +
                                        "<td>" + pokemon.get().isMegaEvolves() + "</td>" +
                                    "</tr>" +
                                "</tbody>" +
                                "<thead class='ransparent'>" +
                                    "<tr>" +
                                        "<th scope='col'>Evolves</th>" +
                                    "</tr>" +
                                "</thead>"+
                                "<tbody>" +
                                    "<tr>" +
                                        "<td>" + pokemon.get().getEvolves() + "</td>" +
                                    "</tr>" +
                                "</tbody>" +
                                "</table>" +
                            "</div>"
                    +   "</body>\n" + "</html>";
        } else {
            return "<html>\n" + "<header><title>Pokemon</title>" + "</header>\n" +
                    "<body>\n" + "<button type='button' class='btn btn-outline-primary' href='window.history.back()'> Back </button>" + "</body>\n" + "</html>";
        }
    }
}
