package com.meby98.framework.unit_converter.distance;

import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureScaled;
import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureTypeScaled;
import lombok.Getter;

@Getter
public final class CM implements UnitOfMeasureScaled {

    @Override
    public UnitOfMeasureTypeScaled getType() {
        return TYPE;
    }

    private static CM instance;
    private static final String DESCRIPTION = "CENTIMETRO";

    private static final UnitOfMeasureTypeScaled TYPE = Distance.getInstance();

    private CM() {}

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public Integer getLevel() {
        return 2;
    }

    public static CM getInstance() {
        if (instance == null) {
            instance = new CM();
        }
        return instance;
    }

    @Override
    public UnitOfMeasureScaled getPreviousLevel() {
        return MM.getInstance();
    }

    @Override
    public UnitOfMeasureScaled getNextLevel() {
        return DM.getInstance();
    }
}
