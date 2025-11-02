package lotto.model;

public enum LottoState {

    MinNumber(1),
    MaxNumber(45),
    price(1000),
    numberCount(6);
    private final Integer LottoNumber;

    LottoState(Integer LottoNumber) {
        this.LottoNumber = LottoNumber;
    }

    public Integer getState(){
        return LottoNumber;
    }

    public int calculateLottoCountFromAmount(int amount){
        return amount/price.getState();
    }

    public int calculateAmountFromLottoCount(int lottoCount){
        return lottoCount*price.getState();
    }
}
