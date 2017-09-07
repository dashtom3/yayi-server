package com.yayiabc.http.mvc.controller.file;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.UploadFile;

@Controller
@RequestMapping("api/file")
public class FileUploadController {
	/**
	 * 为客户端颁发上传凭证
	 * @return
	 */
	@RequestMapping("getUpToken")
	@ResponseBody
	public DataWrapper<Void> getUpToken(){
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		String upToken =UploadFile.getUpToken();
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		dataWrapper.setMsg(upToken);
		return dataWrapper;
	}


	
	
}
