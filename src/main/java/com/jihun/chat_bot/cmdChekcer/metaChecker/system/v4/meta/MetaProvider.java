package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MetaProvider {
    private static final String DELIMITER = " ";
    private final Queue<String> values;

    public MetaProvider(Queue<String> values) {
        this.values = validate(values);
    }

    private static Queue<String> validate(Queue<String> values) {
        if (Objects.isNull(values)) {
            return new LinkedList<>();
        }

        return values.stream()
                .filter(Objects::nonNull)
                .filter(s -> !s.isBlank())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public static MetaProvider from(String metas) {
        if (Objects.isNull(metas)) {
            return new MetaProvider(new LinkedList<>());
        }

        return new MetaProvider(
                Stream.of(metas.split(DELIMITER))
                        .collect(Collectors.toCollection(LinkedList::new))
        );
    }

    public Meta poll() {
        if (values.isEmpty()) {
            throw new IllegalStateException("empty meta");
        }

        return Meta.from(this.values.poll());
    }

    public String resolveRemainValue() {
        String value = String.join(" ", values);
        values.clear();
        return value;
    }
}
