package com.dxj.c2c.social.govern.report.service;

import com.dxj.c2c.social.govern.report.domain.ReportTask;
import com.dxj.c2c.social.govern.report.domain.ReportTaskVote;
import com.dxj.c2c.social.govern.report.mapper.ReportTaskMapper;
import com.dxj.c2c.social.govern.report.mapper.ReportTaskVoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 举报任务投票Service组件
 */
@Service
public class ReportTaskVoteServiceImpl implements ReportTaskVoteService {

    /**
     * 举报任务投票Mapper组件
     */
    @Autowired
    private ReportTaskVoteMapper reportTaskVoteMapper;
    /**
     * 举报任务Mapper组件
     */
    @Autowired
    private ReportTaskMapper reportTaskMapper;

    /**
     * 初始化评审员对举报任务的投票
     *
     * @param reviewerIds  评审员id
     * @param reportTaskId 举报任务id
     */
    @Override
    public void initVotes(List<Long> reviewerIds, Long reportTaskId) {
        for (Long reviewerId : reviewerIds) {
            ReportTaskVote reportTaskVote = new ReportTaskVote();
            reportTaskVote.setReviewerId(reviewerId);
            reportTaskVote.setReportTaskId(reportTaskId);
            reportTaskVote.setVoteResult(ReportTaskVote.UNKNOWN);

            reportTaskVoteMapper.insert(reportTaskVote);
        }
    }

    /**
     * 对举报任务执行投票
     *
     * @param reviewerId   评审员id
     * @param reportTaskId 举报任务id
     * @param voteResult   投票结果
     */
    @Override
    public void vote(Long reviewerId, Long reportTaskId, Integer voteResult) {
        ReportTaskVote reportTaskVote = new ReportTaskVote();
        reportTaskVote.setReviewerId(reviewerId);
        reportTaskVote.setReportTaskId(reportTaskId);
        reportTaskVote.setVoteResult(voteResult);
        reportTaskVoteMapper.update(reportTaskVote);
    }

    /**
     * 对举报任务进行归票
     *
     * @param reportTaskId 举报任务id
     */
    @Override
    public Boolean calculateVotes(Long reportTaskId) {
        List<ReportTaskVote> reportTaskVotes = reportTaskVoteMapper.selectByReportTaskId(reportTaskId);

        int quorum = reportTaskVotes.size() / 2 + 1;

        int approvedVotes = 0;
        int unapprovedVotes = 0;

        for (ReportTaskVote reportTaskVote : reportTaskVotes) {
            if (reportTaskVote.getVoteResult().equals(ReportTaskVote.APPROVED)) {
                approvedVotes++;
            } else if (reportTaskVote.getVoteResult().equals(ReportTaskVote.UNAPPROVED)) {
                unapprovedVotes++;
            }
        }

        if (approvedVotes >= quorum) {
            ReportTask reportTask = new ReportTask();
            reportTask.setId(reportTaskId);
            reportTask.setVoteResult(ReportTask.VOTE_RESULT_APPROVED);
            reportTaskMapper.update(reportTask);

            return true;
        } else if (unapprovedVotes >= quorum) {
            ReportTask reportTask = new ReportTask();
            reportTask.setId(reportTaskId);
            reportTask.setVoteResult(ReportTask.VOTE_RESULT_UNAPPROVED);
            reportTaskMapper.update(reportTask);

            return true;
        }

        return false;
    }

    /**
     * 查询举报任务下的所有投票
     *
     * @param reportTaskId 举报任务id
     * @return 投票
     */
    @Override
    public List<ReportTaskVote> queryByReportTaskId(Long reportTaskId) {
        return reportTaskVoteMapper.selectByReportTaskId(reportTaskId);
    }

}
