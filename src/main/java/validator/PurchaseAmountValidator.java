package validator;

public class PurchaseAmountValidator {
    private static final Integer LottoPrice = 1000;

    public static void validate(String PurchaseAmount) {
        validateNumber(PurchaseAmount);
        int parsedPurchaseAmount = Integer.parseInt(PurchaseAmount);
        validateGreaterThanZero(parsedPurchaseAmount);
        validateMultipleOfThousand(parsedPurchaseAmount);
    }

    private static void validateNumber(String PurchaseAmount) {
        if (PurchaseAmount.matches("^[0-9]"))
            throw new IllegalArgumentException("숫자를 입력해주세요.");
    }

    private static void validateGreaterThanZero(int purchaseAmount) {
        if (purchaseAmount <= 0)
            throw new IllegalArgumentException("구입한 로또 가격은 0보다 커야합니다.");
    }

    private static void validateMultipleOfThousand(int purchaseAmount) {
        if (purchaseAmount % LottoPrice != 0)
            throw new IllegalArgumentException("로또는 1000원단위입니다.");
    }
}
