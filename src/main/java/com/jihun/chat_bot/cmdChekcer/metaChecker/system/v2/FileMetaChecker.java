package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v2;

import com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import static com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType.MATCH_FAIL_PARTLY_MATCHED;
import static com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType.MATCH_FAIL_TOTALLY;
import static com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType.MATCH_SUCCESS;

public class FileMetaChecker extends AbstractMetaChecker {
    private static final Set<String> MATCHER = Set.of("f", "file");

    public FileMetaChecker(List<MetaChecker> nextMetaCheckers) {
        super(nextMetaCheckers);
    }

    public MetaCheckType check(List<String> metas) {
        if (Objects.isNull(metas) || metas.isEmpty()) {
            return MATCH_FAIL_TOTALLY;
        }

        if (isEndCheckerButLeftMeta(metas)) {
            return MetaCheckType.MATCH_FAIL_TOTALLY;
        }

        if (isMeta(metas)) {
            return MATCH_SUCCESS;
        }

        if (isMetaPartlyMatched(metas)) {
            return MATCH_FAIL_PARTLY_MATCHED;
        }

        return MATCH_FAIL_TOTALLY;
    }


    private boolean isEndCheckerButLeftMeta(List<String> metas) {
        return metas.size() >= 2 && nextMetaCheckers.isEmpty();
    }

    
    private boolean isMeta(List<String> metas) {
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
