package com.meby98.framework.unit_converter.area;

import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureScaled;
import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureTypeScaled;

public final class DM2 implements UnitOfMeasureScaled {

    private static DM2 instance;

    public static DM2 getInstance() {
        if (instance == null) {
            instance = new DM2();
        }
        return instance;
    }

    private static final UnitOfMeasureTypeScaled TYPE = Area.getInstance();
    private static final String DESCRIPTION = "DECIMETRO_CUADRADO";

    private DM2() {}

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public UnitOfMeasureTypeScaled getType() {
        return TYPE;
    }

    @Override
    public Integer getLevel() {
        return 3;
    }

    @Override
    public UnitOfMeasureScaled getNextLevel() {
        return M2.getInstance();
    }

    @Override
    public UnitOfMeasureScaled getPreviousLevel() {
        return CM2.getInstance();
    }
}
