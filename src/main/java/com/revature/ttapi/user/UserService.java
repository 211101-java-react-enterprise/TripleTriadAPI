package com.revature.ttapi.user;

import com.revature.ttapi.common.exceptions.AccountExistsException;
import com.revature.ttapi.common.exceptions.AuthenticationException;
import com.revature.ttapi.user.dtos.requests.UserRequest;
import com.revature.ttapi.user.models.AppUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional(rollbackFor = AccountExistsException.class)
    public AppUser registerNewUserAccount(UserRequest userRequest) throws AccountExistsException {
        if (usernameExists(userRequest.getUsername())) {
            throw new AccountExistsException("There is already an account with that username: "
                    + userRequest.getUsername());
        }
        AppUser user = AppUser.fromRequest(userRequest);
        user = userRepo.save(user);
        user = userRepo.getById(user.getId());
        System.out.println(user);
        return user;
    }

    @Transactional
    public List<AppUser> getAllUsers() {
        return new ArrayList<>(userRepo.findAll());
    }

    @Transactional
    public boolean usernameExists(String username) {
        return userRepo.findByUsername(username).isPresent();
    }

    public Optional<AppUser> authenticateUser(String username, String password) {
        return userRepo.findByUsernameAndPassword(username, password);
    }

    @Transactional
    public void deleteUser(String username){
        userRepo.deleteByUsername(username);
    }

    @Transactional
    public void addCard(String username, int cardID){
        AppUser user = userRepo.findByUsername(username).get();
        user.addCard(cardID);
    }

    @Transactional
    public AppUser getUser(String username) throws NoSuchElementException {
        return userRepo.findByUsername(username).get();
    }

    @Transactional(readOnly = true)
    public AppUser login(String username, String password) throws NoSuchElementException {
        AppUser user = userRepo.findByUsername(username).get();
        System.out.println(user.getPassword());
        System.out.println(password);
        if (user.getPassword().equals(password)){
            return user;
        }else{
            throw new AuthenticationException("Invalid password");
        }
    }
}
