package com.jihun.chat_bot.cmdChekcer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateCmdCheckerTest {

    @DisplayName("사용 가능한 cmd 는 message 를 생성 합니다.")
    @Test
    void test1() {
        //given
        String command = "/date";
        DateCmdChecker dateCmdChecker = new DateCmdChecker();

        //when
        Throwable actual = Assertions.catchThrowable(() -> dateCmdChecker.createErrorMessage(command));

        //then
        Assertions.assertThat(actual)
            .isNotNull();
    }

    @DisplayName("처리 가능한 cmd 는 exception 이 발생 합니다.")
    @Test
    void test2() {
        //given
        DateCmdChecker dateCmdChecker = new DateCmdChecker();

        //when
        Throwable actual = Assertions.catchThrowable(() -> dateCmdChecker.createErrorMessage("system_not"));

        //then
        Assertions.assertThat(actual)
            .isNull();
    }

    @DisplayName("처리 불가한 cmd 는 true 를 반환 합니다.")
    @Test
    void test3() {
        //given
        DateCmdChecker dateCmdChecker = new DateCmdChecker();

        //when
        Boolean actual = dateCmdChecker.isNotSupport("system_not");

        //then
        Assertions.assertThat(actual)
            .isTrue();
    }


    @DisplayName("처리 불가한 cmd 는 false 를 반환 합니다.")
    @Test
    void test4() {
        //given
        DateCmdChecker dateCmdChecker = new DateCmdChecker();

        //when
        Boolean actual = dateCmdChecker.isNotSupport("date");

        //then
        Assertions.assertThat(actual)
            .isFalse();
    }
}