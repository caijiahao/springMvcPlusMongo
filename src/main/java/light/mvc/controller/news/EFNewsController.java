package light.mvc.controller.news;


import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.SessionInfo;
import light.mvc.pageModel.news.EFCategory;
import light.mvc.pageModel.news.EFNews;
import light.mvc.pageModel.sys.EFResourceMeta;
import light.mvc.service.news.EFCategoryServiceI;
import light.mvc.service.news.EFNewsServiceI;
import light.mvc.service.sys.DictionaryServiceI;
import light.mvc.service.sys.ResourceMetaServiceI;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/news")
public class EFNewsController extends BaseController{

	@Autowired
	private EFNewsServiceI newsService;
	
	@Autowired
	private EFCategoryServiceI categoryService;
	
	@Autowired
	private DictionaryServiceI dictService;
	
	@Autowired
	private ResourceMetaServiceI resourceMetaService;
	
	/**
	 * 跳转到列表页面
	 * @return
	 */
	@RequestMapping("/newsPage")
	public String get_news_page(HttpServletRequest request) {
		request.setAttribute("categoryTypeID", dictService.getDictionary("NewsCategoryType", "News").getId());
		request.setAttribute("categoryList", getCategoryByTypeId(dictService.getDictionary("NewsCategoryType", "News").getId()));
		
		return "/eumode/news/news";
	}
	
	@RequestMapping("/informationPage")
	public String get_information_page(HttpServletRequest request) {
		request.setAttribute("categoryTypeID", dictService.getDictionary("NewsCategoryType", "Information").getId());
		request.setAttribute("categoryList", getCategoryByTypeId(dictService.getDictionary("NewsCategoryType", "Information").getId()));
		
		return "/eumode/news/news";
	}
	
	@RequestMapping("/reportPage")
	public String get_report_page(HttpServletRequest request) {
		request.setAttribute("categoryTypeID", dictService.getDictionary("NewsCategoryType", "Report").getId());
		request.setAttribute("categoryList", getCategoryByTypeId(dictService.getDictionary("NewsCategoryType", "Report").getId()));
				
		return "/eumode/news/news";
	}
	
	@RequestMapping("/baseConstructionPage")
	public String get_baseConstruction(HttpServletRequest request) {
		request.setAttribute("categoryTypeID", dictService.getDictionary("NewsCategoryType", "BaseConstruction").getId());
		request.setAttribute("categoryList", getCategoryByTypeId(dictService.getDictionary("NewsCategoryType", "BaseConstruction").getId()));
		
		return "/eumode/news/news";
	}
	
	private List<EFCategory> getCategoryByTypeId(Long id){
		EFCategory c = new EFCategory();
		c.setType(id);
		List<EFCategory> categoryList = categoryService.getAllData(c);
		return categoryList;
	}
	
