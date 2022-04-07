package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.support.TestMetaChecker;

import java.util.Collection;
import java.util.List;

public class MetaCheckerTest extends CommonMetaCheckerTest{
    //Note. 데이터의 순서 변경시 테스트의 의도가 깨질 수 있습니다.
    private static final List<String> TEST_METAS = List.of("t", "test", "m", "meta");

    @Override
    protected MetaChecker getMetaChecker() {
        return TestMetaChecker.from(TEST_METAS);
    }

    @Override
    protected Collection<String> getAllMatchMetas() {
        return List.of("t", "test", "m", "meta");
    }

    @Override
    protected Collection<String> getNonMatchMetas() {
        return List.of("b", "ba", "banner", "tt", "mm");
    }
}
