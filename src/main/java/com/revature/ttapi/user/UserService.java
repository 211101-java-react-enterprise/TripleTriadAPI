package com.revature.ttapi.user;

import com.revature.ttapi.common.exceptions.AuthenticationException;
import com.revature.ttapi.common.exceptions.InvalidRequestException;
import com.revature.ttapi.common.exceptions.ResourceNotFoundException;
import com.revature.ttapi.common.exceptions.ResourcePersistenceException;
import com.revature.ttapi.user.dtos.requests.EditUserRequest;
import com.revature.ttapi.user.dtos.requests.RegistrationRequest;
import com.revature.ttapi.user.dtos.responses.UserResponse;
import com.revature.ttapi.user.models.AppUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        List<UserResponse> users = userRepo.findAll()
                                           .stream()
                                           .map(UserResponse::new)
                                           .collect(Collectors.toList());
        if (users.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return users;
    }

    @Transactional
    public UserResponse registerNewUser(RegistrationRequest newRegistration) {
        return new UserResponse(userRepo.saveAndFlush(AppUser.fromRequest(newRegistration)));
    }

    @Transactional(readOnly = true)
    public AppUser authenticateUser(String username, String password) {
        if (username == null || username.trim()
                                        .equals("") || password == null || password.trim()
                                                                                   .equals("")) {
            throw new InvalidRequestException("Invalid credential values provided!");
        }
        return userRepo.findUserByUsernameAndPassword(username, password)
                       .orElseThrow(AuthenticationException::new);
    }

    @Transactional
    public void updateUser(EditUserRequest editRequest) {
        try {
            AppUser appUserRecord = userRepo.findById(editRequest.getId())
                                            .orElseThrow(ResourceNotFoundException::new);
            appUserRecord.setPassword(editRequest.getPassword());
        } catch (ResourcePersistenceException e) {
            throw new ResourcePersistenceException("Could not update account details", e);
        }
    }

    @Transactional
    public boolean isUsernameAvailable(String username) {
        try {
            return userRepo.findAppUserByUsername(username)
                           .isPresent();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
