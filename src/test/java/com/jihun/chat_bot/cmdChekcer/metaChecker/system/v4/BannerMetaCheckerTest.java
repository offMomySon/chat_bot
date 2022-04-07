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
    protected Collection<String> getPartialAndAllMatchMetas() {
        return List.of("banner");
    }

    @Override
    protected Collection<String> getPartialMatchMetas() {
        return List.of("bb", "brnner");
    }

    @Override
    protected Collection<String> getNonMatchMetas() {
        return List.of("dd", "d", "danners");
    }
}
