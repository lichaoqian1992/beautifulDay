package com.manji.messageserver.aop;

import com.manji.messageserver.common.enums.StatusEnum;
import com.manji.messageserver.common.errorcode.ErrorCodeEnum;
import com.manji.messageserver.common.exception.AssertException;
import com.manji.messageserver.common.exception.ServiceException;
import com.manji.messageserver.common.utils.CollectionsUtils;
import com.manji.messageserver.dao.entity.BlackDO;
import com.manji.messageserver.dao.entity.FriendsDO;
import com.manji.messageserver.dao.entity.UserDO;
import com.manji.messageserver.dao.repository.BlackDAO;
import com.manji.messageserver.dao.repository.FriendsDAO;
import com.manji.messageserver.dao.repository.UserDAO;
import com.manji.messageserver.requestParam.*;
import com.manji.messageserver.responseResult.BaseResult;
import com.manji.messageserver.responseResult.StandardResultInfo;
import com.manji.messageserver.utils.DateUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;



import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by pudding on 2016-12-12.
 */
@Aspect
@Component
public class WebServiceAspect {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private FriendsDAO friendsDAO;

    @Autowired
    private BlackDAO blackDAO;

    private static final Logger logger = LoggerFactory.getLogger(WebServiceAspect.class);

    @Around("execution(* com.manji.messageserver.service.*.*(..))")
    public Object handleException(ProceedingJoinPoint pjp) throws Throwable {
        boolean isStandardWebServiceMethod = false;
        Object[] args = pjp.getArgs();
        Signature signature = pjp.getSignature();
        Class returnType = ((MethodSignature) signature).getReturnType();
        if (args != null && args.length > 0 && args[0] instanceof BaseRequestParam && BaseResult.class.isAssignableFrom(returnType)) {
            isStandardWebServiceMethod = true;
        }

        if (isStandardWebServiceMethod) {
            BaseRequestParam param = (BaseRequestParam) args[0];
            logger.info("WebService入参：param={}", param);

            Set<ConstraintViolation<BaseRequestParam>> click = param.click();
            if (!CollectionsUtils.isEmptyCollection(click)) {
                for (ConstraintViolation<BaseRequestParam> violations : click) {
                    return BaseResult.getFailResult(ErrorCodeEnum.PARAM_VALIDATION_FAIL.getCode(), ErrorCodeEnum.PARAM_VALIDATION_FAIL.getMessage(), violations.getPropertyPath() + "|" + violations.getMessage());
                }
            }
            Object origResult = null;

            try {
                origResult = pjp.proceed(args);
            } catch (AssertException e) {
                logger.info("校验失败：", e);
                handleExceptionInTransaction();
                return BaseResult.getFailResult(e.getErrorCode(), e.getErrorMessage());
            } catch (IllegalArgumentException e) {
                logger.info("参数错误：", e);
                handleExceptionInTransaction();
                ErrorCodeEnum errorCode = ErrorCodeEnum.getByCode(e.getMessage());
                BaseResult result = new BaseResult();
                if (errorCode != null) {
                    result.setFailResult(errorCode.getCode(), errorCode.getMessage());
                } else {
                    result.setFailResult(ErrorCodeEnum.INVALID_ARGUMENTS.getCode(),
                            e.getMessage());
                }
                return result;
            } catch (ServiceException e) {
                logger.info("web服务异常：", e);
                handleExceptionInTransaction();
                return BaseResult.getFailResult(e.getResultCode(), e.getResultMessage());

            } catch (DataIntegrityViolationException e) {
                handleExceptionInTransaction();
                BaseResult result = new BaseResult();
                if (e.getCause() instanceof ConstraintViolationException) {
                    logger.info("数据重复：", e);
                    result.setFailResult(ErrorCodeEnum.DATA_DUPLICATE.getCode(), "数据重复");
                } else {
                    logger.info("数据库异常：", e);
                    result.setFailResult(ErrorCodeEnum.DB_ERROR.getCode(), "数据库异常");
                }
                return result;
            } catch (DataAccessException e) {
                handleExceptionInTransaction();
                logger.info("数据库访问错误：", e);
                return BaseResult.getFailResult(ErrorCodeEnum.DB_ERROR.getCode(), "数据库访问错误");
            } catch (Exception e) {
                logger.error("未知异常：", e);
                handleExceptionInTransaction();
                return BaseResult.getFailResult(ErrorCodeEnum.UNKNOWN_ERROR.getCode(), ErrorCodeEnum.UNKNOWN_ERROR.getMessage());
            }
            return origResult;
        } else {
            return pjp.proceed(args);
        }
    }


