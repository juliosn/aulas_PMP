package com.example.demo.infrastructure.response;

import java.util.List;

public class PokeApiResponse {
    public String name;
    public int height;
    public int weight;
    public List<TypeWrapper> types;

    public static class TypeWrapper {
        public Type type;
    }

    public static class Type {
        public String name;
    }
}
