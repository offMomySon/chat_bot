package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v2;

import com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType;
import java.util.List;
import java.util.Set;

public class ExitMetaChecker extends AbstractMetaChecker {
    private static final Set<String> MATCHER = Set.of("e", "exit");

    public ExitMetaChecker(List<MetaChecker> nextMetaCheckers, String meta) {
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
