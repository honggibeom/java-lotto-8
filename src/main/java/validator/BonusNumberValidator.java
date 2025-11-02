package validator;

import lotto.error.ErrorMessage;
import lotto.error.IllegalArgumentExceptionFactory;
import lotto.model.LottoState;

public class BonusNumberValidator {
    public static void validate(String bonusNumber) {
        validateNumber(bonusNumber);
        int parsedBonusNumber = Integer.parseInt(bonusNumber);
        validateBetweenMinNumberThanMaxNumber(parsedBonusNumber);
    }

    private static void validateNumber(String bonusNumber) {
        if (!bonusNumber.matches("-?\\d+"))
            throw IllegalArgumentExceptionFactory.create(ErrorMessage.enterNumber.getErrorMessage());
    }

    private static void validateBetweenMinNumberThanMaxNumber(int bonusNumber) {
        if (bonusNumber < LottoState.MinNumber.getState() || bonusNumber > LottoState.MaxNumber.getState())
            throw IllegalArgumentExceptionFactory.create(ErrorMessage.enterBetweenMinNumberThanMaxNumber.getErrorMessage());
    }
}
