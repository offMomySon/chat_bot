package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;

public class Meta {
    private static final Meta EMPTY = Meta.from("empty");
    private static final boolean NOT_ANY_MATCH = false;
    public static final boolean ANY_ONE_MATCH = true;

    private final Set<String> values;
    private final boolean anyOneMatch;

    private Meta(Set<String> values, boolean anyOneMatch) {
        this.values = toLowercaseValues(values);
        this.anyOneMatch = anyOneMatch;
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
                .collect(Collectors.toCollection(LinkedHashSet::new)),
                NOT_ANY_MATCH
        );
    }

    public static Meta empty() {
        return EMPTY;
    }

    public static Meta anyOneMatch() {
        return new Meta(emptySet(), ANY_ONE_MATCH);
    }

    public boolean isAnyOneMatch() {
        return anyOneMatch;
    }

    public boolean isEmpty() {
        return this == EMPTY || values.isEmpty();
    }

    public boolean contain(Meta givenMeta) {
        if (anyOneMatch) {
            return true;
        }

        return givenMeta.values
                .stream()
                .anyMatch(values::contains);
    }

    public boolean doesNotContain(Meta givenMeta) {
        return !contain(givenMeta);
    }

    public List<String> getValues() {
        if (isEmpty()) {
            return emptyList();
        }

        return values.stream()
                .collect(Collectors.toUnmodifiableList());
    }

    public Meta combine(Meta meta) {
        LinkedHashSet<String> newValues = new LinkedHashSet<>(this.values);
        newValues.addAll(meta.values);

        return new Meta(newValues, meta.anyOneMatch || anyOneMatch);
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
