package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;
import lombok.NonNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MetaCheckResult {
    private static final int PARTIAL_MATCHED_LENGTH = 1;

    private final List<String> invalidMetaValues;

    public MetaCheckResult(List<String> invalidMetaValues) {
        this.invalidMetaValues = validate(invalidMetaValues);
    }

    private static List<String> validate(List<String> invalidMetaValues) {
        if (Objects.isNull(invalidMetaValues)) {
            return Collections.emptyList();
        }

        return invalidMetaValues.stream()
                .filter(Objects::nonNull)
                .filter(s -> !s.isEmpty())
                .filter(s -> !s.isBlank())
                .collect(Collectors.toUnmodifiableList());
    }

    public static MetaCheckResult from(@NonNull Meta meta) {
        return new MetaCheckResult(meta.getValues());
    }

    public static MetaCheckResult valid() {
        return new MetaCheckResult(Collections.emptyList());
    }

    private List<String> filterPartialMatched(Meta meta) {
        return this.invalidMetaValues.stream()
                .filter(invalidMetaValue -> isPartialMatched(meta, invalidMetaValue))
                .collect(Collectors.toUnmodifiableList());
    }

    private static boolean isPartialMatched(Meta givenMeta, String invalidMetaValue) {
        for (String metaValue: givenMeta.getValues()) {
            if (isPartialMatched(invalidMetaValue, metaValue)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isPartialMatched(String givenMetaValue, String bannerMeta) {
        for (int i = 0; i < PARTIAL_MATCHED_LENGTH; i++) {
            char bannerMetaChar = bannerMeta.charAt(i);
            char givenMetaChar = givenMetaValue.charAt(i);

            if (bannerMetaChar != givenMetaChar) {
                return false;
            }
        }

        return true;
    }


    public boolean isValid() {
        return this.invalidMetaValues.isEmpty();
    }

    private boolean isInvalid() {
        return !isValid();
    }

    public String createMessage(Meta meta) {
        if (isValid()) {
            return "";
        }

        List<String> partialInvalidMetaValues = filterPartialMatched(meta);
        if (!partialInvalidMetaValues.isEmpty()) {
            return decorateMessage(partialInvalidMetaValues);
        }

        return String.join(" | ", this.invalidMetaValues);
    }

    private static String decorateMessage(List<String> invalidMetaValues) {
        return String.join(" | ", invalidMetaValues);
    }
}
