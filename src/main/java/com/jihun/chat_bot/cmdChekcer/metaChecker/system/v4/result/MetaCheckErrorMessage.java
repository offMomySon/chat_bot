package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.result;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class MetaCheckErrorMessage {
    private static final int PARTIAL_MATCHED_LENGTH = 1;

    private final Meta baseMeta;

    public MetaCheckErrorMessage(@NotNull Meta baseMeta) {
        this.baseMeta = baseMeta;
    }

    public String create(Meta targetMeta) {
        List<String> metaValues = baseMeta.getValues();

        List<String> partialInvalidMetaValues = filterPartialMatched(metaValues, targetMeta);
        if (!partialInvalidMetaValues.isEmpty()) {
            return decorateMessage(partialInvalidMetaValues);
        }

        return decorateMessage(metaValues);
    }

    private static List<String> filterPartialMatched(List<String> metaValues, Meta givenMeta) {
        return metaValues.stream()
                .filter(invalidMetaValue -> isPartialMatched(givenMeta, invalidMetaValue))
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

    private static String decorateMessage(List<String> invalidMetaValues) {
        return String.join(" | ", invalidMetaValues);
    }
}
