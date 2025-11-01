package validator;

import lotto.model.LottoState;

import java.util.Arrays;

public class WinningNumberValidator {
    public static void validate(String WinningNumber) {
        validateOnlyNumbersAndCommas(WinningNumber);
        validateGreaterThanMinNumberAndSmallerThanMaxNumber(WinningNumber);
    }

    private static void validateOnlyNumbersAndCommas(String WinningNumber) {
        if (!WinningNumber.matches("^[0-9]+(,[0-9]+){5}$"))
            throw new IllegalArgumentException("숫자6개를 컴마로 구분하여 입력해주세요");
    }

    private static void validateGreaterThanMinNumberAndSmallerThanMaxNumber(String WinningNumber) {
        boolean isGreaterThanZeroAndSmallerThan46 = Arrays.stream(WinningNumber.split(","))
                .map(Integer::parseInt)
                .anyMatch(num -> num < LottoState.MinNumber.getState() || num > LottoState.MaxNumber.getState());
        if (isGreaterThanZeroAndSmallerThan46)
            throw new IllegalArgumentException("보너스 숫자는 1~45사이의 숫자를 입력해야합니다.");
    }
}
