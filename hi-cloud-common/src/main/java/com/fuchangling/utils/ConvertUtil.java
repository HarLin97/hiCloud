package com.fuchangling.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cglib.beans.BeanMap;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 类型互转工具类
 *
 * @author:wangzhen
 * @version:V1.0 2017年9月22日
 */
@Slf4j
public class ConvertUtil {
    /**
     * 将实体对象转换成Map
     * 2017年9月22日
     *
     * @param model
     * @return author:wangzhen
     */
    public static Map<String, Object> object2Map(final Object model) {

        final Map<String, Object> map = new HashMap<String, Object>();
        if (model != null) {
            final BeanMap beanMap = BeanMap.create(model);
            for (final Object key : beanMap.keySet()) {
                if (beanMap.get(key) != null) {
                    Object value = beanMap.get(key);
                    //处理Timestamp,去掉毫秒
                    if (value instanceof Timestamp) {
                        value = DateUtil.getDateStr((Timestamp) value);
                    }
                    map.put(key.toString(), value);
                }
            }
        }
        return map;

    }

    /**
     * 该方法是将传入的一个集合列表转换成对象列表
     * 注意：对象的属性个数要和传入的列表个数及顺序一一对应
     * 2015年12月18日
     *
     * @param dataList
     * @param cl
     * @return
     * @author:wangzhen
     */
    @SuppressWarnings("all")
    public static List list2Object(List dataList, Class cl) {
        try {
            if (CollectionUtils.isNotEmpty(dataList)) {
                List modelList = new ArrayList();
                for (int k = 0; k < dataList.size(); k++) {
                    Object[] row = (Object[]) dataList.get(k);
                    int rowLength = row.length;
                    // 获取实体类的所有属性，返回Field数组
                    Field[] fields = cl.getDeclaredFields();
                    if (fields.length <= 0) {
                        fields = cl.getSuperclass().getDeclaredFields();
                    }
                    Object obj = cl.newInstance();
                    for (int i = 0; i < fields.length; i++) {
                        Field field = fields[i];
                        //获取原来的访问控制权限
                        boolean accessFlag = fields[i].isAccessible();
                        //修改访问控制权限
                        fields[i].setAccessible(true);
                        field.set(obj, row[i]);
                        //恢复访问控制权限
                        fields[i].setAccessible(accessFlag);
                        if (i == rowLength - 1) {
                            break;
                        }
                    }
                    modelList.add(obj);
                }
                return modelList;
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e.getCause());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e.getCause());
        }
        return new ArrayList();

    }

}
