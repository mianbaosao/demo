package org.example.suanfa.project.codeVerse.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.google.common.collect.Sets;

/**
 * ExcelUtils
 *
 * @author guansheng
 * @date 2024/06/20
 */
@Slf4j
public class ExcelUtils {

  public static MultipartFile findFirstMultipartFile(MultipartHttpServletRequest request) {
    if (request == null || MapUtils.isEmpty(request.getFileMap())) {
      return null;
    }

    return request.getFileMap().values()
        .stream()
        .filter(ExcelUtils::checkExcel)
        .findFirst()
        .orElse(null);
  }

  public static boolean checkExcel(MultipartFile multipartFile) {
    String orgFileName = multipartFile.getOriginalFilename();
    log.debug("mutipartFile name is {}", orgFileName);
    boolean invalidFilename = StringUtils.isBlank(orgFileName)
        || !orgFileName.contains(".")
        || !Sets.newHashSet("xlsx", "xls")
        .contains(orgFileName.substring(orgFileName.lastIndexOf(".") + 1));
    if (invalidFilename) {
      log.warn("file name is not excel, orgFileName={}", orgFileName);
      return false;
    }
    return true;
  }

  public static <T> void writeExcel(String fileName, List<T> data, Class clazz,
      HttpServletResponse response) {
    response.setStatus(200);
    OutputStream outputStream = null;
    ExcelWriter excelWriter = null;
    try {
      if (StringUtils.isBlank(fileName)) {
        throw new RuntimeException("'fileName' 不能为空");
      }
      fileName = fileName.concat(".xlsx");
      response.setHeader("Content-Disposition",
          "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
      outputStream = response.getOutputStream();

      //必须放到循环外，否则会刷新流
      excelWriter = EasyExcel.write(outputStream).build();

      //创建sheet表格，并设置表格名称
      WriteTable writeTable = EasyExcel.writerTable(0).head(clazz).needHead(true)
          .automaticMergeHead(false).build();
      WriteSheet writeSheet = EasyExcel.writerSheet("Sheet1").build();

      // 写出数据
      excelWriter.write(data, writeSheet, writeTable);
    } catch (Exception e) {
      log.error("导出excel数据异常：", e);
      throw new RuntimeException(e);
    } finally {
      if (excelWriter != null) {
        excelWriter.finish();
      }
      if (outputStream != null) {
        try {
          outputStream.flush();
          outputStream.close();
        } catch (IOException e) {
          log.error("导出数据关闭流异常", e);
        }
      }
    }
  }

}
