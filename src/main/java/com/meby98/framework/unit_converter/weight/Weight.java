package com.meby98.framework.unit_converter.weight;

import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureType;

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
