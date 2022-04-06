package com.jihun.chat_bot.checker.meta.system.v2;

import com.jihun.chat_bot.checker.meta.MetaCheckType;
import java.util.List;
import java.util.Set;

public class UpdateMetaChecker extends AbstractMetaChecker {
    private static final Set<String> MATCHER = Set.of("u", "update");

    public UpdateMetaChecker(List<MetaChecker> nextMetaCheckers, String meta) {
        super(nextMetaCheckers, meta);
    }

    public MetaCheckType check() {
        if (isMatch()) {
            return MetaCheckType.MATCH_SUCCESS;
        }

        if (isMetaPartlyMatched()) {
            return MetaCheckType.MATCH_FAIL_PARTLY_MATCHED;
        }

        return MetaCheckType.MATCH_FAIL_TOTALLY;
    }

    private boolean isMatch() {
        return MATCHER.contains(meta);
    }

    protected boolean isMetaPartlyMatched() {
        return MATCHER.stream().anyMatch(m -> m.charAt(0) == meta.charAt(0));
    }

    public Set<String> getPossibleMeta() {
        return MATCHER;
    }
}
