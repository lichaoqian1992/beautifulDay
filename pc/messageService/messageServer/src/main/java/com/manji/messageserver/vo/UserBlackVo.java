package com.manji.messageserver.vo;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2016/12/14.
 */
@Data
public class UserBlackVo {
    private String action;
    private String url;
    private Object data;
    private Long timestamp;
    private Integer duration;
    private Integer count;
    private String organization;
    private String applicationName;
    private List<Entitie> entities = new ArrayList<Entitie>();
    @Data
    class Entitie{
        private String uuid;
        private String type;
        private Long created;
        private Long modified;
        private Boolean activated;
    }
}
