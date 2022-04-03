package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v3;

import com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType;
import com.jihun.chat_bot.cmdChekcer.metaChecker.message.MetaErrorMsg;
import java.util.Set;

public class TextMetaCheckerV3 extends ChainMetaCheckerV3 {
    private static final Set<String> MATCHER = Set.of("TXT");

    public TextMetaCheckerV3(MetaCheckerV3 next, String meta) {
        super(next, meta);
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
