package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;

import java.util.Collection;
import java.util.List;

public class ExitMetaChecker extends MetaChecker {
    private static final Meta META = Meta.from("e", "exit");

    @Override
    protected Meta getMeta() {
        return META;
    }
}
