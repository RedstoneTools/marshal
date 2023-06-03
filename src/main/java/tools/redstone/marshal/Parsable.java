package tools.redstone.marshal;

import tools.redstone.marshal.exceptions.ParseException;

public interface Parsable<TValue, TReader> extends Displayable {
    TValue parse(TReader reader) throws ParseException;
}
