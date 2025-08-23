package com.meby98.framework.unit_converter;

public interface UnitOfMeasure {

    String getDescription();

    UnitOfMeasureType getType();

    Double convert(UnitOfMeasure to, Double value);
}
