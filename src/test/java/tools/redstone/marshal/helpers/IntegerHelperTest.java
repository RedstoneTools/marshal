package tools.redstone.marshal.helpers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tools.redstone.marshal.helpers.IntegerHelper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntegerHelperTest {
    public static List<Object[]> validDigitsToIntegers() {
        return List.of(
                new Object[] { List.of(0), 0 },
                new Object[] { List.of(5), 5 },
                new Object[] { List.of(5, 2), 52 },
                new Object[] { List.of(1, 6, 5), 165 },
                new Object[] { List.of(0, 0, 5, 3), 53 }
        );
    }

    @ParameterizedTest
    @MethodSource("validDigitsToIntegers")
    void integerFromDigits(List<Integer> digits, Integer expected) {
        assertEquals(expected, IntegerHelper.integerFromDigits(digits));
    }
}