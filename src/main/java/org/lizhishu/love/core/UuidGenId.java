package org.lizhishu.love.core;

/**
 * @author sleo
 * @date 2019/3/7
 */

import tk.mybatis.mapper.genid.GenId;

import java.util.UUID;


public class UuidGenId implements GenId<String> {
    @Override
    public String genId(String table, String column) {
        return UUID.randomUUID().toString();
    }
}