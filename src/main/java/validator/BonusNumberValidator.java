package validator;

import lotto.model.LottoState;

public class BonusNumberValidator {
    public static void validate(String BonusNumber) {
        validateNumber(BonusNumber);
        int parsedBonusNumber = Integer.parseInt(BonusNumber);
        validateBetweenMinNumberThanMaxNumber(parsedBonusNumber);
    }

    private static void validateNumber(String BonusNumber) {
        if (BonusNumber.matches("[^0-9]"))
            throw new IllegalArgumentException("숫자를 입력해주세요.");
    }

    private static void validateBetweenMinNumberThanMaxNumber(int bonusNumber) {
        if (bonusNumber < LottoState.MinNumber.getState() || bonusNumber > LottoState.MaxNumber.getState())
            throw new IllegalArgumentException("보너스 숫자는 1~45사이의 숫자를 입력해야합니다.");
    }
}
