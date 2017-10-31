package com.manji.lineol.common.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.manji.lineol.common.info.ShopConfigInfo;
import com.manji.lineol.model.ShopConfig;
import com.manji.lineol.util.BeanUtil;

@Component
public class ShopConfigInfoConverter implements Converter<ShopConfig, ShopConfigInfo>{

	@Override
	public ShopConfigInfo convert(ShopConfig shopConfig) {
		ShopConfigInfo configInfo=new ShopConfigInfo();
		try {
			BeanUtil.copyProperties(shopConfig, configInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return configInfo;
	}

}
