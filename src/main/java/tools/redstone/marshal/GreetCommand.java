package tools.redstone.marshal;

import lombok.NonNull;
import tools.redstone.marshal.options.ValueOption;

public class GreetCommand extends Command<GreetCommand.Options> {
    public static class Options {
        public final ValueOption<String> name = ValueOption
            .withParser(null);
    }

    @Override
    public String getName() {
        return "greet";
    }

    @Override
    public Class<Options> getOptionsClass() {
        return Options.class;
    }

    @Override
    public void execute(Options options) {
        System.out.println("Hello " + options.name.getValue() + "!");
    }
}
