package com.meby98.unit_converter.weight_scaled;

import com.meby98.unit_converter.UnitOfMeasureScaled;
import com.meby98.unit_converter.UnitOfMeasureTypeScaled;

public final class DG implements UnitOfMeasureScaled {

    private static DG instance;
    private static final UnitOfMeasureTypeScaled TYPE = WeightScaled.getInstance();

    private static final String DESCRIPTION = "DECIGRAMO";

    @Override
    public UnitOfMeasureTypeScaled getType() {
        return TYPE;
    }

    private DG() {}

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    public static DG getInstance() {
        if (instance == null) {
            instance = new DG();
        }
        return instance;
    }

    @Override
    public Integer getLevel() {
        return 3;
    }

    @Override
    public UnitOfMeasureScaled getNextLevel() {
        return G.getInstance();
    }

    @Override
    public UnitOfMeasureScaled getPreviousLevel() {
        return CG.getInstance();
    }
}
