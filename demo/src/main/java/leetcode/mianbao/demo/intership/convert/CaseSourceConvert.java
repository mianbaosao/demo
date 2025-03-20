package leetcode.mianbao.demo.intership.convert;

import leetcode.mianbao.demo.intership.po.CaseSource;
import leetcode.mianbao.demo.intership.po.CaseSourceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description:
 * @Author:bread
 * @Date: 2024-11-04 15:46
 */
@Mapper
public interface CaseSourceConvert {
    CaseSourceConvert INSTANCE= Mappers.getMapper(CaseSourceConvert.class);


    CaseSourceDTO toDTO(CaseSource caseSource);
}
