package light.mvc.controller.news;


import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Grid;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.news.EFCategory;
import light.mvc.pageModel.sys.Dictionary;
import light.mvc.service.news.EFCategoryServiceI;
import light.mvc.service.sys.DictionaryServiceI;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/category")
public class EFCategoryController extends BaseController {

    @Autowired
    private EFCategoryServiceI categoryService;

    @Autowired
    private DictionaryServiceI dictService;

    /**
     * 跳转到列表页面
     *
     * @return
     */
    @RequestMapping("/getPage")
    public String categoryPage(HttpServletRequest request) {
        List<Dictionary> typeList = dictService.combox("NewsCategoryType");
        request.setAttribute("typeList", typeList);

        return "/eumode/news/category";
    }

    /**
     * 获取列表数据
     *
     * @param category
     * @param ph
     * @return
     */
    @RequestMapping("/dataGrid")
    @ResponseBody
    public Grid dataGrid(EFCategory category, PageFilter ph) {
//		spring自动将参数注入到ph对象中
        Grid grid = new Grid();

        List<EFCategory> list = categoryService.dataGrid(category, ph);
        for (EFCategory t : list) {
            t.setTypeDesc(dictService.get(t.getType()).getText());
        }
        grid.setRows(list);
        grid.setTotal(categoryService.count(category, ph));
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
                categoryService.delete(id, request);
            }

            j.setMsg("删除成功！");
            j.setSuccess(true);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }

    /**
     * 跳转到新增页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage(HttpServletRequest request) {
        List<Dictionary> typeList = dictService.combox("NewsCategoryType");
        request.setAttribute("typeList", typeList);

        return "/eumode/news/categoryAdd";
    }

    /**
     * 新增操作
     *
     * @param nobj
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Json add(EFCategory category) {
        Json j = new Json();
        try {
            categoryService.add(category);
            j.setSuccess(true);
            j.setMsg("添加成功！");
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }

        return j;
    }

    /**
     * 跳转到编辑页面
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, Long id) {
        EFCategory category = categoryService.get(id);
        request.setAttribute("category", category);

        List<Dictionary> typeList = dictService.combox("NewsCategoryType");
        request.setAttribute("typeList", typeList);

        return "/eumode/news/categoryEdit";
    }

    /**
     * 更新操作
     *
     * @param category
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Json update(EFCategory category) {
        Json j = new Json();
        try {
            categoryService.edit(category);
            j.setSuccess(true);
            j.setMsg("编辑成功！");
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }

        return j;
    }
}
