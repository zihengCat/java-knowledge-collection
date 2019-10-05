package io.ziheng.stack;
public class StackIsFullException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public StackIsFullException() {
        super();
    }
    public StackIsFullException(String message) {
        super(message);
    }
    public StackIsFullException(String message, Throwable cause) {
        super(message, cause);
    }
}
/* EOF */
