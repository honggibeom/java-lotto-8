package lotto.view;

import lotto.model.Lotto;
import lotto.model.WinningCondition;

import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.stream.Collectors;

public class OutputView {
    public void printLottoCount(int lottoCount) {
        System.out.printf("\n%d개를 구매했습니다.%n", lottoCount);
    }

    public void printLotteriesNumber(List<Lotto> lotteries) {
        lotteries.forEach(this::printLottoNumber);
    }

    public void printLottoNumber(Lotto lotto) {
        String numbers = lotto.getNumbers().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.println("[" + numbers + "]");
    }

    public void printWinningLottoCount(List<Integer> winningLottoCounts) {
        NumberFormat nf = NumberFormat.getInstance(Locale.KOREA);
        WinningCondition[] conditions = WinningCondition.values();
        System.out.println("\n당첨통계\n---");

        for (int i = conditions.length - 1; i >= 0; i--) {
            WinningCondition c = conditions[i];
            int count = winningLottoCounts.get(i);
            String matchBonus = "";
            if (c.getMatchBonusNumber()) matchBonus = ", 보너스 볼 일치";
            System.out.printf("%d개 일치%s (%s원) - %d개%n",
                    c.getMatchNumberCount(),
                    matchBonus,
                    nf.format(c.getWinningPrize()),
                    count);
        }
    }

    public void printLottoProfit(double profit) {
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profit);
    }
}
