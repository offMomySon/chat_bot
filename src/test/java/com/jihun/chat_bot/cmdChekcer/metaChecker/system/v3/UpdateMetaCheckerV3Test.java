package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v3;

import com.jihun.chat_bot.cmdChekcer.metaChecker.message.MetaErrorMsg;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static com.jihun.chat_bot.cmdChekcer.metaChecker.system.v3.ChainMetaCheckerV3.EMPTY;

class UpdateMetaCheckerV3Test {

    @DisplayName("사용 가능한 meta 는 empty error msg 를 생성 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "update right",
        "update banner right",
        "update b right",
        "update exit right",
        "update e right",
        "update file right",
        "update f right"
    })
    void test1(String _metas) {
        //given
        String[] metas = _metas.split(" ");

        UpdateMetaCheckerV3 updateChecker = null;
        if (metas.length == 2) {
            TextMetaCheckerV3 textChecker = new TextMetaCheckerV3(metas[1], EMPTY);
            updateChecker = new UpdateMetaCheckerV3(metas[0], textChecker);
        }

        if (metas.length == 3) {
            TextMetaCheckerV3 textChecker = new TextMetaCheckerV3(metas[2], EMPTY);

            // update banner text
            BannerMetaCheckerV3 bannerChecker = new BannerMetaCheckerV3(metas[1], textChecker);

            // update exit text
            ExitMetaCheckerV3 exitChecker = new ExitMetaCheckerV3(metas[1], textChecker);

            // update file text
            FileMetaCheckerV3 fileChecker = new FileMetaCheckerV3(metas[1], textChecker);

            MetaCheckerV3 compositeChecker = new CompositeMetaChecker.Builder()
                .nextChecker(bannerChecker)
                .nextChecker(exitChecker)
                .nextChecker(fileChecker)
                .build();

            updateChecker = new UpdateMetaCheckerV3(metas[0], compositeChecker);
        }

        //when
        MetaErrorMsg actual = updateChecker.createMetaErrorMsg();

        //then
        Assertions.assertThat(actual)
            .isEqualTo(MetaErrorMsg.EMPTY);
    }

    @DisplayName("사용 불가한 meta 는 error msg 를 생성 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "update bannerFail case",
        "update bFail case",
        "update exitFail case",
        "update eFail case",
        "update fileFail case",
        "update fFail case"
    })
    void test2(String _metas) {
        //given
        String[] metas = _metas.split(" ");

        TextMetaCheckerV3 textChecker = new TextMetaCheckerV3(metas[2], EMPTY);

        // update banner text
        BannerMetaCheckerV3 bannerChecker = new BannerMetaCheckerV3(metas[1], textChecker);

        // update exit text
        ExitMetaCheckerV3 exitChecker = new ExitMetaCheckerV3(metas[1], textChecker);

        // update file text
        FileMetaCheckerV3 fileChecker = new FileMetaCheckerV3(metas[1], textChecker);

        MetaCheckerV3 compositeChecker = new CompositeMetaChecker.Builder()
            .nextChecker(bannerChecker)
            .nextChecker(exitChecker)
            .nextChecker(fileChecker)
            .build();

        UpdateMetaCheckerV3 updateChecker = new UpdateMetaCheckerV3(metas[0], compositeChecker);

        //when
        MetaErrorMsg actual = updateChecker.createMetaErrorMsg();

        //then
        Assertions.assertThat(actual)
            .isNotEqualTo(MetaErrorMsg.EMPTY);
    }
}