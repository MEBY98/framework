package com.meby98.unit_converter.weight_scaled;

import com.meby98.unit_converter.UnitOfMeasureScaled;
import com.meby98.unit_converter.UnitOfMeasureTypeScaled;

public final class CG implements UnitOfMeasureScaled {

    private static CG instance;

    private static final UnitOfMeasureTypeScaled TYPE = WeightScaled.getInstance();
    private static final String DESCRIPTION = "CENTIGRAMO";

    public static CG getInstance() {
        if (instance == null) {
            instance = new CG();
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

    private CG() {}

    @Override
    public UnitOfMeasureScaled getPreviousLevel() {
        return MG.getInstance();
    }

    @Override
    public UnitOfMeasureScaled getNextLevel() {
        return DG.getInstance();
    }
}
