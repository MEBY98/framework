package com.meby98.unit_converter.weight;

import com.meby98.unit_converter.UnitOfMeasure;
import com.meby98.unit_converter.UnitOfMeasureType;
import com.meby98.unit_converter.exceptions.ConversionNotImplementedException;

import static com.meby98.unit_converter.Converter.Constants.COEFFICIENT_LB_TO_KG;

public final class KG implements UnitOfMeasure {

    private static KG instance;

    private static final String DESCRIPTION = "KILOGRAMO";

    private KG() {
    }

    public static KG getInstance() {
        if (instance == null) {
            instance = new KG();
        }
        return instance;
    }

    private static final UnitOfMeasureType TYPE = Weight.getInstance();

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public Double convert(final UnitOfMeasure to, final Double value) {
        if (to instanceof T) {
            return value / 1000;
        } else if (to instanceof LB) {
            return value * COEFFICIENT_LB_TO_KG;
        } else if (to instanceof KG) {
            return value;
        } else if (to instanceof G) {
            return value * 1000;
        } else {
            throw new ConversionNotImplementedException("ERROR: CONVERSION NOT IMPLEMENTED FOR FROM: " + this.getType().getDescription() + " TO: " + to.getType().getDescription());
        }
    }

    @Override
    public UnitOfMeasureType getType() {
        return TYPE;
    }
}
