package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v2;

import java.util.List;

public abstract class AbstractMetaChecker implements MetaChecker {
    protected static final int META_POSITION = 0;

    protected abstract boolean isMetaPartlyMatched(List<String> metas);
}
