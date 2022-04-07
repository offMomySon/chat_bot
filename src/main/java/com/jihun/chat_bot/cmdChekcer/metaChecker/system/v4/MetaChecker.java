package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import lombok.NonNull;

import java.util.Collection;
import java.util.Objects;

public abstract class MetaChecker {
    private static final int PARTIAL_MATCHED_LENGTH = 1;

    public MetaResult valid(@NonNull String meta) {
        if (allMatched(meta)) {
            return MetaResult.ALL_MATCHED;
        }

        if (partialMatched(meta)) {
            return MetaResult.PARTIAL_MATCHED;
        }

        return MetaResult.NONE_MATCHED;
    }

    private boolean allMatched(String givenMeta) {
        return getMetas().stream()
                .anyMatch(bannerMeta -> Objects.equals(bannerMeta, givenMeta));
    }

    private boolean partialMatched(String givenMeta) {
        return getMetas().stream()
                .anyMatch(bannerMeta -> isPartialMatched(bannerMeta, givenMeta));
    }

    private static boolean isPartialMatched(String bannerMeta, String givenMeta) {
        for (int i = 0; i < PARTIAL_MATCHED_LENGTH; i++) {
            char bannerMetaChar = bannerMeta.charAt(i);
            char givenMetaChar = givenMeta.charAt(i);

            if (bannerMetaChar != givenMetaChar) {
                return false;
            }
        }

        return true;
    }

    protected abstract Collection<String> getMetas();
}
