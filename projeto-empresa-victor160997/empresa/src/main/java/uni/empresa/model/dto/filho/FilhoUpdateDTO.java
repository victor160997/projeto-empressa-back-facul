package uni.empresa.model.dto.filho;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilhoUpdateDTO {
    private int id;
    private String nome;
    private int idEmpregado;
}
