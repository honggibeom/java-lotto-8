package validator;

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
            throw new IllegalArgumentException("숫자를 입력해주세요.");
    }

    private static void validateGreaterThanZero(int purchaseAmount) {
        if (purchaseAmount <= 0)
            throw new IllegalArgumentException("구입한 로또 가격은 0보다 커야합니다.");
    }

    private static void validateMultipleOfThousand(int purchaseAmount) {
        if (purchaseAmount % LottoState.price.getState() != 0)
            throw new IllegalArgumentException("로또는 1000원단위입니다.");
    }
}
