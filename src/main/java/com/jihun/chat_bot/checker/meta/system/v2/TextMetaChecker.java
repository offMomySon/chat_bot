package com.jihun.chat_bot.checker.meta.system.v2;

import com.jihun.chat_bot.checker.meta.MetaCheckType;
import java.util.List;
import java.util.Set;

public class TextMetaChecker extends AbstractMetaChecker {
    private static final Set<String> MATCHER = Set.of("TXT");

    public TextMetaChecker(MetaChecker metaChecker, String meta) {
        super(metaChecker, meta);
    }

    @Override
    public Set<String> getMeta() {
        return MATCHER;
    }
}
