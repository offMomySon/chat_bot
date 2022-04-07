package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import java.util.Collection;
import java.util.List;

public class UpdateMetaCheckerTest extends CommonMetaCheckerTest {
    @Override
    protected MetaChecker getMetaChecker() {
        return new UpdateMetaChecker();
    }

    @Override
    protected Collection<String> getAllMatchMetas() {
        return List.of("update", "u");
    }

    @Override
    protected Collection<String> getPartialAndAllMatchMetas() {
        return List.of("update");
    }

    @Override
    protected Collection<String> getPartialMatchMetas() {
        return List.of("up");
    }

    @Override
    protected Collection<String> getNonMatchMetas() {
        return List.of("a", "apdate");
    }
}
