package com.yayiabc.http.mvc.service;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.model.AppBanner;

import java.util.List;

/**
 * Created by 小月亮 on 2017/9/5.
 */
public interface AppBannerService {
    DataWrapper<Void> add(AppBanner appBanner);

    DataWrapper<Void> delete(Integer appBannerId);

    DataWrapper<Void> update(AppBanner appBanner);

    DataWrapper<List<AppBanner>> query();
}
