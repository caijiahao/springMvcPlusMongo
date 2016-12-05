package light.mvc.pageModel.base;

public class JsonToMobile implements java.io.Serializable{
	
	private Result result;
	
	private Notice notice;
	
	private Object obj;
	


	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	

}
