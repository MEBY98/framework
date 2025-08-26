package com.meby98.unit_converter.area;

import com.meby98.unit_converter.UnitOfMeasureScaled;
import com.meby98.unit_converter.UnitOfMeasureTypeScaled;

public final class M2 implements UnitOfMeasureScaled {

    private static M2 instance;

    private static final String DESCRIPTION = "METRO_CUADRADO";
    private static final UnitOfMeasureTypeScaled TYPE = Area.getInstance();

    public static M2 getInstance() {
        if (instance == null) {
            instance = new M2();
        }
        return instance;
    }

    private M2() {}

    @Override
    public Integer getLevel() {
        return 4;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public UnitOfMeasureTypeScaled getType() {
        return TYPE;
    }

    @Override
    public UnitOfMeasureScaled getNextLevel() {
        return null;
    }

    @Override
    public UnitOfMeasureScaled getPreviousLevel() {
        return DM2.getInstance();
    }
}
