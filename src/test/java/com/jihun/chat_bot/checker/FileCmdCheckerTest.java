package com.jihun.chat_bot.checker;

import com.jihun.chat_bot.checker.cmd.FileCmdChecker;
import com.jihun.chat_bot.message.CmdErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileCmdCheckerTest {

    @DisplayName("사용 가능한 cmd 에서 error msg 를 생성시 exception 이 발생합니다.")
    @Test
    void test1() {

        //given
        FileCmdChecker fileCmdChecker = new FileCmdChecker("file");

        //when
        Throwable actual = Assertions.catchThrowable(() -> fileCmdChecker.createErrorMessage());

        //then
        Assertions.assertThat(actual)
            .isNotNull();
    }

    @DisplayName("사용 불가한 cmd 는 error msg 를 생성합니다.")
    @Test
    void test2() {
        //given
        FileCmdChecker fileCmdChecker = new FileCmdChecker("file_not");

        //when
        CmdErrorMessage actual = fileCmdChecker.createErrorMessage();

        //then
        Assertions.assertThat(actual)
            .isNotNull();
    }

    @DisplayName("사용 가능한 cmd 에 대해 isNotSupport 여부를 검사하면 false 를 반환합니다.")
    @Test
    void test3() {
        //given
        FileCmdChecker fileCmdChecker = new FileCmdChecker("file");

        //when
        Boolean actual = fileCmdChecker.isNotSupport();

        //then
        Assertions.assertThat(actual)
            .isFalse();
    }

    @DisplayName("사용 불가한 cmd 에 대해 isNotSupport 여부를 검사하면 true 를 반환합니다.")
    @Test
    void test4() {
        //given
        FileCmdChecker fileCmdChecker = new FileCmdChecker("file_not");

        //when
        Boolean actual = fileCmdChecker.isNotSupport();

        //then
        Assertions.assertThat(actual)
            .isTrue();
    }
}