package com.manji.cusystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.manji.cusystem.base.BaseResult;
import com.manji.cusystem.dao.common.Account;
import com.manji.cusystem.service.MailService;
import com.manji.cusystem.utils.MailUtils;
import com.manji.cusystem.utils.PicUtils;
import com.manji.cusystem.vo.message.MessageVo;
import io.swagger.annotations.*;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2017/9/11.
 *
 * 发送邮件的控制层
 */
@RestController
@RequestMapping(value = "/mail")
//@Api("邮件管理相关接口文档")
@Data
public class MailController extends BaseController{

    @Autowired
    private MailService service;

    @Autowired
    JavaMailSender javaMailSender;

    private BaseResult result = new BaseResult();


    private File upload; // 文件
    private String uploadContentType; // 文件类型
    private String uploadFileName; // 文件名

    @Value(value = "${ckeditor.storage.image.path}")
    private String ckeditorStorageImagePath;

    @Value(value = "${ckeditor.access.image.url}")
    private String ckeditorAccessImageUrl;

    /**
     * 新增邮件
     * @param vo
     * @param bindingResult
     * @param sessionId
     * @return
     */
    @RequestMapping(value = "/addMail",method = RequestMethod.GET)
    /*@ApiOperation(value = "新增邮件",notes = "新增邮件")
    @ApiImplicitParams({@ApiImplicitParam(name = "oType",value = "区分是save还是send",required = true,dataType = "String",paramType = "path"),
                        @ApiImplicitParam(name = "cusType",value = "邮件类型，传数字",required = true,dataType = "String",paramType = "path"),
                        @ApiImplicitParam(name = "cusContent",value = "邮件内容",required = true,dataType = "String",paramType = "path"),
                        @ApiImplicitParam(name = "cusAcceptType",value = "接收对象，逗号分割的",required = true,dataType = "String",paramType = "path"),
                        @ApiImplicitParam(name = "cusCount",value = "邮件数量，人数",required = true,dataType = "String",paramType = "path"),
                        @ApiImplicitParam(name = "cusTheme",value = "邮件主题",required = true,dataType = "String",paramType = "path"),
                        @ApiImplicitParam(name = "cusUrl",value = "图片地址",required = false,dataType = "String",paramType = "path"),
                        @ApiImplicitParam(name = "cusKind",value = "标识,邮件=email",required = true,dataType = "String",paramType = "path"),
                        @ApiImplicitParam(name = "sessionId",value = "登录校验",required = true,dataType = "String",paramType = "path")})*/
    public String addMail(MessageVo vo, BindingResult bindingResult, @Param(value = "sessionId") String sessionId, @Param("oType") String oType){

        result = logins(sessionId);
        if(result.getCode().toString().equals("200")){

            if(null == oType || oType.equals("")){
                result.setCode("404");
                result.setContent("参数oType必须为save或者send");
                result.setResult("");
                return JSONObject.toJSONString(result);
            }
            if(bindingResult.hasErrors()){
                result.setCode("404");
                result.setContent(bindingResult.getAllErrors().get(0).getDefaultMessage());
                result.setResult("");
            }else{
                Account user = (Account)result.getResult();
                result = service.addMail(vo,user,oType);
            }
        }
        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 上传图片，返回路径
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadPic",method = RequestMethod.POST)
    public String uploadPic(MultipartFile[] file){
        String path = "";
        //result = logins(sessionId);
        //if(result.getCode().toString().equals("200")){
            if(file.length > 0){
                for(int i=0;i<file.length;i++){
                    if(i != file.length-1){

                        path += new PicUtils().postPic(file[i])+",";
                    }else{
                        path += new PicUtils().postPic(file[i]);
                    }
                }
                //处理返回的路径
                result.setCode("200");
                result.setContent("上传成功");
                result.setResult(path);
            }
       // }

        return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 文件上传
     * @param response
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/imgUpload",method = RequestMethod.POST)
    public void imgUpload(@RequestParam("upload") MultipartFile file,HttpServletResponse response, HttpServletRequest request){

//        String ckeditorStorageImagePath = "F:\\pic\\";
//        String ckeditorAccessImageUrl = "http://192.168.0.211:8801";
        String path = "";
                 if (!file.isEmpty()) {
                         try {
                                 response.setContentType("text/html; charset=UTF-8");
                                 response.setHeader("Cache-Control", "no-cache");

                                 //response.setHeader("X-Frame-Options", "SAMEORIGIN");
                                PrintWriter out = response.getWriter();

                                 String fileClientName = file.getOriginalFilename();
                                 String fileFix = StringUtils.substring(fileClientName,
                                                 fileClientName.lastIndexOf(".") + 1);
                                if (!StringUtils.equalsIgnoreCase(fileFix, "jpg")
                                         && !StringUtils.equalsIgnoreCase(fileFix, "jpeg")
                                         && !StringUtils.equalsIgnoreCase(fileFix, "bmp")
                                         && !StringUtils.equalsIgnoreCase(fileFix, "gif")
                                         && !StringUtils.equalsIgnoreCase(fileFix, "png")) {
//                                         logger.error("Incorrect format of the uploading file -- " + fileFix);

                                     }


                             String pathName = ckeditorStorageImagePath + fileClientName;
                             File newfile = new File(pathName);
                             byte[] bytes = file.getBytes();
                             BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newfile));
                             stream.write(bytes);
                             stream.close();

                             // 组装返回url，以便于ckeditor定位图片 ckeditorAccessImageUrl = "http://192.168.0.211:8801";
                             String fileUrl = ckeditorAccessImageUrl + "/" + newfile.getName();
                             System.out.println(fileUrl);
                             // 将上传的图片的url返回给ckeditor
                             String callback = request.getParameter("CKEditorFuncNum");
                             String script = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + callback + ", '" + fileUrl + "');</script>";

                             out.println(script);
                             out.flush();
                             out.close();
                             /*//实现图片上传
                             if(file != null){
                                 path = new PicUtils().postPic(file);

                             }


                                 // 将上传的图片的url返回给ckeditor
                                 String callback = request.getParameter("CKEditorFuncNum");
                                 //String script = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + callback + ", '" + path + "',''" + ")</script>";
                                out.println("<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + path + "')</script>");
                             //out.println("<script type=\"text/javascript\">var b = window.top!=window.self;document.write( \"当前窗口是否在一个框架中：\"+b );console.log(location.href);CKEDITOR.tools.callFunction(\" + callback + \",'\" + path + \"')</script>");
                             //out.println("<script type=\"text/javascript\">");
                             //out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
                             //        + ",'" + path + "')"); // 相对路径用于显示图片
                             //out.println("</script>");
                            //out.println("<input type=\"hidden\" id=\"pics\" value=\"path\">");

                             //out.println(script);
                                //out.println("</script>");
                                 out.flush();
                                 out.close();*/
                             } catch (Exception e) {
                             System.out.println("You failed to upload " + path + " => " + e.getMessage());
                             }
                     } else {
                        System.out.println("You failed to upload " + path + " because the file was empty.");
                     }


        /*String path = "";

        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        // CKEditor提交的很重要的一个参数
        String callback = request.getParameter("CKEditorFuncNum");
        String expandedName = ""; // 文件扩展名
        if (uploadContentType.equals("image/pjpeg")
                || uploadContentType.equals("image/jpeg")) {
            // IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
            expandedName = ".jpg";
        } else if (uploadContentType.equals("image/png")
                || uploadContentType.equals("image/x-png")) {
            // IE6上传的png图片的headimageContentType是"image/x-png"
            expandedName = ".png";
        } else if (uploadContentType.equals("image/gif")) {
            expandedName = ".gif";
        } else if (uploadContentType.equals("image/bmp")) {
            expandedName = ".bmp";
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
                    + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");
            out.println("</script>");
            return null;
        }
        if (upload.length() > 600 * 1024) {
            out.println("<script type=\"text/javascript\">");
            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
                    + ",''," + "'文件大小不得大于600k');");
            out.println("</script>");
            return null;
        }
        //实现图片上传
        if(upload != null){
                path += new PicUtils().postPic((MultipartFile) upload);

        }

        // 返回"图像"选项卡并显示图片  request.getContextPath()为web项目名
        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
                + ",'" + path + "','')");
        out.println("</script>");
        return null;*/
    }

