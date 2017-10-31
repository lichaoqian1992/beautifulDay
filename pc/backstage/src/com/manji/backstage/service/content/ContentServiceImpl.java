package com.manji.backstage.service.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manji.backstage.mapper.content.ContentMapper;
import com.manji.backstage.model.base.Page;
import com.manji.backstage.model.content.ActAdvert;
import com.manji.backstage.model.content.ActBusGoods;
import com.manji.backstage.model.content.ActBusShop;
import com.manji.backstage.model.content.ActBusiness;
import com.manji.backstage.model.content.ActCommon;
import com.manji.backstage.model.content.ActIndUser;
import com.manji.backstage.model.content.ActIndex;
import com.manji.backstage.model.content.ActRecUser;
import com.manji.backstage.model.content.ActRecherge;
import com.manji.backstage.model.content.ActShop;
import com.manji.backstage.model.content.ActShopGoods;
import com.manji.backstage.model.content.ActSms;
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
import com.manji.backstage.model.content.BusiTemp;
import com.manji.backstage.model.content.Channel;
import com.manji.backstage.model.content.ChannelField;
import com.manji.backstage.model.content.ChannelSite;
import com.manji.backstage.model.content.ChannelSpec;
import com.manji.backstage.vo.content.ActAdvertVo;
import com.manji.backstage.vo.content.ActBusGoodsVo;
import com.manji.backstage.vo.content.ActBusShopVo;
import com.manji.backstage.vo.content.ActBusinessVo;
import com.manji.backstage.vo.content.ActCommonVo;
import com.manji.backstage.vo.content.ActIndUserVo;
import com.manji.backstage.vo.content.ActIndexVo;
import com.manji.backstage.vo.content.ActRecUserVo;
import com.manji.backstage.vo.content.ActRechergeVo;
import com.manji.backstage.vo.content.ActShopGoodsVo;
import com.manji.backstage.vo.content.ActShopVo;
import com.manji.backstage.vo.content.ActSmsVo;
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
import com.manji.backstage.vo.content.BusiTempVo;
import com.manji.backstage.vo.content.ChannelFieldVo;
import com.manji.backstage.vo.content.ChannelSiteVo;
import com.manji.backstage.vo.content.ChannelSpecVo;
import com.manji.backstage.vo.content.ChannelVo;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentMapper mapper;

	@Override
	public Page<Article> queryArticle(ArticleVo vo) {

		Page<Article> page =new Page<Article>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<Article> dataList =mapper.queryArticle(vo);
		int totalCount =mapper.countArticle(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public Article getArticle(int id) {
		return mapper.getArticle(id);
	}

	@Override
	public boolean updArticle(Article art) {
		if(mapper.updArticle(art)>0){
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
		if(mapper.delArticle(id)>0){
			return true;
		}
		
		return false;
	}

	
	
	
	@Override
	public Page<ArticleInfo> queryArticleInfo(ArticleInfoVo vo) {
		Page<ArticleInfo> page =new Page<ArticleInfo>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ArticleInfo> dataList =mapper.queryArticleInfo(vo);
		int totalCount =mapper.countArticleInfo(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ArticleInfo getArticleInfo(int id) {
		return mapper.getArticleInfo(id);
	}

	@Override
	public boolean updArticleInfo(ArticleInfo ai) {
		if(mapper.updArticleInfo(ai)>0){
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
		if(mapper.delArticleInfo(id)>0){
			return true;
		}
		return false;
	}

	
	
	@Override
	public Page<ArticleContent> queryArticleContent(ArticleContentVo vo) {
		Page<ArticleContent> page =new Page<ArticleContent>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ArticleContent> dataList =mapper.queryArticleContent(vo);
		int totalCount =mapper.countArticleContent(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ArticleContent getArticleContent(int id) {
		return mapper.getArticleContent(id);
	}

	@Override
	public boolean updArticleContent(ArticleContent ac) {
		if(mapper.updArticleContent(ac)>0){
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
		if(mapper.delArticleContent(id)>0){
			return true;
		}
		return false;
	}

	
	
	@Override
	public Page<ArticleCount> queryArticleCount(ArticleCountVo vo) {
		Page<ArticleCount> page =new Page<ArticleCount>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ArticleCount> dataList =mapper.queryArticleCount(vo);
		int totalCount =mapper.countArticleCount(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ArticleCount getArticleCount(int id) {
		return mapper.getArticleCount(id);
	}

	@Override
	public boolean updArticleCount(ArticleCount ac) {
		if(mapper.updArticleCount(ac)>0){
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
		if(mapper.delArticleCount(id)>0){
			return true;
		}
		return false;
	}

	
	
	
	@Override
	public Page<ArticleComment> queryArticleComment(ArticleCommentVo vo) {
		Page<ArticleComment> page =new Page<ArticleComment>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ArticleComment> dataList =mapper.queryArticleComment(vo);
		int totalCount =mapper.countArticleComment(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ArticleComment getArticleComment(int id) {
		return mapper.getArticleComment(id);
	}

	@Override
	public boolean updArticleComment(ArticleComment ac) {
		if(mapper.updArticleComment(ac)>0){
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
		if(mapper.delArticleComment(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ArticleAlbums> queryArticleAlbums(ArticleAlbumsVo vo) {
		Page<ArticleAlbums> page =new Page<ArticleAlbums>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ArticleAlbums> dataList =mapper.queryArticleAlbums(vo);
		int totalCount =mapper.countArticleAlbums(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ArticleAlbums getArticleAlbums(int id) {
		return mapper.getArticleAlbums(id);
	}

	@Override
	public boolean updArticleAlbums(ArticleAlbums aa) {
		if(mapper.updArticleAlbums(aa)>0){
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
		if(mapper.delArticleAlbums(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ArticleGoods> queryArticleGoods(ArticleGoodsVo vo) {
		Page<ArticleGoods> page =new Page<ArticleGoods>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ArticleGoods> dataList =mapper.queryArticleGoods(vo);
		int totalCount =mapper.countArticleGoods(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ArticleGoods getArticleGoods(int id) {
		
		return mapper.getArticleGoods(id);
	}

	@Override
	public boolean updArticleGoods(ArticleGoods ag) {
		if(mapper.updArticleGoods(ag)>0){
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
		if(mapper.delArticleGoods(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ArticleScreen> queryArticleScreen(ArticleScreenVo vo) {
		Page<ArticleScreen> page =new Page<ArticleScreen>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ArticleScreen> dataList =mapper.queryArticleScreen(vo);
		int totalCount =mapper.countArticleScreen(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ArticleScreen getArticleScreen(long id) {
		return mapper.getArticleScreen(id);
	}

	@Override
	public boolean updArticleScreen(ArticleScreen as) {
		if(mapper.updArticleScreen(as)>0){
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
		if(mapper.delArticleScreen(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<BusiTemp> queryBusiTemp(BusiTempVo vo) {
		Page<BusiTemp> page =new Page<BusiTemp>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<BusiTemp> dataList =mapper.queryBusiTemp(vo);
		int totalCount =mapper.countBusiTemp(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public BusiTemp getBusiTemp(int id) {
		return mapper.getBusiTemp(id);
	}

	@Override
	public boolean updBusiTemp(BusiTemp bt) {
		if(mapper.updBusiTemp(bt)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addBusiTemp(BusiTemp bt) {
		mapper.addBusiTemp(bt);
	}

	@Override
	public boolean delBusiTemp(int id) {
		if(mapper.delBusiTemp(id)>0){
			return true;
		}
		return false;
	}

	
	
	
	
	
	
	
	
	
	
	
	@Override
	public Page<ActBusiness> queryActBusiness(ActBusinessVo vo) {
		Page<ActBusiness> page =new Page<ActBusiness>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActBusiness> dataList =mapper.queryActBusiness(vo);
		int totalCount =mapper.countActBusiness(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActBusiness getActBusiness(int id) {
		return mapper.getActBusiness(id);
	}

	@Override
	public boolean updActBusiness(ActBusiness ab) {
		if(mapper.updActBusiness(ab)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActBusiness(ActBusiness ab) {
		mapper.addActBusiness(ab);
	}

	@Override
	public boolean delActBusiness(int id) {
		if(mapper.delActBusiness(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ActIndex> queryActIndex(ActIndexVo vo) {
		Page<ActIndex> page =new Page<ActIndex>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActIndex> dataList =mapper.queryActIndex(vo);
		int totalCount =mapper.countActIndex(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActIndex getActIndex(int id) {
		return mapper.getActIndex(id);
	}

	@Override
	public boolean updActIndex(ActIndex ai) {
		if(mapper.updActIndex(ai)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActIndex(ActIndex ai) {
		mapper.addActIndex(ai);
	}

	@Override
	public boolean delActIndex(int id) {
		if(mapper.delActIndex(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ActIndUser> queryActIndUser(ActIndUserVo vo) {
		Page<ActIndUser> page =new Page<ActIndUser>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActIndUser> dataList =mapper.queryActIndUser(vo);
		int totalCount =mapper.countActIndUser(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActIndUser getActIndUser(int id) {
		return mapper.getActIndUser(id);
	}

	@Override
	public boolean updActIndUser(ActIndUser aiu) {
		if(mapper.updActIndUser(aiu)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActIndUser(ActIndUser aiu) {

		mapper.addActIndUser(aiu);
	}

	@Override
	public boolean delActIndUser(int id) {
		if(mapper.delActIndUser(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ActCommon> queryActCommon(ActCommonVo vo) {
		Page<ActCommon> page =new Page<ActCommon>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActCommon> dataList =mapper.queryActCommon(vo);
		int totalCount =mapper.countActCommon(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActCommon getActCommon(int id) {
		return mapper.getActCommon(id);
	}

	@Override
	public boolean updActCommon(ActCommon ac) {
		if(mapper.updActCommon(ac)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActCommon(ActCommon ac) {
		mapper.addActCommon(ac);
		
	}

	@Override
	public boolean delActCommon(int id) {
		if(mapper.delActCommon(id)>0){
			return true;
		}
		
		return false;
	}

	@Override
	public Page<ActShop> queryActShop(ActShopVo vo) {
		Page<ActShop> page =new Page<ActShop>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActShop> dataList =mapper.queryActShop(vo);
		int totalCount =mapper.countActShop(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActShop getActShop(int id) {
		return mapper.getActShop(id);
	}

	@Override
	public boolean updActShop(ActShop as) {
		if(mapper.updActShop(as)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActShop(ActShop as) {
		mapper.addActShop(as);
		
	}

	@Override
	public boolean delActShop(int id) {
		if(mapper.delActShop(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ActShopGoods> queryActShopGoods(ActShopGoodsVo vo) {
		Page<ActShopGoods> page =new Page<ActShopGoods>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActShopGoods> dataList =mapper.queryActShopGoods(vo);
		int totalCount =mapper.countActShopGoods(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActShopGoods getActShopGoods(int id) {
		return mapper.getActShopGoods(id);
	}

	@Override
	public boolean updActShopGoods(ActShopGoods asg) {
		if(mapper.updActShopGoods(asg)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActShopGoods(ActShopGoods asg) {
		mapper.addActShopGoods(asg);
		
	}

	@Override
	public boolean delActShopGoods(int id) {
		if(mapper.delActShopGoods(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ActSms> queryActSms(ActSmsVo vo) {
		Page<ActSms> page =new Page<ActSms>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActSms> dataList =mapper.queryActSms(vo);
		int totalCount =mapper.countActSms(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActSms getActSms(int id) {
		return mapper.getActSms(id);
	}

	@Override
	public boolean updActSms(ActSms as) {
		if(mapper.updActSms(as)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActSms(ActSms as) {
		mapper.addActSms(as);
		
	}

	@Override
	public boolean delActSms(int id) {
		if(mapper.delActSms(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ActAdvert> queryActAdvert(ActAdvertVo vo) {
		Page<ActAdvert> page =new Page<ActAdvert>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActAdvert> dataList =mapper.queryActAdvert(vo);
		int totalCount =mapper.countActAdvert(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActAdvert getActAdvert(int id) {
		return mapper.getActAdvert(id);
	}

	@Override
	public boolean updActAdvert(ActAdvert aa) {
		if(mapper.updActAdvert(aa)){
			return true;
		}
		return false;
	}

	@Override
	public void addActAdvert(ActAdvert aa) {
		mapper.addActAdvert(aa);
		
	}

	@Override
	public boolean delActAdvert(int id) {
		if(mapper.delActAdvert(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ActBusShop> queryActBusShop(ActBusShopVo vo) {
		Page<ActBusShop> page =new Page<ActBusShop>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActBusShop> dataList =mapper.queryActBusShop(vo);
		int totalCount =mapper.countActBusShop(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActBusShop getActBusShop(int id) {
		return mapper.getActBusShop(id);
	}

	@Override
	public boolean updActBusShop(ActBusShop abs) {
		if(mapper.updActBusShop(abs)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActBusShop(ActBusShop abs) {
		mapper.addActBusShop(abs);
		
	}

	@Override
	public boolean delActBusShop(int id) {
		if(mapper.delActBusShop(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ActBusGoods> queryActBusGoods(ActBusGoodsVo vo) {
		Page<ActBusGoods> page =new Page<ActBusGoods>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActBusGoods> dataList =mapper.queryActBusGoods(vo);
		int totalCount =mapper.countActBusGoods(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActBusGoods getActBusGoods(int id) {
		return mapper.getActBusGoods(id);
	}

	@Override
	public boolean updActBusGoods(ActBusGoods abg) {
		if(mapper.updActBusGoods(abg)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActBusGoods(ActBusGoods abg) {
		mapper.addActBusGoods(abg);
		
	}

	@Override
	public boolean delActBusGoods(int id) {
		if(mapper.delActBusGoods(id)>0){
			return true;
		}
		return false;
	}

	@Override
	public Page<ActRecherge> queryActRecherge(ActRechergeVo vo) {
		Page<ActRecherge> page =new Page<ActRecherge>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActRecherge> dataList =mapper.queryActRecherge(vo);
		int totalCount =mapper.countActRecherge(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}

	@Override
	public ActRecherge getActRecherge(int id) {
		return mapper.getActRecherge(id);
	}

	@Override
	public boolean updActRecherge(ActRecherge ar) {
		if(mapper.updActRecherge(ar)>0){
			return true;
		}
		return false;
	}

	@Override
	public void addActRecherge(ActRecherge ar) {
		mapper.addActRecherge(ar);
		
	}

	@Override
	public boolean delActRecherge(int id) {
		if(mapper.delActRecherge(id)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public Page<ActRecUser> queryActRecUser(ActRecUserVo vo) {
		Page<ActRecUser> page =new Page<ActRecUser>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ActRecUser> dataList =mapper.queryActRecUser(vo);
		int totalCount =mapper.countActRecUser(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public ActRecUser getActRecUser(int id) {
		return mapper.getActRecUser(id);
	}
	
	@Override
	public boolean updActRecUser(ActRecUser aru) {
		if(mapper.updActRecUser(aru)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public void addActRecUser(ActRecUser aru) {
		mapper.addActRecUser(aru);
		
	}
	
	@Override
	public boolean delActRecUser(int id) {
		if(mapper.delActRecUser(id)>0){
			return true;
		}
		return false;
	}

	
	
	@Override
	public Page<Channel> queryChannel(ChannelVo vo) {
		Page<Channel> page =new Page<Channel>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<Channel> dataList =mapper.queryChannel(vo);
		int totalCount =mapper.countChannel(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public Channel getChannel(int id) {
		return mapper.getChannel(id);
	}
	
	@Override
	public boolean updChannel(Channel aru) {
		if(mapper.updChannel(aru)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public void addChannel(Channel aru) {
		mapper.addChannel(aru);
		
	}
	
	@Override
	public boolean delChannel(int id) {
		if(mapper.delChannel(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	@Override
	public Page<ChannelField> queryChannelField(ChannelFieldVo vo) {
		Page<ChannelField> page =new Page<ChannelField>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ChannelField> dataList =mapper.queryChannelField(vo);
		int totalCount =mapper.countChannelField(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public ChannelField getChannelField(int id) {
		return mapper.getChannelField(id);
	}
	
	@Override
	public boolean updChannelField(ChannelField aru) {
		if(mapper.updChannelField(aru)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public void addChannelField(ChannelField aru) {
		mapper.addChannelField(aru);
		
	}
	
	@Override
	public boolean delChannelField(int id) {
		if(mapper.delChannelField(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	@Override
	public Page<ChannelSpec> queryChannelSpec(ChannelSpecVo vo) {
		Page<ChannelSpec> page =new Page<ChannelSpec>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ChannelSpec> dataList =mapper.queryChannelSpec(vo);
		int totalCount =mapper.countChannelSpec(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public ChannelSpec getChannelSpec(int id) {
		return mapper.getChannelSpec(id);
	}
	
	@Override
	public boolean updChannelSpec(ChannelSpec aru) {
		if(mapper.updChannelSpec(aru)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public void addChannelSpec(ChannelSpec aru) {
		mapper.addChannelSpec(aru);
		
	}
	
	@Override
	public boolean delChannelSpec(int id) {
		if(mapper.delChannelSpec(id)>0){
			return true;
		}
		return false;
	}
	
	
	
	@Override
	public Page<ChannelSite> queryChannelSite(ChannelSiteVo vo) {
		Page<ChannelSite> page =new Page<ChannelSite>();
		
		if("".equals(vo.getIndex())||0==vo.getIndex()){
			vo.setIndex(1);
			page.setIndex(1);
		}else{
			page.setIndex(vo.getIndex());
		}
		
		List<ChannelSite> dataList =mapper.queryChannelSite(vo);
		int totalCount =mapper.countChannelSite(vo);
		page.transform(totalCount, dataList);
		
		return page;
	}
	
	@Override
	public ChannelSite getChannelSite(int id) {
		return mapper.getChannelSite(id);
	}
	
	@Override
	public boolean updChannelSite(ChannelSite aru) {
		if(mapper.updChannelSite(aru)>0){
			return true;
		}
		return false;
	}
	
	@Override
	public void addChannelSite(ChannelSite aru) {
		mapper.addChannelSite(aru);
		
	}
	
	@Override
	public boolean delChannelSite(int id) {
		if(mapper.delChannelSite(id)>0){
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
	
	
	
	
	
	
	
	
}
