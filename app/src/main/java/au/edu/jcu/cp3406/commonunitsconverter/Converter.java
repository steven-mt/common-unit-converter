package au.edu.jcu.cp3406.commonunitsconverter;

public final class Converter {
    private static final double FT_IN_CM = 30.48;
    private static final double IN_IN_CM = 2.54;
    private static final double KG_IN_LB = 2.20462262185;
    private static final double MI_IN_KM = 1.609344;

    private Converter() {
    }

    public static double getCmToFtAndIn(double value, String finalUnit, boolean isInRemainder) {
        finalUnit = finalUnit.toLowerCase();
        if (finalUnit.equals("ft")) {
            value /= FT_IN_CM;
        } else if (finalUnit.equals("in")) {
            if (!isInRemainder) {
                value /= IN_IN_CM;
            } else {
                value = (value % FT_IN_CM) / IN_IN_CM;
            }
        }
        return value;
    }

    public static double getFtOrInToCm(double value, String unitToConvert) {
        switch (unitToConvert) {
            case "ft":
                value *= FT_IN_CM;
                break;
            case "in":
                value *= IN_IN_CM;
                break;
        }
        return value;
    }

    public static double getCelsiusToFahrenheit(double value) {
        return (value * 9 / 5) + 32;
    }

    public static double getFahrenheitToCelsius(double value) {
        return (value - 32) * 5 / 9;
    }

    public static double getKgToLb(double value) {
        return value * KG_IN_LB;
    }

    public static double getLbToKg(double value) {
        return value / KG_IN_LB;
    }

    public static double getKmToMi(double value) {
        return value / MI_IN_KM;
    }

    public static double getMiToKm(double value) {
        return value * MI_IN_KM;
    }
}
