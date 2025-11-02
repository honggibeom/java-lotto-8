package lotto.validator;

import lotto.error.ErrorMessage;
import org.junit.jupiter.api.Test;
import validator.BonusNumberValidator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class BonusNumberValidatorTest {
    @Test
    void validBonusNumberShouldPass() {
        assertDoesNotThrow(() -> BonusNumberValidator.validate("1"));
        assertDoesNotThrow(() -> BonusNumberValidator.validate("10"));
        assertDoesNotThrow(() -> BonusNumberValidator.validate("45"));
    }

    @Test
    void nonNumericBonusNumberShouldThrowException() {
        String InvalidBonusNumber = "a";
        assertThatThrownBy(() -> BonusNumberValidator.validate(InvalidBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.enterNumber.getErrorMessage());
    }

    @Test
    void mixedNumberAndLetterShouldThrowException() {
        String InvalidBonusNumber = "a1";
        assertThatThrownBy(() -> BonusNumberValidator.validate(InvalidBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.enterNumber.getErrorMessage());
    }

    @Test
    void smallerThanMinNumberShouldThrowException() {
        String InvalidBonusNumber = "0";
        assertThatThrownBy(() -> BonusNumberValidator.validate(InvalidBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.enterBetweenMinNumberThanMaxNumber.getErrorMessage());
    }

    @Test
    void greaterThanMaxNumberShouldThrowException() {
        String InvalidBonusNumber = "46";
        assertThatThrownBy(() -> BonusNumberValidator.validate(InvalidBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(
                        ErrorMessage.enterBetweenMinNumberThanMaxNumber.getErrorMessage()
                );
    }

    @Test
    void negativeNumberShouldThrowException() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> BonusNumberValidator.validate("-5")
        );
        assertThat(exception.getMessage())
                .contains(ErrorMessage.enterBetweenMinNumberThanMaxNumber.getErrorMessage());
    }


}
