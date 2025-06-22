package com.example.demo.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PokemonResponse {

    private String name;
    private int height;
    private int weight;
    private List<Type> types;

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public static class Type {
        private TypeInfo type;

        public TypeInfo getType() {
            return type;
        }

        public void setType(TypeInfo type) {
            this.type = type;
        }
    }

    public static class TypeInfo {
        private String name;

        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }
    }
}
