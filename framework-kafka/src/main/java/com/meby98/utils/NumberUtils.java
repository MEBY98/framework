package com.meby98.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class NumberUtils {

    public static Double round (final Double value, final int decimals) {
        return Math.round((value * Math.pow(10, decimals))) / Math.pow(10, decimals);
    }
}
