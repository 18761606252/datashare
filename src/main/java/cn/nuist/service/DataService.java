package cn.nuist.service;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import cn.nuist.pojo.BaseDict;
import cn.nuist.pojo.QueryVo;
import cn.nuist.pojo.Temperature;


public interface DataService {

	//下拉列表
	public List<BaseDict> findDictByCode(String code);
	
	//分类查询
	public List<Temperature> findTemperatureByVo (QueryVo vo);
	//分页用
	public Integer findTemperatureByVoCount (QueryVo vo);
	
	public Temperature findTemperatureById(BigInteger id);

	//导入
	public void importExcelInfo(InputStream in, MultipartFile file, String salaryDate) throws Exception;
	//根据dataStation导入
	public XSSFWorkbook exportExcelInfo(String dataStation) throws Exception;

	
}
