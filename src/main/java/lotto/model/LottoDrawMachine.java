package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class LottoDrawMachine {
    public static List<Integer> drawNumbers(){
        return Randoms.pickUniqueNumbersInRange(LottoState.MinNumber.getState(),
                LottoState.MaxNumber.getState(),LottoState.numberCount.getState() );
    }
}
