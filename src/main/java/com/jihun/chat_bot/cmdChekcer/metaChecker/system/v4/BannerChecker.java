package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import java.util.Set;

public class BannerChecker {
    private static final Set<String> METAS = Set.of("b", "banner");

    public boolean valid(String meta) {
        return METAS.contains(meta);
    }
}
