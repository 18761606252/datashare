package cn.nuist.service;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.nuist.dao.TemperatureMapper;
import cn.nuist.pojo.BaseDict;
import cn.nuist.pojo.ExcelBean;
import cn.nuist.pojo.QueryVo;
import cn.nuist.pojo.Temperature;
import cn.nuist.utils.ExcelUtil;
import cn.nuist.dao.DictMapper;

@Service
public class DataServiceImpl implements DataService {
	
	@Autowired
	private TemperatureMapper temperatureMapper;

	@Autowired
	private DictMapper dictMapper;
	
	@Override
	public List<BaseDict> findDictByCode(String code) {
		List<BaseDict> list = dictMapper.findDictByCode(code);
		return list;
	}


	@Override
	public List<Temperature> findTemperatureByVo(QueryVo vo) {
		List<Temperature> list = temperatureMapper.findTemperatureByVo(vo);
		return list;
	}

	@Override
	public Integer findTemperatureByVoCount(QueryVo vo) {
		Integer count = temperatureMapper.findTemperatureByVoCount(vo);
		return count;
	}

	@Override
	public Temperature findTemperatureById(BigInteger id) {
		Temperature temperature=temperatureMapper.findTemperatureById(id);
		return temperature;
	}

	@Override
	public void importExcelInfo(InputStream in, MultipartFile file, String salaryDate) throws Exception {
		List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());  
	    List<Temperature> Temlist = new ArrayList<Temperature>();  
	    //遍历listob数据，把数据放到List中  
	    for (int i = 0; i < listob.size(); i++) {  
	        List<Object> ob = listob.get(i);  
	        Temperature t = new Temperature();  
	        //设置编号  
	   //     salarymanage.setSerial(SerialUtil.salarySerial());  
	        //通过遍历实现把每一列封装成一个model中，再把所有的model用List集合装载  
	        // salarymanage.setAdminId(adminId);    
	        t.setStation_id(Integer.valueOf((String) ob.get(1)));  
	        t.setYear_data(String.valueOf(ob.get(2)));  
	        t.setMon_data(String.valueOf(ob.get(3)));  
	        t.setDay_data(Integer.valueOf((String) ob.get(4)));  
	        t.setT1(String.valueOf(ob.get(5)));  
	        t.setT2(String.valueOf(ob.get(6)));  
	     
	        t.setT3(String.valueOf(ob.get(7))); 
	        t.setT4(String.valueOf(ob.get(8))); 
	        t.setT5(String.valueOf(ob.get(9))); 
	        t.setT6(String.valueOf(ob.get(10))); 
	        t.setT7(String.valueOf(ob.get(11))); 
	        t.setT8(String.valueOf(ob.get(12))); 
	        t.setT9(String.valueOf(ob.get(13))); 
	        t.setT10(String.valueOf(ob.get(14))); 
	        t.setT11(String.valueOf(ob.get(15))); 
	        t.setT12(String.valueOf(ob.get(16))); 
	        t.setT13(String.valueOf(ob.get(17))); 
	        t.setT14(String.valueOf(ob.get(18))); 
	        
	        t.setT15(String.valueOf(ob.get(19))); 
	        t.setT16(String.valueOf(ob.get(20))); 
	        t.setT17(String.valueOf(ob.get(21))); 
	        t.setT18(String.valueOf(ob.get(22))); 
	        t.setT19(String.valueOf(ob.get(23))); 
	        t.setT20(String.valueOf(ob.get(24))); 
	        t.setT21(String.valueOf(ob.get(25))); 
	        t.setT22(String.valueOf(ob.get(26))); 
	        t.setT23(String.valueOf(ob.get(27))); 
	        t.setT24(String.valueOf(ob.get(28))); 
	        
	        t.setTmax(String.valueOf(ob.get(29))); 
	        t.setTmin(String.valueOf(ob.get(30))); 
	        
	    }  
	    //批量插入  
	   // temperatureMapper.insertInfoBatch(Temlist);  
	}

	
	
	public XSSFWorkbook exportExcelInfo(String dataStation) throws Exception{
		//根据条件查询数据，把数据装载到一个list中  
	    List<Temperature> Temlist = temperatureMapper.downloadTemperatureBydataStation(dataStation);  
	    List<ExcelBean> excel=new ArrayList<>();  
	    Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();  
	    XSSFWorkbook xssfWorkbook=null;  
	    //设置标题栏  
	    excel.add(new ExcelBean("站点号","station_id",0));  
	    excel.add(new ExcelBean("年份","year_data",0));  
	    excel.add(new ExcelBean("月份","mon_data",0));  
	    excel.add(new ExcelBean("日期","day_data",0));  
	    excel.add(new ExcelBean("0点","t1",0));  
	    excel.add(new ExcelBean("1点","t2",0));  
	    excel.add(new ExcelBean("2点","t3",0));  
	    excel.add(new ExcelBean("3点","t4",0));  
	    excel.add(new ExcelBean("4点","t5",0));  
	    excel.add(new ExcelBean("5点","t6",0));  
	    excel.add(new ExcelBean("6点","t7",0));  
	    excel.add(new ExcelBean("7点","t8",0));  
	    excel.add(new ExcelBean("8点","t9",0));  
	    excel.add(new ExcelBean("9点","t10",0));  
	    excel.add(new ExcelBean("10点","t11",0));  
	    excel.add(new ExcelBean("11点","t12",0));  
	    excel.add(new ExcelBean("12点","t13",0));  
	    excel.add(new ExcelBean("13点","t14",0));  
	    excel.add(new ExcelBean("14点","t15",0));  
	    excel.add(new ExcelBean("15点","t16",0));  
	    excel.add(new ExcelBean("16点","t17",0));  
	    excel.add(new ExcelBean("17点","t18",0));  
	    excel.add(new ExcelBean("18点","t19",0));  
	    excel.add(new ExcelBean("19点","t20",0));  
	    excel.add(new ExcelBean("20点","t21",0));  
	    excel.add(new ExcelBean("21点","t22",0));  
	    excel.add(new ExcelBean("22点","t23",0));  
	    excel.add(new ExcelBean("23点","t24",0));  
	    excel.add(new ExcelBean("最大值","tmax",0));  
	    excel.add(new ExcelBean("最小值","tmin",0));  
	    map.put(0, excel);  
	    String sheetName = dataStation + "站点数据";  
	    //调用ExcelUtil的方法  
	 
	    xssfWorkbook = ExcelUtil.createExcelFile(Temperature.class, Temlist, map, sheetName);  
	    System.out.println(Temlist); 
	    return xssfWorkbook;
	}


}
