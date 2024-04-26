package uni.empresa.model.dto.remuneracao;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemuneracaoCreateDTO {
    private double valor;
    private LocalDateTime dataPagamento;
    private int idEmpregado;
}
