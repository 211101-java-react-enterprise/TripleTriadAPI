package com.revature.ttapi.user.dtos.requests;

import com.revature.ttapi.common.dtos.PasswordForm;
import com.revature.ttapi.user.validators.Username;
import com.revature.ttapi.user.validators.password.PasswordMatch;
import com.revature.ttapi.user.validators.password.ValidPassword;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@PasswordMatch
public class UserRequest implements PasswordForm {

    @NotBlank
    @Username
    private String username;

    @NotBlank
    @ValidPassword
    private String password;

    @NotBlank
    @ValidPassword
    private String matchingPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequest that = (UserRequest) o;
        return username.equals(that.username) && password.equals(that.password) && matchingPassword.equals(that.matchingPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, matchingPassword);
    }

    @Override
    public String toString() {
        return "NewRegistrationRequest{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
