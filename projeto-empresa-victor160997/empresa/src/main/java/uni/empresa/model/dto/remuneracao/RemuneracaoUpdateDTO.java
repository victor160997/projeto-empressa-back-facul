package uni.empresa.model.dto.remuneracao;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemuneracaoUpdateDTO {
    private int id;
    private double valor;
    private LocalDateTime dataPagamento;
}
