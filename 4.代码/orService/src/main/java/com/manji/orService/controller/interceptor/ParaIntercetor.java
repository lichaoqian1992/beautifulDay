package com.manji.orService.controller.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.manji.orService.common.Message;
import com.manji.orService.dao.account.Account;
import com.manji.orService.enums.ErrorCodeEnums;
import com.manji.orService.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public class ParaIntercetor implements HandlerInterceptor {

    @Autowired
    private FeignService feignService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String sessionId = httpServletRequest.getParameter("sessionId");

        List<MultipartFile> fileList = null;
        MultipartFile[] file = null;

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(httpServletRequest.getSession().getServletContext());
        HttpSession session = httpServletRequest.getSession(true);
        if (multipartResolver.isMultipart(httpServletRequest)) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) httpServletRequest;
            fileList = multipartRequest.getFiles("file");
            file = (MultipartFile[]) fileList.toArray(new MultipartFile[fileList.size()]);
        }

        try {
            if (sessionId != null) {
                Account back = feignService.sessionId(sessionId);
                if (back.getResult() == 0) {
                    session.setAttribute("account", back);
                } else {
                    Message messageReturn = new Message(ErrorCodeEnums.UnknownError.getCode(), "sessionId异常", null);
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    httpServletResponse.getWriter().write(JSON.toJSONString(messageReturn));
                    httpServletResponse.getWriter().close();
                    session.removeAttribute("account");
                    return false;
                }
            }

//            if (file != null) {
//                String files = "";
//                for (int i = 0; i < file.length; i++) {
//                    JSONObject pice = feignService.uploadPic(file[i]);
//
//                    if (pice.get("code").toString().equals("200")) {
//                        if (i == file.length - 1) {
//                            files += pice.get("result").toString();
//                        } else {
//                            files += pice.get("result").toString() + ",";
//                        }
//                    } else {
//                        Message messageReturn = new Message(ErrorCodeEnums.PicUpload.getCode(), ErrorCodeEnums.PicUpload.getMessage(), null);
//                        httpServletResponse.setContentType("application/json;charset=utf-8");
//                        httpServletResponse.getWriter().write(JSON.toJSONString(messageReturn));
//                        httpServletResponse.getWriter().close();
//                        return false;
//                    }
//                }
//
//                httpServletRequest.setAttribute("files", files);
//            }
        } catch (Exception e) {
            Message messageReturn = new Message(ErrorCodeEnums.UnknownError.getCode(), e.getMessage(), null);
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(JSON.toJSONString(messageReturn));
            httpServletResponse.getWriter().close();
            return false;
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
