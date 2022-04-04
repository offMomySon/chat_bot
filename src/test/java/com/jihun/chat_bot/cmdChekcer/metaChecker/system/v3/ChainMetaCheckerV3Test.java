package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ChainMetaCheckerV3Test {

    @DisplayName("next")
    @ParameterizedTest
    @ValueSource(strings = {
        "update banner"
    })
    void test1(String _metas) {

        String[] metas = _metas.split(" ");
    }
}