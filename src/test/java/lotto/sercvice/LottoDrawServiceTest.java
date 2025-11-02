package lotto.sercvice;

import lotto.model.Lotto;
import lotto.model.LottoState;
import lotto.service.LottoDrawService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoDrawServiceTest {
    @Test
    void drawLotteriesShouldReturnCorrectNumberOfLotto() {
        LottoDrawService service = new LottoDrawService();
        int attempts = 5;

        List<Lotto> lotteries = service.drawLotteries(attempts);
        assertThat(lotteries).hasSize(attempts);
    }

    @Test
    void drawLotteriesEachLottoShouldHaveValidNumbers() {
        LottoDrawService service = new LottoDrawService();
        int attempts = 5;
        List<Lotto> lotteries = service.drawLotteries(attempts);
        for (Lotto lotto : lotteries) {
            assertThat(lotto.getNumbers()).allMatch(
                    num -> num >= LottoState.MinNumber.getState() && num <= LottoState.MaxNumber.getState()
            );
        }
    }
}
