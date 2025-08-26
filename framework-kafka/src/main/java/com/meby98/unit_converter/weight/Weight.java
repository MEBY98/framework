package com.meby98.unit_converter.weight;

import com.meby98.unit_converter.UnitOfMeasureType;

public final class Weight implements UnitOfMeasureType {

    private static Weight instance;

    @Override
    public String getDescription() {
        return "WEIGHT";
    }

    private Weight() {}

    public static Weight getInstance() {
        if (instance == null) {
            instance = new Weight();
        }
        return instance;
    }
}
