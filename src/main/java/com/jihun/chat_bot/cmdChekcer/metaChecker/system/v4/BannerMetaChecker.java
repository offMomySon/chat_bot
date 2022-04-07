package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import java.util.Collection;
import java.util.List;

public class BannerMetaChecker extends MetaChecker {
    private static final List<String> METAS = List.of("b", "banner");

    @Override
    protected Collection<String> getMetas() {
        return METAS;
    }
}
