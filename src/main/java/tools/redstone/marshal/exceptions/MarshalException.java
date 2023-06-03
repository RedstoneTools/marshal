package tools.redstone.marshal.exceptions;

public abstract class MarshalException extends RuntimeException {
    protected MarshalException() {
        super();
    }

    protected MarshalException(String message) {
        super(message);
    }
}
