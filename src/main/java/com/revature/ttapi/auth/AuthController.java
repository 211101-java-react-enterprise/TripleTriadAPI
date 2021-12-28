package com.revature.ttapi.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.ttapi.auth.dtos.request.LoginRequest;
import com.revature.ttapi.user.UserService;
import com.revature.ttapi.user.models.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(consumes = "application/json")
    public void login(@RequestBody LoginRequest loginRequest, HttpSession session) throws HTTPException {
        Optional<AppUser> authorizedUser = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        if (!authorizedUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        session.setAttribute("authUser", authorizedUser);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpSession session) { session.invalidate(); }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void error(ResponseStatusException e){
        e.printStackTrace();
    }

}
