package com.jihun.chat_bot.metaChecker;

import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MetaCheckerTest {


//        /file [txt:fileName] [|r|read]
//        /file [txt:fileName] [w|write]
//        /file [txt:fileName] [a|append]
//        /file [c|cur|current|txt:fileName] [e|exit]
//        /file [c|cur|current|txt:fileName] [c|commit]
//        /file [c|cur|current|txt:fileName] [r|rollback]
//        /file [|l|list] [|ec|eo]
//        /file [|l|list] [o|open]
//        /file [|l|list] [e|exit]

    @DisplayName("성공하는 system cmd 면, null 을 반환합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"/system update exit", "/system u exit", "/system e", "/system exit", "/system f", "/system f", "/system u banner text", "/system u b banner text", "/system u banner banner text", "/system u b text", "/system u banner text", "/system u banner1 text", "/system u ban banner text", "/system update b banner text", "/system update ban banner text", "/system update ban banner text"})
    void test1(String cmd) {
        //given
        SystemMetaChecker systemMetaChecker = new SystemMetaChecker();

        //when
        String actual = systemMetaChecker.createMetaFailMessage(cmd);

        //then
        Assertions.assertThat(actual)
            .isNull();
    }

    @DisplayName("실패하는 system cmd 이면, error 메세지를 생성합니다.")
    @ParameterizedTest
    @CsvSource(value = {"/system up exit_ error: `up`는 올바르지 않은 메타 데이터 입니다. 다음의 메타 데이터중 하나를 선택해주세요. [u|update], [f|file], [e|exit]"
        , "/system upa exit_ error: `upa`는 올바르지 않은 메타 데이터 입니다. 다음의 메타 데이터중 하나를 선택해주세요. [u|update], [f|file], [e|exit]"
        , "/system updated exit_ error: `updated`는 올바르지 않은 메타 데이터 입니다. 다음의 메타 데이터중 하나를 선택해주세요. [u|update], [f|file], [e|exit]"
        , "/system au exit_ error: `au`는 올바르지 않은 메타 데이터 입니다. 다음의 메타 데이터중 하나를 선택해주세요. [u|update], [f|file], [e|exit]"
        , "/system ex_ error: `ex`는 올바르지 않은 메타 데이터 입니다. 다음의 메타 데이터중 하나를 선택해주세요. [u|update], [f|file], [e|exit]"
        , "/system aex_ error: `aex`는 올바르지 않은 메타 데이터 입니다. 다음의 메타 데이터중 하나를 선택해주세요. [u|update], [f|file], [e|exit]"}, delimiter = '_')
    void test2(String cmd, String expect) {
        //given
        SystemMetaChecker systemMetaChecker = new SystemMetaChecker();

        //when
        String actual = systemMetaChecker.createMetaFailMessage(cmd);

        //then
        Assertions.assertThat(actual)
            .isEqualTo(expect);
    }

    private String createSystemMetaFailMessage(String _cmd) {
        String[] cmd = _cmd.substring(1).split(" ");
        String firstCmd = cmd[1];

        Set<String> usableFileCmd = Set.of("file", "f", "exit", "e", "update", "u");

        if (usableFileCmd.contains(firstCmd)) {
            return null;
        }

        return "error: `" + cmd[1] + "`는 올바르지 않은 메타 데이터 입니다.\n" +
            "다음의 메타 데이터중 하나를 선택해주세요.\n" +
            "[u|update]";
    }
}