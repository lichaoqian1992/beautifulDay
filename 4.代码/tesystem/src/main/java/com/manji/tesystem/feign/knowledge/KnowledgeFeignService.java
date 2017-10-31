package com.manji.tesystem.feign.knowledge;

import com.manji.tesystem.feign.response.common.ResultPageInfoObject;
import com.manji.tesystem.feign.response.knowledge.Information;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="ackservice")
public interface KnowledgeFeignService {

    /**
     * 知识库分页查询列表
     * @return
     */
    @RequestMapping(value = "/info/findInfo", method = RequestMethod.GET)
    ResultPageInfoObject<Information> findInfo(@RequestParam("title")String title, @RequestParam("tree_id") Integer tree_id, @RequestParam("state")Integer state
            , @RequestParam("pjt_code")String pjt_code, @RequestParam("pageNum")int pageNum, @RequestParam("pageSize")int pageSize);

    /**
     * 更改知识点的状态
     * @param id
     * @param state
     * @return
     */
    @RequestMapping(value = "/info/updateInformationState", method = RequestMethod.GET)
    boolean updateInformationState(@RequestParam("id") Integer id,@RequestParam("state")Integer state);

    /**
     * 查询某条知识点的详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/info/findInformationById", method = RequestMethod.GET)
    Information findInformationById(@RequestParam("id") Integer id);

    /**
     * 添加一个知识点
     * @return
     */
    @RequestMapping(value = "/info/insertInformation", method = RequestMethod.GET)
    boolean insertInformation(@RequestParam("title") String title,@RequestParam("content") String content,
                              @RequestParam("category") String category,@RequestParam("tree_id") Integer tree_id,@RequestParam("state") Integer state);
    /**
     * 修改某个知识点
     * @param id
     * @return
     */
    @RequestMapping(value = "/info/updateInformation", method = RequestMethod.GET)
    boolean updateInformation(@RequestParam("id") Integer id,@RequestParam("title") String title,@RequestParam("content") String content,
                              @RequestParam("category") String category,@RequestParam("tree_id") Integer tree_id,@RequestParam("state") Integer state);

    /**
     * 批量删除知识点
     * @return
     */
    @RequestMapping(value ="/info/deleteInformation",method = RequestMethod.GET)
    boolean deleteInformation(@RequestParam("id")int[] id);
}
