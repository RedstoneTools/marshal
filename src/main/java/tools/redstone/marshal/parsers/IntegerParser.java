package tools.redstone.marshal.parsers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tools.redstone.marshal.TokenReader;
import tools.redstone.marshal.exceptions.ParseException;
import tools.redstone.marshal.helpers.IntegerHelper;

import static tools.redstone.marshal.parsers.DigitParser.digit;
import static tools.redstone.marshal.parsers.OneOrMoreParser.oneOrMore;
import static tools.redstone.marshal.parsers.SignParser.sign;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IntegerParser extends Parser<Integer> {
    private static final IntegerParser INSTANCE = new IntegerParser();

    public static IntegerParser integer() {
        return INSTANCE;
    }

    @Override
    public String getGenericDisplayName() {
        return "an integer";
    }

    @Override
    public String getSingularDisplayName() {
        return "integer";
    }

    @Override
    public String getPluralDisplayName() {
        return "integers";
    }

    @Override
    public Integer parse(TokenReader tokenReader) throws ParseException {
        var sign = tokenReader.read(sign())
                .orElse(1);

        var value = tokenReader.read(oneOrMore(digit()))
                .map(IntegerHelper::integerFromDigits)
                .orElseThrow(this);

        return sign * value;
    }
}
