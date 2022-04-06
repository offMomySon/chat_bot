package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v3;

import com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static com.jihun.chat_bot.cmdChekcer.metaChecker.system.v3.ChainMetaCheckerV3.EMPTY;

class TextMetaCheckerV3Test {

    @DisplayName("체크 대상 meta 가 문자열이면 MATCH_SUCCESS 를 반환합니다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "text",
        "124",
        "***",
        "!@#@$",
        "test123",
        "test!@(",
        "***123"
    })
    void test1(String meta) {
        //given
        TextMetaCheckerV3 textMetaCheckerV3 = new TextMetaCheckerV3(meta, EMPTY);

        //when
        MetaCheckType actual = textMetaCheckerV3.check();

        //then
        Assertions.assertThat(actual)
            .isEqualTo(MetaCheckType.MATCH_SUCCESS);
    }

    @DisplayName("비어있는 meta 를 받으면 Exception 이 발생합니다.")
    @ParameterizedTest
    @EmptySource
    @NullSource
    @ValueSource(strings = {" "})
    void test2(String meta) {
        //given
        //when
        Throwable actual = Assertions.catchThrowable(() -> new TextMetaCheckerV3(meta, EMPTY));

        //then
        Assertions.assertThat(actual)
            .isNotNull();
    }

    @DisplayName("체크 대상인 metas 를 반환합니다.")
    @Test
    void test3() {
        //given
        TextMetaCheckerV3 textMetaCheckerV3 = new TextMetaCheckerV3("txt", EMPTY);

        //when
        Set<String> actual = textMetaCheckerV3.getCheckMetas();

        //then
        Assertions.assertThat(actual)
            .isNotNull()
            .isNotEmpty();
    }

}