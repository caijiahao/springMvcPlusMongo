package light.mvc.controller.manual;


import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.manual.EFExpertList;
import light.mvc.pageModel.manual.EFmanualCategory;
import light.mvc.pageModel.sys.User;
import light.mvc.service.manual.EFManualCategoryServiceI;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/manualCategory")
public class EFManualCategoryController extends BaseController {

    @Autowired
    private EFManualCategoryServiceI categoryService;

    @RequestMapping("/getPage")
    public String categoryPage(HttpServletRequest request) {
        List<EFmanualCategory> rootList = categoryService.getByPid((long) 0);
        request.setAttribute("rootList", rootList);

        return "/eumode/manual/category";
    }

    @RequestMapping("/dataGrid")
    @ResponseBody
    public Grid dataGrid(EFmanualCategory category, PageFilter ph) {
//		spring自动将参数注入到ph对象中
        Grid grid = new Grid();
        grid.setRows(categoryService.dataGrid(category, ph));
        grid.setTotal(categoryService.count(category, ph));
        return grid;
    }

    @RequestMapping("/treeGrid")
    @ResponseBody
    public List<EFmanualCategory> treeGrid(EFmanualCategory category, PageFilter ph) {
//		spring自动将参数注入到ph对象中		
        return categoryService.treeGrid(category);
    }


    @RequestMapping("/getAllManualTree")
    @ResponseBody
    public List<Tree> getAllManualTree() {
        return categoryService.allTree();
    }

    @RequestMapping("/getManualTreeByCode")
    @ResponseBody
    public List<Tree> getManualTreeByCode(HttpServletRequest request, String categoryRoot) {
        if (categoryRoot == null) {
            categoryRoot = "";
        }

        return categoryService.getTreeByCode(categoryRoot);
    }

    @ResponseBody
    @RequestMapping("/getExpertByCategory")
    public List<User> getExpertByCategory(Long id) {
        EFExpertList expertList = categoryService.getExpertList(id);
        return expertList.getList();
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
                categoryService.delete(id, request);
            }

            j.setMsg("删除成功！");
            j.setSuccess(true);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }

    @RequestMapping("/addPage")
    public String addPage(HttpServletRequest request, String categoryRoot) {
        request.setAttribute("categoryRoot", categoryRoot);
        return "/eumode/manual/categoryAdd";
    }

    @RequestMapping("/editPage")
    public String getEditPage(HttpServletRequest request, Long id, String categoryRoot) {
        EFmanualCategory category = categoryService.get(id);
        request.setAttribute("category", category);
        request.setAttribute("categoryRoot", categoryRoot);
        return "/eumode/manual/categoryEdit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Json edit(EFmanualCategory nobj) {

        Json j = new Json();
        try {
            categoryService.edit(nobj);
            j.setSuccess(true);
            j.setMsg("添加成功！");
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }

        return j;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Json add(EFmanualCategory nobj) {

        Json j = new Json();
        try {
            categoryService.add(nobj);
            j.setSuccess(true);
            j.setMsg("添加成功！");
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }

        return j;
    }

    @RequestMapping("/getRootCategory")
    @ResponseBody
    public Json getRootCategory() {

        Json j = new Json();
        try {
            List<EFmanualCategory> rootList = categoryService.getByPid((long) 0);
            j.setObj(rootList);
            j.setSuccess(true);
            j.setMsg("添加成功！");
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }

        return j;
    }

}