    /**
     * 单个注册的数据保存后置处理
     * @param joinPoint
     * @param returnValue
     */
    @AfterReturning(pointcut="execution(* com.manji.messageserver.service.UserService.registerIM(..)))", returning="returnValue")
    public void handelUserRegisterDataSave(JoinPoint joinPoint, Object returnValue) {
        Object[] args = joinPoint.getArgs();
        if (args != null && args[0] != null) {
            RegisterRequestParam requestParam = (RegisterRequestParam) args[0];
            StandardResultInfo resultInfo= (StandardResultInfo) returnValue;
            if(resultInfo.getStatusEnum().equals(StatusEnum.SUCCESS)){
                UserDO userDO=new UserDO();
                userDO.setActivated("true");
                userDO.setCreateTime(DateUtil.getFormatDate(DateUtil.getFormatStr(new Date())));
                userDO.setStatus("0");
                userDO.setUserName(requestParam.getUserName());
                userDO.setUserPassword(requestParam.getPassword());
                userDAO.save(userDO);
                logger.info("用户数据入库成功 user={}",userDO);
            }else{
                logger.warn("环信请求失败-不做数据保存 result={}",resultInfo);
            }
        }

    }


    /**
     * 用户昵称修改保存
     * @param joinPoint
     * @param returnValue
     */
    @AfterReturning(pointcut="execution(* com.manji.messageserver.service.UserService.modifyUserNickname(..)))", returning="returnValue")
    public void handelUserNickNameDataSave(JoinPoint joinPoint, Object returnValue){
        Object[] args = joinPoint.getArgs();
        if (args != null && args[0] != null) {
            modifyUserNicknameRequestParam requestParam= (modifyUserNicknameRequestParam) args[0];
            StandardResultInfo resultInfo= (StandardResultInfo) returnValue;
            if(resultInfo.getStatusEnum().equals(StatusEnum.SUCCESS)){
                UserDO user = userDAO.findByUserName(requestParam.getUserName());
                if(user!=null){
                    user.setModifyTime(DateUtil.getFormatDate(DateUtil.getFormatStr(new Date())));
                    user.setNickname(requestParam.getNickname());
                    userDAO.save(user);
                }else{
                    logger.warn("用户为空不做任何处理 requestParam={}",requestParam);
                }
            }else{
                logger.warn("环信请求失败-不做数据同步 result={}",resultInfo);
            }

        }
    }


    /**
     * 用户密码修改数据同步
     * @param joinPoint
     * @param returnValue
     */
    @AfterReturning(pointcut="execution(* com.manji.messageserver.service.UserService.modifyUserPassword(..)))", returning="returnValue")
    public void handelUserPasswordDateSave(JoinPoint joinPoint, Object returnValue){
        Object[] args = joinPoint.getArgs();
        if (args != null && args[0] != null) {
            modifyUserPasswordRequestParam requestParam= (modifyUserPasswordRequestParam) args[0];
            StandardResultInfo resultInfo= (StandardResultInfo) returnValue;
            if(resultInfo.getStatusEnum().equals(StatusEnum.SUCCESS)){
                UserDO user = userDAO.findByUserName(requestParam.getUserName());
                if(user!=null){
                    user.setModifyTime(DateUtil.getFormatDate(DateUtil.getFormatStr(new Date())));
                    user.setUserPassword(requestParam.getNewPassword());
                    userDAO.save(user);
                }else{
                    logger.warn("用户为空不做任何处理 requestParam={}",requestParam);
                }
            }else{
                logger.warn("环信请求失败-不做数据同步 result={}",resultInfo);
            }
        }


    }


