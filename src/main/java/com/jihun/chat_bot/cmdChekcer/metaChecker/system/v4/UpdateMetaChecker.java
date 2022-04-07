package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import java.util.Collection;
import java.util.List;

public class UpdateMetaChecker extends MetaChecker {
    private static final List<String> METAS = List.of("update", "u");

    @Override
    protected Collection<String> getMetas() {
        return METAS;
    }
}
