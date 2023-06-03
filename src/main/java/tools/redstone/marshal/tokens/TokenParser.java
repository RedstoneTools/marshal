package tools.redstone.marshal.tokens;

import com.mojang.brigadier.StringReader;
import tools.redstone.marshal.Parsable;

public abstract class TokenParser<TValue> implements Parsable<TValue, StringReader> {
}
