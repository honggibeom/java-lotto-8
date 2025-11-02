package lotto.model;

public enum WinningCondition {
    firstRank(1, 6, false, 2000000000),
    secondRank(2, 5, true, 30000000),
    thirdRank(3, 5, false, 1500000),
    fourthRank(4, 4, false, 50000),
    fifthRank(5, 3, false, 5000);

    private final Integer rank;
    private final Integer matchNumberCount;
    private final Integer winningPrize;
    private final Boolean matchBonusNumber;

    WinningCondition(Integer rank, Integer matchNumberCount, Boolean matchBonusNumber, Integer winningPrize) {
        this.rank = rank;
        this.matchNumberCount = matchNumberCount;
        this.matchBonusNumber = matchBonusNumber;
        this.winningPrize = winningPrize;
    }

    public Integer getRank() {
        return rank;
    }

    public Integer getMatchNumberCount() {
        return matchNumberCount;
    }

    public Integer getWinningPrize() {
        return winningPrize;
    }

    public Boolean getMatchBonusNumber() {
        return matchBonusNumber;
    }
}
