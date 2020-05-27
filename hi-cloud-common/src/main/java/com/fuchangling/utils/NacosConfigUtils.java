package com.fuchangling.utils;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author harlin
 */
public class NacosConfigUtils {

    private static String serverAddr = "localhost";

    /**
     * 初始化获取conf相关api
     *
     * @param serverAddr 服务地址
     * @return
     */
    public static ConfigService init(String serverAddr) throws NacosException {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        return NacosFactory.createConfigService(properties);
    }

    /**
     * 获取配置内容
     *
     * @param configService conf相关api
     * @param dataId        配置名称
     * @param group         分组
     * @param overtime      超时时间 5000
     * @return 配置内容
     */
    public static String getConfig(ConfigService configService, String dataId, String group, long overtime) throws NacosException {
        if (configService == null) {
            return init(serverAddr).getConfig(dataId, group, overtime);
        }
        return configService.getConfig(dataId, group, overtime);
    }

    /**
     * 推送配置
     *
     * @param configService conf相关api
     * @param dataId        配置名称
     * @param group         分组
     * @param content       推送的配置内容
     * @return
     * @throws NacosException
     */
    public static boolean publishConfig(ConfigService configService, String dataId, String group, String content) throws NacosException {
        if (configService == null) {
            return init(serverAddr).publishConfig(dataId, group, content);
        }
        return configService.publishConfig(dataId, group, content);
    }

    /**
     * 删除指定配置
     *
     * @param configService conf相关api
     * @param dataId        配置名称
     * @param group         分组
     * @return
     * @throws NacosException
     */
    public static boolean removeConfig(ConfigService configService, String dataId, String group) throws NacosException {
        if (configService == null) {
            return init(serverAddr).removeConfig(dataId, group);
        }
        return configService.removeConfig(dataId, group);
    }


    public static void main(String[] args) throws NacosException {
        String dataId = "test.yml";
        String group = "DEFAULT_GROUP";

        ConfigService configService = init(serverAddr);

        getConfig(configService, dataId, group, 500);

        publishConfig(configService, dataId, group, "iop:\n" +
                "  captcha: true");

        //添加监听当有变动时会输出配置内容
        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("recieve:" + configInfo);
            }

            @Override
            public Executor getExecutor() {
                return null;
            }
        });
    }

}
