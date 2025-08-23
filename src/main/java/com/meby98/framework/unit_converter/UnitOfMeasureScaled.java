package com.meby98.framework.unit_converter;

public interface UnitOfMeasureScaled extends UnitOfMeasure {
    String getDescription();

    UnitOfMeasureTypeScaled getType();

    Integer getLevel();

    UnitOfMeasureScaled getNextLevel();

    UnitOfMeasureScaled getPreviousLevel();

    default Double convert(final UnitOfMeasure to, final Double value) {

        int diffLevel = ((UnitOfMeasureScaled) to).getLevel() - this.getLevel();

        if (diffLevel < 0) {
            return this.convert(((UnitOfMeasureScaled) to).getNextLevel(), this.convertDown(value));
        } else if (diffLevel > 0){
            return this.convert(((UnitOfMeasureScaled) to).getPreviousLevel(), this.convertUp(value));
        } else {
            return value;
        }
    }

    default Double convertDown(final Double value) {
        return this.getType().convertDown(value);
    }

    default Double convertUp(final Double value) {
        return this.getType().convertUp(value);
    }
}
