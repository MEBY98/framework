package com.meby98.framework.unit_converter.area;

import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureScaled;
import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureTypeScaled;
import lombok.Getter;

@Getter
public final class MM2 implements UnitOfMeasureScaled {

    private static MM2 instance;

    private static final String DESCRIPTION = "MILIMETRO_CUADRADO";
    private static final UnitOfMeasureTypeScaled TYPE = Area.getInstance();

    public static MM2 getInstance() {
        if (instance == null) {
            instance = new MM2();
        }
        return instance;
    }

    private MM2() {}

    @Override
    public UnitOfMeasureTypeScaled getType() {
        return TYPE;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
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
    public UnitOfMeasureScaled getNextLevel() {
        return CM2.getInstance();
    }
}
