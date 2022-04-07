package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public abstract class CommonMetaCheckerTest {
    private static final Meta SKIP = Meta.empty();

    @DisplayName("올바른 배너 메타의 유효성검사를 진행합니다.")
    @ParameterizedTest
    @MethodSource("provideAllMatchMetas")
    void testIsValidBannerMetaWhenGivenValidBannerMeta(Meta meta) {
        if (isTestSkip(meta)) {
            assertThat(meta).isEqualTo(SKIP);
            return;
        }
        // given
        MetaChecker metaChecker = getMetaChecker();

        // when
        MetaResult actual = metaChecker.valid(meta);

        // then
        assertThat(actual).isSameAs(MetaResult.ALL_MATCHED);
    }

    @DisplayName("부분적으로 일치하거나 전부 일치하면 전부 일치한 결과로 검사합니다.")
    @ParameterizedTest
    @MethodSource("providePartialAndAllMatchMetas")
    void testIsValidBannerMetaWhenGivenBannerMetaIsBothMatchedPartialAndAll(Meta meta) {
        if (isTestSkip(meta)) {
            assertThat(meta).isEqualTo(SKIP);
            return;
        }

        // given
        MetaChecker metaChecker = getMetaChecker();

        // when
        MetaResult actual = metaChecker.valid(meta);

        // then
        assertThat(actual).isSameAs(MetaResult.ALL_MATCHED);
    }

    @DisplayName("부분적으로 올바른 배너 메타의 유효성검사를 진행합니다.")
    @ParameterizedTest
    @MethodSource("providePartialMatchMetas")
    void testIsPartialValidBannerMetaWhenGivenInvalidBannerMeta(Meta meta) {
        if (isTestSkip(meta)) {
            assertThat(meta).isEqualTo(SKIP);
            return;
        }

        // given
        MetaChecker metaChecker = getMetaChecker();

        // when
        MetaResult actual = metaChecker.valid(meta);

        // then
        assertThat(actual).isSameAs(MetaResult.PARTIAL_MATCHED);
    }

    @DisplayName("올바르지 않은 배너 메타의 유효성검사를 진행합니다.")
    @ParameterizedTest
    @MethodSource("provideNoneMatchMetas")
    void testIsInvalidBannerMetaWhenGivenInvalidBannerMeta(Meta meta) {
        if (isTestSkip(meta)) {
            assertThat(meta).isEqualTo(SKIP);
            return;
        }

        // given
        MetaChecker metaChecker = getMetaChecker();

        // when
        MetaResult actual = metaChecker.valid(meta);

        // then
        assertThat(actual).isSameAs(MetaResult.NONE_MATCHED);
    }

    private Stream<Arguments> provideAllMatchMetas() {
        return provideMeta(getAllMatchMetas());
    }

    private Stream<Arguments> providePartialAndAllMatchMetas() {
        return provideMeta(getPartialAndAllMatchMetas());
    }

    private Stream<Arguments> providePartialMatchMetas() {
        return provideMeta(getPartialMatchMetas());
    }

    private Stream<Arguments> provideNoneMatchMetas() {
        return provideMeta(getNonMatchMetas());
    }

    private static Stream<Arguments> provideMeta(Collection<String> metas) {
        if (metas.isEmpty()) {
            return Stream.of(Arguments.of(SKIP));
        }

        return metas.stream().map(Meta::from).map(Arguments::of);
    }

    private static boolean isTestSkip(Meta meta) {
        return meta == SKIP;
    }

    protected abstract MetaChecker getMetaChecker();
    protected abstract Collection<String> getAllMatchMetas();
    protected abstract Collection<String> getPartialAndAllMatchMetas();
    protected abstract Collection<String> getPartialMatchMetas();
    protected abstract Collection<String> getNonMatchMetas();
}
