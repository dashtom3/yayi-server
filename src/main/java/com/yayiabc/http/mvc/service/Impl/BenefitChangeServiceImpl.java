package com.yayiabc.http.mvc.service.Impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yayiabc.common.enums.ErrorCodeEnum;
import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.common.utils.ExcelUtil;
import com.yayiabc.common.utils.Page;
import com.yayiabc.http.mvc.dao.BenefitChangeDao;
import com.yayiabc.http.mvc.pojo.jpa.Benefit;
import com.yayiabc.http.mvc.pojo.jpa.BenefitDetail;
import com.yayiabc.http.mvc.pojo.jpa.ExcelEntry;
import com.yayiabc.http.mvc.pojo.jpa.QbRecord;
import com.yayiabc.http.mvc.service.BenefitChangeService;
import com.yayiabc.http.mvc.service.UserMyQbService;

@Service
public class BenefitChangeServiceImpl implements BenefitChangeService{
	
	@Autowired
	private BenefitChangeDao benefitChangeDao;
	
	@Autowired
	private UserMyQbService userMyQbService;

	@Override
	public DataWrapper<Void> add(String benefitName, Integer benefitQb,
			Integer benefitNum, String updated) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		try {
			Date updateTime=sdf.parse(updated);
			Benefit benefit =new Benefit();
			benefit.setBenefitName(benefitName);
			benefit.setBenefitQb(benefitQb);
			benefit.setBenefitNum(benefitNum);
			benefit.setBenefitValueNum(benefitNum);
			benefit.setUpdated(updateTime);
			benefitChangeDao.addBenefit(benefit);
			System.out.println(benefit.getBenefitId());
			Integer benefitId =benefit.getBenefitId();
			for(int i=0;i<benefitNum;i++){
				String benefitCode=UUID.randomUUID().toString();
				benefitChangeDao.addBenefitDetail(benefitId,benefitCode);
			}
			dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		} catch (ParseException e) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			e.printStackTrace();
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> use(String token, String benefitCode) {
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		//1.判断是否有该优惠码
		BenefitDetail benefitDetail =benefitChangeDao.getBenefitByBenefitCode(benefitCode);
		if(benefitDetail!=null){
			//2.判断优惠码是否被兑换过
			if(benefitDetail.getState()==1){
				//3.判断优惠码是否已经过期
				Benefit benefit =benefitChangeDao.getBenefitByBenefitId(benefitDetail.getBenefitId());
				Date data=new Date();
				if(data.getTime()<benefit.getUpdated().getTime()){
					//4.开始兑换乾币
					QbRecord qbRecord =new QbRecord();
					qbRecord.setQbRget(benefit.getBenefitQb());
					qbRecord.setRemark("优惠码兑换"+benefit.getBenefitQb()+"乾币");
					userMyQbService.add(qbRecord, token);
					//5.修改优惠码的兑换状态
					String userId=benefitChangeDao.getUserIdByToken(token);
					String phone =benefitChangeDao.getPhoneByUserId(userId);
					benefitChangeDao.updateState(benefitDetail.getBenefitCodeId(),phone);
					//6.修改优惠码未兑换的数量
					benefitChangeDao.updateBenefitValueNum(benefit.getBenefitId());
				}else{
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
					String msg="优惠码已过期";
					dataWrapper.setMsg(msg);
				}
			}else{
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				String msg="优惠码已经被兑换过";
				dataWrapper.setMsg(msg);
			}
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			String msg="优惠码不存在";
			dataWrapper.setMsg(msg);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<Benefit>> list(String benefitName, Integer enable,Integer currentPage,Integer numberPerPage) {
		DataWrapper<List<Benefit>> dataWrapper=new DataWrapper<List<Benefit>>();
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		if("".equals(benefitName)){
			benefitName=null;
		}
		Integer totalNumber=benefitChangeDao.getTotalNum(benefitName,enable);
		dataWrapper.setPage(page, totalNumber);
		Integer currentNumber =page.getCurrentNumber();
		List<Benefit> benefitList=benefitChangeDao.getBenefitList(benefitName,enable,currentNumber,numberPerPage);
		for (Benefit benefit : benefitList) {
			if(benefit.getBenefitValueNum()!=0&&benefit.getUpdated().getTime()>new Date().getTime()){
				benefit.setEnable(1);
			}else{
				benefit.setEnable(2);
			}
		}
		dataWrapper.setData(benefitList);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<BenefitDetail>> detail(Integer benefitId,
			Integer currentPage, Integer numberPerPage) {
		DataWrapper<List<BenefitDetail>> dataWrapper =new DataWrapper<List<BenefitDetail>>();
		Page page =new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		Benefit benefit =benefitChangeDao.getBenefitByBenefitId(benefitId);
		Integer totalNumber =benefit.getBenefitNum();
		dataWrapper.setPage(page, totalNumber);
		Integer currentNumber =page.getCurrentNumber();
		List<BenefitDetail> benefitDetailList =benefitChangeDao.getBenefitDetailListByBenefitId(benefitId,currentNumber,numberPerPage);
		dataWrapper.setData(benefitDetailList);
		dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> downLoad(Integer benefitId,
			HttpServletResponse response){
		DataWrapper<Void> dataWrapper =new DataWrapper<Void>();
		Benefit benefit =benefitChangeDao.getBenefitByBenefitId(benefitId);
		String fileName=benefit.getBenefitName()+new Date().getTime();
		System.out.println(fileName);
		fileName =fileName+".xls";
		List<ExcelEntry> excelEntryList=benefitChangeDao.getExcelEntryList(benefitId);
		for (ExcelEntry excelEntry : excelEntryList) {
			excelEntry.setBenefitNum(benefit.getBenefitQb());
		}
        List<Map<String,Object>> list=createExcelRecord(excelEntryList);
        String columnNames[]={"可兑换乾币数","有效期","优惠码"};//列名
        String keys[]={"benefitNum","updated","benefitCode"};//map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
        	response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
        	ServletOutputStream out = response.getOutputStream();
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            dataWrapper.setErrorCode(ErrorCodeEnum.No_Error);
        } catch (IOException e) {
            e.printStackTrace();
            dataWrapper.setErrorCode(ErrorCodeEnum.Error);
        } finally {
            if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
        return dataWrapper;
    }

	
	
	private List<Map<String, Object>> createExcelRecord(List<ExcelEntry> excelEntries) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
        ExcelEntry excelEntry=null;
        SimpleDateFormat sdf =new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        for (int j = 0; j < excelEntries.size(); j++) {
            excelEntry=excelEntries.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("benefitNum", excelEntry.getBenefitNum());
            mapValue.put("updated", sdf.format(excelEntry.getUpdated()));
            mapValue.put("benefitCode", excelEntry.getBenefitCode());
            listmap.add(mapValue);
        }
        return listmap;
    }

}
