package tools.redstone.marshal.helpers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tools.redstone.marshal.exceptions.InvalidOptionNameException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OptionHelper {
    public static void validateShortName(String shortOptionName) {
        if (!isValidShortName(shortOptionName)) {
            throw new InvalidOptionNameException(shortOptionName);
        }
    }

    public static boolean isValidShortName(String shortOptionName) {
        return shortOptionName != null
            && shortOptionName.matches("[A-Za-z]");
    }

    public static void validateLongName(String longOptionName) {
        if (!isValidLongName(longOptionName)) {
            throw new InvalidOptionNameException(longOptionName);
        }
    }

    public static boolean isValidLongName(String longOptionName) {
        return longOptionName != null
            && longOptionName.matches("[a-z]+(-[a-z]+)*");
    }
}
