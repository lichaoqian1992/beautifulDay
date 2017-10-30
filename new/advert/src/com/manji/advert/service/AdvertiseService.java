package com.manji.advert.service;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.manji.advert.model.Advertise;
import com.manji.advert.model.param.AdvertiseParam;
import com.manji.advert.utils.DatesUtils;

public class AdvertiseService {



	//查询省
	public List<Record> findFirstRegion() {

		List<Record> first =Db.find("select * from ad_region where type=? ","1");
		
		
		return first;
	}

	//查询县
	public List<Record> findSecodRegion(String provinceCode) {

		List<Record> second =Db.find("select code,region from ad_region where type=? and code like '"+provinceCode+"%'","2");
		
		
		return second;
	}

	//查询市
	public List<Record> findThirdRegion(String cityCode) {

		List<Record> third =Db.find("select code,region from ad_region where type=? and code like '"+cityCode+"%'","3");
		
		
		
		return third;
	}


	//查询对应端口下的频道
	public List<Record> findchannel(String port){
		List<Record> list=Db.find("select * from ad_channel where port='"+port+"'");
		return list;
	}

	//通过频道id查询对应广告位
	public List<Record> findplace(String channelid){
		List<Record> list=Db.find("select * from ad_place where pl_channel='"+channelid+"'");
		return list;
	}

	//通过图片名称搜索管理图片
	public List<Record> findfile(String filename){
		List<Record> list=Db.find("select * from ad_file where file_name like'"+filename+"%'");
		return list;
	}

	//新增广告
	public int insertAdvertise(Advertise advertise){
		//去掉空格
		String val=advertise.getAd_value().replaceAll(" ", "");
		val=val.replaceAll(" ", "");
		String sql="insert into ad_advertise(ad_title,ad_brief,ad_type,ad_region,ad_code,ad_pic,ad_value,ad_place,ad_state,ad_ctime,ad_stime,ad_etime,ad_remark,ad_sort) values('"+advertise.getAd_title()+"','"+advertise.getAd_brief()+"'," +
		"'"+advertise.getAd_type()+"','"+advertise.getAd_region()+"','"+advertise.getAd_code()+"','"+ advertise.getAd_pic()+"'," +
		"'"+val+"','"+ advertise.getAd_place()+"','"+1+"','"+DatesUtils.time()+"'," +
		"null,null,null,"+advertise.getAd_sort()+");";
		return  Db.update(sql);
	}

	//建立点击数
	public int insertCount(){
		Record record=Db.findFirst("select max(id) as id from ad_advertise");
		String sql="insert into ad_count values("+record.getInt("id")+",0,0)";
		return	Db.update(sql);
	}

	//获取最新一条广告
	public Record maxAdvertse(){
		Record record=Db.findFirst("select max(id) as id from ad_advertise");
		return record;
	}

	//通过图片名称查询图片
	public Record findFileByFileName(String filename){
		String sql="select * from ad_file where file_name='"+filename+"'";
		Record record=Db.findFirst(sql);
		return record;
	}

	//通过广告id查询点击次数
	public Record findcountByad_id(int ad_id){
		String sql="select * from ad_count where ad_id='"+ad_id+"'";
		Record record=Db.findFirst(sql);
		return record;
	}

	//通过广告位id查询广告位
	public Record findPlaceById(int placeid){
		String sql="select * from ad_place where id="+placeid+"";
		Record record=Db.findFirst(sql);
		return record;
	}

	//通过终端id查询终端
	public  Record findChannelByid(int ad_channelid){
		String sql="select * from ad_channel where id="+ad_channelid+"";
		Record record=Db.findFirst(sql);
		return record;
	}

	//修改广告状态
	public int updateAdvertiseState(String id,String state){
		String sql="update ad_advertise set ad_state='"+state+"' where id="+id+"";
		return  Db.update(sql);
	}

	//修改广告
	public int updateAdvertiseState(Advertise advertise){
		//去掉空格
		String val=advertise.getAd_value().replaceAll(" ", "");
		val=val.replaceAll(" ", "");

		String sql="update ad_advertise set ad_title='"+advertise.getAd_title()+"',ad_brief='"+advertise.getAd_brief()+"'" +
				",ad_type='"+advertise.getAd_type()+"',ad_region='"+advertise.getAd_region()+"',ad_code='"+advertise.getAd_code()+"',ad_pic='"+advertise.getAd_pic()+"',ad_value='"+val+"'" +
				",ad_place="+advertise.getAd_place()+",ad_sort="+advertise.getAd_sort()+"  where id="+advertise.getId()+"";
		return  Db.update(sql);
	}

	//查询全部广告位
	public List<Record> findadplace(){
		String sql="select * from ad_place";
		return Db.find(sql);
	}

	//返回城市字符串
	public List<Record> codename(String loc) {
		String locStr ="";
		switch(loc.length()){

			case 6:
				locStr +="'"+loc.substring(0, 2)+"',";
				locStr +="'"+loc.substring(0, 4)+"',";
				locStr +="'"+loc+"'";
				break;
			case 4:
				locStr +=loc.substring(0, 2)+",";;
				locStr +=loc;
				break;

			case 2:
				locStr +=loc;
				break;

			default :
				locStr ="00";
		}
		List<Record> record=Db.find("select * from ad_region where code in("+locStr+")");
		return record;
	}



	//通过id查询广告
	public Record getAdvertiseByid(String id){
		return  Db.findFirst("select * from ad_advertise where id=?",id);
	}




	//查询全部广告
	public Page<Record> getAdvertisePage(AdvertiseParam params) {

		StringBuffer sqlBuffer =new StringBuffer("from ad_advertise where 1=1");
		
		if(params.getTitle()!=null&&!"".equals(params.getTitle())){
			sqlBuffer.append("and ad_title like '%"+params.getTitle()+"%'");
		}

		if(params.getType()!=null&&!"".equals(params.getType())){
			sqlBuffer.append("and ad_type ='"+params.getType()+"'");
		}

		if(params.getCode()!=null&&!"".equals(params.getCode())){
			sqlBuffer.append("and ad_code  like'"+params.getCode()+"%'");
		}

		if(params.getState()!=null&&!"".equals(params.getState())){
			sqlBuffer.append("and ad_state ='"+params.getState()+"'");
		}

		if(params.getPlace()!=null&&!"".equals(params.getPlace())){
			sqlBuffer.append("and ad_place in ("+params.getPlace()+")");
		}

		sqlBuffer.append("order by id desc");
		
		Page<Record> page =Db.paginate(params.getIndex(), 20, "select *", sqlBuffer.toString());
		
		return page;
	}

}
