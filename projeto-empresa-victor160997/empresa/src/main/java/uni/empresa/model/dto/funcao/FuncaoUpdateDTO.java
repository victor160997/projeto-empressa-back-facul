package uni.empresa.model.dto.funcao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncaoUpdateDTO {
    private int id;
    private String nome;
    private double remuneracao;
}
