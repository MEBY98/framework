package com.meby98.framework.unit_converter.volume;

import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureScaled;
import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureTypeScaled;

public final class MM3 implements UnitOfMeasureScaled {

    private static MM3 instance;

    private static final String DESCRIPTION = "MILIMETRO_CUBICO";
    private static final UnitOfMeasureTypeScaled TYPE = Volume.getInstance();

    public static MM3 getInstance() {
        if (instance == null) {
            instance = new MM3();
        }
        return instance;
    }

    private MM3() {}

    @Override
    public UnitOfMeasureTypeScaled getType() {
        return TYPE;
    }

    @Override
    public UnitOfMeasureScaled getNextLevel() {
        return CM3.getInstance();
    }

    @Override
    public Integer getLevel() {
        return 1;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public UnitOfMeasureScaled getPreviousLevel() {
        return null;
    }
}
