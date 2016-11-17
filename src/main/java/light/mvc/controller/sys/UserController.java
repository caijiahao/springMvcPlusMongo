package light.mvc.controller.sys;


import com.alibaba.fastjson.JSON;
import light.mvc.controller.base.BaseController;
import light.mvc.framework.constant.GlobalConstant;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.SessionInfo;
import light.mvc.pageModel.sys.EFResourceMeta;
import light.mvc.pageModel.sys.User;
import light.mvc.service.base.ServiceException;
import light.mvc.service.sys.DictionaryServiceI;
import light.mvc.service.sys.ResourceMetaServiceI;
import light.mvc.service.sys.UserServiceI;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserServiceI userService;

    @Autowired
    private DictionaryServiceI dictionaryService;

    @Autowired
    private ResourceMetaServiceI resourceMetaService;


    @RequestMapping("/manager")
    public String manager(HttpServletRequest request) {
        request.setAttribute("usertypeJson", JSON.toJSONString(dictionaryService.combox("usertype")));
        return "/eumode/sys/user";
    }

    @RequestMapping("/dataGrid")
    @ResponseBody
    public Grid dataGrid(User user, PageFilter ph) {
        Grid grid = new Grid();
        grid.setRows(userService.dataGrid(user, ph));
        grid.setTotal(userService.count(user, ph));
        return grid;
    }


    @RequestMapping("/editPwdPage")
    public String editPwdPage(HttpServletRequest request) {
        return "/eumode/sys/userEditPwd";
    }

    @RequestMapping("/editUserPwd")
    @ResponseBody
    public Json editUserPwd(HttpServletRequest request, String oldPwd, String pwd) {
        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(GlobalConstant.SESSION_INFO_ADMIN);
        Json j = new Json();
        try {
            if (userService.editUserPwd(sessionInfo, oldPwd, pwd)) {
                j.setSuccess(true);
                j.setMsg("密码修改成功！");
            } else {
                j.setSuccess(false);
                j.setMsg("原密码输入错误！");
            }
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }

    @RequestMapping("/addPage")
    public String addPage(HttpServletRequest request) {
        request.setAttribute("sexList", GlobalConstant.sexlist);
        request.setAttribute("needPublishList", GlobalConstant.needPublishList);
        return "/eumode/sys/userAdd";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Json add(User user) {
        Json j = new Json();
        User u = userService.getByLoginName(user);
        if (u != null) {
            j.setMsg("用户名已存在!");
        } else {
            try {
                Long personalid = userService.add(user);
                if (personalid != null && personalid > 0) {
                    if (user.getImagePath() != null && user.getImagePath().length() > 0) {
                        String[] arrayList = user.getImagePath().split("\\^");
                        String imageName = arrayList[0];
                        String imagePath = arrayList[1];

                        EFResourceMeta rm = new EFResourceMeta();
                        rm.setType(dictionaryService.getDictionary("ResourceMetaType", "Expert").getId());
                        rm.setMetaID(personalid);
                        rm.setMetaPath(imagePath);
                        rm.setMetaDescription(imageName);
                        resourceMetaService.add(rm);
                    }
                }
                j.setSuccess(true);
                j.setMsg("添加成功！");
            } catch (Exception e) {
                j.setMsg(e.getMessage());
            }

        }
        return j;
    }

    @RequestMapping("/get")
    @ResponseBody
    public User get(Long id) {
        return userService.get(id);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Json delete(Long id) {
        Json j = new Json();
        try {
            userService.delete(id);
            j.setMsg("删除成功！");
            j.setSuccess(true);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }

    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, Long id) {
        User u = userService.get(id);
        request.setAttribute("user", u);
        request.setAttribute("sexList", GlobalConstant.sexlist);
        request.setAttribute("needPublishList", GlobalConstant.needPublishList);

        List<EFResourceMeta> rmList = resourceMetaService.get(u.getAutoID(), dictionaryService.getDictionary("ResourceMetaType", "Expert").getId());
        if (rmList != null && rmList.size() > 0) {
            u.setImagePath(rmList.get(0).getMetaPath());
        }
        return "/eumode/sys/userEdit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Json edit(User user) {
        Json j = new Json();
        try {
            userService.edit(user);
            if (user.getImagePath() != null && user.getImagePath().length() > 0) {
                String[] arrayList = user.getImagePath().split("\\^");
                String imageName = arrayList[0];
                String imagePath = arrayList[1];

                List<EFResourceMeta> rmList = resourceMetaService.get(user.getAutoID(), dictionaryService.getDictionary("ResourceMetaType", "Expert").getId());
                if (rmList != null && rmList.size() > 0) {
                    //更换图片
                    EFResourceMeta rm = rmList.get(0);
                    rm.setMetaPath(imagePath);
                    rm.setMetaDescription(imageName);
                    resourceMetaService.edit(rm);
                } else {
                    //新增图片
                    EFResourceMeta rm = new EFResourceMeta();
                    rm.setType(dictionaryService.getDictionary("ResourceMetaType", "Expert").getId());
                    rm.setMetaID(user.getAutoID());
                    rm.setMetaPath(imagePath);
                    rm.setMetaDescription(imageName);
                    resourceMetaService.add(rm);
                }
            }
            j.setSuccess(true);
            j.setMsg("编辑成功！");
        } catch (ServiceException e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }

    @RequestMapping("/getExpertByManual")
    @ResponseBody
    public Json getExpertByManual(User user) {
        Json j = new Json();
        try {
            userService.setManual(user);
            j.setMsg("授权成功！");
            j.setSuccess(true);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }


    @RequestMapping("/manualPage")
    public String manualPage(HttpServletRequest request, Long id) {
        User u = userService.get(id);
        request.setAttribute("user", u);
        return "/eumode/sys/userManual";
    }

    @RequestMapping("/setManual")
    @ResponseBody
    public Json setManual(User user) {
        Json j = new Json();
        try {
            userService.setManual(user);
            j.setMsg("授权成功！");
            j.setSuccess(true);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }


    //允许上传的文件后缀
    private static String fileExt = "jpg,jpeg,bmp,gif,png";
    //上传文件的大小限制，5M
    private static Long maxSize = 5242880L;
    // 0:不建目录 1:按天存入目录 2:按月存入目录 3:按扩展名存目录 建议使用按天存
    private static String dirType = "0";
    //上传文件存储目录
    private static String userImageDir = "/uploadfile/user_image/";


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
        String saveDirPath = userImageDir + fileFolder + "/";
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
