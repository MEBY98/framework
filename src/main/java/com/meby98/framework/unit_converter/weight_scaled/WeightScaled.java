package com.meby98.framework.unit_converter.weight_scaled;

import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureTypeScaled;

public final class WeightScaled implements UnitOfMeasureTypeScaled {

    @Override
    public String getDescription() {
        return "WEIGHT_SCALED";
    }

    public static WeightScaled getInstance() {
        if (instance == null) {
            instance = new WeightScaled();
        }
        return instance;
    }

    private WeightScaled() {}

    @Override
    public Double convertDown(final Double value) {
        return value * 10;
    }

    private static WeightScaled instance;

    @Override
    public Double convertUp(final Double value) {
        return value / 10;
    }
}
