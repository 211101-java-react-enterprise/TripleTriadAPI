package com.revature.ttapi.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.ttapi.common.exceptions.AccountExistsException;
import com.revature.ttapi.common.exceptions.ResourceNotFoundException;
import com.revature.ttapi.user.dtos.requests.UserRequest;
import com.revature.ttapi.user.dtos.responses.UserResponse;
import com.revature.ttapi.user.models.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {



    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/usernamecheck/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void checkUsernameAvailability(@PathVariable String username) {
        if (userService.usernameExists(username)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/registration", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody UserResponse registerAccount(@Valid @RequestBody UserRequest userRequest, HttpServletRequest servletRequest) {
        try {
            AppUser registeredUser = userService.registerNewUserAccount(userRequest);
            return new UserResponse(registeredUser);
        } catch (AccountExistsException e) {
            return null;
        }
    }


    @PostMapping(value = "/get", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody UserResponse getUser(@RequestBody String username) {
        System.out.println(username);
        AppUser user = userService.getUser(username);
        if(user == null) throw new ResourceNotFoundException();
        return new UserResponse(user);
    }

    @GetMapping(value = "/get", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<UserResponse> getAllUsers() {
        List<AppUser> user = userService.getAllUsers();
        if(user == null) throw new ResourceNotFoundException();
        List<UserResponse> resp;
        resp = user.stream().map(UserResponse::new)
                .collect(Collectors.toList());
        return resp;

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void error(ResourceNotFoundException e){
        e.printStackTrace();
    }
}
