package tools.redstone.marshal.exceptions;

public class InvalidOptionNameException extends MarshalException {
    public InvalidOptionNameException(String name) {
        super("Invalid option name '" + name + "'.");
    }
}
