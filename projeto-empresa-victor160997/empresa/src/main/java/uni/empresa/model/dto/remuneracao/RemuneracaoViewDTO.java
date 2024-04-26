package uni.empresa.model.dto.remuneracao;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uni.empresa.model.Empregado;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemuneracaoViewDTO {
    private int id;
    private double valor;
    private LocalDateTime dataPagamento;
    private Empregado empregado;
}
