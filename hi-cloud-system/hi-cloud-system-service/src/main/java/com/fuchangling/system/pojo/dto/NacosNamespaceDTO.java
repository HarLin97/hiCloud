package com.fuchangling.system.pojo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class NacosNamespaceDTO {

    /**
     * code : 200
     * message : null
     * data : [{"namespace":"","namespaceShowName":"public","quota":200,"configCount":2,"type":0},{"namespace":"20984dc7-920a-407b-9932-a3ef24a48186","namespaceShowName":"local-dev","quota":200,"configCount":1,"type":2}]
     */

    private int code;
    private Object message;
    private List<DataBean> data;

    @NoArgsConstructor
    @Data
    public static class DataBean {
        /**
         * namespace :
         * namespaceShowName : public
         * quota : 200
         * configCount : 2
         * type : 0
         */

        private String namespace;
        private String namespaceShowName;
        private int quota;
        private int configCount;
        private int type;
    }
}
