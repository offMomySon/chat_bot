package com.jihun.chat_bot.metaChecker.system.v2;

import com.jihun.chat_bot.metaChecker.MetaCheckType;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import static com.jihun.chat_bot.metaChecker.MetaCheckType.MATCH_FAIL_PARTLY_MATCHED;
import static com.jihun.chat_bot.metaChecker.MetaCheckType.MATCH_FAIL_TOTALLY;
import static com.jihun.chat_bot.metaChecker.MetaCheckType.MATCH_SUCCESS;

public class ExitMetaChecker {
    private final static Set<String> matcher = Set.of("e", "exit");
    private final static int metaPosition = 0;

    private final List<String> metas;

    public ExitMetaChecker(List<String> metas) {
        this.metas = Collections.unmodifiableList(metas);
    }

    public MetaCheckType check() {
        if (metas.size() >= 3) {
            return MATCH_FAIL_TOTALLY;
        }

        if (metas.size() == 2 && matcher.contains(getMeta())) {
            return MATCH_SUCCESS;
        }

        if (metas.size() == 1) {
            return MATCH_SUCCESS;
        }

        if (isPartlyMatched()) {
            return MATCH_FAIL_PARTLY_MATCHED;
        }

        return MATCH_FAIL_TOTALLY;
    }

    private boolean isPartlyMatched() {
        String meta = getMeta();

        return matcher.stream().anyMatch(m -> m.charAt(0) == meta.charAt(0));
    }

    private String getMeta() {
        if (metas.size() >= 2) {
            return metas.get(metaPosition);
        }
        throw new RuntimeException("meta 를 얻을 수 없습니다.");
    }

    public Set<String> getPossibleMeta() {
        return matcher;
    }
}
