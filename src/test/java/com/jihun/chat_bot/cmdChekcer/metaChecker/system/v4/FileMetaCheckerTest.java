package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import java.util.Collection;
import java.util.List;

public class FileMetaCheckerTest extends CommonMetaCheckerTest {
    @Override
    protected MetaChecker getMetaChecker() {
        return new FileMetaChecker();
    }

    @Override
    protected Collection<String> getAllMatchMetas() {
        return List.of("f", "file");
    }

    @Override
    protected Collection<String> getNonMatchMetas() {
        return List.of("a", "aile", "fi");
    }
}
