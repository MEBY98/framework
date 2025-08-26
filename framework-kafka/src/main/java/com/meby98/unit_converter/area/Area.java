package com.meby98.unit_converter.area;

import com.meby98.unit_converter.UnitOfMeasureTypeScaled;

public final class Area implements UnitOfMeasureTypeScaled {

    @Override
    public String getDescription() {
        return "AREA";
    }
    private static Area instance;


    private Area() {}

    @Override
    public Double convertDown(final Double value) {
        return value * 100;
    }

    public static Area getInstance() {
        if (instance == null) {
            instance = new Area();
        }
        return instance;
    }

    @Override
    public Double convertUp(final Double value) {
        return value / 100;
    }
}
