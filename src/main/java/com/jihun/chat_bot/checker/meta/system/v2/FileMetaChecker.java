package com.jihun.chat_bot.checker.meta.system.v2;

import com.jihun.chat_bot.checker.meta.MetaCheckType;
import java.util.List;
import java.util.Set;
import static com.jihun.chat_bot.checker.meta.MetaCheckType.MATCH_FAIL_PARTLY_MATCHED;
import static com.jihun.chat_bot.checker.meta.MetaCheckType.MATCH_FAIL_TOTALLY;
import static com.jihun.chat_bot.checker.meta.MetaCheckType.MATCH_SUCCESS;

public class FileMetaChecker extends AbstractMetaChecker {
    private static final Set<String> MATCHER = Set.of("f", "file");

    public FileMetaChecker(MetaChecker nextMetaChecker, String meta) {
        super(nextMetaChecker, meta);
    }

    public Set<String> getMeta() {
        return MATCHER;
    }
}
