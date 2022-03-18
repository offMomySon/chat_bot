package com.jihun.chat_bot.cmdChekcer;

import java.util.List;
import org.junit.jupiter.api.Test;

class CompositeCmdErrorCheckerTest {
    @Test
    void temp(){

        CmdErrorChecker cmdErrorChecker = new CompositeCmdErrorChecker(
                List.of(new SystemCmdChecker(),
                        new FileCmdChecker(),
                        new CalCmdChecker(),
                        new DateCmdChecker())
        );

        String _input = "Data exit";
        String[] input = _input.split(" ");


        // 들어온 input 들에 대해 적절한지 검사해야하고
        // 적절하지 않다면 view 를 생성해야한다.
        if(cmdErrorChecker.isNotSupport(input[0])){
//            System.out.println("warning :: '" + input[0] + "' 는 존재하지 않는 명령어 입니다.");
            String errorMessage = cmdErrorChecker.createErrorMessage(input[0]);

            System.out.println(errorMessage);
        }
    }
}

// 전략.
// 1. 일단 요구사항 구현
// 2. 개선 필요한 부분 생각.
// 3. 디자인 고려.

// 챗봇에서 잘못된 커맨드 에러메시지 출력 요구사항을 처리하기위한 객체 디자인 해보기

//## 프로그램 기능 요구사항
//
//        ### 프로그램 예외 처리
//
//        - 명령어 리스트에 없는 명령어를 입력했을 경우
//        - 출력창에 다음의 경고 문구를 출력해줍니다.
//        - **warning: `입력한 명령어` 명령어는 존재하지 않는 명령어 입니다.**
//
//        ```
//        > /exitt
//        warning: `exitt` 명령어는 존재하지 않는 명령어 입니다.
//        ```
//
//        - 올바른 메타 데이터가 아닌 명령어를 입력했을 경우
//        - 출력창에 다음의 에러 문구를 출력해줍니다.
//        - **error: `입력한 메타데이터`는 올바르지 않은 메타 데이터 입니다.
//        다음의 메타 데이터중 하나를 선택해주세요.
//        [메타데이터1|메타데이터2|...]**
//
//        ```
//        > /system up exit
//        error: `up`는 올바르지 않은 메타 데이터 입니다.
//        다음의 메타 데이터중 하나를 선택해주세요.
//        [u|update]
//        ```
//
//        - 입력한 임의의 메타 데이터가 요구하는 포맷이 아닌 경우
//        - 출력창에 다음의 에러 문구를 출력해줍니다.
//        - **error: `입력한 데이터`는 올바르지 않는 포맷입니다.
//        다음과 같이 포맷 양식을 지켜주세요.
//        `포맷내용`**
//
//        ```
//        > /cal 1 - 오
//        error: `오`는 올바르지 않는 포맷입니다.
//        다음과 같이 포맷 양식을 지켜주세요.
//        `숫자 데이터만 가능합니다.`
//        ```