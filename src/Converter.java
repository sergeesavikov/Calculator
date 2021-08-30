public class Converter {
    public static String fromArabicToRoman(Integer value) {
        StringBuilder sb = new StringBuilder();
        Integer times;
        String[] romans = new String[] { "I", "IV", "V", "IX", "X", "XL", "L",
                "XC", "C", "CD", "D", "CM", "M" };
        Integer[] ints = new Integer[] { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500,
                900, 1000 };
        for (int i = ints.length - 1; i >= 0; i--) {
            times = value / ints[i];
            value %= ints[i];
            while (times > 0) {
                sb.append(romans[i]);
                times--;
            }
        }
        return sb.toString();
    }

    public static Integer fromRomanToArabic(String value) {
        String[] numerals = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {100, 90, 50, 40, 10, 9, 5, 4, 1};

        int result = 0;
        for (int i = 0; i < numerals.length; i++) {
            while (value.startsWith(numerals[i])) {
                result += values[i];
                value = value.substring(numerals[i].length());
            }
        }
        if(result <= 0 | result > 100) {
            throw new IllegalArgumentException("Вы ввели неверное римское число");
        }
        return result;
    }
}