    @RequestMapping(value = "/admin/upload", method = RequestMethod.POST)
    public Map<String, String> imgUpload3(MultipartFile upfile) {
        System.out.println("============开始上传=================");
        Map<String, String> result = new HashMap<String, String>();
        String path = "";
        //实现图片上传
        if(upfile != null){
            path = new PicUtils().postPic(upfile);

        }
        System.out.println(path);
        File file = new File(path);
        result.put("url", path);
        result.put("size", String.valueOf(file.length()));
        result.put("type",
                file.getName().substring(file.getName().lastIndexOf(".")));
        result.put("state", "SUCCESS");


        return result;
    }

    @RequestMapping(value = "/test")
    @ApiIgnore
    public String test(){

        String to = "376775994@qq.com,1193418905@qq.com";

        //list.add("376775994@qq.com");
       //list.add("1193418905@qq.com");
        /* list.add("738393697@qq.com");
        list.add("1336575170@qq.com");
        list.add("1015598423@qq.com");
        list.add("781408983@qq.com");
        list.add("919239554@qq.com");
        list.add("1103184505@qq.com");*/
        String content = "测试邮件。。。。。。。。";
        String theme = "图片";
        //new MailUtils().sendFileMail(to,content,theme,"C:\\Users\\Administrator\\Desktop\\cus_reason.xlsx",javaMailSender);
        //new MailUtils().sendTextMail(to,content,theme,javaMailSender);
        //new MailUtils().sendBatchMail(list,theme,content,"C:\\Users\\Administrator\\Desktop\\cus_reason.xlsx",javaMailSender);

        new MailUtils().sendInlineResourceMail(to,theme,"<html><body>日落<img src='cid:rscId001' ></body></html>","E:\\日落.png","rscId001",javaMailSender);
        return "SUCCESS";
    }




















