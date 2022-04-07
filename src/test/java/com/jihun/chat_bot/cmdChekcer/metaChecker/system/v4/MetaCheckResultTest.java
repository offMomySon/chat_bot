package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MetaCheckResultTest {
    @DisplayName("유효하지 않은 `Meta`값이 하나라도 매치되지 않는 경우 유효합니다.")
    @Test
    void testIfNoneMatchedInvalidMetaValueThenValid() {
        // given
        MetaCheckResult metaCheckResult = MetaCheckResult.from(Meta.empty());

        // when
        boolean actual = metaCheckResult.isValid();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("빈값으로 구성된 `Meta`값이 있는 경우도 유효합니다.")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "   "})
    void testIfMatchedOnlyEmptyMetaThenValid(String nullOrEmptyOrBlank) {
        // given
        MetaCheckResult metaCheckResult = MetaCheckResult.from(Meta.from(nullOrEmptyOrBlank));

        // when
        boolean actual = metaCheckResult.isValid();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("주어진 `Meta`와 부분적으로 일치 하는 것이 없으면 유효하지 않는 값이 전부 출력됩니다.")
    @ParameterizedTest
    @CsvSource("b, ban, banner")
    void testIfNotExistPartialMatchedInvalidValueThenAllContainInMessage(String metaValue1, String metaValue2, String metaValue3) {
        // given
        Meta givenMeta = Meta.from("m", "meta");

        MetaCheckResult metaCheckResult = MetaCheckResult.from(Meta.from(metaValue1, metaValue2, metaValue3));

        // when
        String message = metaCheckResult.createMessage(givenMeta);

        // then
        assertThat(message).contains(metaValue1, metaValue2, metaValue3);
    }

    @DisplayName("주어진 `Meta`와 부분적으로 일치 하는 것이 있다면 부분적으로 일치하는 값이 출력됩니다.")
    @ParameterizedTest
    @MethodSource("providePartialInvalidMetaValueExistTestSuit")
    void testIfExistPartialMatchedInvalidValueThenContainPartialInvalidValueInMessage(Meta meta, List<String> invalidMetaValues, List<String> expect) {
        // given
        MetaCheckResult metaCheckResult = MetaCheckResult.from(Meta.from(invalidMetaValues.toArray(String[]::new)));

        // when
        String message = metaCheckResult.createMessage(meta);

        // then
        assertThat(message).contains(expect);
    }

    private static Stream<Arguments> providePartialInvalidMetaValueExistTestSuit() {
        Meta meta = Meta.from("b");
        List<String> invalidMetaValues = List.of("b", "banner", "a", "apple");
        List<String> expect = List.of("b", "banner");

        return Stream.of(
                Arguments.of(
                        meta, invalidMetaValues,expect
                )
        );
    }
}
