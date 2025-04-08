package org.example.suanfa.project.codeVerse.excel;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.metadata.data.ReadCellData;


/**
 * excel注解校验
 *
 * @author guansheng@kkworld.com
 * @date 2024/6/26
 */
public class ExcelImportValid {

    /**
     * Excel导入字段校验
     *
     * @param object 校验的JavaBean 其属性须有自定义注解
     */
    public static void valid(Object object, int rowNumber) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            //设置可访问
            field.setAccessible(true);
            //属性的值
            Object fieldValue = null;
            try {
                fieldValue = field.get(object);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("导入参数检查失败");
            }

            boolean isExcelProperty = field.isAnnotationPresent(ExcelProperty.class);
            if (!isExcelProperty) {
                continue;
            }
            int columnNumber = field.getAnnotation(ExcelProperty.class).index();


        }
    }

}
