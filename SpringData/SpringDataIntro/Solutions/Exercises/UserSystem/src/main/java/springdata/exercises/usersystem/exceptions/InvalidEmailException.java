package springdata.exercises.usersystem.exceptions;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException() {
        super("Invalid email!");
    }

    public InvalidEmailException(String message) {
        super(message);
    }

    public InvalidEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEmailException(Throwable cause) {
        super(cause);
    }
}
