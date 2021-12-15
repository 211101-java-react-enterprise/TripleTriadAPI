package com.revature.ttapi.auth;

import com.revature.ttapi.auth.dtos.request.LoginRequest;
import com.revature.ttapi.user.models.AppUser;
import com.revature.ttapi.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
/*
    userService.authenticateUser didn't exist when I pulled this down
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(consumes = "application/json")
    public void login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        AppUser authUser =
                userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        session.setAttribute("authUser", authUser);
    }
*/

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpSession session) { session.invalidate(); }

}
