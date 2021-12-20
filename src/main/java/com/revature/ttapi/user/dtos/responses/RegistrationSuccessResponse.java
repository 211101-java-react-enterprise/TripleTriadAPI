package com.revature.ttapi.user.dtos.responses;

import com.revature.ttapi.user.validators.Uuid;

import java.util.UUID;

public class RegistrationSuccessResponse {

    @Uuid
    private UUID id;

    public RegistrationSuccessResponse() {
        super();
    }

    public RegistrationSuccessResponse(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "RegistrationSuccessResponse{" +
                "id='" + id + '\'' +
                '}';
    }

}
