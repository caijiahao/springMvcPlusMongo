package light.mvc.pageModel.base;

public class JsonAPI implements java.io.Serializable{

	private boolean success= false;
	
	private String errcode;
	
	private String errmsg;
	
	private String access_token;
	
	private int limit;
	
	private Object obj;

	public JsonAPI() {
		super();
		// TODO Auto-generated constructor stub
	}


	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int i) {
		this.limit = i;
	}


	public Object getObj() {
		return obj;
	}


	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
	
	
}
