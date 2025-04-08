package org.example.suanfa.project.codeVerse.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.enums.RowTypeEnum;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;




/**
 * AbstractUploadBasicListener
 *
 * @author guansheng@kkworld.com
 * @date 2023/06/20
 */
@Slf4j
public class AbstractUploadBasicListener<T> implements ReadListener<T> {

    private List<T> excelDtoList = new ArrayList<>();

    public int getUploadType() {
        return 0;
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            log.error("第{}行，第{}列解析异常，数据为：{}, 报错信息：{}", excelDataConvertException.getRowIndex() + 1,
                    excelDataConvertException.getColumnIndex() + 1, excelDataConvertException.getCellData().getStringValue(),
                    excelDataConvertException.getMessage());
            throw new RuntimeException(String.format("第%s行，第%s列解析异常，数据为：%s, 报错信息：%s", excelDataConvertException.getRowIndex() + 1,
                    excelDataConvertException.getColumnIndex() + 1, excelDataConvertException.getCellData().getStringValue(),
                    excelDataConvertException.getMessage()));
        } else {
            log.error("Excel解析失败: {}", exception.getMessage());
        }
        throw new IllegalArgumentException(exception.getMessage());
    }

    @Override
    public void invoke(T data, AnalysisContext analysisContext) {
        //是否忽略空白行设置为false (参考：com.alibaba.excel.read.processor.DefaultAnalysisEventProcessor.endRow)
        log.debug("AbstractUploadBasicListener invoke row is {}", analysisContext.readRowHolder().getRowIndex());
        analysisContext.readWorkbookHolder().setIgnoreEmptyRow(false);
        ExcelImportValid.valid(data, analysisContext.readRowHolder().getRowIndex());
        getExcelDtoList().add(data);
    }

    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        log.info("管理：解析到一条头数据:{}", JsonUtils.toJson(headMap));
        if (context.readRowHolder().getRowIndex() == 2) {
            if (headMap.size() < getRealNeedHeaders().size()) {
                throw new IllegalArgumentException("表头字段数量不正确， need:" + JsonUtils.toJson(getRealNeedHeaders()));
            }

            for (int i = 0; i < getRealNeedHeaders().size(); i++) {
                if (!headMap.get(i).getStringValue().equals(getRealNeedHeaders().get(i))) {
                    throw new IllegalArgumentException(
                            "表头字段不正确，need:" + getRealNeedHeaders().get(i) + ", but:" + headMap.get(i).getStringValue() + ", 请检查上传表格是否正确！");
                }
            }
        }
    }

    @Override
    public boolean hasNext(AnalysisContext context) {
        if (RowTypeEnum.EMPTY.equals(context.readRowHolder().getRowType())) {
            //遇到第一个空行直接结束
            //解析结束的工作
            doAfterAllAnalysed(context);
            return false;
        }
        return ReadListener.super.hasNext(context);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("data analyse complete");
    }

    protected List<String> getRealNeedHeaders() {
        return new ArrayList<>();
    }

    public List<T> getExcelDtoList() {
        return excelDtoList;
    }

    public void setExcelDtoList(List<T> excelDtoList) {
        this.excelDtoList = excelDtoList;
    }

}
