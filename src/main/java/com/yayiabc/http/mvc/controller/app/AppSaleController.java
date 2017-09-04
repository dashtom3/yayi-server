package com.yayiabc.http.mvc.controller.app;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.SaleInfo;
import com.yayiabc.http.mvc.service.AppSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 小月亮 on 2017/9/4.
 */
@Controller
@RequestMapping("api/appSale")
public class AppSaleController {
    @Autowired
    private AppSaleService appSaleService;
    @RequestMapping("register")
    @ResponseBody
    public DataWrapper<Void> register(
            @ModelAttribute SaleInfo saleInfo,
            @RequestParam(value = "code", required = true) String code
            ){
        return appSaleService.register(saleInfo,code);
    }
}
