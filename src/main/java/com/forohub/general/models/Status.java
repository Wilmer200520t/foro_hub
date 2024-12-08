package com.forohub.general.models;

public enum Status {
    Active("A"),
    Inactive("I"),
    Blocked("B");

    private String status;
    private Status(String status) {
        this.status = status;
    }
}
