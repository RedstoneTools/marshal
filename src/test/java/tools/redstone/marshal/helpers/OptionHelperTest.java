package tools.redstone.marshal.helpers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import tools.redstone.marshal.exceptions.InvalidOptionNameException;

import static org.junit.jupiter.api.Assertions.*;

class OptionHelperTest {
    static String[] invalidShortNames() {
        return new String[] {
            null,
            "",
            "ABC",
            "_",
            "ab",
            "124",
            "@",
            "abc def",
            "def_a",
            "x1",
            "a-",
            "-",
            "-a",
            "a--a",
        };
    }

    static String[] validShortNames() {
        return new String[] {
            "a",
            "b",
            "c",
            "D",
            "E",
            "F"
        };
    }

    static String[] invalidLongNames() {
        return new String[] {
            null,
            "",
            "ABC",
            "_",
            "124",
            "@",
            "abc def",
            "def_a",
            "x1",
            "a-",
            "-",
            "-a",
            "a--a",
        };
    }

    static String[] validLongNames() {
        return new String[] {
            "abc-def",
            "abc",
            "b",
            "a-h-e-f"
        };
    }

    @ParameterizedTest
    @MethodSource("invalidShortNames")
    void validateShortName_invalidName_throws(String invalidShortName) {
        assertThrows(InvalidOptionNameException.class, () -> OptionHelper.validateShortName(invalidShortName));
    }

    @ParameterizedTest
    @MethodSource("validShortNames")
    void validateShortName_validName_doesNotThrow(String validShortName) {
        assertDoesNotThrow(() -> OptionHelper.validateShortName(validShortName));
    }

    @ParameterizedTest
    @MethodSource("invalidShortNames")
    void isValidShortName_invalidName_returnsFalse(String invalidShortName) {
        assertFalse(OptionHelper.isValidShortName(invalidShortName));
    }

    @ParameterizedTest
    @MethodSource("validShortNames")
    void isValidShortName_validName_returnsTrue(String validShortName) {
        assertTrue(OptionHelper.isValidShortName(validShortName));
    }

    @ParameterizedTest
    @MethodSource("invalidLongNames")
    void validateLongName_invalidName_throws(String invalidLongName) {
        assertThrows(InvalidOptionNameException.class, () -> OptionHelper.validateLongName(invalidLongName));
    }

    @ParameterizedTest
    @MethodSource("validLongNames")
    void validateLongName_validName_doesNotThrow(String validLongName) {
        assertDoesNotThrow(() -> OptionHelper.validateLongName(validLongName));
    }

    @ParameterizedTest
    @MethodSource("invalidLongNames")
    void isValidLongName_invalidName_returnsFalse(String invalidLongName) {
        assertFalse(OptionHelper.isValidLongName(invalidLongName));
    }

    @ParameterizedTest
    @MethodSource("validLongNames")
    void isValidLongName_validName_returnsTrue(String validLongName) {
        assertTrue(OptionHelper.isValidLongName(validLongName));
    }
}
