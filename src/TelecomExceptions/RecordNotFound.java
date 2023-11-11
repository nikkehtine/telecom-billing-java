package TelecomExceptions;

public class RecordNotFound extends RuntimeException {
    public RecordNotFound(String number) {
        super(
                String.format("No record for %s", number));
    }
}