	/**
	 * 获取列表数据
	 * @param news
	 * @param ph
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public  Grid data_grid(EFNews news, Integer selectPersonal, PageFilter ph,HttpServletRequest request) {
		Grid grid = new Grid();
		
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
		news.setPersonalID(sessionInfo.getId());	//默认只搜索出本人添加的记录
		if(selectPersonal != null && selectPersonal == 1){
			//搜出全部人添加的记录
			news.setPersonalID(null);
		}

		List<EFNews> list = newsService.dataGrid(news, ph);
		for(EFNews t : list){
			t.setStatusDesc(dictService.get(t.getStatus()).getText());
			t.setCategoryName(categoryService.get(t.getCategoryID()).getCategoryName());
		}
		grid.setRows(list);
		grid.setTotal(newsService.count(news,ph));
		return grid;
	}
	
	/**
	 * 删除操作
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String ids,HttpServletRequest request) {
		JSONArray jsonIds;
		Json j = new Json();
		try {
			jsonIds = new JSONArray(ids);
			for (int i = 0; i < jsonIds.length(); i++) {
				long id = jsonIds.getLong(i);
				newsService.delete(id);
				
				//删除相关附件
				EFResourceMeta rm = new EFResourceMeta();
				rm.setType(dictService.getDictionary("ResourceMetaType", "NewsInformation").getId());
				rm.setMetaID(id);
				List<EFResourceMeta> list = resourceMetaService.dataGrid(rm);
				for(EFResourceMeta t : list){
					resourceMetaService.delete(t.getAutoID());
				}
			}
			
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 删除附件操作
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteAttachment")
	@ResponseBody
	public Json deleteAttachment(String id, String newsid, HttpServletRequest request){
		Json j = new Json();
		try {
			//删除相关附件
			EFResourceMeta rm = resourceMetaService.get(Long.parseLong(id));
			if(rm.getType()==dictService.getDictionary("ResourceMetaType", "NewsInformation").getId() 
					&& rm.getMetaID() == Long.parseLong(newsid)){
				resourceMetaService.delete(rm.getAutoID());
				j.setMsg("删除附件成功！");
				j.setSuccess(true);
			}
			else{
				j.setMsg("没有权限删除该附件！");
			}
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 跳转到新增页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(Long categoryTypeID, HttpServletRequest request) {
		EFCategory c = new EFCategory();
		c.setType(categoryTypeID);
		List<EFCategory> categories = categoryService.getAllData(c);
		for(EFCategory t:categories){
			t.setTypeDesc(dictService.get(t.getType()).getText());
		}
		request.setAttribute("categories", categories);
		if(categoryTypeID == dictService.getDictionary("NewsCategoryType", "Information").getId()){
			//通知公告才允许上传附件
			request.setAttribute("allowAttachment", true);
		}
		return "/eumode/news/newsAdd";
	}
	

	/**
	 * 新增操作
	 * @param nobj
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(EFNews news, HttpServletRequest request) {
		Json j = new Json();
		//添加额外信息
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
		news.setPersonalID(sessionInfo.getId());//发布者ID
		news.setAuthor(sessionInfo.getName());//作者
		news.setReadCount(0);//浏览次数默认为0
		news.setStatus(dictService.getDictionary("NewsContentStatus", "Edit").getId());//17:编辑 | 18：发布
		
		try {
			String fileName = generateTmpArticleFileName(sessionInfo.getId().toString(),request);
			String saveFilePath = request.getSession().getServletContext().getRealPath("");
			writeContentToFile(news.getPageContent(), saveFilePath + fileName);
			news.setContent(fileName);
			Long newsId = newsService.add(news);
			if(newsId != null)
			{
				if(news.getAttachmentContent() != null && news.getAttachmentContent().length() > 0){
					String[] attachmentList = news.getAttachmentContent().split(";");
					for(String attachment : attachmentList)
					{
						if(attachment.length() == 0)
							continue;
						String[] arrayList = attachment.split("\\^");
						String attachmentName = arrayList[0];
						String attachmentPath = arrayList[1];
						
						EFResourceMeta rm = new EFResourceMeta();
						rm.setType(dictService.getDictionary("ResourceMetaType", "NewsInformation").getId());
						rm.setMetaID(newsId);
						rm.setMetaPath(attachmentPath);
						rm.setMetaDescription(attachmentName);
						resourceMetaService.add(rm);
					}
				}
			}
			j.setSuccess(true);
			j.setMsg("保存成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		
		return j;
	}

	/**
	 * 跳转到详细页面
	 * @param id
	 * @return
	 */
	@RequestMapping("detailPage")
	public String detailPage(Long id, HttpServletRequest request){
		EFNews news = newsService.get(id);
		if(news != null)
		{
			String saveFilePath = request.getSession().getServletContext().getRealPath("");
			String fileName = news.getContent();
			try
			{
				news.setPageContent(readContentFromFile(saveFilePath + fileName));
			}catch(Exception ex){
				
			}
			request.setAttribute("news", news);
			EFCategory category = categoryService.get(news.getCategoryID());
			request.setAttribute("category", category);
			EFResourceMeta rm = new EFResourceMeta();
			rm.setType(dictService.getDictionary("ResourceMetaType", "NewsInformation").getId());
			rm.setMetaID(id);
			List<EFResourceMeta> list = resourceMetaService.dataGrid(rm);
			if(list.size() > 0)
			{
				request.setAttribute("attachmentList", list);
			}
			return "/eumode/news/newsDetail";
		}
		else
		{
			List<EFCategory> categories = categoryService.getAllData(null);
			request.setAttribute("categories", categories);
			return "/eumode/news/newsAdd";
		}
	}
	
	
	/**
	 * 跳转到编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping("editPage")
	public String editPage(Long id, HttpServletRequest request){		
		EFNews news = newsService.get(id);
		if(news != null)
		{
			EFCategory category = categoryService.get(news.getCategoryID());
			EFCategory c = new EFCategory();
			c.setType(category.getType());
			
			List<EFCategory> categories = categoryService.getAllData(c);
			for(EFCategory t:categories){
				t.setTypeDesc(dictService.get(t.getType()).getText());
			}
			request.setAttribute("categories", categories);
			
			if(category.getType() == dictService.getDictionary("NewsCategoryType", "Information").getId()){
				//通知公告才允许上传附件
				request.setAttribute("allowAttachment", true);
				
				EFResourceMeta rm = new EFResourceMeta();
				rm.setType(dictService.getDictionary("ResourceMetaType", "NewsInformation").getId());
				rm.setMetaID(id);
				List<EFResourceMeta> list = resourceMetaService.dataGrid(rm);
				if(list.size() > 0)
				{
					request.setAttribute("attachmentList", list);
				}
			}
			
			String saveFilePath = request.getSession().getServletContext().getRealPath("");
			String fileName = news.getContent();
			try
			{
				news.setPageContent(readContentFromFile(saveFilePath + fileName));
			}catch(Exception ex){
				
			}
			request.setAttribute("news", news);
			request.setAttribute("cate", category);

			return "/eumode/news/newsEdit";
		}
		else
		{
			return "/eumode/news/newsAdd";
		}
	}
	
	/**
	 * 更新操作
	 * @param nobj
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(EFNews news, HttpServletRequest request) {
		Json j = new Json();
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
		EFNews oldnews = newsService.get(news.getAutoID());
		try {
			String fileName = oldnews.getContent();
			String saveFilePath = request.getSession().getServletContext().getRealPath("");
			writeContentToFile(news.getPageContent(), saveFilePath + fileName);
			news.setContent(oldnews.getContent());
			newsService.edit(news);
			if(news.getAttachmentContent() != null && news.getAttachmentContent().length() > 0){
				String[] attachmentList = news.getAttachmentContent().split(";");
				for(String attachment : attachmentList)
				{
					if(attachment.length() == 0)
						continue;
					String[] arrayList = attachment.split("\\^");
					String attachmentName = arrayList[0];
					String attachmentPath = arrayList[1];
					
					EFResourceMeta rm = new EFResourceMeta();
					rm.setType(dictService.getDictionary("ResourceMetaType", "NewsInformation").getId());
					rm.setMetaID(news.getAutoID());
					rm.setMetaPath(attachmentPath);
					rm.setMetaDescription(attachmentName);
					resourceMetaService.add(rm);
				}
			}
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		
		return j;
	}
	
	@RequestMapping("/publish")
	@ResponseBody
	public Json publish(Long id, HttpServletRequest request){
		Json j = new Json();
		Long publishFlagVal = dictService.getDictionary("NewsContentStatus", "Publish").getId();
		try {
			String saveFilePath = request.getSession().getServletContext().getRealPath("");
			EFNews news = newsService.get(id);
			String fileName = news.getContent();
			String pageContent = readContentFromFile(saveFilePath + fileName);
			pageContent = java.net.URLDecoder.decode(pageContent, "UTF-8");
			
			String template = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'><title>%s</title></head><body>%s</body></html>";
			pageContent = String.format(template, news.getTitle(), pageContent);
			String webFileName = "";
			if(news.getWebPath() == null || news.getWebPath().length() == 0){
				webFileName = generateWebSitePath(request);
			}
			else{
				webFileName = news.getWebPath();
			}
			
			writeContentToFile(pageContent, saveFilePath + webFileName);
			
			news.setWebPath(webFileName);
			news.setStatus(publishFlagVal);
			newsService.publish(news);
			j.setSuccess(true);
			j.setMsg("发布成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		
		return j;
	}
	
	@RequestMapping("/unPublish")
	@ResponseBody
	public Json unPublish(Long id){
		Json j = new Json();
		Long unPublishFlagVal = dictService.getDictionary("NewsContentStatus", "Edit").getId();
		EFNews news = newsService.get(id);
		try {
			news.setStatus(unPublishFlagVal);
			newsService.unPublish(news);
			j.setSuccess(true);
			j.setMsg("撤回成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		
		return j;
	}
	
	//允许上传的文件后缀
	private static String fileExt = "jpg,jpeg,bmp,gif,png,doc,docx,xls,xlsx,pdf";
	//上传文件的大小限制，5M
	private static Long maxSize = 5242880L;  					
	// 0:不建目录 1:按天存入目录 2:按月存入目录 3:按扩展名存目录 建议使用按天存  
    private static String dirType = "1";  
    //上传文件存储目录
    private static String baseImageDir = "/uploadfile/article_image/";
  //上传文件存储目录
    private static String baseAttachmentDir = "/uploadfile/article_attachment/";
    
    /*
     * 上传文件操作
	 * @param request, response
	 * @return
     * */
	@RequestMapping("/getUploadFile")
	@ResponseBody
	public void getUploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setContentType("text/html; charset=UTF-8");  
        response.setHeader("Cache-Control", "no-cache");  
        
