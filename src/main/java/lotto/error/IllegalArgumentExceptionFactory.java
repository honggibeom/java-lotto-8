package lotto.error;

public class IllegalArgumentExceptionFactory {
    public static IllegalArgumentException create(final String errorMessage) {
        return new IllegalArgumentException("[ERROR] "+errorMessage);
    }
}
