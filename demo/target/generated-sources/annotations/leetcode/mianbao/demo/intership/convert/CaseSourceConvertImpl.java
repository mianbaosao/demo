package leetcode.mianbao.demo.intership.convert;

import javax.annotation.processing.Generated;
import leetcode.mianbao.demo.intership.po.CaseSource;
import leetcode.mianbao.demo.intership.po.CaseSourceDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-10T11:06:39+0800",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250628-1110, environment: Java 21.0.7 (Eclipse Adoptium)"
)
public class CaseSourceConvertImpl implements CaseSourceConvert {

    @Override
    public CaseSourceDTO toDTO(CaseSource caseSource) {
        if ( caseSource == null ) {
            return null;
        }

        CaseSourceDTO caseSourceDTO = new CaseSourceDTO();

        caseSourceDTO.setCaseSourceId( caseSource.getCaseSourceId() );
        caseSourceDTO.setDefendantName( caseSource.getDefendantName() );
        caseSourceDTO.setId( caseSource.getId() );
        caseSourceDTO.setRegistrant( caseSource.getRegistrant() );
        caseSourceDTO.setRegistrationDate( caseSource.getRegistrationDate() );
        caseSourceDTO.setUnitName( caseSource.getUnitName() );

        return caseSourceDTO;
    }
}
