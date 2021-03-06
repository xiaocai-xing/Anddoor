package com.wan.door.controller;

import com.wan.door.common.ErrorInfo;

import com.wan.door.common.ResponseInfo;
import com.wan.door.common.ResponseInfotwo;
import com.wan.door.dao.DelPage;
import com.wan.door.dao.PageDao;
import com.wan.door.dao.PageElement;
import com.wan.door.data.User.UserDel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

/**
 * @author: create by xiaocai-xing
 * @TODO: todo新增页面元素
 * @description: com.wan.door.controller
 * @date:2022/6/6
 */

@Slf4j
@RestController
@RequestMapping("/pageElement")
public class WebDriverController {

    private PageDao pageService;


    @Autowired
    public void setPageService(PageDao pageService){
        this.pageService = pageService;
    }
    @PostMapping("/add")
    public ResponseInfotwo savepageelement(@RequestBody PageElement pageElement, HttpSession session){


        Map<String,String> pageinfo = new HashMap();
        pageinfo.put("page_name",pageElement.getPageName());
        pageinfo.put("element_name",pageElement.getElementName());
        pageinfo.put("by_type",pageElement.getByType());
        pageinfo.put("by_value",pageElement.getByValue());
        pageinfo.put("remark",pageElement.getRemark());
        pageinfo.put("create_by",pageElement.getCreateBy());
        pageinfo.put("update_by",pageElement.getUpdateBy());

        boolean pages = pageService.pageadd(pageinfo);
        if (!pages){
            return new ResponseInfotwo(false,new ErrorInfo(520,"添加失败"));
        }else {
            return new ResponseInfotwo(true,"页面元素添加成功");
        }
    }


    //获取页面列表接口
    @RequestMapping("/pageeleList")
    public ResponseInfo UserList(){
        ResponseInfo result = new ResponseInfo();
        List<Map<String, Object>> Pageinfo = pageService.QueryPageElementList();
        result.setData(Pageinfo);
        result.setCode(1);
        return result;



    }


    @RequestMapping("/DelpageeleList")
    public ResponseInfo UserDel(@RequestBody DelPage delPage){
        ResponseInfo result = new ResponseInfo();
        if (!pageService.QueryPageName(delPage.getPage_name())){
                result.setCode(500);
        }else {
            boolean page = pageService.DelpageElements(delPage.getPage_name());
            if (!page){
                result.setCode(500);
                result.setError("删除页面元素失败！");
            }else {
                result.setCode(1);
                result.setMsg("删除成功");
            }
        }
        return result;
    }

}
