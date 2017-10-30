package com.manji.tesystem.controller.api.knowledge;

import com.manji.tesystem.common.enums.CodeEnum;
import com.manji.tesystem.common.exception.BusinessDealException;
import com.manji.tesystem.common.result.BaseObjectResult;
import com.manji.tesystem.common.result.BaseResult;
import com.manji.tesystem.controller.api.knowledge.requestbody.AddKnowledgeBody;
import com.manji.tesystem.controller.api.knowledge.requestbody.EditKnowledgeBody;
import com.manji.tesystem.feign.response.common.ResultPageInfoObject;
import com.manji.tesystem.feign.response.knowledge.Information;
import com.manji.tesystem.interceptor.LoginAuth;
import com.manji.tesystem.service.KnowledgeManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "/api-knowledge", description = "知识库管理")
@RequestMapping(value="/knowledge")
public class KnowledgeController {

    @Autowired KnowledgeManager knowledgeManager;
    /**
     * 知识库列表
     * @return
     */
    @LoginAuth
    @ApiOperation(value="知识库列表")
    @GetMapping(value ="/list")
    public BaseObjectResult<ResultPageInfoObject<Information>> list(
            @RequestHeader(required = false)String sessionId,@RequestParam(required = false) String title,
            @RequestParam(required = false) Integer state,@RequestParam(required = false) Integer treeId,
            @RequestParam(required = true) Integer pageNum,@RequestParam(required = true) Integer pageSize) throws Exception {
        BaseObjectResult<ResultPageInfoObject<Information>> baseResult = new BaseObjectResult<ResultPageInfoObject<Information>>(CodeEnum.SUCCESS.getCode(), "查询成功");
        ResultPageInfoObject<Information> res = knowledgeManager.list(title,treeId,state,pageNum,pageSize);
        baseResult.setResult(res);
        return baseResult;
    }
    /**
     * 查询某条知识点的详情
     * @return
     */
    @LoginAuth
    @ApiOperation(value="查询某条知识点的详情", notes = "参数说明 id：知识库id")
    @GetMapping(value ="/findInformationById")
    public BaseObjectResult<Information> findInformationById(@RequestHeader(required = false)String sessionId,@RequestParam(required = true) Integer id) throws Exception {
        BaseObjectResult<Information> baseResult = new BaseObjectResult<Information>(CodeEnum.SUCCESS.getCode(), "查询成功");
        Information result = knowledgeManager.findInformationById(id);
        if(null == result){
            throw new BusinessDealException("查询的信息不存在");
        }
        baseResult.setResult(result);
        return baseResult;
    }
    /**
     * 修改知识点状态
     * @return
     */
    @LoginAuth
    @ApiOperation(value="修改知识点状态", notes = "参数说明 id：知识库id" +
            "<br/> state : 修改知识库信息状态(0:启用,1:禁用)")
    @PostMapping(value ="/updateInformationState")
    public BaseResult updateInformationState(@RequestHeader(required = false)String sessionId,@RequestParam(required = true) Integer id,@RequestParam(required = true) Integer state) throws Exception {
        BaseResult baseResult = new BaseResult(CodeEnum.SUCCESS.getCode(), "修改成功");
        if(!(0 == state || 1 == state)){
            throw new BusinessDealException("未知的状态");
        }
        boolean flag = knowledgeManager.updateInformationState(id, state);
        if(!flag){
            throw new BusinessDealException("修改失败");
        }
        return baseResult;
    }
    /**
     * 添加一个知识点
     * @return
     */
    @LoginAuth
    @ApiOperation(value="添加一个知识点", notes = "Body提交")
    @PostMapping(value ="/add")
    public BaseResult add(@RequestHeader(required = false)String sessionId,@RequestBody @Validated AddKnowledgeBody requestBody) throws Exception {
        BaseResult baseResult = new BaseResult(CodeEnum.SUCCESS.getCode(), "添加成功");
        if(!(0 == requestBody.getState() || 1 == requestBody.getState())){
            throw new BusinessDealException("未知的状态修改");
        }
        boolean flag = knowledgeManager.addInfomation(requestBody);
        if(!flag){
            throw new BusinessDealException("添加失败");
        }
        return baseResult;
    }
    /**
     * 修改某个知识点信息
     * @return
     */
    @LoginAuth
    @ApiOperation(value="修改某个知识点信息", notes = "Body提交")
    @PostMapping(value ="/updateInformation")
    public BaseResult updateInformation(@RequestHeader(required = false)String sessionId,@RequestBody @Validated EditKnowledgeBody requestBody) throws Exception {
        BaseResult baseResult = new BaseResult(CodeEnum.SUCCESS.getCode(), "修改成功");
        if(!(0 == requestBody.getState() || 1 == requestBody.getState())){
            throw new BusinessDealException("未知的状态修改");
        }
        boolean flag = knowledgeManager.updateInformation(requestBody);
        if(!flag){
            throw new BusinessDealException("修改失败");
        }
        return baseResult;
    }

    /**
     * 删除知识点
     * @return
     */
    @LoginAuth
    @ApiOperation(value="删除知识点", notes = "Body提交")
    @PostMapping(value ="/deleteInformation")
    public BaseResult deleteInformation(@RequestHeader(required = false)String sessionId,@RequestBody int[] ids) throws Exception {
        BaseResult baseResult = new BaseResult(CodeEnum.SUCCESS.getCode(), "删除成功");
        if(null == ids){
            throw new BusinessDealException("请选择要删除的知识点");
        }
        if(ids.length <= 0){
            throw new BusinessDealException("请选择要删除的知识点");
        }
        boolean flag = knowledgeManager.deleteInformation(ids);
        if(!flag){
            throw new BusinessDealException("删除失败");
        }
        return baseResult;
    }
}
