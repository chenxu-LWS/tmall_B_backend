package com.loveunited.tmall_b_backend.service.activity;

import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loveunited.tmall_b_backend.antlr.DSLInsertActivityVisitor;
import com.loveunited.tmall_b_backend.antlr.DSLLexer;
import com.loveunited.tmall_b_backend.antlr.DSLParser;
import com.loveunited.tmall_b_backend.antlr.ThrowingErrorListener;
import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.common.page.PageBean;
import com.loveunited.tmall_b_backend.entity.Activity;
import com.loveunited.tmall_b_backend.mapper.ActivityMapper;

/**
 * @author LiuWenshuo
 * Created on 2022-03-27
 */
@Service
public class ActivityService {
    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    DSLInsertActivityVisitor visitor;

    /**
     * 插入一个新的活动
     * @param activityName
     * @param DSL
     * @return
     * @throws BizException
     */
    public Integer insertActivity(String activityName, String DSL) throws BizException{
        Activity activity = new Activity();
        parseActivityDSL(activity, DSL);
        activity.setActivityName(activityName);
        activity.setOnline(0);
        activity.setDSL(DSL);
        System.out.println(activity);
        activityMapper.insertActivity(activity);
        return activity.getId();
    }
    private void parseActivityDSL(Activity activity, String DSL) throws BizException {
        try {
            ANTLRInputStream input = new ANTLRInputStream(DSL);
            DSLLexer lexer = new DSLLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            DSLParser parser = new DSLParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(ThrowingErrorListener.INSTANCE);
            ParseTree tree = parser.init();
            visitor.setActivity(activity);
            visitor.visit(tree);
        } catch (ParseCancellationException e) {
            throw new BizException(ErrInfo.DSL_SYNTAX_ERROR);
        }
    }

    /**
     * 更新活动状态
     * @param id
     * @param status
     * @return
     */
    public Integer updateActivityStatus(Integer id, Integer status) {
        return activityMapper.updateActivityStatus(id, status);
    }

    /**
     * 分页查询所有活动
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageBean<Activity> queryAllActivityByPage(Integer pageNo, Integer pageSize) {
        final List<Activity> activities =
                activityMapper.queryAllActivityByPage(pageNo * pageSize, pageSize);
        PageBean<Activity> result = new PageBean<>();
        result.setPageNo(pageNo);
        result.setPageSize(pageSize);
        result.setList(activities);
        result.setTotalNum(activityMapper.queryAllActivityTotalNum());
        return result;
    }

    /**
     * 分页查询所有在线活动
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageBean<Activity> queryOnlineActivityByPage(Integer pageNo, Integer pageSize) {
        final List<Activity> activities =
                activityMapper.queryOnlineActivityByPage(pageNo * pageSize, pageSize);
        PageBean<Activity> result = new PageBean<>();
        result.setPageNo(pageNo);
        result.setPageSize(pageSize);
        result.setList(activities);
        result.setTotalNum(activityMapper.queryOnlineActivityTotalNum());
        return result;
    }

    /**
     * 根据ID查询活动
     * @param id
     * @return
     */
    public Activity queryActivityById(Integer id) {
        return activityMapper.queryActivityById(id);
    }
}
