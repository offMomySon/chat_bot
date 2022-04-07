package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.support.TestMetaChecker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompositeMetaCheckerTest extends CommonMetaCheckerTest {
    private static final List<String> BANNER_METAS = List.of("b", "ban", "banner");
    private static final List<String> EXIT_METAS = List.of("e", "exit");
    private static final List<String> TEST_METAS = List.of("t", "test");

    private static final List<String> ALL_METAS = flatValues(BANNER_METAS, EXIT_METAS, TEST_METAS);

    private final List<MetaChecker> META_CHECKERS = List.of(
            TestMetaChecker.from(BANNER_METAS),
            TestMetaChecker.from(EXIT_METAS),
            TestMetaChecker.from(TEST_METAS)
    );

    @Override
    protected MetaChecker getMetaChecker() {
        return CompositeMetaChecker.of(META_CHECKERS);
    }

    @Override
    protected Collection<String> getAllMatchMetas() {
        return ALL_METAS;
    }

    @Override
    protected Collection<String> getPartialAndAllMatchMetas() {
        return List.of(
                randomPick(BANNER_METAS).substring(0, 1),
                randomPick(EXIT_METAS).substring(0, 1),
                randomPick(TEST_METAS).substring(0, 1)
        );
    }

    @Override
    protected Collection<String> getPartialMatchMetas() {
        return List.of(
                randomPick(BANNER_METAS).charAt(0) + "_random",
                randomPick(EXIT_METAS).charAt(0) + "_random",
                randomPick(TEST_METAS).charAt(0) + "_random"
        );
    }

    @Override
    protected Collection<String> getNonMatchMetas() {
        return List.of("NONE1", "NONE2");
    }

    @SafeVarargs
    private static List<String> flatValues(List<String>... values) {
        return Stream.of(values)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList());
    }

    private static String randomPick(List<String> strings) {
        if (Objects.isNull(strings) || strings.isEmpty()) {
            return "";
        }

        List<String> newStrings = new ArrayList<>(strings);
        Collections.shuffle(newStrings);

        return newStrings.get(0);
    }
}
