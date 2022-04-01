package com.jihun.chat_bot.metaChecker.system.v2;

import com.jihun.chat_bot.metaChecker.MetaCheckType;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import static com.jihun.chat_bot.metaChecker.MetaCheckType.MATCH_FAIL_PARTLY_MATCHED;
import static com.jihun.chat_bot.metaChecker.MetaCheckType.MATCH_FAIL_TOTALLY;
import static com.jihun.chat_bot.metaChecker.MetaCheckType.MATCH_SUCCESS;

public class ExitMetaChecker extends LastMetaChecker {
    private static final Set<String> MATCHER = Set.of("e", "exit");
    private static final int META_LENGTH = 2;

    public MetaCheckType check(List<String> metas) {
        if (Objects.isNull(metas)) {
            return MATCH_FAIL_TOTALLY;
        }

        if (isNotCheckable(metas)) {
            return MATCH_FAIL_TOTALLY;
        }

        if (isMatch(metas)) {
            return MATCH_SUCCESS;
        }

        if (isMetaPartlyMatched(metas)) {
            return MATCH_FAIL_PARTLY_MATCHED;
        }

        return MATCH_FAIL_TOTALLY;
    }

    public boolean isNotCheckable(List<String> metas) {
        return metas.size() != META_LENGTH;
    }

    private boolean isMatch(List<String> metas) {
        return MATCHER.contains(metas.get(META_POSITION));
    }

    protected boolean isMetaPartlyMatched(List<String> metas) {
        String meta = metas.get(META_POSITION);

        return MATCHER.stream().anyMatch(m -> m.charAt(0) == meta.charAt(0));
    }

    public Set<String> getPossibleMeta() {
        return MATCHER;
    }
}
