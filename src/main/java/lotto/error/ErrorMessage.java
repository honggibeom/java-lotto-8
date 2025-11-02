package lotto.error;

import lotto.model.LottoState;

public enum ErrorMessage {
    enterNumber("숫자를 입력해주세요."),
    enterNumberMultipleOfThousand("1000 단위의 수를 입력해주세요."),
    enterNumberGreaterThanZero("0보다 큰 수를 입력해주세요"),
    enterBetweenMinNumberThanMaxNumber(LottoState.MinNumber.getState() +
            "~" + LottoState.MaxNumber.getState() + " 사이의 수를 입력해주세요"),
    enterOnlyNumbersAndCommas("숫자" + LottoState.numberCount.getState() + "개를 쉼표로 구분하여 입력해주세요");

    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

}
