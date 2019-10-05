package io.ziheng.stack;
public class StackIsEmptyException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public StackIsEmptyException() {
        super();
    }
    public StackIsEmptyException(String message) {
        super(message);
    }
    public StackIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
/* EOF */
