//package com.jihun.chat_bot.checker.meta.system.v3;
//
//import com.jihun.chat_bot.checker.meta.result.MetaCheckResult;
//import java.util.Arrays;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//import static com.jihun.chat_bot.checker.meta.system.v3.ChainMetaCheckerV3.EMPTY;
//
//class CompositeMetaCheckerTest {
//
//    private static CompositeMetaChecker createMetaCheckerAfterUpdateMeta(String[] metas) {
//        if (metas.length == 1) {
//            TextMetaCheckerV3 textChecker = new TextMetaCheckerV3(metas[0], EMPTY);
//
//            return new CompositeMetaChecker.Builder()
//                .nextChecker(textChecker)
//                .build();
//        }
//
//        TextMetaCheckerV3 textChecker = new TextMetaCheckerV3(metas[1], EMPTY);
//        // banner text
//        BannerMetaCheckerV3 bannerChecker = new BannerMetaCheckerV3(metas[0], textChecker);
//        // exit text
//        ExitMetaCheckerV3 exitChecker = new ExitMetaCheckerV3(metas[0], textChecker);
//        // file text
//        FileMetaCheckerV3 fileChecker = new FileMetaCheckerV3(metas[0], textChecker);
//
//        return new CompositeMetaChecker.Builder()
//            .nextChecker(bannerChecker)
//            .nextChecker(exitChecker)
//            .nextChecker(fileChecker)
//            .build();
//    }
//
//    @DisplayName("사용 가능한 meta 는 비어있는 error msg 를 생성 합니다.")
//    @ParameterizedTest
//    @ValueSource(strings = {
//        "update right",
//        "update banner right",
//        "update b right",
//        "update exit right",
//        "update e right",
//        "update file right",
//        "update f right"
//    })
//    void test1(String _metas) {
//        //given
//        String[] metas = _metas.split(" ");
//
//        CompositeMetaChecker compositeChecker = createMetaCheckerAfterUpdateMeta(
//            Arrays.copyOfRange(metas, 1, metas.length));
//        UpdateMetaCheckerV3 updateChecker = new UpdateMetaCheckerV3(metas[0], compositeChecker);
//
//        //when
//        MetaCheckResult actual = updateChecker.createMetaErrorMsg();
//
//        //then
//        Assertions.assertThat(actual)
//            .isEqualTo(MetaCheckResult.EMPTY);
//    }
//
//    @DisplayName("사용 불가한 meta 는 비어있지 않은 error msg 를 생성 합니다.")
//    @ParameterizedTest
//    @ValueSource(strings = {
//        "update bannerFail case",
//        "update bFail case",
//        "update exitFail case",
//        "update eFail case",
//        "update fileFail case",
//        "update fFail case"
//    })
//    void test2(String _metas) {
//        //given
//        String[] metas = _metas.split(" ");
//
//        CompositeMetaChecker compositeChecker = createMetaCheckerAfterUpdateMeta(
//            Arrays.copyOfRange(metas, 1, metas.length));
//        UpdateMetaCheckerV3 updateChecker = new UpdateMetaCheckerV3(metas[0], compositeChecker);
//
//        //when
//        MetaCheckResult actual = updateChecker.createMetaErrorMsg();
//
//        //then
//        Assertions.assertThat(actual)
//            .isNotEqualTo(MetaCheckResult.EMPTY);
//    }
//}