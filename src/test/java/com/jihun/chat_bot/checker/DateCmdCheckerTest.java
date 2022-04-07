package com.jihun.chat_bot.checker;

import com.jihun.chat_bot.checker.cmd.DateCmdChecker;
import com.jihun.chat_bot.message.CmdErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateCmdCheckerTest {

    @DisplayName("사용 가능한 cmd 에서 error msg 를 생성시 exception 이 발생합니다.")
    @Test
    void test1() {

        //given
        DateCmdChecker dateCmdChecker = new DateCmdChecker("date");

        //when
        Throwable actual = Assertions.catchThrowable(() -> dateCmdChecker.createErrorMessage());

        //then
        Assertions.assertThat(actual)
            .isNotNull();
    }

    @DisplayName("사용 불가한 cmd 는 error msg 를 생성합니다.")
    @Test
    void test2() {
        //given
        DateCmdChecker dateCmdChecker = new DateCmdChecker("date_not");

        //when
        CmdErrorMessage actual = dateCmdChecker.createErrorMessage();

        //then
        Assertions.assertThat(actual)
            .isNotNull();
    }

    @DisplayName("사용 가능한 cmd 에 대해 isNotSupport 여부를 검사하면 false 를 반환합니다.")
    @Test
    void test3() {
        //given
        DateCmdChecker dateCmdChecker = new DateCmdChecker("date");

        //when
        Boolean actual = dateCmdChecker.isNotSupport();

        //then
        Assertions.assertThat(actual)
            .isFalse();
    }

    @DisplayName("사용 불가한 cmd 에 대해 isNotSupport 여부를 검사하면 true 를 반환합니다.")
    @Test
    void test4() {
        //given
        DateCmdChecker dateCmdChecker = new DateCmdChecker("date_not");

        //when
        Boolean actual = dateCmdChecker.isNotSupport();

        //then
        Assertions.assertThat(actual)
            .isTrue();
    }
}