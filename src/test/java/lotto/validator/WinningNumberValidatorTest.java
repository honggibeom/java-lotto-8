package lotto.validator;

import lotto.error.ErrorMessage;
import org.junit.jupiter.api.Test;
import validator.WinningNumberValidator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

public class WinningNumberValidatorTest {
    @Test
    void validWinningNumber_ShouldPass() {
        String validInput = "1,2,3,4,5,6"; // 정상 6개 숫자
        assertThatCode(() -> WinningNumberValidator.validate(validInput))
                .doesNotThrowAnyException();
    }

    @Test
    void nonNumericOrInvalidSeparatorShouldThrowException() {
        String[] invalidWinningNumbers = {
                "1,2,3,4,5,a",
                "1,2,3,4,5",
                "1,2,3,4,,6",
                "1;2;3;4;5;6"
        };

        for (String invalidWinningNumber : invalidWinningNumbers) {
            assertThatThrownBy(() -> WinningNumberValidator.validate(invalidWinningNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.enterOnlyNumbersAndCommas.getErrorMessage());
        }
    }

    @Test
    void outOfRangeNumbersShouldThrowException() {
        String[] invalidWinningNumbers = {
                "0,2,3,4,5,6",
                "1,2,3,4,5,46",
                "-1,2,3,4,5,6"
        };

        for (String invalidWinningNumber : invalidWinningNumbers) {
            assertThatThrownBy(() -> WinningNumberValidator.validate(invalidWinningNumber))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ErrorMessage.enterBetweenMinNumberThanMaxNumber.getErrorMessage());
        }
    }
}
