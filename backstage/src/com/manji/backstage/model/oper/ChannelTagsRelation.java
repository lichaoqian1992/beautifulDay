package com.manji.backstage.model.oper;

//dt_channel_tags_relation（热门频道TAG标签对应关系信息表）

public class ChannelTagsRelation {
	int id;
	String channel_id;
	int tag_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public int getTag_id() {
		return tag_id;
	}
	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}
	
}
