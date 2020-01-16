package grammarModel.exceptions;

public class TreeGenException extends Exception {

	private static final long serialVersionUID = 1985096602594847603L;

	public TreeGenException() {
	}

	public TreeGenException(String message) {
		super(message);
	}

	public TreeGenException(Throwable cause) {
		super(cause);
	}

	public TreeGenException(String message, Throwable cause) {
		super(message, cause);
	}

	public TreeGenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
