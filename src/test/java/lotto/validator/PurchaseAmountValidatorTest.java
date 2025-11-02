package lotto.validator;

import lotto.error.ErrorMessage;
import org.junit.jupiter.api.Test;

import validator.PurchaseAmountValidator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class PurchaseAmountValidatorTest {
    @Test
    void validPurchaseAmountShouldPass() {
        assertDoesNotThrow(() -> PurchaseAmountValidator.validate("5000"));
        assertDoesNotThrow(() -> PurchaseAmountValidator.validate("1000"));
        assertDoesNotThrow(() -> PurchaseAmountValidator.validate("3000"));
    }

    @Test
    void nonNumericInputShouldThrowException() {
        String InvalidPurchaseAmount = "abc";
        assertThatThrownBy(() -> PurchaseAmountValidator.validate(InvalidPurchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자를 입력해주세요.");
    }

    @Test
    void ZeroShouldThrowException() {
        String InvalidPurchaseAmount = "0";
        assertThatThrownBy(() -> PurchaseAmountValidator.validate(InvalidPurchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.enterNumberGreaterThanZero.getErrorMessage());
    }

    @Test
    void negativeShouldThrowException() {
        String InvalidPurchaseAmount = "-1000";
        assertThatThrownBy(() -> PurchaseAmountValidator.validate(InvalidPurchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.enterNumberGreaterThanZero.getErrorMessage());
    }

    @Test
    void notMultipleOfThousandShouldThrowException() {
        String InvalidPurchaseAmount = "1500";
        assertThatThrownBy(() -> PurchaseAmountValidator.validate(InvalidPurchaseAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.enterNumberMultipleOfThousand.getErrorMessage());
    }
}
