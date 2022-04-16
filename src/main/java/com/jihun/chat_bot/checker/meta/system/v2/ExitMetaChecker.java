package com.jihun.chat_bot.checker.meta.system.v2;

import com.jihun.chat_bot.checker.meta.MetaCheckType;
import java.util.List;
import java.util.Set;

public class ExitMetaChecker extends AbstractMetaChecker {
    private static final Set<String> MATCHER = Set.of("e", "exit");

    public ExitMetaChecker(MetaChecker nextMetaChecker, String meta) {
        super(nextMetaChecker, meta);
    }

    public Set<String> getMeta() {
        return MATCHER;
    }
}
