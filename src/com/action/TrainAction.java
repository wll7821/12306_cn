package com.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.bean.dao.TrainDao;
import com.bean.po.Train;
import com.wll7821.filter.WebContext;

public class TrainAction {

	private TrainDao trainDao;
	public void setTrainDao(TrainDao trainDao) {
		this.trainDao = trainDao;
	}
	private Train train;
	public void setTrain(Train train) {
		this.train = train;
	}
	
	public String addTrain(){
		HttpServletRequest req = WebContext.getRequest();
		 if(null == train.getArrive_time() || null == train.getStart_time()){
			 req.setAttribute("error", "发车时间或到达时间不能为空！");
			 return "jsonp";
		 }
		
		 long lishi = train.getArrive_time().getTime() - train.getStart_time().getTime();
		 train.setLishi((int) (lishi/1000/60));
		 
		try {
			trainDao.save(train);
			req.setAttribute("success", true);
		} catch (SQLException e) {
			req.setAttribute("error", e);
		}
		
		return "jsonp";
	}
	
}
