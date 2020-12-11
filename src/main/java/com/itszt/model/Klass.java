package com.itszt.model;

public class Klass {
    private int field;

    public Klass(int field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "field=" + field;
    }
}
