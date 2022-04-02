package com.jihun.chat_bot.metaChecker.system.v2;

import com.jihun.chat_bot.cmdChekcer.metaChecker.message.MetaErrorMsg;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v2.BannerMetaChecker;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v2.ExitMetaChecker;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v2.FileMetaChecker;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v2.TextMetaChecker;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v2.UpdateMetaChecker;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UpdateMetaCheckerTest {

    @DisplayName("사용 가능한 meta 아니면, error msg 를 반환합니다. ")
    @ParameterizedTest
    @ValueSource(strings = {
        "update fileFAIL meta",
        "update file meta FAIL",
        "update fFAIL meat",
        "update f meat",
        "update f meat FAIL",
        "update bannerFAIL meta",
        "update bFAIL meta",
        "update exitFAIL meta",
        "update eFAIL meta ",
        "update text FAIL"
    })
    void test1(String meta) {
        //given
        String[] metas = meta.split(" ");

        //update txt
        TextMetaChecker t1 = new TextMetaChecker(List.of());

        //update b txt
        TextMetaChecker t2 = new TextMetaChecker(List.of());
        BannerMetaChecker b1 = new BannerMetaChecker(List.of(t2));

        // update e txt
        TextMetaChecker t3 = new TextMetaChecker(List.of());
        ExitMetaChecker e1 = new ExitMetaChecker(List.of(t3));

        // update f test
        TextMetaChecker t4 = new TextMetaChecker(List.of());
        FileMetaChecker f1 = new FileMetaChecker(List.of(t4));

        UpdateMetaChecker updateMetaChecker = new UpdateMetaChecker(
            List.of(t1, b1, e1, f1));

        //when
        MetaErrorMsg actual = updateMetaChecker.createErrorMsg(List.of(metas));

        //then
        Assertions.assertThat(actual)
            .isNotEqualTo(MetaErrorMsg.EMPTY);
    }

    @DisplayName("사용가능한 meta 이면, empty error msg 를 반환합니다. ")
    @ParameterizedTest
    @ValueSource(strings = {
        "update txt",
        "update b txt",
        "update banner test",
        "update bannerTest",
        "update f test",
        "update file test",
        "update e txt",
        "update exit test",
    })
    void test2(String meta) {
        //given
        String[] metas = meta.split(" ");

        //update txt
        TextMetaChecker t1 = new TextMetaChecker(List.of());

        //update b txt
        TextMetaChecker t2 = new TextMetaChecker(List.of());
        BannerMetaChecker b1 = new BannerMetaChecker(List.of(t2));

        // update e txt
        TextMetaChecker t3 = new TextMetaChecker(List.of());
        ExitMetaChecker e1 = new ExitMetaChecker(List.of(t3));

        // update f test
        TextMetaChecker t4 = new TextMetaChecker(List.of());
        FileMetaChecker f1 = new FileMetaChecker(List.of(t4));

        UpdateMetaChecker updateMetaChecker = new UpdateMetaChecker(
            List.of(t1, b1, e1, f1));

        //when
        MetaErrorMsg actual = updateMetaChecker.createErrorMsg(List.of(metas));

        //then
        Assertions.assertThat(actual)
            .isEqualTo(MetaErrorMsg.EMPTY);
    }
}