package tools.redstone.marshal.parsers;

import lombok.NonNull;
import tools.redstone.marshal.TokenReader;
import tools.redstone.marshal.tokens.ExactStringTokenParser;
import tools.redstone.marshal.tokens.TokenParser;

public class BooleanParser extends Parser<@NonNull Boolean> {
    private static final TokenParser<@NonNull Boolean> trueToken = new ExactStringTokenParser<>("true", true);
    private static final TokenParser<@NonNull Boolean> falseToken = new ExactStringTokenParser<>("false", false);
    private static final TokenParser<@NonNull Boolean> yesToken = new ExactStringTokenParser<>("yes", true);
    private static final TokenParser<@NonNull Boolean> noToken = new ExactStringTokenParser<>("no", false);

    @Override
    public @NonNull String getGenericDisplayName() {
        return "a boolean";
    }

    @Override
    public @NonNull String getSingularDisplayName() {
        return "boolean";
    }

    @Override
    public @NonNull String getPluralDisplayName() {
        return "booleans";
    }

    @Override
    public @NonNull Boolean parse(@NonNull TokenReader tokenReader) {
        return tokenReader.read(trueToken)
                .orElseRead(falseToken)
                .orElseRead(yesToken)
                .orElseRead(noToken)
                .orElseThrow(this);
    }
}
