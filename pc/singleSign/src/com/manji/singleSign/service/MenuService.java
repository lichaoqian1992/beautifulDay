package com.manji.singleSign.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.singleSign.model.bean.Menu;
import com.manji.singleSign.model.query.MenuVo;

public class MenuService extends BaseService {

    public Page<Record> menuList(int currentPage, int pages, String powerName, String deptCode) {

        //拼装sql
        StringBuffer sql = new StringBuffer();
        sql.append("from sso_menu m,sso_system s where m.sys_id =s.id");
        if (powerName != null && !powerName.equals("")) {
            sql.append(" and title like '" + powerName + "%'");
        }
        if (deptCode != null && !deptCode.equals("")) {
            sql.append(" and m.sys_id=" + deptCode + "");
        }
        sql.append(" order by m.id");

        Page<Record> list = Db.paginate(currentPage, pages, "select m.*,s.system_name", sql.toString());

        return list;
    }

    public Page<Record> queryMenu(MenuVo mv) {

        StringBuffer sqlBuffer = new StringBuffer("from sso_menu m,sso_system s where 1=1  and m.sys_id =s.id ");

        if (mv.getSys_id() != 0) {
            sqlBuffer.append("and m.id ='" + mv.getSys_id() + "'");
        }

        sqlBuffer.append("order by m.id");

        Page<Record> page = Db.paginate(mv.getIndex(), 20, "select m.*,s.system_name", sqlBuffer.toString());

        return page;
    }

    public Record getMenuDetail(String menu_id) {

        Record rec = Db
                .findFirst("select m.*,s.system_name from sso_menu m,sso_system s where m.sys_id =s.id and m.id='"
                        + menu_id + "'");

        return rec;
    }

    public boolean addMenu(Menu m) {

        Record rec = new Record();

        rec.set("title", m.getTitle()).set("layer", m.getLayer()).set("address", m.getAddress()).set("url", m.getUrl())
                .set("type", m.getType()).set("sys_id", m.getSys_id()).set("is_del", m.getIs_del())
                .set("sort", m.getSort());

        boolean menuIn=Db.save("sso_menu", rec);
        return menuIn;

    }

    public boolean updateMenu(Menu m) {

        Record rec = new Record();
        rec.set("title", m.getTitle()).set("layer", m.getLayer()).set("address", m.getAddress()).set("url", m.getUrl())
                .set("type", m.getType()).set("sys_id", m.getSys_id()).set("is_del", m.getIs_del())
                .set("sort", m.getSort()).set("id", m.getId());

        boolean menuUp=Db.update("sso_menu", rec);
        return menuUp;
    }

    /**
     * 根据系统编号查询菜单
     *
     * @param id
     * @return
     */
    public List<Record> findSysById(String id) {
        return Db.find("select * from sso_menu where sys_id=?", id);
    }

    public List<Record> role() {
        return Db.find("select * from sso_roles where id not in(select role_id from sso_role_menu )");
    }

    /**
     * 根据menuId查询单个menu信息
     * @param id
     * @return
     */
    public Record menuId(String id) {
        return Db.findFirst("select * from sso_menu where id=?", id);
    }

}
