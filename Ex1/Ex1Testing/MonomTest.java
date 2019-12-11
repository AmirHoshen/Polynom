package Ex1Testing;

import Ex1.Monom;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.fail;

class MonomTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("Start Monom Test \n");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("End Monom Test");
    }

    @Test
    void testEquals() {
        Monom m1 = new Monom(-1,0);
        Monom m2 = new Monom(Monom.MINUS1);
        if(!m1.equals(m2)){
            fail("Err at testEquals: Monom's not equal");
        }else{
            System.out.println("Monoms are equal\nExpected: "+m1.toString()+"\t\tActual: "+m2.toString());
        }
    }

    @Test
    void f() {
        Monom m = new Monom(-1,1);
        double ans = m.f(-1);
        if(ans!=m.f(-1)){
            fail("f(x) should be fixed");
        }else System.out.println("f(x) while x is (-1)  OK\nExpected: "+m.f(-1)+"\t\tActual: "+ans);
    }

    @Test
    void derivative() {
        Monom m = new Monom(-1,2);
        Monom ans = new Monom(m.derivative());
        if(!m.derivative().equals(ans)){
            System.out.println("monom m is: "+m.toString()+ " ans is: "+ans.toString());
            fail("Derivative should be fixed");
        }else System.out.println("Derivative is OK\nExpected: "+m.derivative()+"\t\tActual: "+ans);
    }

    @Test
    void isZero() {
        Monom m = new Monom(Monom.ZERO);
        if(!m.isZero()){
            fail("isZero method should be fixed");
        }else System.out.println("isZero is OK\nExpected: "+m.toString()+"\t\tActual: "+m.toString());
    }

    @Test
    void add() {
        Monom m1 = new Monom(Monom.MINUS1);
        Monom m2 = new Monom(Monom.MINUS1);
        m1.add(m2);
        Monom m3 = new Monom(m1);
        if(!m1.equals(m3)){
            fail("add method should be fixed");
        }System.out.println("add is OK\nExpected: "+m1.toString()+"\t\tActual: "+m3.toString());
    }

    @Test
    void substract() {
        Monom m1 = new Monom(Monom.MINUS1);
        Monom m2 = new Monom(Monom.MINUS1);
        m1.substract(m2);
        Monom m3 = new Monom(m1);
        if (!m3.isZero() & !m1.isZero()) {
            fail("substract should be fixed");
        }else System.out.println("substract is OK\nExpected: "+m1.toString()+"\t\tActual: "+m3.toString());
    }

    @Test
    void multiply() {
        Monom m1 = new Monom(Monom.MINUS1);
        Monom m2 = new Monom(m1);
        Monom m3 = new Monom(m1);
        m1.multiply(m2);
        m2.multiply(m3);
        if(!m1.equals(m2)){
            fail("multiply should be fixed");
        }else System.out.println("multiply is OK\nExpected: "+m1.toString()+"\t\tActual: "+m2.toString());
    }
}