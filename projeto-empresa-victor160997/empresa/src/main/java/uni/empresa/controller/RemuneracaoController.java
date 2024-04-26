package uni.empresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uni.empresa.model.dto.remuneracao.RemuneracaoUpdateDTO;
import uni.empresa.service.RemuneracaoService;

@RestController
@RequestMapping("/remuneracao")
public class RemuneracaoController {
    @Autowired
    private RemuneracaoService remuneracaoService;

    @PostMapping("/pagaEmpregado/{idEmpregado}")
    public ResponseEntity<?> salvaSalarioEmpregado(@PathVariable(required = true) int idEmpregado) {
        try {
            remuneracaoService.salvaSalarioEmpregado(idEmpregado);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizaSalarioEmpregado")
    public ResponseEntity<?> atualizaSalarioEmpregado(
            @RequestBody(required = true) RemuneracaoUpdateDTO remuneracaoUpdateDTO) {
        try {
            remuneracaoService.atualizaRemuneracao(remuneracaoUpdateDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deletaRemuneracao(@PathVariable(required = true) int id) {
        try {
            remuneracaoService.deletaRemuneracao(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{idEmpregado}")
    public ResponseEntity<?> listaRemuneracoesEmpregado(@PathVariable(required = true) int idEmpregado) {
        try {
            return ResponseEntity.ok(remuneracaoService.buscaRemuneracao(idEmpregado));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listaRemuneracoes() {
        try {
            return ResponseEntity.ok(remuneracaoService.listaRemuneracoes());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
