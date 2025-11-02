package validator;

import lotto.error.IllegalArgumentExceptionFactory;
import lotto.model.LottoState;
import lotto.error.ErrorMessage;
import java.util.Arrays;


public class WinningNumberValidator {
    public static void validate(String winningNumber) {
        validateOnlyNumbersAndCommas(winningNumber);
        validateGreaterThanMinNumberAndSmallerThanMaxNumber(winningNumber);
    }

    private static void validateOnlyNumbersAndCommas(String winningNumber) {
        String regex = "^[0-9]+(,[0-9]+){"+(LottoState.numberCount.getState()-1)+"}$";
        if (!winningNumber.matches(regex))
            throw IllegalArgumentExceptionFactory.create(ErrorMessage.enterOnlyNumbersAndCommas.getErrorMessage());
    }

    private static void validateGreaterThanMinNumberAndSmallerThanMaxNumber(String winningNumber) {
        boolean isGreaterThanZeroAndSmallerThan46 = Arrays.stream(winningNumber.split(","))
                .map(Integer::parseInt)
                .anyMatch(num -> num < LottoState.MinNumber.getState() || num > LottoState.MaxNumber.getState());
        if (isGreaterThanZeroAndSmallerThan46)
            throw IllegalArgumentExceptionFactory.create(ErrorMessage.enterBetweenMinNumberThanMaxNumber.getErrorMessage());
    }
}
