package com.meby98.unit_converter.distance;

import com.meby98.unit_converter.UnitOfMeasureScaled;
import com.meby98.unit_converter.UnitOfMeasureTypeScaled;
import lombok.Getter;

@Getter
public final class M implements UnitOfMeasureScaled {

    private static M instance;

    public static M getInstance() {
        if (instance == null) {
            instance = new M();
        }
        return instance;
    }

    private M() {}

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    private static final String DESCRIPTION = "METRO";
    private static final UnitOfMeasureTypeScaled TYPE = Distance.getInstance();

    @Override
    public UnitOfMeasureTypeScaled getType() {
        return TYPE;
    }

    @Override
    public Integer getLevel() {
        return 4;
    }

    @Override
    public UnitOfMeasureScaled getNextLevel() {
        return null;
    }

    @Override
    public UnitOfMeasureScaled getPreviousLevel() {
        return DM.getInstance();
    }
}
