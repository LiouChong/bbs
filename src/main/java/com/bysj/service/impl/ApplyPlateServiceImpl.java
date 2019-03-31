package com.bysj.service.impl;

import com.bysj.common.request.BaseConverter;
import com.bysj.common.request.BaseServiceImpl;
import com.bysj.common.request.ObjectQuery;
import com.bysj.common.response.PageResult;
import com.bysj.common.utils.UserHandle;
import com.bysj.dao.ApplyPlateDao;
import com.bysj.entity.ApplyPlate;
import com.bysj.entity.vo.request.ApplyPlateRequest;
import com.bysj.entity.vo.response.ApplyPlateResponse;
import com.bysj.service.IApplyPlateService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 版主申请表 服务实现类
 * </p>
 *
 * @author lc
 * @since 2019-03-12
 */
@Service
public class ApplyPlateServiceImpl extends BaseServiceImpl<ApplyPlate> implements IApplyPlateService {

    @Resource
    private ApplyPlateDao applyPlateDao;
    @Resource
    private BaseConverter<ApplyPlateRequest, ApplyPlate> requestConverter;
    @Resource
    private BaseConverter<ApplyPlate, ApplyPlateResponse> responseConverter;
    @Autowired
    private UserHandle userHandle;

    @Override
    @RequiresAuthentication
    public String saveApplyPlate(ApplyPlateRequest request) throws Exception {

        Integer userId = userHandle.getUserId();
        // 若数据库中已经
        if (applyPlateDao.getInfoByUserIdAndPlateId(userId, request.getPlateId()) != null) {
            return "请勿重复申请！";
        }
        ApplyPlate applyPlate = requestConverter.convert(request, ApplyPlate.class);
        Date nowDate = new Date();

        applyPlate.setGmtCreate(nowDate);
        applyPlate.setGmtModify(nowDate);
        applyPlate.setUserId(userId);
        try {
            if (applyPlateDao.insert(applyPlate) == 1) {
                return "申请成功";
            } else {
                return "申请失败！";
            }
        } catch (Exception e) {
            return "申请失败！";
        }
    }

    @Override
    public Integer updateApplyPlate(ApplyPlateRequest request) throws Exception {
        ApplyPlate applyPlate = requestConverter.convert(request, ApplyPlate.class);
        return applyPlateDao.update(applyPlate);
    }

    @Override
    public List<ApplyPlateResponse> findAllApply(ObjectQuery query) throws Exception {
        List<ApplyPlateResponse> applyPlateList = applyPlateDao.findAllApply(query);
        return applyPlateList;
    }

    @Override
    public Integer findAllCount() {
        return applyPlateDao.findAllCount();
    }

    @Override
    public PageResult<ApplyPlateResponse> findPageApplyPlate(ObjectQuery query) throws Exception {
        return new PageResult<>(query.getPageSize(), this.findAllCount(), query.getCurrentPage(), this.findAllApply(query));
    }
}
