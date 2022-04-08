package com.jihun.chat_bot.cmdChekcer.metaChecker;

import com.jihun.chat_bot.cmdChekcer.metaChecker.cal.CalMetaChecker;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CalSystemCmdCheckerTest {

    /**
     * @param _cmd [num:leftOperand] [+|-|*|/] [num:rightOperand]
     */
    @DisplayName("정상적인 calculate meta 면, false 을 반환 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "0 + 1",
        "0 - 1",
        "0 * 1",
        "0 / 1",
        "1 + 2",
        "1 - 2",
        "1 * 2",
        "1 / 2",
        "10000000000000000000000000000000000000000000000000000000000000000000000000 / 2",
        "1 / 20000000000000000000000000000000000000000000000000000000000000000000000000",
        "1000000000000000000000000000000000 / 200000000000000000000000000"
    })
    void test1(String _cmd) {
        //given
        CalMetaChecker calMetaChecker = new CalMetaChecker(List.of(_cmd.split(" ")));

        //when
        boolean actual = calMetaChecker.isNotMatch();

        //then
        Assertions.assertThat(actual)
            .isFalse();
    }

    /**
     * @param _cmd [num:leftOperand] [+|-|*|/] [num:rightOperand]
     */
    @DisplayName("잘못된 operand format 이면, false 을 반환 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "string / 1",
        "1 / string",
        "string / string",
        "a / b"
    })
    void test2(String _cmd) {
        //given
        CalMetaChecker calMetaChecker = new CalMetaChecker(List.of(_cmd.split(" ")));

        //when
        boolean actual = calMetaChecker.isNotMatch();

        //then
        Assertions.assertThat(actual)
            .isTrue();
    }

    @DisplayName("calculate meta 의 길이가 3 이 아니면, true 을 반환 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "1 / 1 illegalCmd",
        "1 / 2 illegalCmd illegalCmd"
    })
    void test3(String _cmd) {
        //given
        CalMetaChecker calMetaChecker = new CalMetaChecker(List.of(_cmd.split(" ")));

        //when
        boolean actual = calMetaChecker.isNotMatch();

        //then
        Assertions.assertThat(actual)
            .isTrue();
    }

    @DisplayName("사용할 수 없는 operator 이면, true 을 반환 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "1 % 1",
        "1 ! 1",
        "1 & 1"
    })
    void test5(String _cmd) {
        //given
        CalMetaChecker calMetaChecker = new CalMetaChecker(List.of(_cmd.split(" ")));

        //when
        boolean actual = calMetaChecker.isNotMatch();

        //then
        Assertions.assertThat(actual)
            .isTrue();
    }

}