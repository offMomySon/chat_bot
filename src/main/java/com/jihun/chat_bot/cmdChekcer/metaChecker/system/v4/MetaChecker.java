package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;
import lombok.NonNull;

public abstract class MetaChecker {
    private static final int PARTIAL_MATCHED_LENGTH = 1;

    public MetaResult valid(@NonNull Meta meta) {
        if (allMatched(meta)) {
            return MetaResult.ALL_MATCHED;
        }

        if (partialMatched(meta)) {
            return MetaResult.PARTIAL_MATCHED;
        }

        return MetaResult.NONE_MATCHED;
    }

    private boolean allMatched(Meta meta) {
        return getMeta().contain(meta);
    }

    private boolean partialMatched(Meta givenMeta) {
        return getMeta()
                .getValues()
                .stream()
                .anyMatch(bannerMeta -> isPartialMatched(bannerMeta, givenMeta));
    }

    private static boolean isPartialMatched(String bannerMeta, Meta givenMeta) {
        for (String metaValue: givenMeta.getValues()) {
            if (isPartialMatched(bannerMeta, metaValue)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isPartialMatched(String bannerMeta, String givenMetaValue) {
        for (int i = 0; i < PARTIAL_MATCHED_LENGTH; i++) {
            char bannerMetaChar = bannerMeta.charAt(i);
            char givenMetaChar = givenMetaValue.charAt(i);

            if (bannerMetaChar != givenMetaChar) {
                return false;
            }
        }

        return true;
    }

    protected abstract Meta getMeta();
}
