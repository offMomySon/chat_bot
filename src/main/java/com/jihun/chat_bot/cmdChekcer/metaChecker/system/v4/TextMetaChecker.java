package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import lombok.NonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TextMetaChecker extends MetaChecker {
    private static final List<String> ANY_METAS = Collections.emptyList();

    @Override
    public MetaResult valid(@NonNull String meta) {
        return MetaResult.ALL_MATCHED;
    }

    @Override
    protected Collection<String> getMetas() {
        return ANY_METAS;
    }
}
