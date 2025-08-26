package com.meby98.unit_converter;

public interface UnitOfMeasureTypeScaled extends UnitOfMeasureType{
    Double convertDown(Double value);

    Double convertUp(Double value);
}
