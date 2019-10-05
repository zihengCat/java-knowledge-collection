package io.ziheng.queue;
public class QueueIsEmptyException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public QueueIsEmptyException() {
        super();
    }
    public QueueIsEmptyException(String message) {
        super(message);
    }
    public QueueIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
/* EOF */
