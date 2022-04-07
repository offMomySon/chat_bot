package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v4.meta;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class MetaProviderTest {
    private static final String DELIMITER = " ";

    @DisplayName("띄어쓰기 구분자로 Meta를 차례대로 꺼냅니다.")
    @ParameterizedTest
    @CsvSource("element1, element2, element3")
    void testOfferMetaOneByOneDelimiterBySpace(String element1, String element2, String element3) {
        // given
        MetaProvider metaProvider = createMetaProvider(element1, element2, element3);

        // when-then
        Stream.of(element1, element2, element3)
                .forEach(element -> assertThatProvidedMeta(metaProvider, element));
    }

    @DisplayName("남은 메타값은 원시값으로 획득할 수 있습니다.")
    @ParameterizedTest
    @CsvSource("element1, element2, element3")
    void testIfRemainMetaThenCanResolvedByLiteral(String element1, String element2, String element3) {
        // given
        MetaProvider metaProvider = createMetaProvider(element1, element2, element3);
        String expect = String.join(DELIMITER, element2, element3);

        // when
        metaProvider.poll();
        String actual = metaProvider.resolveRemainValue();

        // then
        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("남은 요소가 없다면 예외가 발생합니다.")
    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {" ", "  "})
    void testWhenEmptyMetaTryOfferMetaThenInvokedException(String emptyOrBlank) {
        // given
        MetaProvider metaProvider = createMetaProvider(emptyOrBlank);

        // when-then
        assertThatIllegalStateException().isThrownBy(metaProvider::poll);
    }

    @DisplayName("원시값으로 획득했다면 남은 요소가 없습니다.")
    @ParameterizedTest
    @CsvSource("element1, element2, element3")
    void testIfAcquireLiteralValueThenNotRemainElement(String element1, String element2, String element3) {
        // given
        MetaProvider metaProvider = createMetaProvider(element1, element2, element3);

        // when-then
        metaProvider.resolveRemainValue();
        assertThatIllegalStateException().isThrownBy(metaProvider::poll);
    }

    private static MetaProvider createMetaProvider(String... elements) {
        String metas = String.join(DELIMITER, elements);
        System.out.println(metas);
        return MetaProvider.from(metas);
    }

    private static void assertThatProvidedMeta(MetaProvider metaProvider, String element) {
        assertThat(metaProvider.poll()).isEqualTo(Meta.from(element));
    }
}
