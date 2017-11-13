package com.yayiabc.http.mvc.service;

import java.util.List;

import com.yayiabc.common.utils.DataWrapper;
import com.yayiabc.http.mvc.pojo.jpa.Train;
import com.yayiabc.http.mvc.pojo.jpa.TrainDetail;
import com.yayiabc.http.mvc.pojo.jpa.TrainOrdera;
import com.yayiabc.http.mvc.pojo.jpa.User;

public interface TrainShowService {

	DataWrapper<List<Train>> show(String classly, Integer currentPage, Integer numberPerpage, Integer state);

	DataWrapper<Void> releaseTrain(Train  train, TrainDetail  trainDetails);

	 DataWrapper<Train> trainDetails(String trainId);

	DataWrapper<Object> confirmRegistration(String token,TrainOrdera trainOrdera);
	
	DataWrapper<Void> share(String trainId);
	
	DataWrapper<Void> spotFabulous(String trainId,String token);

	String getTrainId(String out_trade_no);

}