        String err = "";  
        String newFileName = "";  

        if ("application/octet-stream".equals(request.getContentType())) { //HTML 5 上传
            try {
                String dispoString = request.getHeader("Content-Disposition");
                int iFindStart = dispoString.indexOf("name=\"")+6;
                int iFindEnd = dispoString.indexOf("\"", iFindStart);
 
                iFindStart = dispoString.indexOf("filename=\"")+10;
                iFindEnd = dispoString.indexOf("\"", iFindStart);
                String sFileName = dispoString.substring(iFindStart, iFindEnd);
 
                int i = request.getContentLength();
                byte buffer[] = new byte[i];
                int j = 0;
                while(j < i) { //获取表单的上传文件
                    int k = request.getInputStream().read(buffer, j, i-j);
                    j += k;
                }
 
                if (buffer.length == 0) { //文件是否为空
                    printInfo(response, "上传文件不能为空", "");
                    return;
                }
                if (maxSize > 0 && buffer.length > maxSize) { //检查文件大小
                    printInfo(response, "上传文件的大小超出限制", "");
                    return;
                }
 
                String filepathString = getSaveFilePath(sFileName, request, response, 1);
                if ("不允许上传此类型的文件".equals(filepathString)) return; //检查文件类型
 
                //System.out.println("newFileName:" + request.getSession().getServletContext().getRealPath("") + filepathString);  
                OutputStream out=new BufferedOutputStream(new FileOutputStream(request.getSession().getServletContext().getRealPath("") + filepathString,true));
                out.write(buffer);
                out.close();
                
                newFileName = request.getContextPath() + filepathString;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                newFileName = "";
                err = "错误: " + ex.getMessage();
            }
        }
        else{
        	Boolean mulipart = ServletFileUpload.isMultipartContent(request);
        	if(!mulipart)
        	{
        		printInfo(response, "上传格式错误", "");
                return;
        	}
        	//以下部分供上传附件使用
        	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> fileList = multipartRequest.getFiles("Filedata");
            
        	try {
        		for (MultipartFile mf : fileList) {
                    if(mf.isEmpty()){  
                    	continue;
                    }
                    CommonsMultipartFile cf= (CommonsMultipartFile)mf;
                    DiskFileItem fi = (DiskFileItem)cf.getFileItem();
                    String fileNameLong = fi.getName();	//获取文件上传路径名称
                    fileNameLong = fileNameLong.replaceAll("^", "");
                	
                	if (mf.getSize() == 0) { //文件是否为空
                        printInfo(response, "上传文件不能为空", "");
                        return;
                    }
                    if (maxSize > 0 && mf.getSize() > maxSize) { //检查文件大小
                        printInfo(response, "上传文件的大小超出限制", "");
                        return;
                    }

                    String filepathString = getSaveFilePath(fileNameLong, request, response, 2);
                    if ("不允许上传此类型的文件".equals(filepathString)) return; //检查文件类型
                    
                    OutputStream out=new BufferedOutputStream(new FileOutputStream(request.getSession().getServletContext().getRealPath("") + filepathString,true));
                    out.write(mf.getBytes());
                    out.close();
                    
                    newFileName += fileNameLong + "^" + request.getContextPath() + filepathString + ";";
                } 
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                newFileName = "";
                err = "错误: " + ex.getMessage();
            }
        }

