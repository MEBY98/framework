package com.meby98.framework.unit_converter.distance;

import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureScaled;
import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureTypeScaled;

public final class MM implements UnitOfMeasureScaled {

    private static MM instance;

    private static final String DESCRIPTION = "MILIMETRO";
    private static final UnitOfMeasureTypeScaled TYPE = Distance.getInstance();

    @Override
    public UnitOfMeasureScaled getPreviousLevel() {
        return null;
    }

    public static MM getInstance() {
        if (instance == null) {
            instance = new MM();
        }
        return instance;
    }

    private MM() {}

    @Override
    public UnitOfMeasureTypeScaled getType() {
        return TYPE;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public UnitOfMeasureScaled getNextLevel() {
        return CM.getInstance();
    }

    @Override
    public Integer getLevel() {
        return 1;
    }
}
