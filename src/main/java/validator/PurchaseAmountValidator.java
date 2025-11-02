package validator;

import lotto.error.ErrorMessage;
import lotto.error.IllegalArgumentExceptionFactory;
import lotto.model.LottoState;

public class PurchaseAmountValidator {
    public static void validate(String purchaseAmount) {
        validateNumber(purchaseAmount);
        int parsedPurchaseAmount = Integer.parseInt(purchaseAmount);
        validateGreaterThanZero(parsedPurchaseAmount);
        validateMultipleOfThousand(parsedPurchaseAmount);
    }

    private static void validateNumber(String purchaseAmount) {
        if (!purchaseAmount.matches("-?\\d+"))
            throw IllegalArgumentExceptionFactory.create(ErrorMessage.enterNumber.getErrorMessage());
    }

    private static void validateGreaterThanZero(int purchaseAmount) {
        if (purchaseAmount <= 0)
            throw IllegalArgumentExceptionFactory.create(ErrorMessage.enterNumberGreaterThanZero.getErrorMessage());
    }

    private static void validateMultipleOfThousand(int purchaseAmount) {
        if (purchaseAmount % LottoState.price.getState() != 0)
            throw IllegalArgumentExceptionFactory.create(ErrorMessage.enterNumberMultipleOfThousand.getErrorMessage());
    }
}
