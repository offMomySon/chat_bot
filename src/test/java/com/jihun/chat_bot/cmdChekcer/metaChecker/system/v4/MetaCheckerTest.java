package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MetaCheckerTest extends CommonMetaCheckerTest{
    //Note. 데이터의 순서 변경시 테스트의 의도가 깨질 수 있습니다.
    private static final List<String> TEST_METAS = List.of("t", "test", "m", "meta");

    @Override
    protected MetaChecker getMetaChecker() {
        return new TestMetaChecker(TEST_METAS);
    }

    @Override
    protected Collection<String> getAllMatchMetas() {
        return List.of("t", "test", "m", "meta");
    }

    @Override
    protected Collection<String> getPartialAndAllMatchMetas() {
        return List.of("test", "meta");
    }

    @Override
    protected Collection<String> getPartialMatchMetas() {
        return List.of("tt", "mm");
    }

    @Override
    protected Collection<String> getNonMatchMetas() {
        return List.of("b", "ba", "banner");
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
