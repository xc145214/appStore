package com.appStore.service.impl;

import com.appStore.dao.AppDAO;
import com.appStore.entity.App;
import com.appStore.service.AppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {@link AppService}的接口实现。
 * Created by Administrator on 2016/7/8.
 */
@Service("appService")
public class AppServiceImpl implements AppService {

    @Resource
    private AppDAO appDAO;
    /**
     * {@link AppService#getAllCount()}的实现。
     */
    @Override
    public Integer getAllCount() {
        return appDAO.readAllCount();
    }

    /**
     * {@link AppService#getPageList(Integer, Integer)}的实现。
     */
    @Override
    public List<App> getPageList(Integer start, Integer limit) {
        return appDAO.readPageList(start,limit);
    }
}
