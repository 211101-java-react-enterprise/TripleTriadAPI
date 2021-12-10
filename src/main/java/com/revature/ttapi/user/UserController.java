package com.revature.ttapi.user;

import com.revature.ttapi.common.util.web.Authenticated;
import com.revature.ttapi.common.util.web.RequesterOwned;
import com.revature.ttapi.common.util.web.Secured;
import com.revature.ttapi.user.dtos.requests.EditUserRequest;
import com.revature.ttapi.user.dtos.requests.NewRegistrationRequest;
import com.revature.ttapi.user.dtos.responses.RegistrationSuccessResponse;
import com.revature.ttapi.user.dtos.responses.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Authenticated
    @Secured(allowedAccountTypes = {AppUser.AccountType.ADMIN, AppUser.AccountType.DEV})
    @GetMapping(produces = "application/json")
    public List<UserResponse> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/username")
    public ResponseEntity<Void> checkUsernameAvailability(@RequestParam String username) {
        return userService.isUsernameAvailable(username) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("/email")
    public ResponseEntity<Void> checkEmailAvailability(@RequestParam String email) {
        return userService.isEmailAvailable(email) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public RegistrationSuccessResponse register(@RequestBody @Valid NewRegistrationRequest newRegistrationRequest) {
        return userService.registerNewUser(newRegistrationRequest);
    }

    @Authenticated
    @RequesterOwned
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(consumes = "application/json")
    public void updateUserInfo(@RequestBody EditUserRequest editUserRequest, HttpServletRequest req) {
        userService.updateUser(editUserRequest);
    }

}
