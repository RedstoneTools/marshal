package tools.redstone.marshal.helpers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IntegerHelper {
    public static int integerFromDigits(List<Integer> digits) {
        var result = 0;

        for (int i = 0; i < digits.size(); i++) {
            result += digits.get(i);

            if (i < digits.size() - 1) {
                result *= 10;
            }
        }

        return result;
    }

    public static int digitFromCharacter(char character) {
        return character - '0';
    }

    public static char characterFromDigit(int digit) {
        return (char)('0' + digit);
    }
}
