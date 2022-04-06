package com.jihun.chat_bot.checker.meta.system.v2;

import com.jihun.chat_bot.checker.meta.MetaCheckType;
import java.util.List;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static com.jihun.chat_bot.checker.meta.MetaCheckType.MATCH_FAIL_TOTALLY;
import static com.jihun.chat_bot.checker.meta.MetaCheckType.MATCH_SUCCESS;

class BannerMetaCheckerTest {

    @DisplayName("meta 가 맞으면, MATCH_SUCCESS Type 을 반환합니다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "b",
        "banner"
    })
    void test1(String _meta) {
        //given
        BannerMetaChecker checker = new BannerMetaChecker(List.of(), _meta);

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
        BannerMetaChecker checker = new BannerMetaChecker(List.of(), _meta);

        //when
        MetaCheckType actual = checker.check();

        //then
        Assertions.assertThat(actual)
            .isEqualTo(MATCH_FAIL_TOTALLY);
    }

    @DisplayName("비어있지 않은 check meta 리스트를 가져 옵니다.")
    @Test
    void test7() {
        //given
        BannerMetaChecker bannerMetaChecker = new BannerMetaChecker(List.of(), "banner");
        Set<String> possibleMeta = bannerMetaChecker.getPossibleMeta();

        //when
        boolean actual = possibleMeta.isEmpty();

        //then
        Assertions.assertThat(actual)
            .isFalse();
    }
}