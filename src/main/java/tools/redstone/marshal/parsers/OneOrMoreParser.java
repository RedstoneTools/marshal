package tools.redstone.marshal.parsers;

import tools.redstone.marshal.Parsable;
import tools.redstone.marshal.TokenReader;
import tools.redstone.marshal.exceptions.ParseException;

import java.util.ArrayList;
import java.util.List;

public class OneOrMoreParser<TValue> extends Parser<List<TValue>> {
    private final Parsable<TValue, ?> parser;

    private OneOrMoreParser(Parsable<TValue, ?> parser) {
        this.parser = parser;
    }

    public static <TValue> OneOrMoreParser<TValue> oneOrMore(Parsable<TValue, ?> parser) {
        return new OneOrMoreParser<>(parser);
    }

    @Override
    public String getGenericDisplayName() {
        return "a string of " + parser.getPluralDisplayName();
    }

    @Override
    public String getSingularDisplayName() {
        return "string of " + parser.getPluralDisplayName();
    }

    @Override
    public String getPluralDisplayName() {
        return "strings of " + parser.getPluralDisplayName();
    }

    @Override
    public List<TValue> parse(TokenReader tokenReader) throws ParseException {
        var values = new ArrayList<>(List.of(tokenReader.read(parser).orElseThrow(this)));

        while (true) {
            var instance = tokenReader.read(parser);

            if (instance.isEmpty()) {
                break;
            }

            values.add(instance.orElseThrow(this));
        }

        return values;
    }
}
