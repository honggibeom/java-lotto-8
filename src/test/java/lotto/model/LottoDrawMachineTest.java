package lotto.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoDrawMachineTest {
    @Test
    void drawNumbersShouldReturnCorrectSize() {
        List<Integer> numbers = LottoDrawMachine.drawNumbers();
        assertThat(numbers).hasSize(LottoState.numberCount.getState());
    }

    @Test
    void drawNumbersShouldBeWithinValidRange() {
        List<Integer> numbers = LottoDrawMachine.drawNumbers();
        int min = LottoState.MinNumber.getState();
        int max = LottoState.MaxNumber.getState();
        assertThat(numbers).allSatisfy(num ->
                assertThat(num).isBetween(min, max)
        );
    }

    @Test
    void drawNumbersShouldNotContainDuplicates() {
        List<Integer> numbers = LottoDrawMachine.drawNumbers();
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        assertThat(uniqueNumbers).hasSize(numbers.size());
    }
}
