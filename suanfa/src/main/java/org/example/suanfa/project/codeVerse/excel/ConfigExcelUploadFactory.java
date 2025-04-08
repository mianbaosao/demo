package org.example.suanfa.project.codeVerse.excel;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcelFactory;
import com.google.common.collect.Lists;



/**
 * excel 配置上传工厂类
 *
 * @author guansheng@kkworld.com
 * @date 2024/6/26
 */
@Slf4j
@Component
public class ConfigExcelUploadFactory {

    @Autowired
    private List<AbstractUploadBasicListener> uploadListeners;

    public <T> List<T> parseConfigExcelDtoList(MultipartFile file, Integer type, Class<T> clazz) throws Exception {
        if (file == null) {
            log.debug("parseTopicFromFile excel file is empty");
            return Lists.newArrayList();
        }
        List<T> list = new ArrayList<>();
        AbstractUploadBasicListener listener = getListenerByType(type);
        if (listener == null) {
            throw new Exception("listener is null, type类型不支持");
        }
        listener.setExcelDtoList(list);
        EasyExcelFactory.read(file.getInputStream(), clazz, listener).sheet().headRowNumber(3).doRead();
        log.info("parseTopicFromFile excel file, type={}, list={}", type, list);
        return list;
    }

    private AbstractUploadBasicListener getListenerByType(Integer type) {
        for (AbstractUploadBasicListener listener : uploadListeners) {
            if (listener.getUploadType() == type) {
                return listener;
            }
        }
        return null;
    }

}
