package com.manji.backstage.test;

import java.util.ArrayList;
import java.util.List;

import com.manji.backstage.vo.operation.AttriVo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ManjiText {

	
	public static void main(String[] args){
		
		
		
		
		
	String str ="尺码:其他,均码,L-170,M-165,S-160,XS-155,S(160/88A),M(165/88A),S,M,L,155/76A,160/80A;颜色:其他,白色,红色,蓝色,紫色,藏青色;袖型:常规,泡泡袖,喇叭袖,灯笼袖,衬衫袖,荷叶袖,插肩袖,其他;面料:其他,棉,蕾丝,针织,蚕丝,牛仔,真丝,牛仔布;裙长:其他,中长款,中裙,短裙,长裙,超短裙;领型:其他,圆领,V领,立领,一字领,娃娃领,POLO领,方领,荷叶领,高领,堆堆领,连帽,半开领,半高领,围巾领,西装领,斜领;裙型:其他,A字裙,大摆型,一步裙 ,百褶裙,公主裙,荷叶边裙,不规则裙,蛋糕裙,灯笼裙,铅笔裙;款式:其他,中腰,高腰,宽松腰,松紧腰,低腰,超低腰;图案:其他,纯色,花色,条纹,碎花,格子,抽象图案,圆点,手绘,风景,千鸟格,人物,卡通动漫,动物图案,字母,大花;衣门襟:其他,单排两粒扣,双排扣,三粒扣,一粒扣,单排扣,拉链,套头;袖长:其他,长袖,七分袖,常规,无袖,短袖;";	
	String str2="dd:aa;";
	String[] array =str.split(";");
	List<AttriVo> aList =new ArrayList<AttriVo>();
	JSONArray obj =JSONArray.fromObject(array);
	
	for(int i=0;i<array.length;i++){
		
		String tempStr =array[i];
		String[] tempArray =tempStr.split(":");
		
		AttriVo av =new AttriVo();
		av.setTitle(tempArray[0]);
		av.setValues(tempArray[1]);
		
		
		
		}
	}
	
	
	
	
}
