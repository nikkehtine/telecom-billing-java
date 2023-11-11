package TelecomExceptions;

public class RecordAlreadyExists extends RuntimeException {
    public RecordAlreadyExists(String number) {
        super(
            String.format("%s has already been recorded", number)
        );
    }
}
