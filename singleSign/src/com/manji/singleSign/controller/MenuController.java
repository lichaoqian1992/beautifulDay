package com.manji.singleSign.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.singleSign.model.Result;
import com.manji.singleSign.model.bean.Menu;
import com.manji.singleSign.model.query.MenuVo;
import com.manji.singleSign.service.MenuService;
import com.manji.singleSign.service.RoleService;

public class MenuController extends Controller {


    private MenuService service = new MenuService();

    private RoleService serviceRole = new RoleService();

    public void index() {

        List<Record> sysList = service.getSysList();

        setAttr("sysList", sysList);

        render("list.html");

    }

    public void query() {

        Result r = new Result();
        MenuVo mv = getBean(MenuVo.class, "");
        if (mv.getIndex() == 0) {
            mv.setIndex(1);
        }

        Page<Record> page = service.queryMenu(mv);


        r.setCode("0000");
        r.setData(page);
        renderJson(r);

    }


    public void detail() {

        Result r = new Result();

        String menu_id = getPara("id");

        Record rec = service.getMenuDetail(menu_id);

        r.setCode("0000");
        r.setData(rec);


        renderJson(r);
    }


    public void update() {

        Menu m = getBean(Menu.class, "");

        service.updateMenu(m);
        Result r = new Result();


        r.setCode("0000");
        r.setData("修改成功！！！");
        renderJson(r);

    }

    /**
     * 根据系统编号查询系统菜单目录
     */
    public void findSysById() {

        String id = getPara("id","");

        List<Record> list = service.findSysById(id);
        renderJson(list);

    }

    /**
     * 查询menu基础数据
     */
    public void menuList() {
        int currentPage = getParaToInt("currentPage", 1);
        int pages = getParaToInt("pages", 20);
        String powerName = getPara("powerName", "");
        String deptCode = getPara("deptCode", "");

        Page<Record> menuList = service.menuList(currentPage, pages, powerName, deptCode);
        renderJson(menuList);
    }

    /**
     * ,根据menuId查询单个menu信息
     */
    public void menuId() {
        String id = getPara("id","");

        Record singleMenu = service.menuId(id);
        renderJson(singleMenu);
    }

    /**
     * 修改menu数据
     */
    public void updateMenu() {
        Menu menu=getBean(Menu.class);
        if(menu.getUrl()==null){
            menu.setUrl("");
        }
        boolean menuUp = service.updateMenu(menu);

        Result r = new Result();
        if(menuUp){
            r.setCode("0000");
            r.setData("修改成功！");
        }else{
            r.setCode("0001");
            r.setData("修改失败！");
        }
        renderJson(r);
    }

    /**
     * 新增menu数据
     */
    public void insertMenu() {
        Menu menu=getBean(Menu.class);
        if(menu.getUrl()==null){
            menu.setUrl("");
        }
        boolean menuAdd = service.addMenu(menu);

        Result r = new Result();
        if(menuAdd){
            r.setCode("0000");
            r.setData("添加成功！");
        }else{
            r.setCode("0001");
            r.setData("添加失败！");
        }
        renderJson(r);
    }
}