package com.meby98.unit_converter.distance;

import com.meby98.unit_converter.UnitOfMeasureScaled;
import com.meby98.unit_converter.UnitOfMeasureTypeScaled;
import lombok.Getter;

@Getter
public final class DM implements UnitOfMeasureScaled {

    private static DM instance;

    private static final String DESCRIPTION = "DECIMETRO";
    private static final UnitOfMeasureTypeScaled TYPE = Distance.getInstance();

    public static DM getInstance() {
        if (instance == null) {
            instance = new DM();
        }
        return instance;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    private DM() {}

    @Override
    public UnitOfMeasureScaled getNextLevel() {
        return M.getInstance();
    }

    @Override
    public UnitOfMeasureTypeScaled getType() {
        return TYPE;
    }

    @Override
    public Integer getLevel() {
        return 3;
    }

    @Override
    public UnitOfMeasureScaled getPreviousLevel() {
        return CM.getInstance();
    }
}
