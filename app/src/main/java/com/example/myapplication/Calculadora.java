package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Calculadora {

    /**
     * @author Roberto
     * @param expresion String de operadores que recibimos para calcular
     * @return resultado de la expresion
     *
     * recibimos un String con operadores y los calculamos dependiendo de lo tecleado en la interfaz
     * */
    public static double resolverExpresion(String expresion) {

        // Elimina todos los espacios de la expresión para facilitar el procesamiento
        expresion = expresion.replaceAll("\\s+", "");

        // Lista para almacenar números en la expresión
        List<Double> listaNumeros = new ArrayList<>();
        // Lista para almacenar operadores en la expresión
        List<Character> listaOperadores = new ArrayList<>();
        // StringBuilder temporal para construir números de varios dígitos
        StringBuilder numeroTemporal = new StringBuilder();

        // Itera sobre cada caracter en la expresión
        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            // Si el caracter es un dígito o un signo menos al inicio o después de un operador, es parte de un número
            if (Character.isDigit(c) || (c == '-' && (i == 0 || esOperador(expresion.charAt(i - 1))))) {
                numeroTemporal.append(c);  // Agrega el dígito al número en construcción
            } else {
                // Cuando se encuentra un operador, convierte el número temporal y lo agrega a la lista de números
                if (numeroTemporal.length() > 0) {
                    listaNumeros.add(Double.parseDouble(numeroTemporal.toString()));
                    numeroTemporal.setLength(0);  // Reinicia para el siguiente número
                }

                // Agrega el operador a la lista de operadores si es un operador válido
                if (esOperador(c)) {
                    listaOperadores.add(c);
                }
            }
        }

        // Agrega el último número a la lista si el número temporal tiene algún valor
        if (numeroTemporal.length() > 0) {
            listaNumeros.add(Double.parseDouble(numeroTemporal.toString()));
        }

        // Muestra los números y operadores en consola (solo para pruebas o depuración)
        System.out.println("Números: " + listaNumeros);
        System.out.println("Operadores: " + listaOperadores);

        // Verifica si hay más operadores que números, lo que indicaría una expresión incompleta
        if (listaOperadores.size() > listaNumeros.size() - 1) {
            throw new IllegalArgumentException("Expresión incompleta: hay operadores sin números asociados.");
        }

        // Primero resuelve las operaciones de multiplicación y división
        for (int i = 0; i < listaOperadores.size(); i++) {
            char operador = listaOperadores.get(i);
            if (operador == 'x' || operador == '/') {
                if (i < listaNumeros.size() - 1) {  // Verifica que hay un número a la derecha
                    double izquierdo = listaNumeros.get(i);
                    double derecho = listaNumeros.get(i + 1);
                    double resultado;

                    // Realiza la multiplicación o división según el operador actual
                    if (operador == 'x') {
                        resultado = izquierdo * derecho;
                    } else {
                        // Verifica que no haya división por cero
                        if (derecho != 0) {
                            resultado = izquierdo / derecho;
                        } else {
                            throw new ArithmeticException("División por cero");
                        }
                    }

                    // Actualiza la lista de números con el resultado y elimina el número y operador usados
                    listaNumeros.set(i, resultado);
                    listaNumeros.remove(i + 1);
                    listaOperadores.remove(i);
                    i--;  // Ajusta el índice para procesar en el orden correcto
                } else {
                    throw new IllegalArgumentException("Operador '" + operador + "' no tiene un número a la derecha");
                }
            }
        }

        // Calcula la suma y resta para obtener el resultado final
        double total = listaNumeros.get(0);  // Comienza con el primer número en la lista
        for (int i = 0; i < listaOperadores.size(); i++) {
            if (i + 1 < listaNumeros.size()) {  // Verifica que hay un número a la derecha
                double derecho = listaNumeros.get(i + 1);
                char operador = listaOperadores.get(i);

                // Realiza la operación de suma o resta
                if (operador == '+') {
                    total += derecho;
                } else if (operador == '-') {
                    total -= derecho;
                }
            } else {
                throw new IllegalArgumentException("Operador '" + listaOperadores.get(i) + "' no tiene un número a la derecha");
            }
        }

        return total;  // Devuelve el resultado de la expresión
    }

    // Método auxiliar para verificar si un caracter es un operador
    private static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == 'x' || c == '/';
    }
}
