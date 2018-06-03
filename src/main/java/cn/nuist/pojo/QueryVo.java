package cn.nuist.pojo;

public class QueryVo {

	private String dataStation;
	private String dataElement;
	private String dataCity;
	private String dataYear;
	private String dataMonth;
	private Integer page = 1;
	private Integer start;
	private Integer size = 13;

	public String getDataStation() {
		return dataStation;
	}

	public void setDataStation(String dataStation) {
		this.dataStation = dataStation;
	}

	public String getDataElement() {
		return dataElement;
	}

	public void setDataElement(String dataElement) {
		this.dataElement = dataElement;
	}

	public String getDataCity() {
		return dataCity;
	}

	public void setDataCity(String dataCity) {
		this.dataCity = dataCity;
	}

	public String getDataYear() {
		return dataYear;
	}

	public void setDataYear(String dataYear) {
		this.dataYear = dataYear;
	}

	public String getDataMonth() {
		return dataMonth;
	}

	public void setDataMonth(String dataMonth) {
		this.dataMonth = dataMonth;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

}
