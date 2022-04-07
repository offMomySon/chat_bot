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
    protected Collection<String> getPartialAndAllMatchMetas() {
        return List.of("f");
    }

    @Override
    protected Collection<String> getPartialMatchMetas() {
        return List.of("fi");
    }

    @Override
    protected Collection<String> getNonMatchMetas() {
        return List.of("a", "aile");
    }
}
