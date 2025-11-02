package lotto.service;

import lotto.model.Lotto;
import lotto.model.LottoDrawMachine;

import java.util.List;
import java.util.stream.IntStream;

public class LottoDrawService {
    public List<Lotto> drawLotteries(int attempts) {
        return IntStream.range(0, attempts)
                .mapToObj(i -> new Lotto(LottoDrawMachine.drawNumbers()))
                .toList();
    }
}
