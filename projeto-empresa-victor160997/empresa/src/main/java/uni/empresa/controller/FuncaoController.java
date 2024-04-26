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

import uni.empresa.model.dto.funcao.FuncaoCreateDTO;
import uni.empresa.model.dto.funcao.FuncaoUpdateDTO;
import uni.empresa.service.FuncaoService;

@RestController
@RequestMapping("/funcao")
public class FuncaoController {
    @Autowired
    private FuncaoService funcaoService;

    @PostMapping("/")
    public ResponseEntity<?> criaFuncao(@RequestBody(required = true) FuncaoCreateDTO funcaoCreateDTO) {
        try {
            funcaoService.criaFuncao(funcaoCreateDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> atualizaFuncao(@RequestBody(required = true) FuncaoUpdateDTO funcaoUpdateDTO) {
        try {
            funcaoService.atualizaFuncao(funcaoUpdateDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaFuncao(@PathVariable(required = true) int id) {
        try {
            funcaoService.deletaFuncao(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{idFuncao}")
    public ResponseEntity<?> buscaFuncao(@PathVariable(required = true) int idFuncao) {
        try {
            return ResponseEntity.ok(funcaoService.buscaFuncao(idFuncao));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listaFuncoes() {
        try {
            return ResponseEntity.ok(funcaoService.listaFuncoes());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
