package tools.redstone.marshal.parsers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tools.redstone.marshal.Parsable;
import tools.redstone.marshal.TokenReader;
import tools.redstone.marshal.exceptions.ParseException;
import tools.redstone.marshal.tokens.ExactCharacterTokenParser;
import tools.redstone.marshal.tokens.ExactStringTokenParser;

import static tools.redstone.marshal.tokens.ExactCharacterTokenParser.character;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignParser extends Parser<Integer> {
    private static final SignParser INSTANCE = new SignParser();

    private static final Parsable<Integer, ?> NEGATIVE_TOKEN = character('-', -1);
    private static final Parsable<Integer, ?> POSITIVE_TOKEN = character('+', 1);

    public static SignParser sign() {
        return INSTANCE;
    }

    @Override
    public String getGenericDisplayName() {
        return "a sign";
    }

    @Override
    public String getSingularDisplayName() {
        return "sign";
    }

    @Override
    public String getPluralDisplayName() {
        return "signs";
    }

    @Override
    public Integer parse(TokenReader tokenReader) throws ParseException {
        return tokenReader.read(NEGATIVE_TOKEN)
                .orElseRead(POSITIVE_TOKEN)
                .orElseThrow(this);
    }
}
