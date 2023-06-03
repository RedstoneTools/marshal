package tools.redstone.marshal.tokens;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import tools.redstone.marshal.exceptions.ParseException;

public class ExactCharacterTokenParser<TValue> extends TokenParser<TValue> {
    private final Character character;
    private final TValue value;

    private ExactCharacterTokenParser(Character character, TValue value) {
        this.character = character;
        this.value = value;
    }

    public static ExactCharacterTokenParser<Character> character(Character character) {
        return character(character, character);
    }

    public static <TValue> ExactCharacterTokenParser<TValue> character(Character character, TValue value) {
        return new ExactCharacterTokenParser<>(character, value);
    }

    @Override
    public String getGenericDisplayName() {
        return "\"" + character + "\"";
    }

    @Override
    public String getSingularDisplayName() {
        return getGenericDisplayName() + " character";
    }

    @Override
    public String getPluralDisplayName() {
        return getGenericDisplayName() + " characters";
    }

    @Override
    public TValue parse(StringReader stringReader) throws ParseException {
        var cursor = stringReader.getCursor();

        try {
            stringReader.expect(character);
        } catch (CommandSyntaxException e) {
            stringReader.setCursor(cursor);

            throw new ParseException(e.getMessage());
        }

        return value;
    }
}
