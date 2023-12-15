package exceptions;

public class ExistingTaskException extends Exception {

    public ExistingTaskException(String message){
        super(message);
    }
}
