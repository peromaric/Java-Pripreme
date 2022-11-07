package hr.java.vjezbe.iznimke;

/**
 * Greska koja se baca kad je vise najmladih studenata
 */
public class PostojiViseNajmladihStudenataException extends RuntimeException{
    public PostojiViseNajmladihStudenataException() {
    }

    public PostojiViseNajmladihStudenataException(String message) {
        super(message);
    }

    public PostojiViseNajmladihStudenataException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostojiViseNajmladihStudenataException(Throwable cause) {
        super(cause);
    }

    public PostojiViseNajmladihStudenataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
