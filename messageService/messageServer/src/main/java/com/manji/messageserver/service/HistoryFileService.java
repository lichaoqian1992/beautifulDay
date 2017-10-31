package com.manji.messageserver.service;

import com.manji.messageserver.vo.HistoryFileVO;
import java.util.List;
/**
 * Created by Administrator on 2016/12/15.
 */
public interface HistoryFileService {
    public List<HistoryFileVO> getHistoryFile(String timeInterval);
}