    /**
     * 批量注册环信成功以后保存数据到数据库的后置处理
     * @param joinPoint 参数值
     * @param returnValue 返回的结果
     */
    @AfterReturning(pointcut = "execution(* com.manji.messageserver.service.UserService.registerBatch(..)))",returning = "returnValue")
    public void handelUserRegisterBitchDataSave(JoinPoint joinPoint,Object returnValue){
        //得到参数和返回值，确保环信那边注册成功以后，我们进行入库操作
        Object[] args = joinPoint.getArgs();//得到参数值
        StandardResultInfo resultInfo= (StandardResultInfo) returnValue;//返回值
        if(args != null && args[0] != null){
            List<BatchRegisterRequestParam> lists= (List<BatchRegisterRequestParam>) args[0];

            List<UserDO> userDOs=new ArrayList<UserDO>();
            if(resultInfo.getStatusEnum().equals(StatusEnum.SUCCESS)){
                for (BatchRegisterRequestParam param:lists) {
                    UserDO userDO=new UserDO();
                    userDO.setActivated("true");
                    userDO.setCreateTime(DateUtil.getFormatDate(DateUtil.getFormatStr(new Date())));
                    userDO.setStatus("0");
                    userDO.setUserName(param.getUsername());
                    userDO.setUserPassword(param.getPassword());
                    userDOs.add(userDO);
                }
                userDAO.save(userDOs);
                logger.info("用户数据入库成功 users={}",userDOs);

            }else{
                logger.warn("环信请求失败-不做数据保存 result={}",resultInfo);
            }
        }
    }

    /**
     * 删除单一用户的后置处理
     * @param joinPoint
     * @param returnValue
     */
    @AfterReturning(pointcut = "execution(* com.manji.messageserver.service.UserService.deleteUser(..)))",returning = "returnValue")
    public void handelDeleteUserDataSave(JoinPoint joinPoint,Object returnValue){
        Object[] args = joinPoint.getArgs();//得到参数
        StandardResultInfo resultInfo= (StandardResultInfo) returnValue;//返回值
        deleteUserRequestParam requestParam = (deleteUserRequestParam)args[0];
        //查询一下该用户
        if(resultInfo.getStatusEnum().equals(StatusEnum.SUCCESS)){
            UserDO user = userDAO.findByUserName(requestParam.getUserName());
            if(user != null){
                user.setModifyTime(DateUtil.getFormatDate(DateUtil.getFormatStr(new Date())));
                user.setStatus("1");
                userDAO.save(user);
                logger.info("用户删除成功 users={}",user);
            }else{
                logger.warn("用户为空不做任何处理 requestParam={}",requestParam);
            }
        }else{
            logger.warn("环信请求失败-不做用户删除操作 result={}",resultInfo);
        }

    }

    /**
     * 解禁用户的后置处理
     * @param joinPoint
     * @param returnValue
     */
    @AfterReturning(pointcut = "execution(* com.manji.messageserver.service.UserService.activateUser(..)))",returning = "returnValue")
    public void handelActivateUserDataSave(JoinPoint joinPoint,Object returnValue){
        Object[] args = joinPoint.getArgs();//得到参数
        StandardResultInfo resultInfo= (StandardResultInfo) returnValue;//返回值
        deactivateUserRequestParam requestParam = (deactivateUserRequestParam)args[0];
        if(resultInfo.getStatusEnum().equals(StatusEnum.SUCCESS)){
            //查询一下该用户
            UserDO user = userDAO.findByUserName(requestParam.getUserName());
            if(user != null){
                user.setModifyTime(DateUtil.getFormatDate(DateUtil.getFormatStr(new Date())));
                user.setActivated("true");
                userDAO.save(user);
                logger.info("用户解禁成功 users={}",user);
            }else{
                logger.warn("用户为空不做任何处理 requestParam={}",requestParam);
            }
        }else{
            logger.warn("环信请求失败-不做用户解禁操作 result={}",resultInfo);
        }
    }

