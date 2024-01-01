package com.paladin.base.common.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import java.util.UUID;

public class XiaoZouBasePooledObjectFactory extends BasePooledObjectFactory<XiaoZou> {

    @Override
    public XiaoZou create() {
        // 创建一个新的MyObject对象
        XiaoZou myObject = new XiaoZou(UUID.randomUUID().toString());
        myObject.create();
        return myObject;
    }

    @Override
    public PooledObject<XiaoZou> wrap(XiaoZou myObject) {
        // 将MyObject对象封装到一个PooledObject对象中并返回
        return new DefaultPooledObject<>(myObject);
    }

    @Override
    public void destroyObject(PooledObject<XiaoZou> pooledObject) {
        // 销毁对象
        XiaoZou myObject = pooledObject.getObject();
        myObject.destroy();
    }

    @Override
    public boolean validateObject(PooledObject<XiaoZou> pooledObject) {
        // 验证对象是否可用
        XiaoZou myObject = pooledObject.getObject();
        return myObject.isValid();
    }
}