package com.manji.datahost.mapper.sqlserver;

import java.util.List;

import com.manji.datahost.model.sqlserver.notice.UserNotice;
import com.manji.datahost.model.sqlserver.notice.UserNoticeRead;
import com.manji.datahost.model.sqlserver.notice.UserNoticeReadVo;
import com.manji.datahost.model.sqlserver.notice.UserNoticeVo;

//用户动态信息表
public interface NoticeMapper {

int countUserNotice(UserNoticeVo vo);
List<UserNotice> getUserNotice(UserNoticeVo vo);
void addUserNotice(UserNotice un);
int updUserNotice(UserNotice un);
int delUserNotice(int id);


int countUserNoticeRead(UserNoticeReadVo vo);
List<UserNoticeRead> getUserNoticeRead(UserNoticeReadVo vo);
void addUserNoticeRead(UserNoticeRead unr);
int updUserNoticeRead(UserNoticeRead unr);
int delUserNoticeRead(int id);
}
