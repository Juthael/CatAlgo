package representation.exceptions;

public class RepresentationException extends Exception {

	private static final long serialVersionUID = 4143536725419573022L;

	public RepresentationException() {
	}

	public RepresentationException(String arg0) {
		super(arg0);
	}

	public RepresentationException(Throwable arg0) {
		super(arg0);
	}

	public RepresentationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public RepresentationException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
