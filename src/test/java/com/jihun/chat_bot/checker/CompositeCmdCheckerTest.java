package com.jihun.chat_bot.checker;

import com.jihun.chat_bot.checker.cmd.CalCmdChecker;
import com.jihun.chat_bot.checker.cmd.CmdChecker;
import com.jihun.chat_bot.checker.cmd.CompositeCmdChecker;
import com.jihun.chat_bot.checker.cmd.DateCmdChecker;
import com.jihun.chat_bot.checker.cmd.FileCmdChecker;
import com.jihun.chat_bot.checker.cmd.SystemCmdChecker;
import com.jihun.chat_bot.message.CmdErrorMessage;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CompositeCmdCheckerTest {

    @DisplayName("사용가능한 명령어가 하나라도 있으면, false 를 반환합니다.")
    @ParameterizedTest
    @CsvSource(value = {
        "system,file_not,cal_not,date_not",
        "system_not,file,cal_not,date_not",
        "system_not,file_not,cal,date_not",
        "system_not,file_not,cal_not,date",
    })
    void test1(String systemCmd, String fileCmd, String calCmd, String dateCmd) {
        //given
        CmdChecker cmdChecker = new CompositeCmdChecker(
            List.of(new SystemCmdChecker(systemCmd),
                    new FileCmdChecker(fileCmd),
                    new CalCmdChecker(calCmd),
                    new DateCmdChecker(dateCmd)
            )
        );

        //when
        boolean actual = cmdChecker.isNotSupport();

        //then
        Assertions.assertThat(actual)
            .isFalse();

    }

    @DisplayName("사용가능한 명령어가 하나도 없으면, true 를 반환합니다.")
    @ParameterizedTest
    @CsvSource(value = {
        "system_not,file_not,cal_not,date_not",
        "system_not,file_not,cal_not,date_not",
        "system_not,file_not,cal_not,date_not",
        "system_not,file_not,cal_not,date_not",
    })
    void test2(String systemCmd, String fileCmd, String calCmd, String dateCmd) {
        //given
        CmdChecker cmdChecker = new CompositeCmdChecker(
            List.of(new SystemCmdChecker(systemCmd),
                    new FileCmdChecker(fileCmd),
                    new CalCmdChecker(calCmd),
                    new DateCmdChecker(dateCmd)
            )
        );

        //when
        boolean actual = cmdChecker.isNotSupport();

        //then
        Assertions.assertThat(actual)
            .isTrue();
    }

    @DisplayName("사용가능한 명령어가 하나라도 있으면, exception 이 발생합니다.")
    @ParameterizedTest
    @CsvSource(value = {
        "system,file_not,cal_not,date_not",
        "system_not,file,cal_not,date_not",
        "system_not,file_not,cal,date_not",
        "system_not,file_not,cal_not,date",
    })
    void test3(String systemCmd, String fileCmd, String calCmd, String dateCmd) {
        //given
        CmdChecker cmdChecker = new CompositeCmdChecker(
            List.of(new SystemCmdChecker(systemCmd),
                    new FileCmdChecker(fileCmd),
                    new CalCmdChecker(calCmd),
                    new DateCmdChecker(dateCmd)
            )
        );

        //when
        Throwable actual = Assertions.catchThrowable(() -> cmdChecker.createErrorMessage());

        //then
        Assertions.assertThat(actual)
            .isNotNull();
    }

    @DisplayName("사용가능한 명령어가 하나도 없으면, error msg 를 반환합니다.")
    @ParameterizedTest
    @CsvSource(value = {
        "system_not,file_not,cal_not,date_not",
        "system_not,file_not,cal_not,date_not",
        "system_not,file_not,cal_not,date_not",
        "system_not,file_not,cal_not,date_not",
    })
    void test4(String systemCmd, String fileCmd, String calCmd, String dateCmd) {
        //given
        CmdChecker cmdChecker = new CompositeCmdChecker(
            List.of(new SystemCmdChecker(systemCmd),
                    new FileCmdChecker(fileCmd),
                    new CalCmdChecker(calCmd),
                    new DateCmdChecker(dateCmd)
            )
        );

        //when
        CmdErrorMessage actual = cmdChecker.createErrorMessage();

        //then
        Assertions.assertThat(actual)
            .isNotNull();
    }
}
