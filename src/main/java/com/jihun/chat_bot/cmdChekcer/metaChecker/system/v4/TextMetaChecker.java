package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;
import org.jetbrains.annotations.NotNull;

public class TextMetaChecker extends MetaChecker {
    private static final Meta META = Meta.empty();

    @Override
    public MetaResult valid(@NotNull Meta meta) {
        return MetaResult.ALL_MATCHED;
    }

    @Override
    protected Meta getMeta() {
        return META;
    }
}
