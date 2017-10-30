package com.manji.msgservice.controller.template;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class EditRequstBody {
    /**
     * 主键id
     *
     * @mbggenerated
     */
    @NotNull(message ="id不能为空")
    private Long templateId;
    /**
     * 调用别名
     *
     * @mbggenerated
     */
    private String callName;


    /**
     * 所属业务分组类型
     *
     * @mbggenerated
     */
    private String bizType;

    /**
     * 模板名字
     *
     * @mbggenerated
     */
    private String templateName;

    /**
     * 模板内容
     *
     * @mbggenerated
     */
    private String templateContent;

    /**
     * 模板状态 open:开启，close:关闭
     *
     * @mbggenerated
     */
    private String templateStatus;

    /**
     * 时间开始
     *
     * @mbggenerated
     */
    private String liveTime;

    /**
     * 时间结束
     *
     * @mbggenerated
     */
    private String deadTime;
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column msg_template.template_id
     *
     * @return the value of msg_template.template_id
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "主键id")
    public Long getTemplateId() {
        return templateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column msg_template.template_id
     *
     * @param templateId the value for msg_template.template_id
     *
     * @mbggenerated
     */
    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column msg_template.call_name
     *
     * @return the value of msg_template.call_name
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "调用别名")
    public String getCallName() {
        return callName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column msg_template.call_name
     *
     * @param callName the value for msg_template.call_name
     *
     * @mbggenerated
     */
    public void setCallName(String callName) {
        this.callName = callName == null ? null : callName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column msg_template.biz_type
     *
     * @return the value of msg_template.biz_type
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "所属业务分组类型")
    public String getBizType() {
        return bizType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column msg_template.biz_type
     *
     * @param bizType the value for msg_template.biz_type
     *
     * @mbggenerated
     */
    public void setBizType(String bizType) {
        this.bizType = bizType == null ? null : bizType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column msg_template.template_name
     *
     * @return the value of msg_template.template_name
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "模板名字")
    public String getTemplateName() {
        return templateName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column msg_template.template_name
     *
     * @param templateName the value for msg_template.template_name
     *
     * @mbggenerated
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column msg_template.template_content
     *
     * @return the value of msg_template.template_content
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "模板内容")
    public String getTemplateContent() {
        return templateContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column msg_template.template_content
     *
     * @param templateContent the value for msg_template.template_content
     *
     * @mbggenerated
     */
    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent == null ? null : templateContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column msg_template.template_status
     *
     * @return the value of msg_template.template_status
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "模板状态 open:开启，close:关闭")
    public String getTemplateStatus() {
        return templateStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column msg_template.template_status
     *
     * @param templateStatus the value for msg_template.template_status
     *
     * @mbggenerated
     */
    public void setTemplateStatus(String templateStatus) {
        this.templateStatus = templateStatus == null ? null : templateStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column msg_template.live_time
     *
     * @return the value of msg_template.live_time
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "时间开始")
    public String getLiveTime() {
        return liveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column msg_template.live_time
     *
     * @param liveTime the value for msg_template.live_time
     *
     * @mbggenerated
     */
    public void setLiveTime(String liveTime) {
        this.liveTime = liveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column msg_template.dead_time
     *
     * @return the value of msg_template.dead_time
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "时间结束")
    public String getDeadTime() {
        return deadTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column msg_template.dead_time
     *
     * @param deadTime the value for msg_template.dead_time
     *
     * @mbggenerated
     */
    public void setDeadTime(String deadTime) {
        this.deadTime = deadTime;
    }
}
