package com.meby98.framework.unit_converter.distance;

import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureTypeScaled;

public final class Distance implements UnitOfMeasureTypeScaled {
    private static Distance instance;

    public static Distance getInstance() {
        if (instance == null) {
            instance = new Distance();
        }
        return instance;
    }

    @Override
    public String getDescription() {
        return "DISTANCE";
    }

    @Override
    public Double convertDown(final Double value) {
        return value * 10;
    }

    @Override
    public Double convertUp(final Double value) {
        return value / 10;
    }

    private Distance() {}
}
