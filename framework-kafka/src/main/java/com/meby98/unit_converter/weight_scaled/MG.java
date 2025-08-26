package com.meby98.unit_converter.weight_scaled;

import com.meby98.unit_converter.UnitOfMeasureScaled;
import com.meby98.unit_converter.UnitOfMeasureTypeScaled;

public final class MG implements UnitOfMeasureScaled {

    private static MG instance;

    private static final String DESCRIPTION = "MILIGRAMO";
    private static final UnitOfMeasureTypeScaled TYPE = WeightScaled.getInstance();

    public static MG getInstance() {
        if (instance == null) {
            instance = new MG();
        }
        return instance;
    }

    private MG() {}

    @Override
    public UnitOfMeasureScaled getNextLevel() {
        return CG.getInstance();
    }

    @Override
    public UnitOfMeasureTypeScaled getType() {
        return TYPE;
    }

    @Override
    public Integer getLevel() {
        return 1;
    }

    @Override
    public UnitOfMeasureScaled getPreviousLevel() {
        return null;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
