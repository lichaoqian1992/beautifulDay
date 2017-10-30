package com.manji.backstage.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.ArtiMapper;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.content.Article;
import com.manji.backstage.model.content.ArticleAlbums;
import com.manji.backstage.model.content.ArticleCategory;
import com.manji.backstage.model.content.ArticleCategoryCount;
import com.manji.backstage.model.content.ArticleCategoryField;
import com.manji.backstage.model.content.ArticleCategorySpec;
import com.manji.backstage.model.content.ArticleCategoryUser;
import com.manji.backstage.model.content.ArticleComment;
import com.manji.backstage.model.content.ArticleContent;
import com.manji.backstage.model.content.ArticleCount;
import com.manji.backstage.model.content.ArticleGoods;
import com.manji.backstage.model.content.ArticleInfo;
import com.manji.backstage.model.content.ArticleScreen;
import com.manji.backstage.model.order.OrderCommentFalse;
import com.manji.backstage.model.order.OrderCommentTem;
import com.manji.backstage.service.ArtiService;
import com.manji.backstage.utils.MD5util;
import com.manji.backstage.utils.PostUrlUtils;
import com.manji.backstage.vo.content.ArticleAlbumsVo;
import com.manji.backstage.vo.content.ArticleCategoryCountVo;
import com.manji.backstage.vo.content.ArticleCategoryFieldVo;
import com.manji.backstage.vo.content.ArticleCategorySpecVo;
import com.manji.backstage.vo.content.ArticleCategoryUserVo;
import com.manji.backstage.vo.content.ArticleCategoryVo;
import com.manji.backstage.vo.content.ArticleCommentVo;
import com.manji.backstage.vo.content.ArticleContentVo;
import com.manji.backstage.vo.content.ArticleCountVo;
import com.manji.backstage.vo.content.ArticleGoodsVo;
import com.manji.backstage.vo.content.ArticleInfoVo;
import com.manji.backstage.vo.content.ArticleScreenVo;
import com.manji.backstage.vo.content.ArticleVo;
import com.manji.backstage.vo.order.OrderCommentFalseVo;
import com.manji.backstage.vo.order.OrderCommentTemVo;

import net.sf.json.JSONObject;
@Service
public class ArtiServiceImpl implements ArtiService {

