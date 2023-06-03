package tools.redstone.marshal.parsers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tools.redstone.marshal.Parsable;
import tools.redstone.marshal.TokenReader;
import tools.redstone.marshal.exceptions.ParseException;
import tools.redstone.marshal.helpers.IntegerHelper;

import java.util.ArrayList;
import java.util.List;

import static tools.redstone.marshal.helpers.IntegerHelper.characterFromDigit;
import static tools.redstone.marshal.parsers.AnyOfParser.anyOf;
import static tools.redstone.marshal.tokens.ExactCharacterTokenParser.character;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DigitParser extends Parser<Integer> {
    private static final DigitParser INSTANCE = new DigitParser();

    private static final List<Parsable<Integer, ?>> DIGIT_TOKENS = new ArrayList<>() {{
        for (int i = 0; i <= 9; i++) {
            add(character(characterFromDigit(i), i));
        }
    }};

    public static DigitParser digit() {
        return INSTANCE;
    }

    @Override
    public String getGenericDisplayName() {
        return "a digit";
    }

    @Override
    public String getSingularDisplayName() {
        return "digit";
    }

    @Override
    public String getPluralDisplayName() {
        return "digits";
    }

    @Override
    public Integer parse(TokenReader tokenReader) throws ParseException {
        return tokenReader.read(anyOf(DIGIT_TOKENS))
                .orElseThrow(this);
    }
}
