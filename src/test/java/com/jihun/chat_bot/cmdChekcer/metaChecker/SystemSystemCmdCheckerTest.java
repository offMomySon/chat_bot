package com.jihun.chat_bot.cmdChekcer.metaChecker;

import com.jihun.chat_bot.cmdChekcer.metaChecker.system.v1.SystemMetaCheckerV1;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class SystemSystemCmdCheckerTest {

    private static List<String> provideUpdateExitRandomlyMeta() {
        List<String> metas = provideUpdateExit();

        List<String> metaWithNum = metas.stream().map(it -> it + " " + provideNumber()).collect(
            Collectors.toList());
        List<String> metaWithText = metas.stream().map(it -> it + " " + provideText()).collect(
            Collectors.toList());
        List<String> metaWithSpecialCh = metas.stream().map(it -> it + " " + provideSpecialText()).collect(
            Collectors.toList());

        return List.of(metaWithNum, metaWithText, metaWithSpecialCh).stream().flatMap(it -> it.stream())
            .collect(Collectors.toList());
    }

    private static List<String> provideUpdateBannerRandomlyMeta() {
        List<String> metas = provideUpdateBannerMeta();
        metas.addAll(List.of("u", "update"));

        List<String> metaWithNum = metas.stream().map(it -> it + " " + provideNumber()).collect(
            Collectors.toList());
        List<String> metaWithText = metas.stream().map(it -> it + " " + provideText()).collect(
            Collectors.toList());
        List<String> metaWithSpecialCh = metas.stream().map(it -> it + " " + provideSpecialText()).collect(
            Collectors.toList());

        return List.of(metaWithNum, metaWithText, metaWithSpecialCh).stream().flatMap(it -> it.stream())
            .collect(Collectors.toList());
    }

    private static List<String> provideUpdateFileRandomlyMeta() {
        List<String> metas = provideUpdateFileMeta();
        List<String> relativePath = provideRelativePath();

        List<String> metaWithRelativePath = metas.stream()
            .flatMap(meta -> relativePath.stream().map(path -> meta + " " + path))
            .collect(Collectors.toList());
        List<String> metaWithNum = metas.stream().map(it -> it + " " + provideNumber())
            .collect(Collectors.toList());
        List<String> metaWithText = metas.stream().map(it -> it + " " + provideText())
            .collect(Collectors.toList());

        return List.of(metaWithRelativePath, metaWithNum, metaWithText).stream().flatMap(it -> it.stream())
            .collect(Collectors.toList());
    }

    private static List<String> provideRelativePath() {
        return List.of("test", "test/../test", "test/../test/", "test/test/test");
    }

    private static List<String> provideUpdateExit() {
        List<String> updateMetas = List.of("u", "update");
        List<String> exitMetas = List.of("e", "exit");

        return updateMetas.stream()
            .flatMap(u -> exitMetas.stream().map(e -> u + " " + e))
            .collect(Collectors.toList());
    }

    private static List<String> provideUpdateBannerMeta() {
        List<String> updateMetas = List.of("u", "update");
        List<String> bannerMetas = List.of("b", "banner");

        return updateMetas.stream()
            .flatMap(u -> bannerMetas.stream().map(e -> u + " " + e))
            .collect(Collectors.toList());
    }

    private static List<String> provideUpdateFileMeta() {
        List<String> updateMetas = List.of("u", "update");
        List<String> fileMetas = List.of("f", "file");

        return updateMetas.stream()
            .flatMap(u -> fileMetas.stream().map(e -> u + " " + e))
            .collect(Collectors.toList());
    }

    private static String provideNumber() {
        Random random = new Random();

        return IntStream.range(0, random.nextInt(5) + 2)
            .mapToObj(it -> "" + random.nextInt(10))
            .reduce("", (x, y) -> x + y);
    }

    private static String provideText() {
        Random random = new Random();

        return IntStream.range(0, random.nextInt(5) + 2)
            .mapToObj(it -> "" + (char) ('a' + random.nextInt(26)))
            .reduce("", (x, y) -> x + y);
    }

    private static String provideSpecialText() {
        Random random = new Random();

        return IntStream.range(0, random.nextInt(5) + 2)
            .mapToObj(it -> "" + (char) ('!' + random.nextInt(14)))
            .reduce("", (x, y) -> x + y);
    }

    private static List<String> provideFileMeta() {
        return List.of("f", "file");
    }

    private static List<String> provideExitMeta() {
        return List.of("e", "exit");
    }

    ///        system [e|exit]
//        /system [f|file]
//        /system [u|update] [e|exit] [txt:bye]
//        /system [u|update][b|banner|][txt:banner]
//        /system [u|update] [f|file] [txt:relativeFilePath]

    @DisplayName("프로그램 종료 처리 meta 이면, true 을 반환 합니다.")
    @ParameterizedTest
    @MethodSource(value = "provideExitMeta")
    void test1(String _cmd) {
        //given
        SystemMetaCheckerV1 systemMetaCheckerV1 = new SystemMetaCheckerV1();
        String[] meta = _cmd.split(" ");

        //when
        Boolean actual = systemMetaCheckerV1.isMatch(meta);

        //then
        Assertions.assertThat(actual)
            .isTrue();
    }

    @DisplayName("프로그램 파일 경로 조회 meta 이면, true 을 반환 합니다.")
    @ParameterizedTest
    @MethodSource(value = "provideFileMeta")
    void test2(String _cmd) {
        //given
        SystemMetaCheckerV1 systemMetaCheckerV1 = new SystemMetaCheckerV1();
        String[] meta = _cmd.split(" ");

        //when
        Boolean actual = systemMetaCheckerV1.isMatch(meta);

        //then
        Assertions.assertThat(actual)
            .isTrue();
    }

    @DisplayName("프로그램 구동시 출력하는 문구를 변경 meta 이면, true 을 반환 합니다.")
    @ParameterizedTest
    @MethodSource(value = "provideUpdateBannerRandomlyMeta")
    void test3(String _cmd) {
        //given
        SystemMetaCheckerV1 systemMetaCheckerV1 = new SystemMetaCheckerV1();
        String[] meta = _cmd.split(" ");

        //when
        Boolean actual = systemMetaCheckerV1.isMatch(meta);

        //then
        Assertions.assertThat(actual)
            .isTrue();
    }

    @DisplayName("프로그램 종료시 출력하는 문구를 변경 meta 이면, true 을 반환 합니다.")
    @ParameterizedTest
    @MethodSource(value = "provideUpdateExitRandomlyMeta")
    void test4(String _cmd) {
        //given
        SystemMetaCheckerV1 systemMetaCheckerV1 = new SystemMetaCheckerV1();
        String[] meta = _cmd.split(" ");

        //when
        Boolean actual = systemMetaCheckerV1.isMatch(meta);

        //then
        Assertions.assertThat(actual)
            .isTrue();
    }

    @DisplayName("파일을 저장할 가상 디렉토리 공간을 지정 meta 이면, true 을 반환 합니다.")
    @ParameterizedTest
    @MethodSource(value = "provideUpdateFileRandomlyMeta")
    void test5(String _cmd) {
        //given
        SystemMetaCheckerV1 systemMetaCheckerV1 = new SystemMetaCheckerV1();
        String[] meta = _cmd.split(" ");

        //when
        Boolean actual = systemMetaCheckerV1.isMatch(meta);

        //then
        Assertions.assertThat(actual)
            .isTrue();
    }

    @DisplayName("존재 하지 않는 meta 이면, false 를 반환 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "ex",
        "fi",
        "fileNot",
        "uNot test",
        "updateNot test",
        "u bannerNot test",
        "u fileNot test",
        "u exitNot test"
    })
    void test6(String _cmd) {
        //given
        SystemMetaCheckerV1 systemMetaCheckerV1 = new SystemMetaCheckerV1();
        String[] meta = _cmd.split(" ");

        //when
        Boolean actual = systemMetaCheckerV1.isMatch(meta);

        //then
        Assertions.assertThat(actual)
            .isFalse();
    }

    @DisplayName("정해진 meta 외에 추가 meta 가 있으면, false 를 반환 합니다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "e onemore",
        "f onemore",
        "file onemore",
        "u one onemore",
        "u file test onemore",
        "u banner test onemore",
        "u banner test onemore twomore",
        "u file test onemore",
        "u exit test onemore"
    })
    void test17(String _cmd) {
        //given
        SystemMetaCheckerV1 systemMetaCheckerV1 = new SystemMetaCheckerV1();
        String[] meta = _cmd.split(" ");

        //when
        Boolean actual = systemMetaCheckerV1.isMatch(meta);

        //then
        Assertions.assertThat(actual)
            .isFalse();
    }

    @DisplayName("sd system meta 면, false 를 반환합니다.")
    @ParameterizedTest
    @ValueSource(strings = {
        "ex",
        "e not",
        "exit not",
        "fi",
        "f not",
        "file not",
        "updateNot banner test",
        "update bannerNot test",
        "updateNot exit test",
        "update exitNot test",
        "update bannerNot test",
        "updateNot banner test",
    })
    void test16(String _cmd) {
        //given
        SystemMetaCheckerV1 systemMetaCheckerV1 = new SystemMetaCheckerV1();
        String[] meta = _cmd.split(" ");

        //when
        Boolean actual = systemMetaCheckerV1.isMatch(meta);

        //then
        Assertions.assertThat(actual)
            .isFalse();
    }
}