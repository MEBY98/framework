package com.meby98.framework.unit_converter;

import com.mercadona.supplychain.lib.common.unit_converter.area.CM2;
import com.mercadona.supplychain.lib.common.unit_converter.area.DM2;
import com.mercadona.supplychain.lib.common.unit_converter.area.M2;
import com.mercadona.supplychain.lib.common.unit_converter.area.MM2;
import com.mercadona.supplychain.lib.common.unit_converter.distance.CM;
import com.mercadona.supplychain.lib.common.unit_converter.distance.DM;
import com.mercadona.supplychain.lib.common.unit_converter.distance.M;
import com.mercadona.supplychain.lib.common.unit_converter.distance.MM;
import com.mercadona.supplychain.lib.common.unit_converter.exceptions.DifferentMeasureTypeException;
import com.mercadona.supplychain.lib.common.unit_converter.exceptions.ParseUnitOfMeasureException;
import com.mercadona.supplychain.lib.common.unit_converter.volume.CM3;
import com.mercadona.supplychain.lib.common.unit_converter.volume.DM3;
import com.mercadona.supplychain.lib.common.unit_converter.volume.M3;
import com.mercadona.supplychain.lib.common.unit_converter.volume.MM3;
import com.mercadona.supplychain.lib.common.unit_converter.weight.KG;
import com.mercadona.supplychain.lib.common.unit_converter.weight.LB;
import com.mercadona.supplychain.lib.common.unit_converter.weight.T;
import com.mercadona.supplychain.lib.common.unit_converter.weight_scaled.CG;
import com.mercadona.supplychain.lib.common.unit_converter.weight_scaled.DG;
import com.mercadona.supplychain.lib.common.unit_converter.weight_scaled.G;
import com.mercadona.supplychain.lib.common.unit_converter.weight_scaled.MG;
import lombok.experimental.UtilityClass;
import org.springframework.lang.NonNull;

@UtilityClass
public final class Converter {

    private static final String NOT_RECOGNIZED_MESSAGE = " NOT RECOGNIZED";

    @UtilityClass
    public static class Constants {
        public static final Double COEFFICIENT_LB_TO_KG = 2.205;
    }

    public static Double convert(@NonNull final UnitOfMeasure from, @NonNull final UnitOfMeasure to, @NonNull final Double value) {

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
            case "G", "Gramo", "GRAMO" -> com.mercadona.supplychain.lib.common.unit_converter.weight.G.getInstance();
            case "KG", "Kilogramo", "KILOGRAMO" -> KG.getInstance();
            case "T", "Tonelada", "TONELADA" -> T.getInstance();
            case "LB", "Libra", "LIBRA" -> LB.getInstance();
            default -> throw new ParseUnitOfMeasureException(unitOfMeasure + NOT_RECOGNIZED_MESSAGE);
        };
    }
}
