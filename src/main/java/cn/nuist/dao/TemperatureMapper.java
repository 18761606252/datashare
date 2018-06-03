package cn.nuist.dao;

import java.math.BigInteger;
import java.util.List;

import cn.nuist.pojo.QueryVo;
import cn.nuist.pojo.Temperature;

public interface TemperatureMapper {
		//分类查询
		public List<Temperature> findTemperatureByVo(QueryVo vo); 
		//分页用，输入是vo,输出是int
		public Integer findTemperatureByVoCount(QueryVo vo);
		
		public Temperature findTemperatureById(BigInteger id);

		public List<Temperature> downloadTemperatureBydataStation(String dataStation);
		
		public Temperature findDataById(BigInteger id);
		
		public void updateDataById(Temperature t);
		
		public void deleteDataById(BigInteger id);

}
