package com.dxj.c2c.social.govern.report.service;

import com.dxj.c2c.social.govern.report.domain.ReportTask;
import com.dxj.c2c.social.govern.report.mapper.ReportTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 举报任务Service实现类
 */
@Service
public class ReportTaskServiceImpl implements ReportTaskService {

    /**
     * 举报任务Mapper组件
     */
    @Autowired
    private ReportTaskMapper reportTaskMapper;

    /**
     * 增加举报任务
     * @param reportTask 举报任务
     */
    @Override
    public void add(ReportTask reportTask) {
        reportTask.setVoteResult(ReportTask.VOTE_RESULT_UNKNOWN);
        reportTaskMapper.insert(reportTask);
    }

    /**
     * 根据id查询举报任务
     * @param id 举报任务id
     * @return 举报任务
     */
    @Override
    public ReportTask queryById(Long id) {
        return reportTaskMapper.selectById(id);
    }

}
