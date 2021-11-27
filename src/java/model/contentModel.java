package model;

public class contentModel {
	private String Id = null;
	private String Brief = null;
	private String CreatedDate = null;
	private int NumPage = 0;
	private int NumId = 0;
	private String Titles = null;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getBrief() {
		return Brief;
	}
	public void setBrief(String brief) {
		Brief = brief;
	}
	public String getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}
	public int getNumPage() {
		return NumPage;
	}
	public void setNumPage(int numPage) {
		NumPage = numPage;
	}
	public int getNumId() {
		return NumId;
	}
	public void setNumId(int numId) {
		NumId = numId;
	}
	public String getTitles() {
		return Titles;
	}
	public void setTitles(String titles) {
		Titles = titles;
	}
	public contentModel(String id,String Title,String brief,String CreateDate) {
		Id=id;
		Titles=Title;
		Brief=brief;
		CreatedDate=CreateDate;
	}


}
