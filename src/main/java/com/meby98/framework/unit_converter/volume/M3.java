package com.meby98.framework.unit_converter.volume;

import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureScaled;
import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureTypeScaled;

public final class M3 implements UnitOfMeasureScaled {

    private static final UnitOfMeasureTypeScaled TYPE = Volume.getInstance();
    private static M3 instance;

    private static final String DESCRIPTION = "METRO_CUBICO";

    public static M3 getInstance() {
        if (instance == null) {
            instance = new M3();
        }
        return instance;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    private M3() {}

    @Override
    public Integer getLevel() {
        return 4;
    }

    @Override
    public UnitOfMeasureTypeScaled getType() {
        return TYPE;
    }

    @Override
    public UnitOfMeasureScaled getPreviousLevel() {
        return DM3.getInstance();
    }

    @Override
    public UnitOfMeasureScaled getNextLevel() {
        return null;
    }
}
