package leetcode.mianbao.demo.intership.convert;

import javax.annotation.Generated;
import leetcode.mianbao.demo.intership.po.CaseSource;
import leetcode.mianbao.demo.intership.po.CaseSourceDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-11T19:05:38+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
public class CaseSourceConvertImpl implements CaseSourceConvert {

    @Override
    public CaseSourceDTO toDTO(CaseSource caseSource) {
        if ( caseSource == null ) {
            return null;
        }

        CaseSourceDTO caseSourceDTO = new CaseSourceDTO();

        caseSourceDTO.setId( caseSource.getId() );
        caseSourceDTO.setUnitName( caseSource.getUnitName() );
        caseSourceDTO.setCaseSourceId( caseSource.getCaseSourceId() );
        caseSourceDTO.setDefendantName( caseSource.getDefendantName() );
        caseSourceDTO.setRegistrant( caseSource.getRegistrant() );
        caseSourceDTO.setRegistrationDate( caseSource.getRegistrationDate() );

        return caseSourceDTO;
    }
}
