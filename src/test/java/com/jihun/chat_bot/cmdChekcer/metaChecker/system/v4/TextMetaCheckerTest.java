package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TextMetaCheckerTest extends CommonMetaCheckerTest {
    private static final List<String> DOES_NOT_EXIST_CASED_METAS = Collections.emptyList();

    @Override
    protected MetaChecker getMetaChecker() {
        return new TextMetaChecker();
    }

    @Override
    protected Collection<String> getAllMatchMetas() {
        return List.of("any", "text", "allMatched");
    }

    @Override
    protected Collection<String> getNonMatchMetas() {
        return DOES_NOT_EXIST_CASED_METAS;
    }
}
