package tools.redstone.marshal;

import com.mojang.brigadier.StringReader;

import static tools.redstone.marshal.parsers.IntegerParser.integer;

public class Program {
    public static void main(String[] args) {
        var reader = new TokenReader(new StringReader("@512g51a6"));

        var i = reader.read(integer())
                .orElseThrow(null);
    }
}