    /**
     * ckeditor图片上传
     *
     * @Title imageUpload
     * @param request
     * @param response
     */
    @RequestMapping("/imgUpload2")
    public void imageUpload(HttpServletRequest request, HttpServletResponse response) {
        String DirectoryName = "upload/";
        try {
            ckeditor(request, response, DirectoryName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 图片类型
    private static List<String> fileTypes = new ArrayList<String>();
    static {
        fileTypes.add(".jpg");
        fileTypes.add(".jpeg");
        fileTypes.add(".bmp");
        fileTypes.add(".gif");
        fileTypes.add(".png");
    }
    /**
     * 图片上传
     *
     * @Title upload
     * @param request
     * @param DirectoryName
     *            文件上传目录：比如upload(无需带前面的/) upload/news ..
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public static String upload(HttpServletRequest request, String DirectoryName) throws IllegalStateException,
            IOException {
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession()
                .getServletContext());
        // 图片名称
        String fileName = null;
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 记录上传过程起始时的时间，用来计算上传时间
                // int pre = (int) System.currentTimeMillis();
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        // 获得图片的原始名称
                        String originalFilename = file.getOriginalFilename();
                        // 获得图片后缀名称,如果后缀不为图片格式，则不上传
                        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                        if (!fileTypes.contains(suffix)) {
                            continue;
                        }
                        // 获得上传路径的绝对路径地址(/upload)-->
                        String realPath = request.getSession().getServletContext().getRealPath("/" + DirectoryName);
                        System.out.println(realPath);
                        // 如果路径不存在，则创建该路径
                        File realPathDirectory = new File(realPath);
                        if (realPathDirectory == null || !realPathDirectory.exists()) {
                            realPathDirectory.mkdirs();
                        }
                        // 重命名上传后的文件名 111112323.jpg
                        fileName = "1" + suffix;
                        // 定义上传路径 .../upload/111112323.jpg
                        File uploadFile = new File(realPathDirectory + "\\" + fileName);
                        System.out.println(uploadFile);
                        file.transferTo(uploadFile);
                    }
                }
                // 记录上传该文件后的时间
                // int finaltime = (int) System.currentTimeMillis();
                // System.out.println(finaltime - pre);
            }
        }
        return fileName;
    }

    /**
     * ckeditor文件上传功能，回调，传回图片路径，实现预览效果。
     *
     * @Title ckeditor
     * @param request
     * @param response
     * @param DirectoryName
     *            文件上传目录：比如upload(无需带前面的/) upload/..
     * @throws IOException
     */
    public static void ckeditor(HttpServletRequest request, HttpServletResponse response, String DirectoryName)
            throws IOException {
        String fileName = upload(request, DirectoryName);
        // 结合ckeditor功能
        // imageContextPath为图片在服务器地址，如upload/123.jpg,非绝对路径
        String imageContextPath = request.getContextPath() + "/" + DirectoryName + "/" + fileName;
        response.setContentType("text/html;charset=UTF-8");
        String callback = request.getParameter("CKEditorFuncNum");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imageContextPath + "',''" + ")");
        out.println("</script>");
        out.flush();
        out.close();
    }
}
