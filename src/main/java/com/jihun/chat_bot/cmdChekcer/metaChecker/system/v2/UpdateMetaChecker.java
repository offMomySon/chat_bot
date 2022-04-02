package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v2;

import com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class UpdateMetaChecker extends AbstractMetaChecker {
    private static final Set<String> MATCHER = Set.of("u", "update");

    public UpdateMetaChecker(List<MetaChecker> nextMetaCheckers) {
        super(nextMetaCheckers);
    }

    public MetaCheckType check(List<String> metas) {
        if (Objects.isNull(metas) || metas.isEmpty()) {
            return MetaCheckType.MATCH_FAIL_TOTALLY;
        }

        if (isEndCheckerButLeftMeta(metas)) {
            return MetaCheckType.MATCH_FAIL_TOTALLY;
        }

        if (isMatch(metas)) {
            return MetaCheckType.MATCH_SUCCESS;
        }

        if (isMetaPartlyMatched(metas)) {
            return MetaCheckType.MATCH_FAIL_PARTLY_MATCHED;
        }

        return MetaCheckType.MATCH_FAIL_TOTALLY;
    }
    
    private boolean isEndCheckerButLeftMeta(List<String> metas) {
        return metas.size() >= 2 && nextMetaCheckers.isEmpty();
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
