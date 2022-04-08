package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.result.MetaCheckResult;
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
        MetaCheckResult actual = metaChecker.check(meta);

        // then
        assertThat(actual.isValid()).isTrue();
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
        MetaCheckResult actual = metaChecker.check(meta);

        // then
        assertThat(actual.isInvalid()).isTrue();
    }

    private Stream<Arguments> provideAllMatchMetas() {
        return provideMeta(getAllMatchMetas());
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
    protected abstract Collection<String> getNonMatchMetas();
}
