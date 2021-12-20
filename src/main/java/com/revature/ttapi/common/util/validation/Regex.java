package com.revature.ttapi.common.util.validation;

public class Regex {

    public static final String PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";

    private Regex() {
        super();
    }
}
