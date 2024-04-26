package uni.empresa.model.dto.empregado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uni.empresa.model.EStatusEmpregado;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpregadoUpdateDTO {
    private int id;
    private String nome;
    private int idFuncao;
    private EStatusEmpregado status;
}
