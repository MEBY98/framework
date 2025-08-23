package com.meby98.framework.unit_converter.volume;

import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureScaled;
import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureTypeScaled;

public final class DM3 implements UnitOfMeasureScaled {

    private static DM3 instance;

    private static final String DESCRIPTION = "DECIMETRO_CUBICO";
    public static DM3 getInstance() {
        if (instance == null) {
            instance = new DM3();
        }
        return instance;
    }

    private static final UnitOfMeasureTypeScaled TYPE = Volume.getInstance();

    private DM3() {}

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
        return M3.getInstance();
    }

    @Override
    public UnitOfMeasureScaled getPreviousLevel() {
        return CM3.getInstance();
    }

    @Override
    public Integer getLevel() {
        return 3;
    }
}
