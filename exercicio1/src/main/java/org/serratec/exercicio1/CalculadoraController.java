package org.serratec.exercicio1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {

    @GetMapping("/somar")
    public Calculadora somar(@RequestParam double a, @RequestParam double b) {
        double resultado = a + b;
        return new Calculadora(a, b, "+", resultado);
    }

    @GetMapping("/multiplicar")
    public Calculadora multiplicar(@RequestParam double a, @RequestParam double b) {
        double resultado = a * b;
        return new Calculadora(a, b, "*", resultado);
    }

    @GetMapping("/dividir")
    public Calculadora dividir(@RequestParam double a, @RequestParam double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Não é possível dividir por zero!");
        }
        double resultado = a / b;
        return new Calculadora(a, b, "/", resultado);
    }

    @GetMapping("/subtrair")
    public Calculadora subtrair(@RequestParam double a, @RequestParam double b) {
        double resultado = a - b;
        return new Calculadora(a, b, "-", resultado);
    }
}