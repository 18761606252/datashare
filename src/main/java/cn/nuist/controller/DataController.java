package cn.nuist.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.utils.Page;
import cn.nuist.pojo.BaseDict;
import cn.nuist.pojo.QueryVo;
import cn.nuist.pojo.Temperature;
import cn.nuist.service.DataService;
import cn.nuist.service.SessionProvider;


@Controller
@RequestMapping("/dataManage")
public class DataController {

	@Autowired
	private DataService customerService;
	@Autowired
	private SessionProvider sessionProvider;

	@Value("${customer.dict.city}")
	private String city;
	@Value("${customer.dict.element}")
	private String element;
	@Value("${customer.dict.year}")
	private String year;
	@Value("${customer.dict.month}")
	private String month;
	

	@RequestMapping("/list")
	public String list(QueryVo vo, Model model) throws Exception {
		// 数据台站
		List<BaseDict> cityList = customerService.findDictByCode(city);
		// 数据要素
		List<BaseDict> elementList = customerService.findDictByCode(element);
		// 数据来源
		List<BaseDict> yearList = customerService.findDictByCode(year);
		//数据级别
		List<BaseDict> monthList = customerService.findDictByCode(month);
		
		if(vo.getDataStation() != null){
			vo.setDataStation(new String(vo.getDataStation().getBytes("iso8859-1"), "utf-8"));
		}
		
		if(vo.getPage()==null){
			vo.setPage(1);
		}
		
		//设置查询的起始记录条数
		vo.setStart((vo.getPage()-1)* vo.getSize());
		
		
		//查询数据列表和数据总数
		List<Temperature> resultList = customerService.findTemperatureByVo(vo);
		Integer count = customerService.findTemperatureByVoCount(vo);
		
		Page<Temperature> page = new Page<Temperature>();
		page.setPage(vo.getPage()); //数据总数
		page.setSize(vo.getSize()); //每页显示条数
		page.setTotal(count); // 当前页数
		page.setRows(resultList);//数据列表
		
		model.addAttribute("page", page);
		
		//查询下拉列表数据
		model.addAttribute("cityType", cityList );
		model.addAttribute("elementType", elementList);
		model.addAttribute("yearType", yearList);
		model.addAttribute("monthType", monthList);
		
		//查询选中数据回显
		model.addAttribute("dataStation", vo.getDataStation());
		model.addAttribute("dataElement", vo.getDataElement());
		model.addAttribute("dataCity", vo.getDataCity());
		model.addAttribute("dataYear", vo.getDataYear());
		model.addAttribute("dataMonth", vo.getDataMonth());
		return "dataManage";
	}
	
	//修改信息
	@RequestMapping("/detail")
	@ResponseBody
	public Temperature detail(BigInteger id) throws Exception{
		Temperature temperature = customerService.findDataById(id);
		return temperature;
	}
	
	@RequestMapping("/download")   //导出
	public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {  
	    String dataStation = request.getParameter("dataStation");  
	    if(dataStation!=""){  
	        response.reset(); //清除buffer缓存  
	        Map<String,Object> map = new HashMap<String,Object>();  
	        // 指定下载的文件名  
	        response.setHeader("Content-Disposition", "attachment;filename="+dataStation+".xlsx");  
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
	        response.setHeader("Pragma", "no-cache");  
	        response.setHeader("Cache-Control", "no-cache");  
	        response.setDateHeader("Expires", 0);  
	        XSSFWorkbook workbook=null;  
	        //导出Excel对象  
	        workbook = customerService.exportExcelInfo(dataStation);  
	        OutputStream output;  
	        try {  
	            output = response.getOutputStream();  
	            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);  
	            bufferedOutPut.flush();  
	            workbook.write(bufferedOutPut);  
	            bufferedOutPut.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    } 
	    System.out.println("Success!");
	}   
	
	
		@RequestMapping("/update")
		public String update(Temperature t) throws Exception{
			customerService.updateDataById(t);
			return "dataManage";
		}
		
		@RequestMapping("/delete")
		public String delete(BigInteger id) throws Exception{
			customerService.deleteDataById(id);
			return "dataManage";
		}
		
		//批量删除
		@RequestMapping("/deletes")
		public String deletes(BigInteger[] ids) throws Exception{
			customerService.deleteDataByIds(ids);
			return "dataManage";
		}

}
