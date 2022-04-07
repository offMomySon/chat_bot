package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;

public class ExitMetaChecker extends AbstractMetaChecker {
    private static final Meta META = Meta.from("e", "exit");

    @Override
    protected Meta getMeta() {
        return META;
    }
}
