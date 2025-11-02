package lotto.model;

import lotto.error.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    @Test
    void constructorWithInvalidNumberCountShouldThrowException() {
        List<Integer> invalidNumbers = List.of(1, 2, 3, 4, 5); // 5개 → 잘못된 개수
        assertThatThrownBy(() -> new Lotto(invalidNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.LottoNumberShouldNumberCount.getErrorMessage());
    }

    @Test
    void getRankingShouldReturnFirstRank() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Set<Integer> winningNumbers = Set.of(1, 2, 3, 4, 5, 6);
        int rank = lotto.getRanking(winningNumbers, 7); // 보너스 번호 미일치
        assertThat(rank).isEqualTo(1);
    }

    @Test
    void getRankingShouldReturnSecondRank() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Set<Integer> winningNumbers = Set.of(1, 2, 3, 4, 5, 10);
        int rank = lotto.getRanking(winningNumbers, 6);
        assertThat(rank).isEqualTo(2);
    }

    @Test
    void getRankingShouldReturnThirdRank() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Set<Integer> winningNumbers = Set.of(1, 2, 3, 4, 5, 10);
        int rank = lotto.getRanking(winningNumbers, 11);
        assertThat(rank).isEqualTo(3);
    }

    @Test
    void getRankingShouldReturnFourthRank() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Set<Integer> winningNumbers = Set.of(1, 2, 3, 4, 10, 11);
        int rank = lotto.getRanking(winningNumbers, 7);
        assertThat(rank).isEqualTo(4);
    }

    @Test
    void getRankingShouldReturnFifthRank() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Set<Integer> winningNumbers = Set.of(1, 2, 3, 10, 11, 12);
        int rank = lotto.getRanking(winningNumbers, 7);
        assertThat(rank).isEqualTo(5);
    }

    @Test
    void getRankingShouldReturnMinusOneWhenNotWinning() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Set<Integer> winningNumbers = Set.of(10, 11, 12, 13, 14, 15);
        int rank = lotto.getRanking(winningNumbers, 7);
        assertThat(rank).isEqualTo(-1);
    }

}
