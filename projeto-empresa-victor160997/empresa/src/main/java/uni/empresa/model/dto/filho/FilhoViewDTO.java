package uni.empresa.model.dto.filho;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uni.empresa.model.Empregado;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilhoViewDTO {
    private int id;
    private String nome;
    private Empregado empregado;
}
