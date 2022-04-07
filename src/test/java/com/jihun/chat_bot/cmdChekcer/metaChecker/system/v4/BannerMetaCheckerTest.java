package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class BannerMetaCheckerTest {
    @DisplayName("올바른 배너 메타의 유효성검사를 진행합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"b", "banner"})
    void testIsValidBannerMetaWhenGivenValidBannerMeta(String meta) {
        // given
        BannerMetaChecker bannerMetaChecker = new BannerMetaChecker();

        // when
        MetaResult actual = bannerMetaChecker.valid(meta);

        // then
        assertThat(actual).isSameAs(MetaResult.ALL_MATCHED);
    }

    @DisplayName("부분적으로 일치하거나 전부 일치하면 전부 일치한 결과로 검사합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"banner"})
    void testIsValidBannerMetaWhenGivenBannerMetaIsBothMatchedPartialAndAll(String meta) {
        // given
        BannerMetaChecker bannerMetaChecker = new BannerMetaChecker();

        // when
        MetaResult actual = bannerMetaChecker.valid(meta);

        // then
        assertThat(actual).isSameAs(MetaResult.ALL_MATCHED);
    }


    @DisplayName("올바르지 않은 배너 메타의 유효성검사를 진행합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"dd", "d", "danners"})
    void testIsInvalidBannerMetaWhenGivenInvalidBannerMeta(String meta) {
        // given
        BannerMetaChecker bannerMetaChecker = new BannerMetaChecker();

        // when
        MetaResult actual = bannerMetaChecker.valid(meta);

        // then
        assertThat(actual).isSameAs(MetaResult.NONE_MATCHED);
    }

    @DisplayName("부분적으로 올바른 배너 메타의 유효성검사를 진행합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"bb", "brnner"})
    void testIsPartialValidBannerMetaWhenGivenInvalidBannerMeta(String meta) {
        // given
        BannerMetaChecker bannerMetaChecker = new BannerMetaChecker();

        // when
        MetaResult actual = bannerMetaChecker.valid(meta);

        // then
        assertThat(actual).isSameAs(MetaResult.PARTIAL_MATCHED);
    }
}
