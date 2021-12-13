package com.revature.ttapi.common.dtos;

import javax.validation.constraints.NotBlank;

public abstract class EditResourceRequest {

    @NotBlank
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
