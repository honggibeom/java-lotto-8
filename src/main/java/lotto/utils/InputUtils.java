package lotto.utils;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class InputUtils {
    public static String askAndValidate(Supplier<String> inputSupplier, Consumer<String> validator) {
        while (true) {
            try {
                String input = inputSupplier.get();
                validator.accept(input);
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
