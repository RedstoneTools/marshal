package tools.redstone.marshal;

import com.mojang.brigadier.StringReader;
import tools.redstone.marshal.exceptions.ParseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class TokenReader {
    public static class ReaderOptional<TValue>
    {
        private final TokenReader tokenReader;
        private final StringReader stringReader;
        private final Optional<TValue> value;
        private final ArrayList<Parsable<?, ?>> usedParsers;

        private ReaderOptional(TokenReader tokenReader, StringReader stringReader, Optional<TValue> value, List<Parsable<?, ?>> usedParsers) {
            this.tokenReader = tokenReader;
            this.stringReader = stringReader;
            this.value = value;
            this.usedParsers = new ArrayList<>(usedParsers);
        }

        private ReaderOptional(TokenReader tokenReader) {
            this(tokenReader, tokenReader.stringReader, Optional.empty(), Collections.emptyList());
        }

        @SuppressWarnings("unchecked")
        public ReaderOptional<TValue> orElseRead(Parsable<TValue, ?> parser) {
            if (value.isPresent()) {
                return this;
            }

            if (usedParsers.contains(parser)) {
                // TODO: Come up with a better error message and consider maybe not throwing this error and just returning instead
                throw new RuntimeException("Duplicate parser");
            }

            usedParsers.add(parser);

            try {
                try {
                    var tp = (Parsable<TValue, StringReader>) parser;
                    return createCopyWithValue(Optional.of(tp.parse(stringReader)));
                } catch (ClassCastException ignored) {
                }

                try {
                    var p = (Parsable<TValue, TokenReader>) parser;
                    return createCopyWithValue(Optional.of(p.parse(tokenReader)));
                } catch (ClassCastException ignored) {
                }
            } catch (ParseException ignored) {
            }

            return createCopyWithValue(Optional.empty());
        }

        public <TNewValue> ReaderOptional<TNewValue> map(Function<TValue, TNewValue> mapper) {
            return createCopyWithValue(value.map(mapper));
        }

        private <TNewValue> ReaderOptional<TNewValue> createCopyWithValue(Optional<TNewValue> value) {
            return new ReaderOptional<>(tokenReader, stringReader, value, usedParsers);
        }

        public TValue orElse(TValue other) {
            return value.orElse(other);
        }

        public TValue orElseThrow(Parsable<?, ?> parserToThrowIn) {
            return value.orElseThrow(() -> tokenReader.failure(parserToThrowIn, usedParsers));
        }

        public boolean isEmpty() {
            return value.isEmpty();
        }

        public boolean isPresent() {
            return value.isPresent();
        }

        public Optional<TValue> toOptional() {
            return value;
        }
    }

    private final StringReader stringReader;

    public TokenReader(StringReader stringReader) {
        this.stringReader = stringReader;
    }

    public <TValue> ReaderOptional<TValue> read(Parsable<TValue, ?> parser) {
        return new ReaderOptional<TValue>(this)
                .orElseRead(parser);
    }

    public ParseException failure(Parsable<?, ?> parserTheFailureWasCausedIn, List<Parsable<?, ?>> subParsers) {
        return ParseException.Builder
                .whileTryingToParse(parserTheFailureWasCausedIn)
                .addSubParsers(subParsers)
                .got(stringReader.getRemaining())
                .build();
    }
}
