package com.manji.msgservice.common.utils.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;

public class SimpleIntrospectedColumn extends IntrospectedColumn {

    @Override
    public boolean isBLOBColumn() {
        return false;
    }

}
