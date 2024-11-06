package com.example.myapplication;

import junit.framework.TestCase;

import org.junit.Test;

public class CalculadoraTest extends TestCase {
    @Test
    public void testSumaSimple() {
        double total = Calculadora.resolverExpresion ("5+3");
        assertEquals("X + Y operations not working correctly", 8.0, total);

    }

    @Test
    public void testSumaMultiple() {
        double total = Calculadora.resolverExpresion("4+3+1");
        assertEquals("+X operations not working correctly", 8.0, total);
    }

    @Test
    public void testMultiplicaciónSimple() {
        double total = Calculadora.resolverExpresion("4*2");
        assertEquals("4*X operations not working correctly", 8.0, total);
    }

    @Test
    public void testMultiplicacionSimple2() {
        double total = Calculadora.resolverExpresion("2*3");
        assertEquals("2*X operations not working correctly", 6.0, total);
    }

    @Test
    public void testMultiplicacionMultiple() {
        double total = Calculadora.resolverExpresion("1*2*8");
        assertEquals("*X operations not working correctly", 16.0, total);
    }

    @Test
    public void testMultiplicacionYSuma() {
        double total = Calculadora.resolverExpresion("2*2+3");
        assertEquals("*X+X operations not working correctly", 7.0, total);
    }

    @Test
    public void testSumaYMultiplicacion() {
        double total = Calculadora.resolverExpresion("3+2*2");
        assertEquals("+X*X operations not working correctly", 7.0, total);
    }

    @Test
    public void testVariasOperaciones() {
        double total = Calculadora.resolverExpresion("3+2*2+4");
        assertEquals("+X*X+X operations not working correctly", 11.0, total);
    }
}