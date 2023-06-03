package tools.redstone.marshal;

public abstract class Command<TOptions> {
    public abstract String getName();
    public abstract Class<TOptions> getOptionsClass();
    public abstract void execute(TOptions options);
}
