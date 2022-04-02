//package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v3;
//
//import com.jihun.chat_bot.cmdChekcer.metaChecker.MetaChecker;
//import com.jihun.chat_bot.cmdChekcer.metaChecker.message.MetaErrorMsg;
//import java.util.Set;
//
//public class BannerTempChecker implements MetaCheckerV2 {
//
//    private static final Set<String> MATCHES = Set.of("banner", "b");
//
//    private final MetaCheckerV2 metaCheckerV2;
//
//    public BannerTempChecker(MetaCheckerV2 metaCheckerV2) {
//        this.metaCheckerV2 = metaCheckerV2;
//    }
//
//
//    public boolean checkCurrentIdxMeta() {
//        if (MATCHES.contains(metas.get(0))) {
//            return true;
//        }
//
//        return false;
//    }
//
//    public Set<String> getAdvice() {
//        return MATCHES;
//    }
//
//    public MetaErrorMsg createErrorMsg() {
//        if (metas.size() == 1) {
//            return MetaErrorMsg.create("some other type", Set.of("temp"));
//        }
//
//        for (MetaChecker metaChecker : metaCheckers) {
//            if (!metaChecker.checkCurrentIdxMeta()) {
//                return MetaErrorMsg.create(metas.get(1), nemetaCheckerxt.getAdvice());
//            }
//        }
//
//        return next.createErrorMsg();
//    }
//}
