package br.com.fatec.pokemon.controller;

import br.com.fatec.pokemon.controller.adapter.UserControllerAdapter;
import br.com.fatec.pokemon.controller.dto.request.UserRequest;
import br.com.fatec.pokemon.controller.dto.response.UserResponse;
import br.com.fatec.pokemon.entity.User;
import br.com.fatec.pokemon.repository.UserRepository;
import br.com.fatec.pokemon.service.UserPokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemon/v1")
public class UserController {
    private final UserRepository repository;
    private final UserPokemonService service;

    public UserController(UserRepository repository, UserPokemonService service) {
        this.repository = repository;
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    public UserResponse save(@RequestBody UserRequest request) {
        User user = UserControllerAdapter.cast(request);
        return UserControllerAdapter.cast(service.register(user));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/find-name/{name}")
    public UserResponse getByName(@PathVariable("name") String name) {
        return UserControllerAdapter.cast(repository.findByName(name));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/find-id/{id}")
    public UserResponse getById(@PathVariable("id") String id) {
        return UserControllerAdapter.cast(repository.findById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable("id") String id) {
        repository.delete(id);
    }

}
