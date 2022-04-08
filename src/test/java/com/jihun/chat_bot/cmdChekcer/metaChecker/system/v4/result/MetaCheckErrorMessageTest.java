package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.result;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MetaCheckErrorMessageTest {
    @DisplayName("주어진 `Meta`와 부분적으로 일치 하는 것이 아예 없다면 모든 요소가 출력됩니다.")
    @ParameterizedTest
    @CsvSource("b, ban, banner")
    void testIfNotExistPartialMatchedThenContainAllValueInMessage(String base1, String base2, String base3) {
        // given
        Meta baseMeta = Meta.from(base1, base2, base3);
        Meta targetMeta = Meta.from("a","c");
        MetaCheckErrorMessage errorMessage = new MetaCheckErrorMessage(baseMeta);

        // when
        String message = errorMessage.create(targetMeta);

        // then
        assertThat(message).contains(base1, base2, base3);
    }

    @DisplayName("주어진 `Meta`와 부분적으로 일치 하는 것이 있다면 부분적으로 일치하는 값이 출력됩니다.")
    @ParameterizedTest
    @MethodSource("providePartialInvalidMetaValueExistTestSuit")
    void testIfExistPartialMatchedInvalidValueThenContainPartialInvalidValueInMessage(Meta meta, List<String> invalidMetaValues, List<String> expect) {
        // given
        Meta baseMeta = Meta.from(invalidMetaValues.toArray(String[]::new));
        MetaCheckErrorMessage errorMessage = new MetaCheckErrorMessage(baseMeta);

        // when
        String message = errorMessage.create(meta);

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
