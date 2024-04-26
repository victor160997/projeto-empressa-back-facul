package uni.empresa.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uni.empresa.model.Funcao;
import uni.empresa.model.dao.FuncaoDAO;
import uni.empresa.model.dto.funcao.FuncaoCreateDTO;
import uni.empresa.model.dto.funcao.FuncaoUpdateDTO;
import uni.empresa.model.dto.funcao.FuncaoViewDTO;

@Service
public class FuncaoService {
    @Autowired
    FuncaoDAO funcaoDAO;

    public void criaFuncao(FuncaoCreateDTO funcaoCreateDTO) throws Exception {

        if (funcaoCreateDTO.getNome().isEmpty() || funcaoCreateDTO.getNome().isBlank()) {
            throw new Exception("Nome da função não pode ser vazio");
        }

        if (funcaoCreateDTO.getRemuneracao() <= 0) {
            throw new Exception("Remuneração da função deve ser maior que zero");
        }

        Funcao funcao = new Funcao();
        funcao.setNome(funcaoCreateDTO.getNome());
        funcao.setRemuneracao(funcaoCreateDTO.getRemuneracao());
        funcaoDAO.save(funcao);
    }

    public void atualizaFuncao(FuncaoUpdateDTO funcaoUpdateDTO) throws Exception {
        if (funcaoUpdateDTO.getNome().isEmpty() || funcaoUpdateDTO.getNome().isBlank()) {
            throw new Exception("Nome da função não pode ser vazio");
        }

        if (funcaoUpdateDTO.getRemuneracao() <= 0) {
            throw new Exception("Remuneração da função deve ser maior que zero");
        }

        Funcao funcao = funcaoDAO.findById(funcaoUpdateDTO.getId()).get();
        funcao.setNome(funcaoUpdateDTO.getNome());
        funcao.setRemuneracao(funcaoUpdateDTO.getRemuneracao());
        funcaoDAO.save(funcao);
    }

    public void deletaFuncao(int id) throws Exception {
        if (!funcaoDAO.existsById(id)) {
            throw new Exception("Função não encontrada");
        }

        funcaoDAO.deleteById(id);
    }

    public FuncaoViewDTO buscaFuncao(int id) throws Exception {
        Optional<Funcao> funcao = funcaoDAO.findById(id);
        if (!funcao.isPresent()) {
            throw new Exception("Função não encontrada");
        }

        FuncaoViewDTO funcaoView = new FuncaoViewDTO();
        funcaoView.setId(funcao.get().getId());
        funcaoView.setNome(funcao.get().getNome());
        funcaoView.setRemuneracao(funcao.get().getRemuneracao());
        return funcaoView;
    }

    public List<FuncaoViewDTO> listaFuncoes() {
        List<Funcao> funcoes = funcaoDAO.findAll();
        List<FuncaoViewDTO> funcoesView = new ArrayList<FuncaoViewDTO>();

        for (Funcao funcao : funcoes) {
            FuncaoViewDTO funcaoView = new FuncaoViewDTO();
            funcaoView.setId(funcao.getId());
            funcaoView.setNome(funcao.getNome());
            funcaoView.setRemuneracao(funcao.getRemuneracao());
            funcoesView.add(funcaoView);
        }

        return funcoesView;
    }
}
