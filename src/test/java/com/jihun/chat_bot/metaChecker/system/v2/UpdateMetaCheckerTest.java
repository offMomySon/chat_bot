package com.jihun.chat_bot.metaChecker.system.v2;

import com.jihun.chat_bot.metaChecker.message.MetaErrorMsg;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UpdateMetaCheckerTest {

    @DisplayName("정상적인 meta 이면, EMPTY error msg 를 반환합니다. ")
    @ParameterizedTest
    @ValueSource(strings = {
        "update file test",
        "update f test",
        "update bannerTest",
        "update banner test",
        "update b test",
        "update exit test",
        "update e test "
    })
    void test1(String meta) {
        //given
        String[] metas = meta.split(" ");

        BannerMetaChecker bannerMetaChecker = new BannerMetaChecker();
        FileMetaChecker fileMetaChecker = new FileMetaChecker();
        ExitMetaChecker exitMetaChecker = new ExitMetaChecker();
        UpdateMetaChecker updateMetaChecker = new UpdateMetaChecker(
            List.of(bannerMetaChecker, fileMetaChecker, exitMetaChecker));

        //when
        MetaErrorMsg actual = updateMetaChecker.createErrorMsg(List.of(metas));

        //then
        Assertions.assertThat(actual)
            .isEqualTo(MetaErrorMsg.EMPTY);
    }

    @DisplayName("정상적인 meta 이면, error msg 를 반환합니다. ")
    @ParameterizedTest
    @ValueSource(strings = {
        "update fileFAIL meta",
        "update fFAIL meat",
        "update bannerFAIL meta",
        "update bFAIL meta",
        "update exitFAIL meta",
        "update eFAIL meta "
    })
    void test2(String meta) {
        //given
        String[] metas = meta.split(" ");

        BannerMetaChecker bannerMetaChecker = new BannerMetaChecker();
        FileMetaChecker fileMetaChecker = new FileMetaChecker();
        ExitMetaChecker exitMetaChecker = new ExitMetaChecker();
        UpdateMetaChecker updateMetaChecker = new UpdateMetaChecker(
            List.of(bannerMetaChecker, fileMetaChecker, exitMetaChecker));

        //when
        MetaErrorMsg actual = updateMetaChecker.createErrorMsg(List.of(metas));

        //then
        Assertions.assertThat(actual)
            .isNotEqualTo(MetaErrorMsg.EMPTY);
    }
}