	@Autowired
	private ArtiMapper mapper;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Page<Article> queryArticle(ArticleVo vo) {

		Page<Article> page = new Page<Article>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<Article> dataList = mapper.queryArticle(vo);
		int totalCount = mapper.countArticle(vo);
		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public Article getArticle(int id) {
		return mapper.getArticle(id);
	}

	@Override
	public boolean updArticle(Article art) {
		if (mapper.updArticle(art) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void addArticle(Article art) {
		mapper.addArticle(art);
	}

	@Override
	public boolean delArticle(int id) {
		if (mapper.delArticle(id) > 0) {
			return true;
		}

		return false;
	}

	@Override
	public Page<ArticleInfo> queryArticleInfo(ArticleInfoVo vo) {
		Page<ArticleInfo> page = new Page<ArticleInfo>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<ArticleInfo> dataList = mapper.queryArticleInfo(vo);
		int totalCount = mapper.countArticleInfo(vo);
		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public ArticleInfo getArticleInfo(int id) {
		return mapper.getArticleInfo(id);
	}

	@Override
	public boolean updArticleInfo(ArticleInfo ai) {
		if (mapper.updArticleInfo(ai) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void addArticleInfo(ArticleInfo ai) {
		mapper.addArticleInfo(ai);
	}

	@Override
	public boolean delArticleInfo(int id) {
		if (mapper.delArticleInfo(id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Page<ArticleContent> queryArticleContent(ArticleContentVo vo) {
		Page<ArticleContent> page = new Page<ArticleContent>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<ArticleContent> dataList = mapper.queryArticleContent(vo);
		int totalCount = mapper.countArticleContent(vo);
		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public ArticleContent getArticleContent(int id) {
		return mapper.getArticleContent(id);
	}

	@Override
	public boolean updArticleContent(ArticleContent ac) {
		if (mapper.updArticleContent(ac) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void addArticleContent(ArticleContent ac) {
		mapper.addArticleContent(ac);
	}

	@Override
	public boolean delArticleContent(int id) {
		if (mapper.delArticleContent(id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Page<ArticleCount> queryArticleCount(ArticleCountVo vo) {
		Page<ArticleCount> page = new Page<ArticleCount>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<ArticleCount> dataList = mapper.queryArticleCount(vo);
		int totalCount = mapper.countArticleCount(vo);
		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public ArticleCount getArticleCount(int id) {
		return mapper.getArticleCount(id);
	}

	@Override
	public boolean updArticleCount(ArticleCount ac) {
		if (mapper.updArticleCount(ac) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void addArticleCount(ArticleCount ac) {
		mapper.addArticleCount(ac);
	}

	@Override
	public boolean delAtricleCount(int id) {
		if (mapper.delArticleCount(id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Page<ArticleComment> queryArticleComment(ArticleCommentVo vo) {
		Page<ArticleComment> page = new Page<ArticleComment>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<ArticleComment> dataList = mapper.queryArticleComment(vo);
		int totalCount = mapper.countArticleComment(vo);
		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public ArticleComment getArticleComment(int id) {
		return mapper.getArticleComment(id);
	}

	@Override
	public boolean updArticleComment(ArticleComment ac) {
		if (mapper.updArticleComment(ac) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void addArticleComment(ArticleComment ac) {
		mapper.addArticleComment(ac);
	}

	@Override
	public boolean delArticleComment(int id) {
		if (mapper.delArticleComment(id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Page<ArticleAlbums> queryArticleAlbums(ArticleAlbumsVo vo) {
		Page<ArticleAlbums> page = new Page<ArticleAlbums>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<ArticleAlbums> dataList = mapper.queryArticleAlbums(vo);
		int totalCount = mapper.countArticleAlbums(vo);
		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public ArticleAlbums getArticleAlbums(int id) {
		return mapper.getArticleAlbums(id);
	}

	@Override
	public boolean updArticleAlbums(ArticleAlbums aa) {
		if (mapper.updArticleAlbums(aa) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void addArticleAlbums(ArticleAlbums aa) {
		mapper.addArticleAlbums(aa);

	}

	@Override
	public boolean delArticleAlbums(int id) {
		if (mapper.delArticleAlbums(id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Page<ArticleGoods> queryArticleGoods(ArticleGoodsVo vo) {
		Page<ArticleGoods> page = new Page<ArticleGoods>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<ArticleGoods> dataList = mapper.queryArticleGoods(vo);
		int totalCount = mapper.countArticleGoods(vo);
		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public ArticleGoods getArticleGoods(int id) {

		return mapper.getArticleGoods(id);
	}

	@Override
	public boolean updArticleGoods(ArticleGoods ag) {
		if (mapper.updArticleGoods(ag) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void addArticleGoods(ArticleGoods ag) {
		mapper.addArticleGoods(ag);
	}

	@Override
	public boolean delArticleGoods(int id) {
		if (mapper.delArticleGoods(id) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Page<ArticleScreen> queryArticleScreen(ArticleScreenVo vo) {
		Page<ArticleScreen> page = new Page<ArticleScreen>();

		if ("".equals(vo.getIndex()) || 0 == vo.getIndex()) {
			vo.setIndex(1);
			page.setIndex(1);
		} else {
			page.setIndex(vo.getIndex());
		}

		List<ArticleScreen> dataList = mapper.queryArticleScreen(vo);
		int totalCount = mapper.countArticleScreen(vo);
		page.transform(totalCount, dataList);

		return page;
	}

	@Override
	public ArticleScreen getArticleScreen(long id) {
		return mapper.getArticleScreen(id);
	}

	@Override
	public boolean updArticleScreen(ArticleScreen as) {
		if (mapper.updArticleScreen(as) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void addArticleScreen(ArticleScreen as) {
		mapper.addArticleScreen(as);
	}

	@Override
	public boolean delArticleScreen(int id) {
		if (mapper.delArticleScreen(id) > 0) {
			return true;
		}
		return false;
	}

	

	
	@Override
	public Page<ArticleCategory> queryArticleCategory(ArticleCategoryVo vo) {
		Page<ArticleCategory> page =new Page<ArticleCategory>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ArticleCategory> dataList =mapper.queryArticleCategory(vo);
		int totalCount =mapper.countArticleCategory(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public ArticleCategory getArticleCategory(int id) {
		return mapper.getArticleCategory(id);
	}
	
	@Override
	public boolean updArticleCategory(ArticleCategory aru) {
		if(mapper.updArticleCategory(aru)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public void addArticleCategory(ArticleCategory aru) {
		mapper.addArticleCategory(aru);
		
	}
	
	@Override
	public boolean delArticleCategory(int id) {
		if(mapper.delArticleCategory(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	@Override
	public Page<ArticleCategoryCount> queryArticleCategoryCount(ArticleCategoryCountVo vo) {
		Page<ArticleCategoryCount> page =new Page<ArticleCategoryCount>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ArticleCategoryCount> dataList =mapper.queryArticleCategoryCount(vo);
		int totalCount =mapper.countArticleCategoryCount(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public ArticleCategoryCount getArticleCategoryCount(int id) {
		return mapper.getArticleCategoryCount(id);
	}
	
	@Override
	public boolean updArticleCategoryCount(ArticleCategoryCount aru) {
		if(mapper.updArticleCategoryCount(aru)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public void addArticleCategoryCount(ArticleCategoryCount aru) {
		mapper.addArticleCategoryCount(aru);
		
	}
	
	@Override
	public boolean delArticleCategoryCount(int id) {
		if(mapper.delArticleCategoryCount(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	@Override
	public Page<ArticleCategoryUser> queryArticleCategoryUser(ArticleCategoryUserVo vo) {
		Page<ArticleCategoryUser> page =new Page<ArticleCategoryUser>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ArticleCategoryUser> dataList =mapper.queryArticleCategoryUser(vo);
		int totalCount =mapper.countArticleCategoryUser(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public ArticleCategoryUser getArticleCategoryUser(int id) {
		return mapper.getArticleCategoryUser(id);
	}
	
	@Override
	public boolean updArticleCategoryUser(ArticleCategoryUser aru) {
		if(mapper.updArticleCategoryUser(aru)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public void addArticleCategoryUser(ArticleCategoryUser aru) {
		mapper.addArticleCategoryUser(aru);
		
	}
	
	@Override
	public boolean delArticleCategoryUser(int id) {
		if(mapper.delArticleCategoryUser(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	@Override
	public Page<ArticleCategoryField> queryArticleCategoryField(ArticleCategoryFieldVo vo) {
		Page<ArticleCategoryField> page =new Page<ArticleCategoryField>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ArticleCategoryField> dataList =mapper.queryArticleCategoryField(vo);
		int totalCount =mapper.countArticleCategoryField(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public ArticleCategoryField getArticleCategoryField(int id) {
		return mapper.getArticleCategoryField(id);
	}
	
	@Override
	public boolean updArticleCategoryField(ArticleCategoryField aru) {
		if(mapper.updArticleCategoryField(aru)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public void addArticleCategoryField(ArticleCategoryField aru) {
		mapper.addArticleCategoryField(aru);
		
	}
	
	@Override
	public boolean delArticleCategoryField(int id) {
		if(mapper.delArticleCategoryField(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	@Override
	public Page<ArticleCategorySpec> queryArticleCategorySpec(ArticleCategorySpecVo vo) {
		Page<ArticleCategorySpec> page =new Page<ArticleCategorySpec>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ArticleCategorySpec> dataList =mapper.queryArticleCategorySpec(vo);
		int totalCount =mapper.countArticleCategorySpec(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public ArticleCategorySpec getArticleCategorySpec(int id) {
		return mapper.getArticleCategorySpec(id);
	}
	
	@Override
	public boolean updArticleCategorySpec(ArticleCategorySpec aru) {
		if(mapper.updArticleCategorySpec(aru)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public void addArticleCategorySpec(ArticleCategorySpec aru) {
		mapper.addArticleCategorySpec(aru);
		
	}
	
	@Override
	public boolean delArticleCategorySpec(int id) {
		if(mapper.delArticleCategorySpec(id)>0){
			return true;
		}
		return false;
	}
	
	

	@Override
	public Page<OrderCommentFalse> queryOrderCommentFalse(OrderCommentFalseVo vo) {
		Page<OrderCommentFalse> page =new Page<OrderCommentFalse>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderCommentFalse(vo);
		List<OrderCommentFalse> list =mapper.queryOrderCommentFalse(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrderCommentFalse(OrderCommentFalse ocf) {
		mapper.addOrderCommentFalse(ocf);
	}
	
	@Override
	public OrderCommentFalse getOrderCommentFalse(int id) {
		return mapper.getOrderCommentFalse(id);
	}
	
	@Override
	public boolean updOrderCommentFalse(OrderCommentFalse ocf) {
		if(mapper.updOrderCommentFalse(ocf)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrderCommentFalse(int id) {
		if(mapper.delOrderCommentFalse(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Page<OrderCommentTem> queryOrderCommentTem(OrderCommentTemVo vo) {
		Page<OrderCommentTem> page =new Page<OrderCommentTem>();
		
		if(vo.getIndex()==0||"".equals(vo.getIndex())){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		int count =mapper.countOrderCommentTem(vo);
		List<OrderCommentTem> list =mapper.queryOrderCommentTem(vo);
		
		page.transform(count, list);
		return page;
	}
	
	@Override
	public void addOrderCommentTem(OrderCommentTem oct) {
		mapper.addOrderCommentTem(oct);
	}
	
	@Override
	public OrderCommentTem getOrderCommentTem(int id) {
		return mapper.getOrderCommentTem(id);
	}
	
	@Override
	public boolean updOrderCommentTem(OrderCommentTem oct) {
		if(mapper.updOrderCommentTem(oct)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delOrderCommentTem(int id) {
		if(mapper.delOrderCommentTem(id)>0){
			return true;
		}
		return false;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public String sendPostReq(String fileName, String base64Str)  {

		Map<String,String> map =new HashMap<String,String>();
		
		String url ="http://service.manji.com/AppService.asmx/UploadFile";
/*		String url ="http://service2.manjiwang.com/AppService.asmx/UploadFile";
*/		
		long timestamp =System.currentTimeMillis();
		long current =timestamp/1000;
		
		String roundStr =current+base64Str+fileName+"asalt" ;
		
		String roundNumber=MD5util.getMD5(roundStr);
		
		try {
			base64Str =URLEncoder.encode(base64Str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		map.put("currentDate", current+"");
		map.put("roundNumber", roundNumber.toLowerCase());
		map.put("isResponseJson", "1");
		map.put("loginType", "Third");
		map.put("base64Str", base64Str);
		map.put("fileName", fileName);
		
		String html =PostUrlUtils.postHttpRequest(url,map);
			
		System.out.println(html+"==================++++++++++++++++++++++");
		String path ="";
		JSONObject htmlObj =JSONObject.fromObject(html);
		if("1".equals(htmlObj.getString("State"))){
			
			path =htmlObj.getJSONObject("Datas").getString("Path");
			
		}
		
		
		
		return path;
		
	}
	
	
}
