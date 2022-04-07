package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;

public class BannerMetaChecker extends MetaChecker {
    private static final Meta META = Meta.from("b", "banner");

    @Override
    protected Meta getMeta() {
        return META;
    }
}
