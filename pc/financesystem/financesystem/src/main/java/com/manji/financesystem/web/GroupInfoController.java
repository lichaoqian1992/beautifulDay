package com.manji.financesystem.web;

import com.manji.financesystem.common.BaseResult;
import com.manji.financesystem.common.ObjectBaseResult;
import com.manji.financesystem.common.PagedQueryResult;
import com.manji.financesystem.primaryDomain.entiity.extra.GroupInfoAndCountDO;
import com.manji.financesystem.primaryDomain.repository.GroupInfoRepository;
import com.manji.financesystem.requestParam.GroupInfoRequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/3/3.
 */
@RequestMapping("/group")
@Controller
public class GroupInfoController {

    @Autowired
    private GroupInfoRepository groupInfoRepository;

    @RequestMapping("/groupInfo.html")
    public String groupInfo(){

        return "/group/groupInfo";
    }

    /**
     * 查询集团账户信息
     * @param param
     * @return
     */
    @RequestMapping("/queryGroupInfo")
    @ResponseBody
    public BaseResult queryGroupInfo(GroupInfoRequestParam param){

        PagedQueryResult pagedQueryResult = new PagedQueryResult();
        List<GroupInfoAndCountDO> list = groupInfoRepository.queryGroupInfo(param);
        int count = list.size();
        pagedQueryResult.setSuccessResult("SUCCESS");
        pagedQueryResult.setResultList(list);
        pagedQueryResult.setTotalCount(count);
        return pagedQueryResult;
    }

}
