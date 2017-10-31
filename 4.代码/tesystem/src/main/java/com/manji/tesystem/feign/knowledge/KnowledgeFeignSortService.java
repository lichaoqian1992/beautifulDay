package com.manji.tesystem.feign.knowledge;

import com.manji.tesystem.feign.response.knowledge.Tree;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value="ackservice")
public interface KnowledgeFeignSortService {

    /**
     * 通过项目code查询全部栏目分类
     * @param pjt_code
     * @return
     */
    @RequestMapping(value ="/tree/findAllTree",method = RequestMethod.GET)
    List<Tree> findAllTree(@RequestParam("pjt_code")String pjt_code);
    /**
     * 通过级别查询栏目分类
     * @param pjt_code
     * @return
     */
    @RequestMapping(value ="/tree/findTreeByMenuId",method = RequestMethod.GET)
    List<Tree> findTreeByMenuId(@RequestParam("pjt_code")String pjt_code,@RequestParam("menuId")Integer menuId);
    /**
     * 查询某个档位下的子集列表
     * @param pjt_code
     * @return
     */
    @RequestMapping(value ="/tree/findTreeSun",method = RequestMethod.GET)
    List<Tree> findTreeSun(@RequestParam("pjt_code")String pjt_code,@RequestParam("id")Integer id);

    /**
     *添加节点
     * @param pjtCode
     * @param id
     * @param menuId
     * @param title
     * @return
     */
    @RequestMapping(value ="/tree/add",method = RequestMethod.GET)
    boolean add(@RequestParam("pjtCode")String pjtCode,@RequestParam("id")Integer id,@RequestParam("menuId")Integer menuId,@RequestParam("title")String title);

    /**
     *修改节点
     * @param id
     * @param title
     * @return
     */
    @RequestMapping(value ="/tree/updateTitle",method = RequestMethod.GET)
    boolean updateTitle(@RequestParam("id")Integer id,@RequestParam("title")String title);

    /**
     *删除节点
     * @param id
     * @return
     */
    @RequestMapping(value ="/tree/delete",method = RequestMethod.GET)
    boolean delete(@RequestParam("id")Integer id);

     /*
     *判断某个节点下面是否有子节点
     * @param id
    */
    @RequestMapping(value ="/tree/isTree",method = RequestMethod.GET)
    boolean isTree(@RequestParam("id")Integer id,@RequestParam("pjt_code")String pjt_code);


    /**
     * 查询某个节点下面的 知识点数量。用来判断是否允许删除节点
     * @param treeId
     * @return
     */
    @RequestMapping(value ="/info/findInformationByTreeCount",method = RequestMethod.GET)
    int findInformationByTreeCount(@RequestParam("treeId")Integer treeId);

}
