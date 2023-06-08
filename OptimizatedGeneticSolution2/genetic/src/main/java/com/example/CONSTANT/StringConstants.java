package com.example.CONSTANT;

public enum StringConstants {
    SERIALSOLVEFILENAME("solve_to_genetic_250"),
    SAVEFILENAME("results_to_neural_250");

    private final String value;

    StringConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
