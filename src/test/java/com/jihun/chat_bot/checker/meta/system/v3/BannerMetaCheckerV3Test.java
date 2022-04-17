//package com.jihun.chat_bot.checker.meta.system.v3;
//
//import com.jihun.chat_bot.checker.meta.MetaCheckType;
//import java.util.Set;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.EmptySource;
//import org.junit.jupiter.params.provider.NullSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import static com.jihun.chat_bot.checker.meta.MetaCheckType.MATCH_FAIL_PARTLY_MATCHED;
//import static com.jihun.chat_bot.checker.meta.MetaCheckType.MATCH_FAIL_TOTALLY;
//import static com.jihun.chat_bot.checker.meta.MetaCheckType.MATCH_SUCCESS;
//import static com.jihun.chat_bot.checker.meta.system.v3.ChainMetaCheckerV3.EMPTY;
//
//class BannerMetaCheckerV3Test {
//
//    @DisplayName("체크하는 meta 와 일치하면 MATCH_SUCCESS 를 반환합니다.")
//    @ParameterizedTest
//    @ValueSource(strings = {
//        "banner",
//        "b"
//    })
//    void test1(String meta) {
//        //given
//        BannerMetaCheckerV3 bannerMetaChecker = new BannerMetaCheckerV3(meta, EMPTY);
//
//        //when
//        MetaCheckType actual = bannerMetaChecker.check();
//
//        //then
//        Assertions.assertThat(actual)
//            .isEqualTo(MATCH_SUCCESS);
//    }
//
//    @DisplayName("체크하는 meta 와 첫글자가 일치하면 MATCH_FAIL_PARTLY_MATCHED 를 반환합니다.")
//    @ParameterizedTest
//    @ValueSource(strings = {
//        "bPartly",
//        "bannerPartly"
//    })
//    void test2(String meta) {
//        //given
//        BannerMetaCheckerV3 bannerMetaCheckerV3 = new BannerMetaCheckerV3(meta, EMPTY);
//
//        //when
//        MetaCheckType actual = bannerMetaCheckerV3.check();
//
//        //then
//        Assertions.assertThat(actual)
//            .isEqualTo(MATCH_FAIL_PARTLY_MATCHED);
//    }
//
//    @DisplayName("체크하는 meta 와 일치하지 않으면 MATCH_FAIL_TOTALLY 를 반환합니다.")
//    @ParameterizedTest
//    @ValueSource(strings = {
//        "notMatch",
//        "nevereverNotMatch"
//    })
//    void test3(String meta) {
//        //given
//        BannerMetaCheckerV3 bannerMetaCheckerV3 = new BannerMetaCheckerV3(meta, EMPTY);
//
//        //when
//        MetaCheckType actual = bannerMetaCheckerV3.check();
//
//        //then
//        Assertions.assertThat(actual)
//            .isEqualTo(MATCH_FAIL_TOTALLY);
//    }
//
//    @DisplayName("체크 대상인 metas 를 반환합니다.")
//    @Test
//    void test4() {
//        //given
//        BannerMetaCheckerV3 bannerMetaChecker = new BannerMetaCheckerV3("banner", EMPTY);
//
//        //when
//        Set<String> actual = bannerMetaChecker.getCheckMetas();
//
//        //then
//        Assertions.assertThat(actual)
//            .isNotNull()
//            .isNotEmpty();
//    }
//
//    @DisplayName("비어있는 meta 를 받으면 Exception 이 발생합니다.")
//    @ParameterizedTest
//    @EmptySource
//    @NullSource
//    @ValueSource(strings = {" "})
//    void test5(String meta) {
//        //given
//        //when
//        Throwable actual = Assertions.catchThrowable(() -> new BannerMetaCheckerV3(meta, EMPTY));
//
//        //then
//        Assertions.assertThat(actual)
//            .isNotNull();
//    }
//
//}