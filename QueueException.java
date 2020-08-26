package Exercise1;

class QueueException extends RuntimeException {
    QueueException(String s) {
        super("Attempted to apply " + s + " ");
    }
}
