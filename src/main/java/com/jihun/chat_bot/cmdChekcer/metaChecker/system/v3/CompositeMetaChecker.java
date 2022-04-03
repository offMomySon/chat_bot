package com.jihun.chat_bot.cmdChekcer.metaChecker.system.v3;

import com.jihun.chat_bot.cmdChekcer.metaChecker.MetaCheckType;
import com.jihun.chat_bot.cmdChekcer.metaChecker.message.MetaErrorMsg;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CompositeMetaChecker implements MetaCheckerV3 {
    private final List<MetaCheckerV3> metaCheckers;
    private final String meta;

    public CompositeMetaChecker(List<MetaCheckerV3> metaCheckers, String meta) {
        this.metaCheckers = validateMetaCheckers(metaCheckers);
        this.meta = validateMeta(meta);
    }

    private String validateMeta(String meta){
        if(Objects.isNull(meta)){
            throw new RuntimeException("meta 가 null 입니다.");
        }
        return meta;
    }

    private List<MetaCheckerV3> validateMetaCheckers(List<MetaCheckerV3> metaCheckers){
        if(Objects.isNull(metaCheckers)){
            throw new RuntimeException("metaChecker 가 null 입니다.");
        }

        metaCheckers = metaCheckers.stream().filter(ch-> Objects.nonNull(ch)).collect(Collectors.toList());

        if(metaCheckers.isEmpty()){
            throw new RuntimeException("metaCheckers 가 존재하지 않습니다.");
        }

        return  metaCheckers;
    }

    @Override
    public MetaCheckType check() {
        return null;
    }

    @Override
    public MetaErrorMsg createMetaErrorMsg() {
        if (metaCheckers.isEmpty()) {
            return MetaErrorMsg.EMPTY;
        }

        Set<String> partlyMatchedMetas = new HashSet<>();
        Set<String> totallyFailedMetas = new HashSet<>();

        for (MetaCheckerV3 metaChecker : metaCheckers) {
            MetaCheckType check = metaChecker.check();

            if (check == MetaCheckType.MATCH_SUCCESS) {
                return metaChecker.createMetaErrorMsg();
            }

            if (check == MetaCheckType.MATCH_FAIL_PARTLY_MATCHED) {
                partlyMatchedMetas.addAll(metaChecker.getPossibleMeta());
            }

            if (check == MetaCheckType.MATCH_FAIL_TOTALLY) {
                totallyFailedMetas.addAll(metaChecker.getPossibleMeta());
            }
        }

        if (partlyMatchedMetas.isEmpty()) {
            return MetaErrorMsg.create(meta, totallyFailedMetas);
        }

        return MetaErrorMsg.create(meta, partlyMatchedMetas);
    }

    @Override
    public Set<String> getPossibleMeta() {
        return null;
    }
}
