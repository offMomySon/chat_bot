package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v2;

import com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class TextMetaChecker extends AbstractMetaChecker {
    private static final Set<String> MATCHER = Set.of("TXT");

    public TextMetaChecker(List<MetaChecker> metaCheckers) {
        super(metaCheckers);
    }

    @Override
    protected boolean isMetaPartlyMatched(List<String> metas) {
        return false;
    }

    @Override
    public MetaCheckType check(List<String> metas) {
        if (Objects.isNull(metas) || metas.isEmpty()) {
            return MetaCheckType.MATCH_FAIL_TOTALLY;
        }

        if (isEndCheckerButLeftMeta(metas)) {
            return MetaCheckType.MATCH_FAIL_TOTALLY;
        }

        return MetaCheckType.MATCH_SUCCESS;
    }

    private boolean isEndCheckerButLeftMeta(List<String> metas) {
        return metas.size() >= 2 && nextMetaCheckers.isEmpty();
    }

    @Override
    public Set<String> getPossibleMeta() {
        return MATCHER;
    }
}
