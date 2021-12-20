package com.revature.ttapi.common.dtos;

public class ResourceCreationResponse {

    private int resourceId;

    public ResourceCreationResponse() {
        super();
    }

    public ResourceCreationResponse(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "ResourceCreationResponse{" +
                "resourceId='" + resourceId + '\'' +
                '}';
    }

}