    /**
     * 禁用用户数据同步
     * @param joinPoint
     * @param returnValue
     */
    @AfterReturning(pointcut = "execution(* com.manji.messageserver.service.UserService.deactivateUser(..)))",returning = "returnValue")
    public void handelDeactivateUserDataSave(JoinPoint joinPoint,Object returnValue){
        Object[] args = joinPoint.getArgs();//得到参数值
        StandardResultInfo resultInfo= (StandardResultInfo) returnValue;//返回值
        if(args != null && args[0] != null){
            deactivateUserRequestParam requestParam= (deactivateUserRequestParam) args[0];
            if(resultInfo.getStatusEnum().equals(StatusEnum.SUCCESS)){
                UserDO user = userDAO.findByUserName(requestParam.getUserName());
                if(user!=null) {
                    user.setModifyTime(DateUtil.getFormatDate(DateUtil.getFormatStr(new Date())));
                    user.setActivated("false");
                    userDAO.save(user);
                }else{
                    logger.warn("用户数据为空不做任何处理 requestParam={}",requestParam);
                }
            }else{
                logger.warn("环信请求失败-不做数据同步 result={}",resultInfo);
            }
        }
    }

    /**
     * 用户成功添加好友的后置处理
     * @param joinPoint
     * @param returnValue
     */
    @AfterReturning(pointcut = "execution(* com.manji.messageserver.service.UserFriendsService.add(..))",returning = "returnValue")
    public void handleAddUserFriendsDataSave(JoinPoint joinPoint,Object returnValue){
        Object[] args = joinPoint.getArgs();
        if(args != null && args[0] != null) {
            AddFriendsRequestParam addFriendsRequestParam = (AddFriendsRequestParam) args[0];
            StandardResultInfo resultInfo = (StandardResultInfo) returnValue;//返回值
            if(resultInfo.getStatusEnum().equals(StatusEnum.SUCCESS)){
                List<FriendsDO> friendsDOs1 = new ArrayList<FriendsDO>();
                //因为环信那边不管是不是好友，都会执行添加好友的操作，所以这里需要判断
                //先查询数据库是否存在好友关系
                FriendsDO list = friendsDAO.findByUserNameAndFriendsName(addFriendsRequestParam.getOwnerUserName(), addFriendsRequestParam.getFriendUserName());
                if(list == null){
                    //因为添加好友是相互的操作，所以保存两条信息在数据库
                    FriendsDO aDo1=new FriendsDO();
                    FriendsDO aDo2=new FriendsDO();
                    Date date = new Date();
                    aDo1.setUserName(addFriendsRequestParam.getOwnerUserName());
                    aDo1.setFriendsName(addFriendsRequestParam.getFriendUserName());
                    aDo1.setCreateTime(DateUtil.getFormatDate(DateUtil.getFormatStr(date)));
                    aDo2.setUserName(addFriendsRequestParam.getFriendUserName());
                    aDo2.setFriendsName(addFriendsRequestParam.getOwnerUserName());
                    aDo2.setCreateTime(DateUtil.getFormatDate(DateUtil.getFormatStr(date)));
                    friendsDOs1.add(aDo1);
                    friendsDOs1.add(aDo2);
                    friendsDAO.save(friendsDOs1);
                    logger.info("添加好友成功 friendsDO={}",friendsDOs1);
                }else{
                    logger.warn("已存在好友关系，不做入库操作 list={}",list);
                }
            }else{
                logger.warn("环信添加好友失败，不做入库操作 result={}",resultInfo);
            }
        }else{
            logger.warn("没有找到参数 args={}",args[0]);
        }
    }

    /**
     * 删除好友的后置处理
     * @param joinPoint
     * @param returnValue
     */
    @AfterReturning(pointcut = "execution(* com.manji.messageserver.service.UserFriendsService.del(..))",returning = "returnValue")
    public void handleDelUserFriendsDataSave(JoinPoint joinPoint,Object returnValue){
        //得到参数和返回值
        Object[] args = joinPoint.getArgs();
        if(args != null && args[0] != null){
            AddFriendsRequestParam addFriendsRequestParam = (AddFriendsRequestParam) args[0];
            StandardResultInfo resultInfo = (StandardResultInfo) returnValue;//返回值
            if(resultInfo.getStatusEnum().equals(StatusEnum.SUCCESS)){
                //先查询数据库是否存在好友关系
                FriendsDO list = friendsDAO.findByUserNameAndFriendsName(addFriendsRequestParam.getOwnerUserName(),addFriendsRequestParam.getFriendUserName());
                FriendsDO list2 = friendsDAO.findByUserNameAndFriendsName(addFriendsRequestParam.getFriendUserName(),addFriendsRequestParam.getOwnerUserName());
                List<FriendsDO> friendsDOs = new ArrayList<FriendsDO>();
                //有可能认为操作，导致只有一方保存了好友关系
                if(list != null){
                    friendsDOs.add(list);
                }
                if(list2 != null){
                    friendsDOs.add(list2);
                }
                if(friendsDOs.size()>0){
                    friendsDAO.deleteInBatch(friendsDOs);
                    logger.info("删除好友成功 friendsDOs={}",friendsDOs);
                }else{
                    logger.warn("删除好友失败，不做数据库操作 friendsDOs={}",friendsDOs);
                }
            }else{
                logger.warn("环信删除好友失败，不做数据库操作 resultInfo={}",resultInfo);
            }
        }
    }

