package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;

public class UpdateMetaChecker extends AbstractMetaChecker {
    private static final Meta META = Meta.from("update", "u");

    @Override
    protected Meta getMeta() {
        return META;
    }
}
