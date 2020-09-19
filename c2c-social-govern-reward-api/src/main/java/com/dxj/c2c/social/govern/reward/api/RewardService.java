package com.dxj.c2c.social.govern.reward.api;

import java.util.List;

/**
 * 奖励服务的接口
 */
public interface RewardService {

    /**
     * 发放奖励
     * @param reviewerIds 评审员id
     */
    void giveReward(List<Long> reviewerIds);

}
