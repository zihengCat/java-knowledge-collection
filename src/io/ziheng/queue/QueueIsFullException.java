package io.ziheng.queue;
public class QueueIsFullException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public QueueIsFullException() {
        super();
    }
    public QueueIsFullException(String message) {
        super(message);
    }
    public QueueIsFullException(String message, Throwable cause) {
        super(message, cause);
    }
}
/* EOF */
