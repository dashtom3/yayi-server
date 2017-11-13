package com.yayiabc.http.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.yayiabc.http.mvc.pojo.jpa.Train;
import com.yayiabc.http.mvc.pojo.jpa.TrainDetail;
import com.yayiabc.http.mvc.pojo.jpa.TrainOrdera;
@Component
public interface TrainShowServiceDao {

	List<Train> show(@Param("classly")String classly,
			@Param("currentNumber")Integer currentNumber, @Param("numberPerPage")Integer numberPerPage
			);

	int insertTrain(
			/*@Param("teacherName")String teacherName,
			@Param("trainPic")String trainPic, 
			@Param("trainName")String trainName,
			@Param("classly")String classly, 
			@Param("trainPrice")String trainPrice,
			@Param("trainCtime")String trainCtime, 
			@Param("trainEtime")String trainEtime,
			@Param("trainId")String trainId*/
			Train train
			);

	int insertTrainDetails(

			@Param("trainKey")int trainKey,
			@Param("trainDetail")TrainDetail trainDetail
			);

	Train trainDetails(@Param("trainId")String trainId);

	Train queryTrainPrice(String trainId);

	int insertTrainOrder(
			TrainOrdera trainOrdera);

	int updateTrainOrderaState(@Param("trainOrderaId")String trainOrderaId);

	int queryCount(@Param("classly")String classly);

	List<TrainDetail> getAllTrainDetails();

	String getTrainId(@Param("out_trade_no")String out_trade_no);
}
