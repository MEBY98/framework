package com.meby98.framework.unit_converter.weight;

import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasure;
import com.mercadona.supplychain.lib.common.unit_converter.UnitOfMeasureType;
import com.mercadona.supplychain.lib.common.unit_converter.exceptions.ConversionNotImplementedException;

import static com.mercadona.supplychain.lib.common.unit_converter.Converter.Constants.COEFFICIENT_LB_TO_KG;

public final class G implements UnitOfMeasure {

    private static G instance;

    private static final String DESCRIPTION = "GRAMO";
    private static final UnitOfMeasureType TYPE = Weight.getInstance();

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    public static G getInstance() {
        if (instance == null) {
            instance = new G();
        }
        return instance;
    }

    private G() {}

    @Override
    public Double convert(final UnitOfMeasure to, final Double value) {
        if (to instanceof T) {
            return (value / 1000) / 1000;
        } else if (to instanceof LB) {
            return value * (COEFFICIENT_LB_TO_KG / 1000);
        } else if (to instanceof KG) {
            return value / 1000;
        } else if (to instanceof G) {
            return value;
        } else {
            throw new ConversionNotImplementedException("ERROR: CONVERSION NOT IMPLEMENTED FOR FROM: " + this.getType().getDescription() + " TO: " + to.getType().getDescription());
        }
    }

    @Override
    public UnitOfMeasureType getType() {
        return TYPE;
    }
}
