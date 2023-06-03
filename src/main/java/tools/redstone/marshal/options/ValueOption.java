package tools.redstone.marshal.options;

import tools.redstone.marshal.parsers.Parser;

public class ValueOption<TValue> extends Option {
    private final Parser<TValue> parser;

    private ValueOption(Parser<TValue> parser) {
        this.parser = parser;
    }

    public static <TValue> ValueOption<TValue> withParser(Parser<TValue> parser) {
        return new ValueOption<>(parser);
    }

    public TValue getValue() {
        throw new RuntimeException();
    }
}
