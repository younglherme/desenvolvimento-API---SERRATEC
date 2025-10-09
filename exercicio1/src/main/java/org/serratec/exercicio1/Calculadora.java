package org.serratec.exercicio1;


    public class Calculadora {
        private double primeiroNumero;
        private double segundoNumero;
        private String operacao;
        private double resultado;

        public Calculadora() {
        }

        public Calculadora(double primeiroNumero, double segundoNumero, String operacao, double resultado) {
            this.primeiroNumero = primeiroNumero;
            this.segundoNumero = segundoNumero;
            this.operacao = operacao;
            this.resultado = resultado;
        }

        public double getPrimeiroNumero() {
            return primeiroNumero;
        }

        public void setPrimeiroNumero(double primeiroNumero) {
            this.primeiroNumero = primeiroNumero;
        }

        public double getSegundoNumero() {
            return segundoNumero;
        }

        public void setSegundoNumero(double segundoNumero) {
            this.segundoNumero = segundoNumero;
        }

        public String getOperacao() {
            return operacao;
        }

        public void setOperacao(String operacao) {
            this.operacao = operacao;
        }

        public double getResultado() {
            return resultado;
        }

        public void setResultado(double resultado) {
            this.resultado = resultado;
        }
    }

