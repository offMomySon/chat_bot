package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import java.util.Collection;
import java.util.List;

public class BannerMetaCheckerTest extends CommonMetaCheckerTest {
    @Override
    protected MetaChecker getMetaChecker() {
        return new BannerMetaChecker();
    }

    @Override
    protected Collection<String> getAllMatchMetas() {
        return List.of("b", "banner");
    }

    @Override
    protected Collection<String> getNonMatchMetas() {
        return List.of("dd", "d", "danners", "bb", "brnner");
    }
}
