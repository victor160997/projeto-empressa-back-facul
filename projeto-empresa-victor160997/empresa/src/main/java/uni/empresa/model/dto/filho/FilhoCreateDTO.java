package uni.empresa.model.dto.filho;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilhoCreateDTO {
    private String nome;
    private int idEmpregado;
}
