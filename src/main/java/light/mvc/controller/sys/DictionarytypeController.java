package light.mvc.controller.sys;

import light.mvc.controller.base.BaseController;
import light.mvc.pageModel.base.Json;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.sys.Dictionarytype;
import light.mvc.service.sys.DictionarytypeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/dictionarytype")
public class DictionarytypeController extends BaseController {

    @Autowired
    private DictionarytypeServiceI dictionarytypeService;


    @RequestMapping("/tree")
    @ResponseBody
    public List<Tree> tree(HttpSession session) {
        return dictionarytypeService.tree();
    }

    @RequestMapping("/add")
    @ResponseBody
    public Json add(Dictionarytype dictionarytype) {
        Json j = new Json();
        try {
            dictionarytypeService.add(dictionarytype);
            j.setSuccess(true);
            j.setMsg("添加成功！");
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Json delete(Long id) {
        Json j = new Json();
        dictionarytypeService.delete(id);
        j.setMsg("删除成功！");
        j.setSuccess(true);
        return j;
    }

    @RequestMapping("/get")
    @ResponseBody
    public Dictionarytype get(Long id) {
        return dictionarytypeService.get(id);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Json edit(Dictionarytype dictionarytype) {
        Json j = new Json();
        try {
            dictionarytypeService.edit(dictionarytype);
            j.setSuccess(true);
            j.setMsg("编辑成功！");
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }

}
