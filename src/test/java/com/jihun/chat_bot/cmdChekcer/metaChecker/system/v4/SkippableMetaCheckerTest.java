package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.support.TestMetaChecker;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SkippableMetaCheckerTest extends CommonMetaCheckerTest {
    private static final List<String> BANNER_META_VALUES = List.of("b", "banner");
    private static final List<String> EXIT_META_VALUES = List.of("e", "exit");

    private static final MetaChecker BASE_META_CHECKER = CompositeMetaChecker.of(
            List.of(
                    TestMetaChecker.from(BANNER_META_VALUES),
                    TestMetaChecker.from(EXIT_META_VALUES)
            )
    );

    @Override
    protected MetaChecker getMetaChecker() {
        return new SkippableMetaChecker(BASE_META_CHECKER);
    }

    @Override
    protected Collection<String> getAllMatchMetas() {
        return Stream.of("any", "one", "else")
                .flatMap(s -> BANNER_META_VALUES.stream())
                .flatMap(s -> EXIT_META_VALUES.stream())
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    protected Collection<String> getNonMatchMetas() {
        return Collections.emptyList();
    }
}
