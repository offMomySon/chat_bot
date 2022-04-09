package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.strategy;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.BannerMetaChecker;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.CompositeMetaChecker;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.ExitMetaChecker;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.FileMetaChecker;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.MetaChecker;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.SkippableMetaChecker;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.TextMetaChecker;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.UpdateMetaChecker;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta.MetaProvider;
import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.result.MetaCheckResult;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class ChanningMetaCheckerTest {
    @DisplayName("update 관련 메타 데이터를 테스합니다.")
    @Nested
    class UpdateTest {
        @DisplayName("유효한 메타 데이터를 테스트 합니다.")
        @ParameterizedTest
        @CsvSource({
            "update txt, txt",
            "update b txt, txt",
            "update banner test, test",
            "update bannerTest, bannerTest",
            "update f test, test",
            "update file test, test",
            "update e txt, txt",
            "update exit test, test",
            "update fileFAIL meta, fileFAIL meta",
            "update fFAIL meat, fFAIL meat",
            "update bannerFAIL meta, bannerFAIL meta",
            "update bFAIL meta, bFAIL meta",
            "update exitFAIL meta, exitFAIL meta",
            "update eFAIL meta, eFAIL meta"
        })
        void testCheckValidMetas(String metas, String value) {
            // given
            MetaProvider metaProvider = MetaProvider.from(metas);
            ChanningMetaChecker channingMetaChecker = createUpdateChanningMetaChecker();

            // when
            MetaCheckResult metaCheckResult = channingMetaChecker.next(metaProvider);

            // then
            assertThat(metaCheckResult.isValid()).isTrue();
            assertThat(metaProvider.resolveRemainValue()).isEqualTo(value);
        }

        @DisplayName("유효하지 않는 메타 데이터를 테스트 합니다.")
        @ParameterizedTest
        @ValueSource(strings = {
            "apdate txt",
            "updatee b txt"
        })
        void testCheckInvalidMetas(String metas) {
            // given
            MetaProvider metaProvider = MetaProvider.from(metas);
            ChanningMetaChecker channingMetaChecker = createUpdateChanningMetaChecker();

            // when
            MetaCheckResult metaCheckResult = channingMetaChecker.next(metaProvider);

            // then
            assertThat(metaCheckResult.isInvalid()).isTrue();
            assertThat(metaProvider.resolveRemainValue()).isEqualTo(metas);
        }

        @NotNull
        private ChanningMetaChecker createUpdateChanningMetaChecker() {
            MetaChecker bannerMetaChecker = new BannerMetaChecker();
            MetaChecker fileMetaChecker = new FileMetaChecker();
            MetaChecker exitMetaChecker = new ExitMetaChecker();

            /* update meta */
            MetaChecker updateMetaChecker = new UpdateMetaChecker();

            /* target meta */
            MetaChecker targetMetaChecker = new SkippableMetaChecker(
                CompositeMetaChecker.of(
                    List.of(bannerMetaChecker, fileMetaChecker, exitMetaChecker)
                )
            );

            return new ChanningMetaChecker(updateMetaChecker,
                                           ChanningMetaChecker.last(targetMetaChecker));
        }
    }

    @DisplayName("test")
    @ParameterizedTest
    @CsvSource({
        "update 111 txt    sdfdsf         sdf, txt",
        "update b 222 txt, txt",
        "update banner 333 test, test",
        "update 444 bannerTest, bannerTest",
        "update f 555 test, test",
        "update file 666 test, test",
        "update e 777 txt, txt"
    })
    void test1(String meta, String expectTxt) {

        MetaProvider metaProvider = MetaProvider.from(meta);

        MetaChecker update = new UpdateMetaChecker();
        MetaChecker banner = new BannerMetaChecker();
        MetaChecker exit = new ExitMetaChecker();
        MetaChecker file = new FileMetaChecker();
        MetaChecker txt = new TextMetaChecker();

        // level 2
        MetaChecker level2 = new SkippableMetaChecker(CompositeMetaChecker.of(List.of(banner, file, exit)));

        ChanningMetaChecker metaChecker = new ChanningMetaChecker(
            update, new ChanningMetaChecker(
            level2, ChanningMetaChecker.last(txt))
        );

        MetaCheckResult actual = metaChecker.next(metaProvider);

        String s = metaProvider.resolveRemainValue();
        System.out.println(actual.getMeta());
        System.out.println(s);

        Assertions.assertThat(actual.isValid())
            .isTrue();

    }
}
