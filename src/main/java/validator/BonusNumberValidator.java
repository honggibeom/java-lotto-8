package validator;

public class BonusNumberValidator {
    private static final Integer MaxNumber = 45;
    private static final Integer MinNumber = 1;

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
        if (bonusNumber < MinNumber || bonusNumber >= MaxNumber)
            throw new IllegalArgumentException("보너스 숫자는 1~45사이의 숫자를 입력해야합니다.");
    }
}
