package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MetaCheckerTest {
    //Note. 데이터의 순서 변경시 테스트의 의도가 깨질 수 있습니다.
    private static final List<String> TEST_METAS = List.of("t", "test", "m", "meta");

    private MetaChecker metaChecker;

    @BeforeEach
    void setUpMetaChecker() {
        this.metaChecker = new TestMetaChecker(TEST_METAS);
    }

    @DisplayName("올바른 배너 메타의 유효성검사를 진행합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"t", "test", "m", "meta"})
    void testIsValidBannerMetaWhenGivenValidBannerMeta(String meta) {
        // given
        // when
        MetaResult actual = metaChecker.valid(meta);

        // then
        assertThat(actual).isSameAs(MetaResult.ALL_MATCHED);
    }

    @DisplayName("부분적으로 일치하거나 전부 일치하면 전부 일치한 결과로 검사합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"test", "meta"})
    void testIsValidBannerMetaWhenGivenBannerMetaIsBothMatchedPartialAndAll(String meta) {
        // given
        // when
        MetaResult actual = metaChecker.valid(meta);

        // then
        assertThat(actual).isSameAs(MetaResult.ALL_MATCHED);
    }


    @DisplayName("올바르지 않은 배너 메타의 유효성검사를 진행합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"b", "ba", "banner"})
    void testIsInvalidBannerMetaWhenGivenInvalidBannerMeta(String meta) {
        // given
        // when
        MetaResult actual = metaChecker.valid(meta);

        // then
        assertThat(actual).isSameAs(MetaResult.NONE_MATCHED);
    }

    @DisplayName("부분적으로 올바른 배너 메타의 유효성검사를 진행합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"tt", "mm"})
    void testIsPartialValidBannerMetaWhenGivenInvalidBannerMeta(String meta) {
        // given
        // when
        MetaResult actual = metaChecker.valid(meta);

        // then
        assertThat(actual).isSameAs(MetaResult.PARTIAL_MATCHED);
    }

    private static class TestMetaChecker extends MetaChecker {
        private final List<String> metas;

        private TestMetaChecker(List<String> metas) {
            this.metas = validate(metas);
        }

        private static List<String> validate(List<String> metas) {
            if (Objects.isNull(metas)) {
                return Collections.emptyList();
            }

            return metas.stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toUnmodifiableList());
        }

        @Override
        protected Collection<String> getMetas() {
            return metas;
        }
    }
}
