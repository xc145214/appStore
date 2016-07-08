package com.appStore.controller;

import com.appStore.entity.App;
import com.appStore.service.AppService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/8.
 */
@RequestMapping(value = "app")
@Controller
public class AppController extends BaseController {

    public AppController() {
    }

    @Resource
    AppService appService;

    /**
     * app 分页列表。
     *
     * @param start
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping(value = "list", produces = "application/json")
    @ResponseBody
    public Map<String, Object> list(
            Integer start,
            Integer limit,
            HttpServletRequest request) {

        Map<String, Object> map = new HashMap<String, Object>();
        int total = 0;
        List<App> list = new ArrayList<App>();

        try {
            total = appService.getAllCount();
            list = appService.getPageList(start, limit);
        } catch (Exception e) {
            map.put("message",e.getMessage());
            map.put("params",getParams(request));
        }
        map.put("total",total);
        map.put("rows",list);
        return map;
    }
}
