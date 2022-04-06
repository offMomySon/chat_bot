package com.jihun.chat_bot.checker.meta.system.v2;

import com.jihun.chat_bot.checker.meta.MetaCheckType;
import java.util.List;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static com.jihun.chat_bot.checker.meta.MetaCheckType.MATCH_FAIL_PARTLY_MATCHED;
import static com.jihun.chat_bot.checker.meta.MetaCheckType.MATCH_FAIL_TOTALLY;
import static com.jihun.chat_bot.checker.meta.MetaCheckType.MATCH_SUCCESS;

class ExitMetaCheckerTest {
    @DisplayName("meta 가 맞으면, MATCH_SUCCESS Type 을 반환합니다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "e",
        "exit"
    })
    void test1(String _meta) {
        //given
        ExitMetaChecker checker = new ExitMetaChecker(List.of(), _meta);

        //when
        MetaCheckType actual = checker.check();

        //then
        Assertions.assertThat(actual)
            .isEqualTo(MATCH_SUCCESS);
    }

    @DisplayName("meta 가 한글자도 일치하지 않으면, MATCH_FAIL_TOTALLY Type 을 반환합니다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "notGood",
        "n"
    })
    void test2(String _meta) {
        //given
        ExitMetaChecker checker = new ExitMetaChecker(List.of(), _meta);

        //when
        MetaCheckType actual = checker.check();

        //then
        Assertions.assertThat(actual)
            .isEqualTo(MATCH_FAIL_TOTALLY);
    }

    @DisplayName("meta 중 일부만 일치하면, MATCH_FAIL_TOTALLY Type 을 반환합니다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "ePartly",
        "exitPartly",
        "exit!ß"
    })
    void test4(String _meta) {
        //given
        ExitMetaChecker checker = new ExitMetaChecker(List.of(), _meta);

        //when
        MetaCheckType actual = checker.check();

        //then
        Assertions.assertThat(actual)
            .isEqualTo(MATCH_FAIL_PARTLY_MATCHED);
    }

    @DisplayName("비어있지 않은 check meta 리스트를 가져 옵니다.")
    @Test
    void test7() {
        //given
        ExitMetaChecker exitMetaChecker = new ExitMetaChecker(List.of(), "exit");
        Set<String> possibleMeta = exitMetaChecker.getPossibleMeta();

        //when
        boolean actual = possibleMeta.isEmpty();

        //then
        Assertions.assertThat(actual)
            .isFalse();
    }
}