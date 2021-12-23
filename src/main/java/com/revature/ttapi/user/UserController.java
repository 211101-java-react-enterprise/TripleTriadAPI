package com.revature.ttapi.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.ttapi.card.models.Card;
import com.revature.ttapi.common.exceptions.AccountExistsException;
import com.revature.ttapi.common.exceptions.AuthenticationException;
import com.revature.ttapi.common.exceptions.CardIDBeyondCollectionTotalException;
import com.revature.ttapi.common.exceptions.ResourceNotFoundException;
import com.revature.ttapi.user.dtos.requests.LoginRequest;
import com.revature.ttapi.user.dtos.requests.UserRequest;
import com.revature.ttapi.user.dtos.responses.UserResponse;
import com.revature.ttapi.user.models.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@CrossOrigin
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
            //If username found return 409 status
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/registration", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody UserResponse registerAccount(@Valid @RequestBody UserRequest userRequest) {
        try {
            AppUser registeredUser = userService.registerNewUserAccount(userRequest);
            return new UserResponse(registeredUser);
        } catch (AccountExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/get/{username}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody UserResponse getUser(@PathVariable String username) {
        AppUser user = userService.getUser(username);
        return new UserResponse(user);
    }

    @GetMapping(value = "/get", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<UserResponse> getAllUsers() {
        List<AppUser> user = userService.getAllUsers();
        List<UserResponse> resp;
        resp = user.stream().map(UserResponse::new)
                .collect(Collectors.toList());
        return resp;

    }

    @GetMapping(value = "/{user}/{cardID}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCard(@PathVariable int cardID, @PathVariable String user){
        if(cardID > Card.getCount()+1 || cardID <= 0){
            throw new CardIDBeyondCollectionTotalException("Card Id was beyond last ID in datasource!");
        }
        userService.addCard(user, cardID);

    }

    @GetMapping(value = "/{user}/addall")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAllCards(@PathVariable String user){
        for (int i = 1; i <= Card.getCount(); i++) {
            userService.addCard(user, i);
        }
    }

    @GetMapping(value = "/delete/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String username) {
        if(userService.usernameExists(username)) {
            userService.deleteUser(username);
        } else {
            throw new ResourceNotFoundException("No such user in the datasource!");
        }

    }

    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse login(@RequestBody LoginRequest req) {
        return new UserResponse(userService.login(req.getUsername(), req.getPassword()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void error(NoSuchElementException e){
        e.printStackTrace();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void error(AuthenticationException e){
        e.printStackTrace();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public void error(ResponseStatusException e){
        e.printStackTrace();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public void error(CardIDBeyondCollectionTotalException e){
        e.printStackTrace();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void error(ResourceNotFoundException e){
        e.printStackTrace();
    }
}
