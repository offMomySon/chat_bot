package com.jihun.chat_bot.metaChecker.system.v2;

import com.jihun.chat_bot.metaChecker.MetaCheckType;
import java.util.List;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static com.jihun.chat_bot.metaChecker.MetaCheckType.MATCH_FAIL_PARTLY_MATCHED;
import static com.jihun.chat_bot.metaChecker.MetaCheckType.MATCH_FAIL_TOTALLY;
import static com.jihun.chat_bot.metaChecker.MetaCheckType.MATCH_SUCCESS;

class ExitMetaCheckerTest {
    @DisplayName("meta 가 맞으면, MATCH_SUCCESS Type 을 반환합니다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "e good",
        "exit good"
    })
    void test1(String _meta) {
        //given
        ExitMetaChecker checker = new ExitMetaChecker();

        //when
        MetaCheckType actual = checker.check(List.of(_meta.split(" ")));

        //then
        Assertions.assertThat(actual)
            .isEqualTo(MATCH_SUCCESS);
    }

    @DisplayName("meta 가 한글자도 일치하지 않으면, MATCH_FAIL_TOTALLY Type 을 반환합니다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "notGood meta",
        "n meta"
    })
    void test2(String _meta) {
        //given
        ExitMetaChecker checker = new ExitMetaChecker();

        //when
        MetaCheckType actual = checker.check(List.of(_meta.split(" ")));

        //then
        Assertions.assertThat(actual)
            .isEqualTo(MATCH_FAIL_TOTALLY);
    }

    @DisplayName("가능한 meta 길이보다 길면, MATCH_FAIL_TOTALLY Type 을 반환합니다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "too long size",
        "too too long size",
        "too too too long size"
    })
    void test3(String _meta) {
        //given
        ExitMetaChecker checker = new ExitMetaChecker();

        //when
        MetaCheckType actual = checker.check(List.of(_meta.split(" ")));

        //then
        Assertions.assertThat(actual)
            .isEqualTo(MATCH_FAIL_TOTALLY);
    }

    @DisplayName("meta 중 일부만 일치하면, MATCH_FAIL_TOTALLY Type 을 반환합니다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "ePartly match",
        "exitPartly match",
        "exit! bb"
    })
    void test4(String _meta) {
        //given
        ExitMetaChecker checker = new ExitMetaChecker();

        //when
        MetaCheckType actual = checker.check(List.of(_meta.split(" ")));

        //then
        Assertions.assertThat(actual)
            .isEqualTo(MATCH_FAIL_PARTLY_MATCHED);
    }

    @DisplayName("비어있지 않은 check meta 리스트를 가져 옵니다.")
    @Test
    void test7() {
        //given
        ExitMetaChecker exitMetaChecker = new ExitMetaChecker();
        Set<String> possibleMeta = exitMetaChecker.getPossibleMeta();

        //when
        boolean actual = possibleMeta.isEmpty();

        //then
        Assertions.assertThat(actual)
            .isFalse();
    }
}