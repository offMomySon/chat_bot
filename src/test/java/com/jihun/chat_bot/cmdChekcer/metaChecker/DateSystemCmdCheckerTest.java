package com.jihun.chat_bot.cmdChekcer.metaChecker;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateSystemCmdCheckerTest {
    @DisplayName("성공하는 calculate meta cmd 면, true 을 반환합니다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "2022-03-09",

        "c +",
        "c -",
        "c + 10",
        "c - 10",
        "c 2022-03-09 +",
        "c 2022-03-09 -",
        "c 2022-03-09 + 10",
        "c 2022-03-09 - 10",

        "cal +",
        "cal -",
        "cal + 10",
        "cal - 10",
        "cal 2022-03-09 +",
        "cal 2022-03-09 -",
        "cal 2022-03-09 + 10",
        "cal 2022-03-09 - 10",

        "calculate +",
        "calculate + ",
        "calculate -",
        "calculate + 10",
        "calculate - 10",
        "calculate 2022-03-09 +",
        "calculate 2022-03-09 -",
        "calculate 2022-03-09 + 10",
        "calculate 2022-03-09 - 10"})
    void test2(String _cmd) {
        //given
        DateMetaChecker dateMetaChecker = new DateMetaChecker();
        String[] meta = _cmd.split(" ");

        //when
        Boolean actual = dateMetaChecker.isMatch(meta);

        //then
        Assertions.assertThat(actual)
            .isTrue();
    }
}