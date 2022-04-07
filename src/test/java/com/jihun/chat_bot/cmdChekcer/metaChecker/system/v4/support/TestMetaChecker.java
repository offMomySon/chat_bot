package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.support;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.AbstractMetaChecker;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.Meta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TestMetaChecker extends AbstractMetaChecker {
    private final Meta meta;

    public TestMetaChecker(@NotNull Meta meta) {
        this.meta = meta;
    }

    public static TestMetaChecker from(List<String> metas) {
        return new TestMetaChecker(Meta.from(metas.toArray(String[]::new)));
    }

    @Override
    protected Meta getMeta() {
        return meta;
    }
}
