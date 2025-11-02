package lotto.controller;

import lotto.model.Lotto;
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

    public void startLottoGame() {
        int lottoCount = getLottoCountFromPurchase();
        List<Lotto> lotteries = drawLotteries(lottoCount);
        Set<Integer> winningNumbers = getWinningNumbers();
        int bonusNumber = getBonusNumber();
        LottoResultService resultService = new LottoResultService(lotteries, winningNumbers, bonusNumber);
        resultService.countWinningLotteries();
        printResults(resultService);
    }

    private int getLottoCountFromPurchase() {
        String purchaseAmount = InputUtils.askAndValidate(inputView::askPurchaseAmount, PurchaseAmountValidator::validate);
        int lottoCount = LottoState.price.calculateLottoCountFromAmount(Integer.parseInt(purchaseAmount));
        outputView.printLottoCount(lottoCount);
        return lottoCount;
    }

    private Set<Integer> getWinningNumbers() {
        String winningNumber = InputUtils.askAndValidate(inputView::askWinningNumbers, WinningNumberValidator::validate);
        return Arrays.stream(winningNumber.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    private int getBonusNumber() {
        String bonusNumber = InputUtils.askAndValidate(inputView::askBonusNumbers, BonusNumberValidator::validate);
        return Integer.parseInt(bonusNumber);
    }

    private List<Lotto> drawLotteries(int lottoCount) {
        LottoDrawService lottoDrawService = new LottoDrawService();
        List<Lotto> lotteries = lottoDrawService.drawLotteries(lottoCount);
        outputView.printLotteriesNumber(lotteries);
        return lotteries;
    }

    private void printResults(LottoResultService resultService) {
        List<Integer> winningLottoCount = resultService.getAllWinningLottoCount();
        outputView.printWinningLottoCount(winningLottoCount);

        double profit = resultService.calculateProfit();
        outputView.printLottoProfit(profit);
    }
}
