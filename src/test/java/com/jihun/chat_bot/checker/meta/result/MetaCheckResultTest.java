package com.jihun.chat_bot.checker.meta.result;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

class MetaCheckResultTest {

    private static Stream<Arguments> provideFailResultPartiallyMatch() {
        Set<String> adviceMeta = Set.of("b", "banner", "a");
        String givenMeta = "bePartially";
        List<String> expect = List.of("b", "banner");

        return Stream.of(Arguments.of(adviceMeta, givenMeta, expect));
    }

    private static Stream<Arguments> provideFailResultNotMatch() {
        Set<String> adviceMeta = Set.of("b", "banner", "a");
        String givenMeta = "notIncludeInAdviceMeta";
        List<String> expect = List.of("b", "banner", "a");

        return Stream.of(Arguments.of(adviceMeta, givenMeta, expect));
    }

    @DisplayName("주어진 advice meta 와 모두 일치하지 않으면 모든 advice meta 를 출력합니다.")
    @ParameterizedTest
    @MethodSource(value = "provideFailResultNotMatch")
    void test1(Set<String> adviceMeta, String givenMeta, List<String> expect) {
        //given
        MetaCheckResult fail = MetaCheckResult.fail(adviceMeta);

        //when
        String actual = fail.createErrorMessage(givenMeta);

        //then
        Assertions.assertThat(actual).contains(expect);
    }

    @DisplayName("주어진 advice meta 와 부분적으로 일치하는 일치하는 advice meta 만 출력합니다.")
    @ParameterizedTest
    @MethodSource(value = "provideFailResultPartiallyMatch")
    void test2(Set<String> adviceMeta, String givenMeta, List<String> expect) {
        //given
        MetaCheckResult fail = MetaCheckResult.fail(adviceMeta);

        //when
        String actual = fail.createErrorMessage(givenMeta);

        //then
        Assertions.assertThat(actual).contains(expect);
    }

    @DisplayName("실패한 Result 이면 true 를 반환합니다.")
    @Test
    void test3() {
        //given
        MetaCheckResult fail = MetaCheckResult.fail(Set.of("test"));

        //when
        boolean actual = fail.failed();

        //then
        Assertions.assertThat(actual).isTrue();
    }

    @DisplayName("성공한 Result 이면 false 를 반환합니다.")
    @Test
    void test4() {
        //given
        MetaCheckResult success = MetaCheckResult.success();

        //when
        boolean actual = success.failed();

        //then
        Assertions.assertThat(actual).isFalse();
    }
}