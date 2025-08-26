package com.meby98.unit_converter.volume;

import com.meby98.unit_converter.UnitOfMeasureScaled;
import com.meby98.unit_converter.UnitOfMeasureTypeScaled;

public final class CM3 implements UnitOfMeasureScaled {

    private static final UnitOfMeasureTypeScaled TYPE = Volume.getInstance();
    private static CM3 instance;

    private static final String DESCRIPTION = "CENTIMETRO_CUBICO";

    private CM3() {}

    public static CM3 getInstance() {
        if (instance == null) {
            instance = new CM3();
        }
        return instance;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public Integer getLevel() {
        return 2;
    }

    @Override
    public UnitOfMeasureTypeScaled getType() {
        return TYPE;
    }

    @Override
    public UnitOfMeasureScaled getPreviousLevel() {
        return MM3.getInstance();
    }

    @Override
    public UnitOfMeasureScaled getNextLevel() {
        return DM3.getInstance();
    }
}
