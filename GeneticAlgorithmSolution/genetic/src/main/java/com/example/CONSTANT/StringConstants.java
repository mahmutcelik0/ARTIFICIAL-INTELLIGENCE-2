package com.example.CONSTANT;

public enum StringConstants {
    SERIALSOLVEFILENAME("solve_to_genetic_50"),
    SAVEFILENAME("results_to_neural_50");

    private final String value;

    StringConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
