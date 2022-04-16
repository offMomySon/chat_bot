package com.jihun.chat_bot.checker.meta.system.v2;

import com.jihun.chat_bot.checker.meta.MetaCheckType;
import java.util.List;
import java.util.Set;

public class UpdateMetaChecker extends AbstractMetaChecker {
    private static final Set<String> MATCHER = Set.of("u", "update");

    public UpdateMetaChecker(MetaChecker nextMetaChecker, String meta) {
        super(nextMetaChecker, meta);
    }

    public Set<String> getMeta() {
        return MATCHER;
    }
}
