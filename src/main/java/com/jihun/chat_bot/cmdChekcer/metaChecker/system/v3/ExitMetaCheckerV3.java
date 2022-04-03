package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v3;

import com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType;
import com.jihun.chat_bot.cmdChekcer.metaChecker.message.MetaErrorMsg;
import java.util.Objects;
import java.util.Set;

public class ExitMetaCheckerV3 extends ChainMetaCheckerV3 {
    private static final Set<String> MATCHER = Set.of("e", "exit");

    public ExitMetaCheckerV3(MetaCheckerV3 next, String meta) {
        super(next, meta);
    }

    @Override
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

    private boolean isMetaPartlyMatched() {
        return MATCHER.stream().anyMatch(m -> m.charAt(0) == meta.charAt(0));
    }

    @Override
    public Set<String> getPossibleMeta() {
        return MATCHER;
    }
}
