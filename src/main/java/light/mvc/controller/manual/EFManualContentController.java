package light.mvc.controller.manual;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.manual.EFmanualCategory;
import light.mvc.pageModel.manual.EFmanualContent;
import light.mvc.pageModel.manual.EFmanualKeyword;
import light.mvc.pageModel.sys.EFResourceMeta;
import light.mvc.service.manual.EFManualCategoryServiceI;
import light.mvc.service.manual.EFManualContentServiceI;
import light.mvc.service.manual.EFManualKeywordServiceI;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/manualContent")
public class EFManualContentController extends BaseController {

    @Autowired
    private EFManualContentServiceI contentService;

    @Autowired
    private EFManualCategoryServiceI categoryService;

    @Autowired
    private EFManualKeywordServiceI keywordService;

    @Autowired
    private DictionaryServiceI dictService;

    @Autowired
    private ResourceMetaServiceI resourceMetaService;

    @RequestMapping("/getPage")
    public String get_news_page() {

        return "/eumode/manual/manual";
    }

    @RequestMapping("/dataGrid")
    @ResponseBody
    public Grid data_grid(EFmanualContent content, PageFilter ph) {
//		spring自动将参数注入到ph对象中
        Grid grid = new Grid();
        grid.setRows(contentService.dataGrid(content, ph));
        grid.setTotal(contentService.count(content, ph));
        return grid;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Json delete(String ids, HttpServletRequest request) {
        JSONArray jsonIds;
        Json j = new Json();
        try {
            jsonIds = new JSONArray(ids);
            for (int i = 0; i < jsonIds.length(); i++) {
                long id = jsonIds.getLong(i);

                EFmanualKeyword kw = new EFmanualKeyword();
                kw.setManualContentID(id);

                List<EFmanualKeyword> keywordlist = keywordService.getAllKeyword(kw);
                for (EFmanualKeyword mk : keywordlist) {
                    keywordService.delete(mk.getAutoID());
                }

                List<EFResourceMeta> attachmentlist = resourceMetaService.get(id, dictService.getDictionary("ResourceMetaType", "Knowledge").getId());
                if (attachmentlist != null && attachmentlist.size() > 0) {
                    for (EFResourceMeta rm : attachmentlist) {
                        resourceMetaService.delete(rm.getAutoID());
                    }
                }

                contentService.delete(id);
            }

            j.setMsg("删除成功！");
            j.setSuccess(true);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }

    @RequestMapping("/addPage")//知识库添加
    public String addPage(HttpServletRequest request) {
        request.setAttribute("categoryRoot", "Variety");
        return "/eumode/manual/manualAdd";
    }

    @RequestMapping("/addVarietyPage")//知识库添加
    public String addVarietyPage(HttpServletRequest request) {
        request.setAttribute("categoryRoot", "Variety");
        return "/eumode/manual/manualAdd";
    }

    @RequestMapping("/addIndustryPage")//知识库添加
    public String addIndustryPage(HttpServletRequest request) {
        request.setAttribute("categoryRoot", "Industry");
        return "/eumode/manual/manualAdd";
    }

    @RequestMapping("/addAchievementPage")//知识库添加
    public String addAchievementPage(HttpServletRequest request) {
        request.setAttribute("categoryRoot", "Achievement");
        return "/eumode/manual/manualAdd";
    }

    @RequestMapping("/addTechnologyPage")//知识库添加
    public String addTechnologyPage(HttpServletRequest request) {
        request.setAttribute("categoryRoot", "Technology");
        return "/eumode/manual/manualAdd";
    }

    @RequestMapping("/detailPage")//知识库详情
    public String viewDetailPage(Long id, HttpServletRequest request) {
        EFmanualKeyword kw = new EFmanualKeyword();
        kw.setManualContentID(id);
        List<EFmanualKeyword> keywordlist = keywordService.getAllKeyword(kw);
        EFmanualContent content = contentService.get(id);

        EFmanualCategory mc = categoryService.get(content.getManualCategoryID());
        List<EFmanualCategory> mcList = new ArrayList<EFmanualCategory>();
        while (mc != null) {
            mcList.add(mc);
            if (mc.getParentID() != null && mc.getParentID() > 0) {
                mc = categoryService.get(mc.getParentID());
            } else {
                mc = null;
            }
        }
        if (mcList.size() > 0) {
            String categoryList = "";
            for (EFmanualCategory category : mcList) {
                categoryList += category.getCategoryName() + "<--";
            }
            categoryList = categoryList.substring(0, categoryList.length() - 3);
            request.setAttribute("categoryList", categoryList);
        }

        if (content != null) {
            request.setAttribute("manualContent", content);
        }
        if (keywordlist != null && keywordlist.size() > 0) {
            String keyworddesc = "";
            for (EFmanualKeyword mk : keywordlist) {
                keyworddesc += mk.getKeyword() + "，";
            }
            keyworddesc = keyworddesc.substring(0, keyworddesc.length() - 1);
            request.setAttribute("keywordList", keyworddesc);
        }
        if (content.getFilePath() != null && content.getFilePath().length() > 0) {
            List<EFResourceMeta> attachmentlist = resourceMetaService.get(content.getAutoID(), dictService.getDictionary("ResourceMetaType", "Knowledge").getId());
            request.setAttribute("attachmentList", attachmentlist);
        }

        return "/eumode/manual/manualDetail";
    }


    @RequestMapping("/add")
    @ResponseBody
    public Json add(EFmanualContent nobj) {
        //System.out.println("addnews");
        Json j = new Json();
        try {

            nobj.setCategoryCode(categoryService.get(nobj.getManualCategoryID()).getCategoryCode());

            Long manualID = null;
            if (nobj.getAttachmentContent() != null && nobj.getAttachmentContent().length() > 0) {
                List<EFResourceMeta> rmlist = new ArrayList<EFResourceMeta>();
                String filenamelist = "";
                String[] attachmentList = nobj.getAttachmentContent().split(";");
                for (String attachment : attachmentList) {
                    if (attachment.length() == 0)
                        continue;
                    String[] arrayList = attachment.split("\\^");
                    String attachmentName = arrayList[0];
                    String attachmentPath = arrayList[1];
                    filenamelist += attachmentName + ";";

                    EFResourceMeta rm = new EFResourceMeta();
                    rm.setType(dictService.getDictionary("ResourceMetaType", "Knowledge").getId());
                    rm.setMetaPath(attachmentPath);
                    rm.setMetaDescription(attachmentName);
                    rmlist.add(rm);
                }
                if (filenamelist.length() > 0) {
                    filenamelist = filenamelist.substring(0, filenamelist.length() - 1);
                }
                nobj.setFilePath(filenamelist);
                nobj.setContent("");

                manualID = contentService.add(nobj);

                if (manualID > 0 && rmlist.size() > 0) {
                    for (EFResourceMeta rm : rmlist) {
                        rm.setMetaID(manualID);
                        resourceMetaService.add(rm);
                    }
                }
            } else if (nobj.getPageContent() != null && nobj.getPageContent().length() > 0) {
                nobj.setContent(java.net.URLDecoder.decode(nobj.getPageContent(), "UTF-8"));

                manualID = contentService.add(nobj);
            }

            if (manualID != null && manualID > 0) {
                if (nobj.getKeywordList() != null && nobj.getKeywordList().length() > 0) {
                    String[] keywordList = nobj.getKeywordList().split(",");
                    for (String keyword : keywordList) {
                        EFmanualKeyword mk = new EFmanualKeyword();
                        mk.setCategoryCode(nobj.getCategoryCode());
                        mk.setCategoryID(nobj.getManualCategoryID());
                        mk.setKeyword(keyword);
                        mk.setManualContentID(manualID);
                        keywordService.add(mk);
                    }
                }
                    /*
					if(nobj.getAttachmentContent() != null && nobj.getAttachmentContent().length() > 0){
						String[] attachmentList = nobj.getAttachmentContent().split(";");
						for(String attachment : attachmentList)
						{
							if(attachment.length() == 0)
								continue;
							String[] arrayList = attachment.split("\\^");
							String attachmentName = arrayList[0];
							String attachmentPath = arrayList[1];
							
							EFResourceMeta rm = new EFResourceMeta();
							rm.setType(dictService.getDictionary("ResourceMetaType", "NewsInformation").getId());
							rm.setMetaID(manualID);
							rm.setMetaPath(attachmentPath);
							rm.setMetaDescription(attachmentName);
							resourceMetaService.add(rm);
						}
					}
					*/
            }

            j.setSuccess(true);
            j.setMsg("添加成功！");
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }

        return j;
    }


    //允许上传的文件后缀
    private static String fileExt = "jpg,jpeg,bmp,gif,png,pdf";
    //上传文件的大小限制，10M
    private static Long maxSize = 10485760L;
    // 0:不建目录 1:按天存入目录 2:按月存入目录 3:按扩展名存目录 建议使用按天存
    private static String dirType = "1";
    //上传文件存储目录
    private static String baseAttachmentDir = "/uploadfile/manual_attachment/";

    /*
     * 上传文件操作
	 * @param request, response
	 * @return
     * */
    @RequestMapping("/getUploadFile")
    @ResponseBody
    public void getUploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");

        String err = "";
        String newFileName = "";
        //System.out.println("up");

        if ("application/octet-stream".equals(request.getContentType())) { //HTML 5 上传
            try {
                String dispoString = request.getHeader("Content-Disposition");
                int iFindStart = dispoString.indexOf("name=\"") + 6;
                int iFindEnd = dispoString.indexOf("\"", iFindStart);

                iFindStart = dispoString.indexOf("filename=\"") + 10;
                iFindEnd = dispoString.indexOf("\"", iFindStart);
                String sFileName = dispoString.substring(iFindStart, iFindEnd);

                int i = request.getContentLength();
                byte buffer[] = new byte[i];
                int j = 0;
                while (j < i) { //获取表单的上传文件
                    int k = request.getInputStream().read(buffer, j, i - j);
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

                String filepathString = getSaveFilePath(sFileName, request, response);
                if ("不允许上传此类型的文件".equals(filepathString)) return; //检查文件类型

                //System.out.println("newFileName:" + request.getSession().getServletContext().getRealPath("") + filepathString);  
                OutputStream out = new BufferedOutputStream(new FileOutputStream(request.getSession().getServletContext().getRealPath("") + filepathString, true));
                out.write(buffer);
                out.close();

                newFileName = request.getContextPath() + filepathString;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                newFileName = "";
                err = "错误: " + ex.getMessage();
            }
        } else {
            Boolean mulipart = ServletFileUpload.isMultipartContent(request);
            if (!mulipart) {
                printInfo(response, "上传格式错误", "");
                return;
            }
            //以下部分供上传附件使用
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> fileList = multipartRequest.getFiles("Filedata");

            try {
                for (MultipartFile mf : fileList) {
                    if (mf.isEmpty()) {
                        continue;
                    }
                    CommonsMultipartFile cf = (CommonsMultipartFile) mf;
                    DiskFileItem fi = (DiskFileItem) cf.getFileItem();
                    String fileNameLong = fi.getName();    //获取文件上传路径名称
                    fileNameLong = fileNameLong.replaceAll("^", "");

                    if (mf.getSize() == 0) { //文件是否为空
                        printInfo(response, "上传文件不能为空", "");
                        return;
                    }
                    if (maxSize > 0 && mf.getSize() > maxSize) { //检查文件大小
                        printInfo(response, "上传文件的大小超出限制", "");
                        return;
                    }

                    String filepathString = getSaveFilePath(fileNameLong, request, response);
                    if ("不允许上传此类型的文件".equals(filepathString)) return; //检查文件类型

                    OutputStream out = new BufferedOutputStream(new FileOutputStream(request.getSession().getServletContext().getRealPath("") + filepathString, true));
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

    private String getSaveFilePath(String sFileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*获取文件扩展名*/  
        /*索引加1的效果是只取xxx.jpg的jpg*/
        String extensionName = sFileName.substring(sFileName.lastIndexOf(".") + 1);
        extensionName = extensionName.toLowerCase();
        //System.out.println("extensionName:" + extensionName);  
    	
        /*检查文件类型*/
        if (("," + fileExt.toLowerCase() + ",").indexOf("," + extensionName + ",") < 0) {
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
        saveDirPath = baseAttachmentDir + fileFolder + "/";
        //System.out.println("saveDirPath:" + saveDirPath);  
          
        /*文件存储在容器中的绝对路径*/
        String saveFilePath = request.getSession().getServletContext().getRealPath("") + saveDirPath;
        //System.out.println("saveFilePath:" + saveFilePath);  
                      
        /*构建文件目录以及目录文件*/
        File fileDir = new File(saveFilePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        
        /*重命名文件*/
        String filename = UUID.randomUUID().toString();

        return saveDirPath + filename + "." + extensionName;
    }

    /**
     * 使用I/O流输出 json格式的数据
     *
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
}


