package com.jihun.chat_bot.checker.meta.system.v2;

import com.jihun.chat_bot.checker.meta.MetaCheckType;
import java.util.List;
import java.util.Set;

public class TextMetaChecker extends AbstractMetaChecker {
    private static final Set<String> MATCHER = Set.of("TXT");

    public TextMetaChecker(List<MetaChecker> metaCheckers, String meta) {
        super(metaCheckers, meta);
    }

    @Override
    protected boolean isMetaPartlyMatched() {
        return false;
    }

    @Override
    public MetaCheckType check() {
        return MetaCheckType.MATCH_SUCCESS;
    }

    @Override
    public Set<String> getPossibleMeta() {
        return MATCHER;
    }
}
