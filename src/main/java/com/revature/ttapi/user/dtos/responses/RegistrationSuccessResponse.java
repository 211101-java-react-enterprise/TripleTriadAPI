package com.revature.ttapi.user.dtos.responses;

public class RegistrationSuccessResponse {

    private String id;

    public RegistrationSuccessResponse() {
        super();
    }

    public RegistrationSuccessResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RegistrationSuccessResponse{" +
                "id='" + id + '\'' +
                '}';
    }

}
