package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import java.util.Collection;
import java.util.List;

public class ExitMetaCheckerTest extends CommonMetaCheckerTest {
    @Override
    protected MetaChecker getMetaChecker() {
        return new ExitMetaChecker();
    }

    @Override
    protected Collection<String> getAllMatchMetas() {
        return List.of("exit", "e");
    }

    @Override
    protected Collection<String> getPartialAndAllMatchMetas() {
        return List.of("e");
    }

    @Override
    protected Collection<String> getPartialMatchMetas() {
        return List.of("ea", "eait");
    }

    @Override
    protected Collection<String> getNonMatchMetas() {
        return List.of("a", "aait");
    }
}
