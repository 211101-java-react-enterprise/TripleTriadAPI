package com.revature.ttapi.common.util.web;

import com.revature.ttapi.user.models.AppUser;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Secured {
    AppUser.AccountType[] allowedAccountTypes();
}