    /**
     * 用户添加黑名单的后置处理
     * @param joinPoint
     * @param returnValue
     */
    @AfterReturning(pointcut = "execution(* com.manji.messageserver.service.UserBlackService.addUserBlack(..))",returning = "returnValue")
    public void handleAddUserBlackDataSave(JoinPoint joinPoint,Object returnValue){
        Object[] args = joinPoint.getArgs();
        if(args != null && args[0] != null && args[1] != null){
            addUserBlackRequestParam requestParams = (addUserBlackRequestParam)args[0];
            StandardResultInfo resultInfo = (StandardResultInfo) returnValue;//返回值
            List<BlackDO> blackDOs = new ArrayList<BlackDO>();
            Date date = new Date();
            if(resultInfo.getStatusEnum().equals(StatusEnum.SUCCESS)){
                //先查询数据库是否存在该用户的黑名单
                for(int i=0;i<requestParams.getUsernames().size();i++){
                    BlackDO list = blackDAO.findByUserNameAndBlackName((String)args[1] , requestParams.getUsernames().get(i));
                    if(list == null){
                        BlackDO blackDO =new BlackDO();
                        blackDO.setUserName((String)args[1]);
                        blackDO.setBlackName(requestParams.getUsernames().get(i));
                        blackDO.setCreateTime(DateUtil.getFormatDate(DateUtil.getFormatStr(date)));
                        blackDOs.add(blackDO);
                    }else{
                        logger.info("用户"+requestParams.getUsernames().get(i)+"已经在黑名单，不做数据库操作 blackInfo={}",list);
                    }
                }
                if(blackDOs.size()>0){
                    blackDAO.save(blackDOs);
                    logger.info("添加黑名单成功 Black={}",blackDOs);
                }
            }else{
                logger.warn("环信添加黑名单失败，不错数据库操作 resultInfo={}",resultInfo);
            }
        }
    }

    /**
     * 删除黑名单的后置处理
     * @param joinPoint
     * @param returnValue
     */
    @AfterReturning(pointcut = "execution(* com.manji.messageserver.service.UserBlackService.removeUserBlack(..))",returning = "returnValue")
    public void handleRemoveUserBlackDataSave(JoinPoint joinPoint,Object returnValue){
        Object[] args = joinPoint.getArgs();
        if(args != null && args[0] != null){
            removeBlackUserRequestParam requestParam = (removeBlackUserRequestParam)args[0];
            StandardResultInfo resultInfo = (StandardResultInfo) returnValue;//返回值
            if(resultInfo.getStatusEnum().equals(StatusEnum.SUCCESS)){
                BlackDO blackDO = blackDAO.findByUserNameAndBlackName(requestParam.getOwnerUserName(),requestParam.getBlockedUserName());
                if(blackDO != null){
                    blackDAO.delete(blackDO);
                    logger.info("删除黑名单成功 blackDo={}",blackDO);
                }else{
                    logger.warn("该用户不存在该黑名单用户,不做数据库操作 blackDo={}",blackDO);
                }
            }else{
                logger.warn("环信删除黑名单失败，不做数据库操作 resultInfo={}",resultInfo);
            }
        }
    }
    private void handleExceptionInTransaction() {
        try {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } catch (Exception e) {
            //	忽略事务不存在的异常
        }
    }
}
