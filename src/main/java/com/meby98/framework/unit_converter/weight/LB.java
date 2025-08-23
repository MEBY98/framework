package com.meby98.framework.unit_converter.weight;

import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasure;
import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureType;
import com.mercadona.supplychain.lib.common.unit_converter.exceptions.ConversionNotImplementedException;

import static com.mercadona.supplychain.lib.common.unit_converter.Converter.Constants.COEFFICIENT_LB_TO_KG;

public final class LB implements UnitOfMeasure {

    private static LB instance;

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
    private static final String DESCRIPTION = "LIBRA";

    private static final UnitOfMeasureType TYPE = Weight.getInstance();

    public static LB getInstance() {
        if (instance == null) {
            instance = new LB();
        }
        return instance;
    }

    private LB() {}

    @Override
    public Double convert(final UnitOfMeasure to, final Double value) {
        if (to instanceof T) {
            return value / (COEFFICIENT_LB_TO_KG * 1000);
        } else if (to instanceof LB) {
            return value;
        } else if (to instanceof KG) {
            return value / COEFFICIENT_LB_TO_KG;
        } else if (to instanceof G) {
            return value * (COEFFICIENT_LB_TO_KG * 1000);
        } else {
            throw new ConversionNotImplementedException("ERROR: CONVERSION NOT IMPLEMENTED FOR FROM: " + this.getType().getDescription() + " TO: " + to.getType().getDescription());
        }
    }

    @Override
    public UnitOfMeasureType getType() {
        return TYPE;
    }
}
