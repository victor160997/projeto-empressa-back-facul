package uni.empresa.model.dto.empregado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpregadoViewDTO {
    private int id;
    private String nome;
    private String funcao;
    private double salarioBrutoMensal;
    private double salarioBrutoAnual;
    private String status;
}
