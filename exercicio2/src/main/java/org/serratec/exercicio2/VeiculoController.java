package org.serratec.exercicio2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    public static List<Veiculo> lista =  new ArrayList<Veiculo>();

    static {
        lista.add(new Veiculo(1234L,"Ford","Fiesta"));
        lista.add(new Veiculo(5678L,"BWM","X6"));
        lista.add(new Veiculo(1234L,"Fiat","siena"));
    }

    @GetMapping
    public List<Veiculo> listar(){
        return lista;
    }

    @GetMapping("/{id}")
    public Veiculo buscar (@PathVariable Long id){

        return lista.stream().filter(veiculo ->
                veiculo.getId()
                .equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo inserir(@RequestBody Veiculo veiculo){
        lista.add(veiculo);
        return veiculo;
    }
    @PutMapping("/{id}")
    public Veiculo atualizar(@RequestBody Veiculo veiculo,@PathVariable Long id){
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getId().equals(id)){
                Veiculo v = new Veiculo(
                        id,
                        veiculo.getMarca(),
                        veiculo.getModelo());
                lista.set(i,v);
                return v;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        for(int i = 0; i< lista.size();i++){
            if(lista.get(i)
                    .getId()
                    .equals(id)){
                lista.remove(i);
                break;
            }
        }
    }


}