package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v2;

import com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType;
import java.util.List;
import java.util.Set;
import static com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType.MATCH_FAIL_PARTLY_MATCHED;
import static com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType.MATCH_FAIL_TOTALLY;
import static com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType.MATCH_SUCCESS;

public class FileMetaChecker extends AbstractMetaChecker {
    private static final Set<String> MATCHER = Set.of("f", "file");

    public FileMetaChecker(List<MetaChecker> nextMetaCheckers, String meta) {
        super(nextMetaCheckers, meta);
    }

    @Override
    public MetaCheckType check() {
        if (isMatch()) {
            return MATCH_SUCCESS;
        }

        if (isMetaPartlyMatched()) {
            return MATCH_FAIL_PARTLY_MATCHED;
        }

        return MATCH_FAIL_TOTALLY;
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
