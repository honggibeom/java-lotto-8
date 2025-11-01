package lotto.model;

public enum LottoState {

    MinNumber(1),
    MaxNumber(45),
    price(1000);

    private final Integer LottoNumber;

    LottoState(Integer LottoNumber) {
        this.LottoNumber = LottoNumber;
    }

    public Integer getState(){
        return LottoNumber;
    }
}
