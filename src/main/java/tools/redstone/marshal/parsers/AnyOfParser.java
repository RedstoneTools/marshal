package tools.redstone.marshal.parsers;

import tools.redstone.marshal.Parsable;
import tools.redstone.marshal.TokenReader;
import tools.redstone.marshal.exceptions.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnyOfParser<TValue> extends Parser<TValue> {
    private final List<Parsable<TValue, ?>> parsers;

    private AnyOfParser(List<Parsable<TValue, ?>> parsers) {
        this.parsers = parsers;
    }

    public static <TValue> AnyOfParser<TValue> anyOf(List<Parsable<TValue, ?>> parsers) {
        return new AnyOfParser<>(parsers);
    }

    @Override
    public String getGenericDisplayName() {
        var sb = new StringBuilder();

        for (int i = 0; i < parsers.size(); i++) {
            var parser = parsers.get(i);

            if (parsers.size() > 1 && i == parsers.size() - 1) {
                sb.append("or ");
            }

            sb.append(parser.getGenericDisplayName());
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);

        return sb.toString();
    }

    @Override
    public String getSingularDisplayName() {
        var sb = new StringBuilder();

        for (int i = 0; i < parsers.size(); i++) {
            var parser = parsers.get(i);

            if (parsers.size() > 1 && i == parsers.size() - 1) {
                sb.append("or ");
            }

            sb.append(parser.getSingularDisplayName());
            sb.append(", ");
        }

        return sb.toString();
    }

    @Override
    public String getPluralDisplayName() {
        var sb = new StringBuilder();

        sb.append("instances of ");
        for (int i = 0; i < parsers.size(); i++) {
            var parser = parsers.get(i);

            if (parsers.size() > 1 && i == parsers.size() - 1) {
                sb.append("or ");
            }

            sb.append(parser.getGenericDisplayName());
            sb.append(", ");
        }

        return sb.toString();
    }

    @Override
    public TValue parse(TokenReader tokenReader) throws ParseException {
        for (var parser : parsers) {
            var value = tokenReader.read(parser);

            if (value.isPresent()) {
                return value.orElseThrow(this);
            }
        }

        throw tokenReader.failure(this, List.copyOf(parsers));
    }
}
