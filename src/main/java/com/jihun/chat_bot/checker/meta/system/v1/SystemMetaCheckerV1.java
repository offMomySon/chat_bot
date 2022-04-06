package com.jihun.chat_bot.checker.meta.system.v1;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;


//

// composite 모듈에서 실패한 모든 모듈의 cmd 모으고 메세지 생성.
// 각각의 모듈은 상위 단계의 fail cmd 를 모으는 역할.

// 테스트는 이전 달계가 정상 실행되었다고 가정하고 실행.

// 체크 모듈 - 각각의 모듈.- 각각의 모듈 - 끝 모듈.
// 각각의 모듈 리턴값
// 1. total fail
// 2. part fail
// 3. match success

// 각가의 모듈은 하위 모듈의 리턴값을 기준으로 메세지를 생성한다.

public class SystemMetaCheckerV1 {
    private static final Set<String> simpleMeta = Set.of("e", "f", "exit", "file");
    private static final Set<String> updateMeta = Set.of("u", "update");
    private static final Set<String> fileMeta = Set.of("f", "file");
    private static final Set<String> exitMeta = Set.of("e", "exit");
    private static final Set<String> bannerMeta = Set.of("b", "banner");
    private static final Pattern relativePathPattern = Pattern.compile(
        "[a-z0-9\\-._~%!$&'()*+,;=@]+(/[a-z0-9\\-._~%!$&'()*+,;=:@]+)*/?");

    public boolean isMatch(String[] meta) {
        if (isSimpleMeta(meta)) {
            return true;
        }

        if (isUpdateMeta(meta)) {
            return true;
        }

        return false;
    }

    private boolean isUpdateMeta(String[] _meta) {
        if (_meta.length < 2) {
            return false;
        }
        if (!updateMeta.contains(_meta[0])) {
            return false;
        }

        String[] nextMeta = Arrays.copyOfRange(_meta, 1, _meta.length);

        if (isExitMeta(nextMeta)) {
            return true;
        }

        if (isFileMeta(nextMeta)) {
            return true;
        }

        if (isBannerMeta(nextMeta)) {
            return true;
        }

        return false;
    }

    private boolean isFileMeta(String[] meta) {
        if (meta.length != 2) {
            return false;
        }
        return fileMeta.contains(meta[0]) && isRelativePath(meta[1]);
    }

    public boolean isRelativePath(String _path) {
        return relativePathPattern.matcher(_path).matches();
    }

    private boolean isBannerMeta(String[] meta) {
        if (meta.length == 1 &&
            !(exitMeta.contains(meta[0]) || fileMeta.contains(meta[0]))
        ) {
            return true;
        }

        if (meta.length != 2) {
            return false;
        }

        return bannerMeta.contains(meta[0]);
    }

    private boolean isExitMeta(String[] meta) {
        return meta.length == 2 && exitMeta.contains(meta[0]);
    }

    private boolean isSimpleMeta(String[] meta) {
        return meta.length == 1 && simpleMeta.contains(meta[0]);
    }
}
