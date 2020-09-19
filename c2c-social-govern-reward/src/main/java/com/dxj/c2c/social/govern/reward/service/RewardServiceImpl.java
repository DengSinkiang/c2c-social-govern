package com.dxj.c2c.social.govern.reward.service;

import com.dxj.c2c.social.govern.reward.api.RewardService;
import com.dxj.c2c.social.govern.reward.domain.RewardCoin;
import com.dxj.c2c.social.govern.reward.mapper.RewardCoinMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 奖励服务的接口实现类
 */
@Service(
        version = "1.0.0",
        interfaceClass = RewardService.class,
        cluster = "failfast",
        loadbalance = "roundrobin"
)
public class RewardServiceImpl implements RewardService {

    /**
     * 奖励金币Mapper组件
     */
    @Autowired
    private RewardCoinMapper rewardCoinMapper;

    /**
     * 发放奖励
     * @param reviewerIds 评审员id
     */
    @Override
    public void giveReward(List<Long> reviewerIds) {
        for(Long reviewerId : reviewerIds) {
            RewardCoin rewardCoin = new RewardCoin();
            rewardCoin.setReviewerId(reviewerId);
            rewardCoin.setCoins(10L);
            rewardCoinMapper.insert(rewardCoin);
        }
    }

}
