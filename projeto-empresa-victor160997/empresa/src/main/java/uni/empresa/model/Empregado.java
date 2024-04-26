package uni.empresa.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empregado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nome;
    private EStatusEmpregado status;
    private boolean noturno;
    private boolean valeTransporte;

    @ManyToOne
    @JoinColumn(name = "id_funcao")
    private Funcao funcao;

    @OneToMany(mappedBy = "empregado", cascade = CascadeType.ALL)
    private List<Filho> filhos;

}
