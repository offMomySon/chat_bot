package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Meta {
    private static final Meta EMPTY = Meta.from();

    private final Set<String> values;

    private Meta(Set<String> values) {
        this.values = toLowercaseValues(values);
    }

    private static Set<String> toLowercaseValues(Set<String> values) {
        if (Objects.isNull(values)) {
            return new LinkedHashSet<>();
        }

        Set<String> newValues = values.stream()
                .filter(Objects::nonNull)
                .map(String::toLowerCase)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        return Collections.unmodifiableSet(newValues);
    }

    public static Meta from(String... values) {
        return new Meta(
                Stream.of(values)
                .collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }

    public static Meta empty() {
        return EMPTY;
    }

    public boolean contain(Meta givenMeta) {
        return givenMeta.values
                .stream()
                .anyMatch(values::contains);
    }

    public List<String> getValues() {
        return values.stream()
                .collect(Collectors.toUnmodifiableList());
    }

    public Meta combine(Meta meta) {
        LinkedHashSet<String> newValues = new LinkedHashSet<>(this.values);
        newValues.addAll(meta.values);

        return new Meta(newValues);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meta)) return false;
        Meta meta = (Meta) o;
        return values.equals(meta.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    @Override
    public String toString() {
        return "Meta{" +
                "values=" + values +
                '}';
    }
}
