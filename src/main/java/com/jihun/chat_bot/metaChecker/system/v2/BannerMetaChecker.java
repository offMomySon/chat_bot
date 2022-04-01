package com.jihun.chat_bot.metaChecker.system.v2;

import com.jihun.chat_bot.metaChecker.MetaCheckType;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import static com.jihun.chat_bot.metaChecker.MetaCheckType.MATCH_FAIL_TOTALLY;
import static com.jihun.chat_bot.metaChecker.MetaCheckType.MATCH_SUCCESS;

public class BannerMetaChecker extends LastMetaChecker {
    private static final Set<String> MATCHER = Set.of("b", "banner");

    private static final int MIN_META_LENGTH = 1;
    private static final int MAX_META_LENGTH = 2;

    public MetaCheckType check(List<String> metas) {
        if (Objects.isNull(metas)) {
            return MATCH_FAIL_TOTALLY;
        }

        if (isNotCheckable(metas)) {
            return MATCH_FAIL_TOTALLY;
        }

        if (isTwoLengthMeta(metas)) {
            return MATCH_SUCCESS;
        }

        if (isOneLengthMeta(metas)) {
            return MATCH_SUCCESS;
        }

        return MATCH_FAIL_TOTALLY;
    }

    public boolean isNotCheckable(List<String> metas) {
        return MIN_META_LENGTH > metas.size() || metas.size() > MAX_META_LENGTH;
    }

    private boolean isOneLengthMeta(List<String> metas) {
        return metas.size() == MIN_META_LENGTH;
    }

    private boolean isTwoLengthMeta(List<String> metas) {
        return metas.size() == MAX_META_LENGTH && MATCHER.contains(metas.get(META_POSITION));
    }

    protected boolean isMetaPartlyMatched(List<String> metas) {
        return false;
    }

    public Set<String> getPossibleMeta() {
        return MATCHER;
    }
}
