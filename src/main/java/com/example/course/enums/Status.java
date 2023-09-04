package com.example.course.enums;

public enum Status {
    ACTIVE("Ativo"), INACTIVE("Insativo");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