        printInfo(response, err, newFileName);  
	}
	
	private String getSaveFilePath(String sFileName, HttpServletRequest request, HttpServletResponse response, int type) throws IOException {
		/*获取文件扩展名*/  
        /*索引加1的效果是只取xxx.jpg的jpg*/  
        String extensionName = sFileName.substring(sFileName.lastIndexOf(".") + 1);  
        extensionName = extensionName.toLowerCase();
        //System.out.println("extensionName:" + extensionName);  
    	
        /*检查文件类型*/  
        if (("," + fileExt.toLowerCase() + ",").indexOf("," + extensionName + ",") < 0){  
            printInfo(response, "不允许上传此类型的文件", "");  
            return "不允许上传此类型的文件";  
        }  
        
        //0:不建目录, 1:按天存入目录, 2:按月存入目录, 3:按扩展名存目录.建议使用按天存.  
        String fileFolder = "";  
        if (dirType.equalsIgnoreCase("1"))  
            fileFolder = new SimpleDateFormat("yyyyMMdd").format(new Date());
        if (dirType.equalsIgnoreCase("2"))  
            fileFolder = new SimpleDateFormat("yyyyMM").format(new Date());  
        if (dirType.equalsIgnoreCase("3"))  
            fileFolder = extensionName;  
        
        /*文件存储的相对路径*/ 
        String saveDirPath = "";
        if(type == 1){
        	//文章中的图片
        	saveDirPath = baseImageDir + fileFolder + "/";
        }
        else if(type == 2){
        	//文章中的附件
        	saveDirPath = baseAttachmentDir + fileFolder + "/";
        }
        //System.out.println("saveDirPath:" + saveDirPath);  
          
        /*文件存储在容器中的绝对路径*/  
        String saveFilePath = request.getSession().getServletContext().getRealPath("") + saveDirPath;  
        //System.out.println("saveFilePath:" + saveFilePath);  
                      
        /*构建文件目录以及目录文件*/  
        File fileDir = new File(saveFilePath);  
        if (!fileDir.exists()) {fileDir.mkdirs();}  
        
        /*重命名文件*/  
        String filename = UUID.randomUUID().toString();  
        
        return saveDirPath + filename + "." + extensionName;
    }
	
	 /** 
     * 使用I/O流输出 json格式的数据 
     * @param response 
     * @param err 
     * @param newFileName 
     * @throws IOException 
     */  
    private void printInfo(HttpServletResponse response, String err, String newFileName) throws IOException {  
        PrintWriter out = response.getWriter();  
        //String filename = newFileName.substring(newFileName.lastIndexOf("/") + 1);  
        out.println("{\"err\":\"" + err + "\",\"msg\":\"" + newFileName + "\"}");  
        out.flush();  
        out.close();  
    }  
    
    //未发布的新闻公告文件
    private static String tmpArticleDir = "/tmpArticle/";
    private static String tmpArticleExt = ".tmparticle";
    /** 
     * 创建临时文件名称
     */ 
    private String generateTmpArticleFileName(String authorid, HttpServletRequest request)
    {
    	String fileFolder = new SimpleDateFormat("yyyyMMdd").format(new Date());
    	/*文件存储的相对路径*/  
        String saveDirPath = tmpArticleDir + authorid + "/" + fileFolder + "/";  
        
        String saveFilePath = request.getSession().getServletContext().getRealPath("") + saveDirPath;
        
        /*构建文件目录以及目录文件*/  
        File fileDir = new File(saveFilePath);  
        if (!fileDir.exists()) {fileDir.mkdirs();}  
        
        /*重命名文件*/  
        String filename = UUID.randomUUID().toString();  

        return saveDirPath + filename + tmpArticleExt;
    }
    
    /** 
     * 将字符串写入文件
     * @param 绝对路径
     */ 
    private void writeContentToFile(String content, String fileName) throws IOException
    {
    	try
    	{
    		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fileName),"UTF-8");
    		osw.write(content);
    		osw.close();
    	}catch (IOException e) {  
    		e.printStackTrace();  
    	}  
    }
    
    /** 
     * 从文件读取字符串
     * @param 绝对路径
     */ 
    private String readContentFromFile(String  fileName) throws IOException
    {
    	String content = "";
    	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
    	String data = null;
    	while((data = br.readLine())!=null)
    	{
    		content += data; 
    	}
    	return content;
    }
    
    //发布的新闻公告文件
    private static String webSitePath = "/Article/";
    /** 
     * 创建html文件名
     */
    private String generateWebSitePath(HttpServletRequest request)
    {
    	String fileFolder = new SimpleDateFormat("yyyyMMdd").format(new Date());
    	/*文件存储的相对路径*/  
        String saveDirPath = webSitePath + fileFolder + "/";  
        
        String saveFilePath = request.getSession().getServletContext().getRealPath("") + saveDirPath;
        
        /*构建文件目录以及目录文件*/  
        File fileDir = new File(saveFilePath);  
        if (!fileDir.exists()) {fileDir.mkdirs();}  
        
        /*重命名文件*/  
        String filename = new SimpleDateFormat("hhmmssSSS").format(new Date()); 

        return saveDirPath + filename + ".html";
    }
}
