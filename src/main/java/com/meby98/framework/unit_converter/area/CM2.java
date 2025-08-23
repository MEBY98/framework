package com.meby98.framework.unit_converter.area;

import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureScaled;
import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureTypeScaled;

public final class CM2 implements UnitOfMeasureScaled {

    private static CM2 instance;

    private static final String DESCRIPTION = "CENTIMETRO_CUADRADO";
    private static final UnitOfMeasureTypeScaled TYPE = Area.getInstance();

    public static CM2 getInstance() {
        if (instance == null) {
            instance = new CM2();
        }
        return instance;
    }

    @Override
    public UnitOfMeasureTypeScaled getType() {
        return TYPE;
    }

    private CM2() {}

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public UnitOfMeasureScaled getPreviousLevel() {
        return MM2.getInstance();
    }

    @Override
    public UnitOfMeasureScaled getNextLevel() {
        return DM2.getInstance();
    }

    @Override
    public Integer getLevel() {
        return 2;
    }
}
