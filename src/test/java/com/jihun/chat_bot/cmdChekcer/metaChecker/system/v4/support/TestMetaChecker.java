package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.support;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.MetaChecker;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestMetaChecker extends MetaChecker {
    private final List<String> metas;

    public TestMetaChecker(List<String> metas) {
        this.metas = validate(metas);
    }

    public static TestMetaChecker from(String... metas) {
        return new TestMetaChecker(
                Stream.of(metas)
                .collect(Collectors.toUnmodifiableList())
        );
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
