package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;

public class FileMetaChecker extends AbstractMetaChecker {
    private static final Meta META = Meta.from("f", "file");

    @Override
    protected Meta getMeta() {
        return META;
    }
}
