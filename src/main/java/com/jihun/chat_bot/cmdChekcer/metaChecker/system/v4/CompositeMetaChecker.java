package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;
import lombok.NonNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CompositeMetaChecker extends MetaChecker {
    private final Meta meta;

    private CompositeMetaChecker(@NonNull Meta meta) {
        this.meta = meta;
    }

    public static CompositeMetaChecker of(List<MetaChecker> metaCheckers) {
        return new CompositeMetaChecker(
                validate(metaCheckers)
                        .stream()
                        .map(MetaChecker::getMeta)
                        .reduce(Meta::combine)
                        .orElseThrow()
        );
    }

    private static <T> List<T> validate(List<T> metaCheckers) {
        if (Objects.isNull(metaCheckers)) {
            return Collections.emptyList();
        }

        return metaCheckers.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    protected Meta getMeta() {
        return meta;
    }
}
