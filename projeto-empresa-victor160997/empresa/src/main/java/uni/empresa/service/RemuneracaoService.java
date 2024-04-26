package uni.empresa.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uni.empresa.model.Empregado;
import uni.empresa.model.Funcao;
import uni.empresa.model.Remuneracao;
import uni.empresa.model.dao.EmpregadoDAO;
import uni.empresa.model.dao.RemuneracaoDAO;
import uni.empresa.model.dto.remuneracao.RemuneracaoUpdateDTO;
import uni.empresa.model.dto.remuneracao.RemuneracaoViewDTO;

@Service
public class RemuneracaoService {
    @Autowired
    EmpregadoDAO empregadoDAO;

    @Autowired
    RemuneracaoDAO remuneracaoDAO;

    public void salvaSalarioEmpregado(int idEmpregado) throws Exception {
        Optional<Empregado> empregado = empregadoDAO.findById(idEmpregado);
        if (!empregado.isPresent()) {
            throw new Exception("Empregado não encontrado");
        }

        Funcao funcEmpregado = empregado.get().getFuncao();

        double salario = funcEmpregado.getRemuneracao();

        salario = salario * 0.87; // INSS

        if (empregado.get().isNoturno()) { // Adicional noturno
            salario = salario * 1.05;
        }

        if (empregado.get().isValeTransporte()) { // Vale transporte
            salario = salario * 0.94;
        }

        int numFilhos = empregado.get().getFilhos().size();
        if (numFilhos > 0 && numFilhos <= 3) { // Bônus por filho
            int bonusFilhos = numFilhos * 50;
            salario += bonusFilhos;
        }

        if (numFilhos > 3) { // Bônus por filho
            int bonusFilhos = 3 * 50;
            salario += bonusFilhos;
        }

        Remuneracao remuneracao = new Remuneracao();
        remuneracao.setEmpregado(empregado.get());
        remuneracao.setDataPagamento(java.time.LocalDateTime.now());
        remuneracao.setValor(salario);

        remuneracaoDAO.save(remuneracao);
    }

    public void atualizaRemuneracao(RemuneracaoUpdateDTO remuneracaoUpdateDTO) throws Exception {
        Optional<Remuneracao> remuneracao = remuneracaoDAO.findById(remuneracaoUpdateDTO.getId());
        if (!remuneracao.isPresent()) {
            throw new Exception("Remuneração não encontrada");
        }

        remuneracao.get().setValor(remuneracaoUpdateDTO.getValor());
        remuneracao.get().setDataPagamento(remuneracaoUpdateDTO.getDataPagamento());

        remuneracaoDAO.save(remuneracao.get());
    }

    public void deletaRemuneracao(int id) throws Exception {
        Optional<Remuneracao> remuneracao = remuneracaoDAO.findById(id);
        if (!remuneracao.isPresent()) {
            throw new Exception("Remuneração não encontrada");
        }

        remuneracaoDAO.delete(remuneracao.get());
    }

    public RemuneracaoViewDTO buscaRemuneracao(int id) throws Exception {
        Optional<Remuneracao> remuneracao = remuneracaoDAO.findById(id);
        if (!remuneracao.isPresent()) {
            throw new Exception("Remuneração não encontrada");
        }

        RemuneracaoViewDTO remuneracaoView = new RemuneracaoViewDTO();
        remuneracaoView.setId(remuneracao.get().getId());
        remuneracaoView.setValor(remuneracao.get().getValor());
        remuneracaoView.setDataPagamento(remuneracao.get().getDataPagamento());
        remuneracaoView.setEmpregado(remuneracao.get().getEmpregado());
        return remuneracaoView;
    }

    public List<RemuneracaoViewDTO> listaRemuneracoes() {
        List<Remuneracao> remuneracoes = remuneracaoDAO.findAll();
        List<RemuneracaoViewDTO> remuneracoesView = new ArrayList<RemuneracaoViewDTO>();

        for (Remuneracao remuneracao : remuneracoes) {
            RemuneracaoViewDTO remuneracaoView = new RemuneracaoViewDTO();
            remuneracaoView.setId(remuneracao.getId());
            remuneracaoView.setValor(remuneracao.getValor());
            remuneracaoView.setDataPagamento(remuneracao.getDataPagamento());
            remuneracaoView.setEmpregado(remuneracao.getEmpregado());
            remuneracoesView.add(remuneracaoView);
        }

        return remuneracoesView;
    }
}
