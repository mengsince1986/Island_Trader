package exceptions;

public class InsufficientFundsException extends IllegalStateException {

	public InsufficientFundsException() {
	}

	public InsufficientFundsException(String message) {
		super(message);
	}
}