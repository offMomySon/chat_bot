package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;

public class TextMetaChecker extends MetaChecker {
    private static final Meta META = Meta.empty();

    @Override
    protected Meta getMeta() {
        return META;
    }
}
