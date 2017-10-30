package com.manji.tesystem.controller.api.knowledge;

import com.manji.tesystem.common.enums.CodeEnum;
import com.manji.tesystem.common.exception.BusinessDealException;
import com.manji.tesystem.common.result.BaseListResult;
import com.manji.tesystem.common.result.BaseResult;
import com.manji.tesystem.feign.response.knowledge.Tree;
import com.manji.tesystem.interceptor.LoginAuth;
import com.manji.tesystem.service.KnowledgeSortManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(value = "/api-knowledgeSort", description = "知识库栏目分类管理")
@RequestMapping(value="/knowledgeSort")
public class KnowledgeSortController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    KnowledgeSortManager knowledgeSortManager;
    /**
     * 查询全部栏目分类
     * @return
     */
    @LoginAuth
    @ApiOperation(value="查询全部栏目分类")
    @GetMapping(value ="/findAllTree")
    public BaseListResult<Tree> findAllTree(@RequestHeader(required = false)String sessionId) throws Exception {
        BaseListResult<Tree> baseResult = new BaseListResult<Tree>(CodeEnum.SUCCESS.getCode(), "查询成功");
        List<Tree> result =  knowledgeSortManager.findAllTree();
        baseResult.setResult(result);
        return baseResult;
    }
    /**
     * 通过级别查询栏目分类
     * @return
     */
    @LoginAuth
    @ApiOperation(value="通过级别查询栏目分类")
    @GetMapping(value ="/findTreeByMenuId")
    public BaseListResult<Tree> findTreeByMenuId(@RequestHeader(required = false)String sessionId,@RequestParam(required = true) Integer menuId) throws Exception {
        BaseListResult<Tree> baseResult = new BaseListResult<Tree>(CodeEnum.SUCCESS.getCode(), "查询成功");
        List<Tree> result =  knowledgeSortManager.findTreeByMenuId(menuId);
        baseResult.setResult(result);
        return baseResult;
    }
    /**
     * 查询某个档位下的子集列表
     * @return
     */
    @LoginAuth
    @ApiOperation(value="查询某个档位下的子集列表")
    @GetMapping(value ="/findTreeSun")
    public BaseListResult<Tree> findTreeSun(@RequestHeader(required = false)String sessionId,@RequestParam(required = true) Integer id) throws Exception {
        BaseListResult<Tree> baseResult = new BaseListResult<Tree>(CodeEnum.SUCCESS.getCode(), "查询成功");
        List<Tree> result =  knowledgeSortManager.findTreeSun(id);
        baseResult.setResult(result);
        return baseResult;
    }
    /**
     * 添加节点
     * @return
     */
    @LoginAuth
    @ApiOperation(value="添加节点",notes = "参数说明 pId：父id,如果添加根的时候，pid不传或者传0" +
            "<br/>menuId:多少级" +
            "<br/>title:名字")
    @PostMapping(value ="/add")
    public BaseResult add(@RequestHeader(required = false)String sessionId,@RequestParam(required = false)Integer pId,
                          @RequestParam(required = true)Integer menuId,@RequestParam(required = true)String title) throws Exception {
        BaseResult baseResult = new BaseResult(CodeEnum.SUCCESS.getCode(), "添加成功");
        if(null == pId){
            pId = 0;
        }
        boolean flag = knowledgeSortManager.add(pId,menuId,title);
        if(!flag){
            throw new BusinessDealException("添加失败");
        }
        return baseResult;
    }
    /**
     * 修改节点
     * @return
     */
    @LoginAuth
    @ApiOperation(value="修改节点",notes = "参数说明 id：节点id" +
            "<br/>title:名字")
    @PostMapping(value ="/edit")
    public BaseResult edit(@RequestHeader(required = false)String sessionId,@RequestParam(required = true)Integer id, @RequestParam(required = true)String title) throws Exception {
        BaseResult baseResult = new BaseResult(CodeEnum.SUCCESS.getCode(), "修改成功");
        boolean flag = knowledgeSortManager.updateTitle(id, title);
        if(!flag){
            throw new BusinessDealException("修改失败");
        }
        return baseResult;
    }

    /**
     * 删除节点
     * @return
     */
    @LoginAuth
    @ApiOperation(value="删除节点",notes = "参数说明 id：节点id")
    @PostMapping(value ="/delete")
    public BaseResult delete(@RequestHeader(required = false)String sessionId,@RequestParam(required = true)Integer id) throws Exception {
        BaseResult baseResult = new BaseResult(CodeEnum.SUCCESS.getCode(), "修改成功");
        boolean flag = knowledgeSortManager.delete(id);
        if(!flag){
            throw new BusinessDealException("删除失败");
        }
        return baseResult;
    }
}
