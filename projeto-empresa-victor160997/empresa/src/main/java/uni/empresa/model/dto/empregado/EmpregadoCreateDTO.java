package uni.empresa.model.dto.empregado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpregadoCreateDTO {
    private String nome;
    private int idFuncao;
}
