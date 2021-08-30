import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculate {
    public static void calculate(String line) {
        line = line.toUpperCase();
        boolean isArabic = match(line, "^\\d{1,2}[\\s-+/*]\\d{1,2}$");
        boolean isRoman = match(line, "^[VIX]{1,4}[\\s-+/*][VIX]{1,4}$");
        String[] operands = line.split("\\W");
        int result = 0;
        if (isArabic) {
            int operand1 = Integer.parseInt(operands[0]);
            int operand2 = Integer.parseInt(operands[1]);
            if ((operand1 >= 0 && operand1 < 11) && (operand2 >= 0 && operand2 < 11)) {
                result = executeOperation(getSignOperation(line), operand1, operand2);
                System.out.println(result);
            } else {
                throw new IllegalArgumentException("Числа не входят в заданный диапазон!");
            }
        }
        if (isRoman) {
            int operand1 = Converter.fromRomanToArabic(operands[0]);
            int operand2 = Converter.fromRomanToArabic(operands[1]);
            if ((operand1 >= 0 && operand1 < 11) && (operand2 >= 0 && operand2 < 11)) {
                result = executeOperation(getSignOperation(line), operand1, operand2);
                if (result > 0) {
                    System.out.println(Converter.fromArabicToRoman(result));
                } else {
                    throw new IllegalArgumentException("Результат вычисления в римских числах не может быть отрицательным или равным нулю!");
                }
            } else {
                throw new IllegalArgumentException("Числа не входят в заданный диапазон!");
            }
        }
        if (!isArabic && !isRoman)
            throw new IllegalArgumentException("Неправильный формат выражения!");
    }

    private static boolean match(String line, String mask) {
        Pattern pattern = Pattern.compile(mask);
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }

    private static String getSignOperation(String line) {
        Pattern pattern = Pattern.compile("\\b[\\s-+/*]");
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        return line.substring(matcher.start(), matcher.end());
    }

    private static int executeOperation(String operation, int operand1, int operand2) {
        if (operation.equals("+")) return operand1 + operand2;
        if (operation.equals("-")) return operand1 - operand2;
        if (operation.equals("/")) return operand1 / operand2;
        if (operation.equals("*")) return operand1 * operand2;
        return 0;
    }
}
