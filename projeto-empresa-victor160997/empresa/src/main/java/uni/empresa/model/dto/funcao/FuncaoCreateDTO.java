package uni.empresa.model.dto.funcao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncaoCreateDTO {
    private String nome;
    private double remuneracao;
}
