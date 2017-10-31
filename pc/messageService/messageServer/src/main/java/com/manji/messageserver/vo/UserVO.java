package com.manji.messageserver.vo;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by pudding on 2016-12-13.
 */
@Data
public class UserVO {

    private String action;

    private String application;

    private Object params = new Params();

    private String path;

    private String uri;

    private Long timestamp;

    private Integer duration;

    private long organization;

    private String applicationName;

    private List<Entitie> entities = new ArrayList<Entitie>();

    private Object data;
    @Data
    class Params {
        private String limit;
    }
    @Data
    class Entitie {
        private String uuid;

        private String type;

        private Long created;

        private Long modified;

        private String username;

        private Boolean activated;

        private String nickname;
    }
    private String cursor;

    private Integer count;
}
