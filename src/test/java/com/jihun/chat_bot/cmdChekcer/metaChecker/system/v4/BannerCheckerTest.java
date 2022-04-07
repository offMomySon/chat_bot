package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class BannerCheckerTest {
    @DisplayName("올바른 배너 메타의 유효성검사를 진행합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"b", "banner"})
    void testIsValidBannerMetaWhenGivenValidBannerMeta(String meta) {
        // given
        BannerChecker bannerChecker = new BannerChecker();

        // when
        boolean actual = bannerChecker.valid(meta);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("올바르지 않은 배너 메타의 유효성검사를 진행합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"bb", "d", "banners"})
    void testIsInvalidBannerMetaWhenGivenInvalidBannerMeta(String meta) {
        // given
        BannerChecker bannerChecker = new BannerChecker();

        // when
        boolean actual = bannerChecker.valid(meta);

        // then
        assertThat(actual).isFalse();
    }
}
