package dnn.entity.feedbackInfo.specimen;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by lgq on 16-9-4.
 * 化学电池
 */
public class ChemicalCell extends Battery{
    private String parameter; //冲放电参数
    private LocalDateTime chemicalCellSubmitDate  ; //送样日期
    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public LocalDateTime getChemicalCellSubmitDate() {
        return chemicalCellSubmitDate;
    }

    public void setChemicalCellSubmitDate(LocalDateTime chemicalCellSubmitDate) {
        this.chemicalCellSubmitDate = chemicalCellSubmitDate;
    }
}
