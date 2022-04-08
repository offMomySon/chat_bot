package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MetaTest {
    private static final String[] META_VALUES = {"m", "meta"};

    @DisplayName("메타 데이터가 하나라도 일치하는지 테스트 합니다")
    @ParameterizedTest
    @ValueSource(strings = {"m", "meta"})
    void testIsValueAnyOneContain(String givenValue) {
        // given
        Meta givenMeta = Meta.from(givenValue);
        Meta meta = Meta.from(META_VALUES);

        // when
        boolean acutal = meta.contain(givenMeta);

        // then
        assertThat(acutal).isTrue();
    }

    @DisplayName("메타 데이터는 대소문자를 가라지 않습니다")
    @ParameterizedTest
    @ValueSource(strings = {"M", "Meta"})
    void testIsValueContainWithIgnoreCase(String givenValue) {
        // given
        Meta givenMeta = Meta.from(givenValue);
        Meta meta = Meta.from(META_VALUES);

        // when
        boolean acutal = meta.contain(givenMeta);

        // then
        assertThat(acutal).isTrue();
    }

    @DisplayName("넣은 순서대로 값이 조회되는지 테스트 합니다")
    @ParameterizedTest
    @CsvSource("a, b, c")
    void testIsGetValidSequenceValues(String value1, String value2, String value3) {
        // given
        Meta meta = Meta.from(value1, value2, value3);
        List<String> expect = List.of(value1, value2, value3);

        // when
        List<String> actual = meta.getValues();

        // then
        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("메타끼리 합병이 되는지 테스트 합니다")
    @ParameterizedTest
    @CsvSource("a, b, c")
    void testCombineMetas(String value1, String value2, String value3) {
        // given
        Meta expect = Meta.from(value1, value2, value3);
        Meta givenMeta1 = Meta.from(value1, value2);
        Meta givenMeta2 = Meta.from(value3);

        // when
        Meta actual = givenMeta1.combine(givenMeta2);

        // then
        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("메타끼리 합병 순서에따라 순서대로 값이 조회되는지 테스트 합니다")
    @ParameterizedTest
    @CsvSource("a, b, c")
    void testWhenCombineMetasThenIsGetValidSequenceValues(String value1, String value2, String value3) {
        // given
        Meta givenMeta1 = Meta.from(value1, value2);
        Meta givenMeta2 = Meta.from(value3);
        List<String> expect = List.of(value1, value2, value3);

        // when
        Meta combineMeta = givenMeta1.combine(givenMeta2);
        List<String> actual = combineMeta.getValues();

        // then
        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("모든 케이스에 매치되는 메타를 테스트 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"any", "one", "match"})
    void testAnyOneMatchMeta(String anyOneMetaValue) {
        // given
        Meta givenMeta = Meta.from(anyOneMetaValue);
        Meta anyOneMatch = Meta.anyOneMatch();

        // when
        boolean actual = anyOneMatch.contain(givenMeta);

        // then
        assertThat(actual).isTrue();
    }
}
