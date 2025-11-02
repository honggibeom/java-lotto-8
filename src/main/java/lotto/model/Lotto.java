package lotto.model;

import lotto.error.ErrorMessage;
import lotto.error.IllegalArgumentExceptionFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LottoState.numberCount.getState()) {
            throw IllegalArgumentExceptionFactory.create(ErrorMessage.LottoNumberShouldNumberCount.getErrorMessage());
        }
    }

    private boolean isMatchWithBonusNumber(Integer bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    private int CountMatchNumber(Set<Integer> winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public int getRanking(Set<Integer> winningNumbers, Integer bonusNumber) {
        boolean matchBonusNumber = isMatchWithBonusNumber(bonusNumber);
        int countMatchNumber = CountMatchNumber(winningNumbers);
        return Arrays.stream(WinningCondition.values()).filter(
                        winningCondition -> matchBonusNumber == winningCondition.getMatchBonusNumber() &&
                                countMatchNumber == winningCondition.getMatchNumberCount())
                .findFirst()
                .map(rank -> rank.ordinal() + 1)
                .orElse(-1);
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
    }
}
