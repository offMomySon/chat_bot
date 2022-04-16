//package com.jihun.chat_bot.checker.meta.system.v2;
//
//import com.jihun.chat_bot.checker.meta.MetaCheckType;
//import com.jihun.chat_bot.message.MetaErrorMsg;
//import java.util.Collections;
//import java.util.List;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//
//class UpdateMetaCheckerTest {
//
//    @DisplayName("사용 가능한 meta 아니면, error msg 를 반환합니다. ")
//    @ParameterizedTest
//    @ValueSource(strings = {
//        "update fileFAIL meta",
//        "update fFAIL meat",
//        "update bannerFAIL meta",
//        "update bFAIL meta",
//        "update exitFAIL meta",
//        "update eFAIL meta "
//    })
//    void test1(String meta) {
//        //given
//        String[] metas = meta.split(" ");
//
//        // update b txt
//        TextMetaChecker t2 = new TextMetaChecker(List.of(), metas[2]);
//        BannerMetaChecker b1 = new BannerMetaChecker(List.of(t2), metas[1]);
//
//        // update e txt
//        TextMetaChecker t3 = new TextMetaChecker(List.of(), metas[2]);
//        ExitMetaChecker e1 = new ExitMetaChecker(List.of(t3), metas[1]);
//
//        // update f test
//        TextMetaChecker t4 = new TextMetaChecker(List.of(), metas[2]);
//        FileMetaChecker f1 = new FileMetaChecker(List.of(t4), metas[1]);
//
//        UpdateMetaChecker updateMetaChecker = new UpdateMetaChecker(List.of(b1, e1, f1), metas[1]);
//
//        //when
//        MetaErrorMsg actual = updateMetaChecker.createErrorMsg();
//
//        System.out.println(actual);
//
//        //then
//        Assertions.assertThat(actual)
//            .isNotEqualTo(MetaErrorMsg.EMPTY);
//    }
//
//    @DisplayName("사용가능한 meta 이면, empty error msg 를 반환합니다. ")
//    @ParameterizedTest
//    @ValueSource(strings = {
//        "update txt",
//        "update b txt",
//        "update banner test",
//        "update bannerTest",
//        "update f test",
//        "update file test",
//        "update e txt",
//        "update exit test",
//    })
//    void test2(String meta) {
//        //given
//        String[] metas = meta.split(" ");
//
//        //update txt
//        TextMetaChecker t1 = null;
//        if (metas.length == 2) {
//            t1 = new TextMetaChecker(List.of(), metas[1]);
//        }
//
//        TextMetaChecker t2 = null;
//        BannerMetaChecker b1 = null;
//        TextMetaChecker t3 = null;
//        ExitMetaChecker e1 = null;
//        TextMetaChecker t4 = null;
//        FileMetaChecker f1 = null;
//
//        if (metas.length == 3) {
//            //update b txt
//            t2 = new TextMetaChecker(List.of(), metas[2]);
//            b1 = new BannerMetaChecker(List.of(t2), metas[1]);
//
//            // update e txt
//            t3 = new TextMetaChecker(List.of(), metas[2]);
//            e1 = new ExitMetaChecker(List.of(t3), metas[1]);
//
//            // update f test
//            t4 = new TextMetaChecker(List.of(), metas[2]);
//            f1 = new FileMetaChecker(List.of(t4), metas[1]);
//        }
//
//        UpdateMetaChecker updateMetaChecker = null;
//        if (metas.length == 2) {
//            updateMetaChecker = new UpdateMetaChecker(List.of(t1), metas[0]);
//        }
//        if (metas.length == 3) {
//            updateMetaChecker = new UpdateMetaChecker(
//                List.of(b1, e1, f1), metas[0]);
//        }
//
//        //when
//        MetaErrorMsg actual = updateMetaChecker.createErrorMsg();
//
//        //then
//        Assertions.assertThat(actual)
//            .isEqualTo(MetaErrorMsg.EMPTY);
//    }
//
//    @DisplayName("사용가능한 update meta 를 받으면 MATCH_SUCCESS 를 반환합니다.")
//    @ParameterizedTest
//    @ValueSource(strings = {
//        "update",
//        "u"
//    })
//    void test3(String _meta) {
//        //given
//        UpdateMetaChecker updateMetaChecker = new UpdateMetaChecker(Collections.emptyList(), _meta);
//
//        //when
//        MetaCheckType actual = updateMetaChecker.check();
//
//        //then
//        Assertions.assertThat(actual)
//            .isEqualTo(MetaCheckType.MATCH_SUCCESS);
//    }
//
//    @DisplayName("첫글자만 일치하는 meta 를 받으면 MATCH_FAIL_PARTLY_MATCHED 를 반환합니다.")
//    @ParameterizedTest
//    @ValueSource(strings = {
//        "updatePARTLY",
//        "uPARTLY"
//    })
//    void test4(String _meta) {
//        //given
//        UpdateMetaChecker updateMetaChecker = new UpdateMetaChecker(Collections.emptyList(), _meta);
//
//        //when
//        MetaCheckType actual = updateMetaChecker.check();
//
//        //then
//        Assertions.assertThat(actual)
//            .isEqualTo(MetaCheckType.MATCH_FAIL_PARTLY_MATCHED);
//    }
//
//    @DisplayName("전혀 일치 하지 않은 meta 를 받으면 MATCH_FAIL_PARTLY_MATCHED 를 반환합니다.")
//    @ParameterizedTest
//    @ValueSource(strings = {
//        "totallyDiff",
//        "totallyDIffMeta"
//    })
//    void test5(String _meta) {
//        //given
//        UpdateMetaChecker updateMetaChecker = new UpdateMetaChecker(Collections.emptyList(), _meta);
//
//        //when
//        MetaCheckType actual = updateMetaChecker.check();
//
//        //then
//        Assertions.assertThat(actual)
//            .isEqualTo(MetaCheckType.MATCH_FAIL_TOTALLY);
//    }
//
//}