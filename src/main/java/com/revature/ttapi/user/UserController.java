package com.revature.ttapi.user;

import com.revature.ttapi.common.util.web.Authenticated;
import com.revature.ttapi.common.util.web.RequesterOwned;
import com.revature.ttapi.common.util.web.Secured;
import com.revature.ttapi.user.dtos.requests.EditUserRequest;
import com.revature.ttapi.user.dtos.requests.RegistrationRequest;
import com.revature.ttapi.user.dtos.responses.UserResponse;
import com.revature.ttapi.user.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void checkUsernameAvailability(@PathVariable String username) {
        if (userService.isUsernameAvailable(username)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Authenticated
    @Secured(allowedAccountTypes = {AppUser.AccountType.ADMIN, AppUser.AccountType.DEV})
    @GetMapping(produces = "application/json")
    public List<UserResponse> getUsers() {
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public UserResponse register(@RequestBody RegistrationRequest registrationRequest) {
        return userService.registerNewUser(registrationRequest);
    }

    @Authenticated
    @RequesterOwned
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(consumes = "application/json")
    public void updateUserInfo(@RequestBody EditUserRequest editUserRequest, HttpServletRequest req) {
        userService.updateUser(editUserRequest);
    }

}
