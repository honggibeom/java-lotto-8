package validator;

import lotto.model.LottoState;

import java.util.Arrays;

public class WinningNumberValidator {
    public static void validate(String winningNumber) {
        validateOnlyNumbersAndCommas(winningNumber);
        validateGreaterThanMinNumberAndSmallerThanMaxNumber(winningNumber);
    }

    private static void validateOnlyNumbersAndCommas(String winningNumber) {
        if (!winningNumber.matches("^[0-9]+(,[0-9]+){5}$"))
            throw new IllegalArgumentException("숫자6개를 컴마로 구분하여 입력해주세요");
    }

    private static void validateGreaterThanMinNumberAndSmallerThanMaxNumber(String winningNumber) {
        boolean isGreaterThanZeroAndSmallerThan46 = Arrays.stream(winningNumber.split(","))
                .map(Integer::parseInt)
                .anyMatch(num -> num < LottoState.MinNumber.getState() || num > LottoState.MaxNumber.getState());
        if (isGreaterThanZeroAndSmallerThan46)
            throw new IllegalArgumentException("보너스 숫자는 1~45사이의 숫자를 입력해야합니다.");
    }
}
