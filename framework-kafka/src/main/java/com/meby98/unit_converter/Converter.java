package com.meby98.unit_converter;

import com.meby98.unit_converter.distance.*;
import com.meby98.unit_converter.volume.*;
import com.meby98.unit_converter.area.*;
import com.meby98.unit_converter.weight.*;
import com.meby98.unit_converter.weight_scaled.*;
import com.meby98.unit_converter.exceptions.DifferentMeasureTypeException;
import com.meby98.unit_converter.exceptions.ParseUnitOfMeasureException;
import com.meby98.unit_converter.weight_scaled.G;
import lombok.experimental.UtilityClass;
import org.springframework.lang.NonNull;

@UtilityClass
public final class Converter {

    private static final String NOT_RECOGNIZED_MESSAGE = " NOT RECOGNIZED";

    @UtilityClass
    public static class Constants {

        public static final Double COEFFICIENT_LB_TO_KG = 2.205;
    }

    public static Double convert(@NonNull final UnitOfMeasure from, @NonNull final UnitOfMeasure to,
                                 @NonNull final Double value) {

        if (!from.getType().equals(to.getType())) {
            throw new DifferentMeasureTypeException("ERROR: UNIT OF MEASURE FROM: " + from.getType().getDescription() + "AND TO: " + to.getType().getDescription() + " ARE DIFFERENTS");
        }

        return from.convert(to, value);
    }

    public static UnitOfMeasure parseUnitOfMeasureDistance(final String unitOfMeasure) {
        return switch (unitOfMeasure) {
            case "MM", "Milimetro", "MILIMETRO" -> MM.getInstance();
            case "CM", "Centimetro", "CENTIMETRO" -> CM.getInstance();
            case "DM", "Decimetro", "DECIMETRO" -> DM.getInstance();
            case "M", "Metro", "METRO" -> M.getInstance();
            default -> throw new ParseUnitOfMeasureException(unitOfMeasure + NOT_RECOGNIZED_MESSAGE);
        };
    }

    public static UnitOfMeasure parseUnitOfMeasureArea(final String unitOfMeasure) {
        return switch (unitOfMeasure) {
            case "MM_2", "Milimetro Cuadrado", "MILIMETRO_CUADRADO" -> MM2.getInstance();
            case "CM_2", "Centimetro Cuadrado", "CENTIMETRO_CUADRADO" -> CM2.getInstance();
            case "DM_2", "Decimetro Cuadrado", "DECIMETRO_CUADRADO" -> DM2.getInstance();
            case "M_2", "Metro Cuadrado", "METRO_CUADRADO" -> M2.getInstance();
            default -> throw new ParseUnitOfMeasureException(unitOfMeasure + NOT_RECOGNIZED_MESSAGE);
        };
    }

    public static UnitOfMeasure parseUnitOfMeasureVolume(final String unitOfMeasure) {
        return switch (unitOfMeasure) {
            case "MM_3", "Milimetro Cubico", "MILIMETRO_CUBICO" -> MM3.getInstance();
            case "CM_3", "Centimetro Cubico", "CENTIMETRO_CUBICO" -> CM3.getInstance();
            case "DM_3", "Decimetro Cubico", "DECIMETRO_CUBICO" -> DM3.getInstance();
            case "M_3", "Metro Cubico", "METRO_CUBICO" -> M3.getInstance();
            default -> throw new ParseUnitOfMeasureException(unitOfMeasure + NOT_RECOGNIZED_MESSAGE);
        };
    }

    public static UnitOfMeasure parseUnitOfMeasureWeightScaled(final String unitOfMeasure) {
        return switch (unitOfMeasure) {
            case "MG", "Miligramo", "MILIGRAMO" -> MG.getInstance();
            case "CG", "Centigramo", "CENTIGRAMO" -> CG.getInstance();
            case "DG", "Decigramo", "DECIGRAMO" -> DG.getInstance();
            case "G", "Gramo", "GRAMO" -> G.getInstance();
            default -> throw new ParseUnitOfMeasureException(unitOfMeasure + NOT_RECOGNIZED_MESSAGE);
        };
    }

    public static UnitOfMeasure parseUnitOfMeasureWeight(final String unitOfMeasure) {
        return switch (unitOfMeasure) {
            case "G", "Gramo", "GRAMO" -> com.meby98.unit_converter.weight.G.getInstance();
            case "KG", "Kilogramo", "KILOGRAMO" -> KG.getInstance();
            case "T", "Tonelada", "TONELADA" -> T.getInstance();
            case "LB", "Libra", "LIBRA" -> LB.getInstance();
            default -> throw new ParseUnitOfMeasureException(unitOfMeasure + NOT_RECOGNIZED_MESSAGE);
        };
    }
}
