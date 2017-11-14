package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.CottomsPost;
public interface ReadNumberServer{

	public DataWrapper<CottomsPost> readNumber(String token,Integer postId) ;

}
