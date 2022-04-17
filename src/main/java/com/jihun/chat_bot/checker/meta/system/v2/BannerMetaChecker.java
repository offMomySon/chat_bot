package com.jihun.chat_bot.checker.meta.system.v2;

import com.jihun.chat_bot.checker.meta.MetaCheckType;
import java.util.List;
import java.util.Set;

public class BannerMetaChecker extends AbstractMetaChecker {
    private static final Set<String> MATCHER = Set.of("b", "banner");

    public BannerMetaChecker(MetaChecker nextMetaCheckers, String meta) {
        super(nextMetaCheckers, meta);
    }

    public Set<String> getMeta() {
        return MATCHER;
    }
}
