package lotto.service;

import lotto.model.Lotto;
import lotto.model.LottoState;
import lotto.model.WinningCondition;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.Arrays;

public class LottoResultService {
    List<Lotto> lotteries;
    Set<Integer> winningNumbers;
    Integer bonusNumber;
    Map<Integer, Integer> winningLottoCount;

    public LottoResultService(List<Lotto> lotteries, Set<Integer> winningNumbers, Integer bonusNumber) {
        this.lotteries = lotteries;
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
        this.winningLottoCount = new HashMap<>();
        Arrays.stream(WinningCondition.values())
                .forEach(winningCondition -> winningLottoCount.put(winningCondition.getRank(), 0));
    }

    public void countWinningLotteries() {
        lotteries.forEach(lotto -> {
            int rank = lotto.getRanking(winningNumbers, bonusNumber);
            if (winningLottoCount.containsKey(rank)) {
                winningLottoCount.put(rank, winningLottoCount.get(rank) + 1);
            }
        });
    }

    public Integer countWinningLotto(int rank) {
        return winningLottoCount.getOrDefault(rank, 0);
    }

    public double calculateProfit() {
        double totalPrize = Arrays.stream(WinningCondition.values())
                .mapToDouble(condition ->
                        condition.getWinningPrize() * countWinningLotto(condition.getRank()))
                .sum();

        double totalCost = LottoState.price.calculateAmountFromLottoCount(lotteries.size());
        return (totalPrize / totalCost) * 100;
    }

    public List<Integer> getAllWinningLottoCount() {
        return winningLottoCount.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .toList();
    }
}
