package tools.redstone.marshal.tokens;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import tools.redstone.marshal.exceptions.ParseException;

public class ExactStringTokenParser<TValue> extends TokenParser<TValue> {
    private final String literal;
    private final TValue value;

    public ExactStringTokenParser(String literal, TValue value) {
        this.literal = literal;
        this.value = value;
    }

    @Override
    public String getGenericDisplayName() {
        return "\"" + literal + "\"";
    }

    @Override
    public String getSingularDisplayName() {
        return getGenericDisplayName() + " value";
    }

    @Override
    public String getPluralDisplayName() {
        return getGenericDisplayName() + " values";
    }

    @Override
    public TValue parse(StringReader stringReader) throws ParseException {
        var cursor = stringReader.getCursor();

        for (var c : literal.toCharArray()) {
            try {
                stringReader.expect(c);
            } catch (CommandSyntaxException e) {
                stringReader.setCursor(cursor);

                throw new ParseException(e.getMessage());
            }
        }

        return value;
    }
}
