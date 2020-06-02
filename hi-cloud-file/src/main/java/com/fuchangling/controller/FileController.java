package com.fuchangling.controller;

import com.fuchangling.common.api.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传下载
 *
 * @author harlin
 */
@RestController
@RequestMapping("file")
public class FileController {

    /**
     * 小文件上传
     */
    public ResultVO<Object> upload(MultipartFile file) {
        return new ResultVO<>();
    }

    /**
     * 大文件分片上传
     */

    /**
     * 图片上传
     */

    /**
     * 文件下载
     */

    /**
     * 文件重命名
     */

    /**
     * 文件删除
     */

    /**
     * 文件覆盖
     */
}
