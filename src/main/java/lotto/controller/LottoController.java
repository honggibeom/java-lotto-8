package lotto.controller;

import lotto.Lotto;
import lotto.model.LottoState;
import lotto.service.LottoDrawService;
import lotto.service.LottoResultService;
import lotto.utils.InputUtils;
import lotto.view.InputView;
import lotto.view.OutputView;
import validator.BonusNumberValidator;
import validator.PurchaseAmountValidator;
import validator.WinningNumberValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outPutView) {
        this.inputView = inputView;
        this.outputView = outPutView;
    }

    public void run() {
        String purchaseAmount = InputUtils.askAndValidate(
                inputView::askPurchaseAmount,
                PurchaseAmountValidator::validate
        );
        int lottoCount = LottoState.price.calculateLottoCountFromAmount(Integer.parseInt(purchaseAmount));
        outputView.printLottoCount(lottoCount);

        LottoDrawService lottoDrawService = new LottoDrawService();
        List<Lotto> lotteries = lottoDrawService.drawLotteries(lottoCount);
        outputView.printLotteriesNumber(lotteries);

        String winningNumber = InputUtils.askAndValidate(inputView::askWinningNumbers, WinningNumberValidator::validate);
        Set<Integer> winningNumberSet = Arrays.stream(winningNumber.split(","))
                .map(Integer::parseInt)   // 문자열 -> 정수
                .collect(Collectors.toSet());

        String bonusNumber = InputUtils.askAndValidate(inputView::askBonusNumbers, BonusNumberValidator::validate);

        LottoResultService lottoResultService = new LottoResultService(lotteries, winningNumberSet, Integer.parseInt(bonusNumber));
        lottoResultService.countWinningLotteries();

        List<Integer> winningLottoCount = lottoResultService.getAllWinningLottoCount();
        outputView.printWinningLottoCount(winningLottoCount);
        double profit = lottoResultService.calculateProfit();
        outputView.printLottoProfit(profit);
    }
}
