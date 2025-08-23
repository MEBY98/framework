package com.meby98.framework.unit_converter.weight_scaled;

import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureScaled;
import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureTypeScaled;

public final class G implements UnitOfMeasureScaled {

    @Override
    public Integer getLevel() {
        return 4;
    }

    private static G instance;
    private static final String DESCRIPTION = "GRAMO";

    private static final UnitOfMeasureTypeScaled TYPE = WeightScaled.getInstance();

    public static G getInstance() {
        if (instance == null) {
            instance = new G();
        }
        return instance;
    }

    private G() {}

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
        return null;
    }

    @Override
    public UnitOfMeasureScaled getPreviousLevel() {
        return DG.getInstance();
    }
}
