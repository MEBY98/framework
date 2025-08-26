package com.meby98.unit_converter.volume;

import com.meby98.unit_converter.UnitOfMeasureTypeScaled;

public final class Volume implements UnitOfMeasureTypeScaled {

    public static Volume getInstance() {
        if (instance == null) {
            instance = new Volume();
        }
        return instance;
    }

    private static Volume instance;

    @Override
    public Double convertDown(final Double value) {
        return value * 1000;
    }

    private Volume() {}

    @Override
    public Double convertUp(final Double value) {
        return value / 1000;
    }

    @Override
    public String getDescription() {
        return "VOLUME";
    }
}
