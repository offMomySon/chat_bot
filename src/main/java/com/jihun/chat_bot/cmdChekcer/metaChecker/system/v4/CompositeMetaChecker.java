package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CompositeMetaChecker extends MetaChecker {
    private final List<String> metas;

    private CompositeMetaChecker(List<String> metas) {
        this.metas = validate(metas);
    }

    public static CompositeMetaChecker of(List<MetaChecker> metaCheckers) {
        return new CompositeMetaChecker(
                validate(metaCheckers).stream()
                .map(MetaChecker::getMetas)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList())
        );
    }

    private static <T>List<T> validate(List<T> metaCheckers) {
        if (Objects.isNull(metaCheckers)) {
            return Collections.emptyList();
        }

        return metaCheckers.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    protected Collection<String> getMetas() {
        return metas;
    }
}
