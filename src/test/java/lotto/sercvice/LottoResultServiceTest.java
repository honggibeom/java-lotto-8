package lotto.sercvice;

import lotto.model.Lotto;
import lotto.model.LottoState;
import lotto.model.WinningCondition;
import lotto.service.LottoResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultServiceTest {
    private List<Lotto> lotteries;
    private Set<Integer> winningNumbers;
    private int bonusNumber;

    @BeforeEach
    void setUp() {
        // 3개의 로또 샘플 생성
        lotteries = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 10, 11, 12))
        );
        winningNumbers = Set.of(1, 2, 3, 4, 5, 6);
        bonusNumber = 7;
    }

    @Test
    void countWinningLotteriesShouldCountCorrectly() {
        LottoResultService resultService = new LottoResultService(lotteries, winningNumbers, bonusNumber);
        resultService.countWinningLotteries();
        // 각 랭킹별 개수 확인
        assertThat(resultService.countWinningLotto(1)).isEqualTo(1);
        assertThat(resultService.countWinningLotto(2)).isEqualTo(1);
        assertThat(resultService.countWinningLotto(5)).isEqualTo(1);
        assertThat(resultService.countWinningLotto(3)).isEqualTo(0);
        assertThat(resultService.countWinningLotto(4)).isEqualTo(0);
    }

    @Test
    void calculateProfitShouldReturnCorrectPercentage() {
        LottoResultService resultService = new LottoResultService(lotteries, winningNumbers, bonusNumber);
        resultService.countWinningLotteries();

        double expectedTotalPrize =
                WinningCondition.firstRank.getWinningPrize() +
                        WinningCondition.secondRank.getWinningPrize() +
                        WinningCondition.fifthRank.getWinningPrize();

        double expectedTotalCost = LottoState.price.calculateAmountFromLottoCount(lotteries.size());
        double expectedProfit = (expectedTotalPrize / expectedTotalCost) * 100;

        assertThat(resultService.calculateProfit()).isEqualTo(expectedProfit);
    }

    @Test
    void getAllWinningLottoCountShouldReturnListSortedByRank() {
        LottoResultService resultService = new LottoResultService(lotteries, winningNumbers, bonusNumber);
        resultService.countWinningLotteries();

        List<Integer> counts = resultService.getAllWinningLottoCount();

        assertThat(counts).containsExactly(1, 1, 0, 0, 1);
    }
}
