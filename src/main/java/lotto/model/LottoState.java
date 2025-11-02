package lotto.model;

public enum LottoState {
    MinNumber(1),
    MaxNumber(45),
    price(1000),
    numberCount(6);
    private final Integer lottoNumber;

    LottoState(Integer LottoNumber) {
        this.lottoNumber = LottoNumber;
    }

    public Integer getState() {
        return lottoNumber;
    }

    public int calculateLottoCountFromAmount(int amount) {
        return amount / price.getState();
    }

    public int calculateAmountFromLottoCount(int lottoCount) {
        return lottoCount * price.getState();
    }
}
