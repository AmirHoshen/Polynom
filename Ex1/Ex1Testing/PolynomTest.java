package Ex1.Ex1Testing;

import Ex1.Monom;
import Ex1.Polynom;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.fail;

class PolynomTest {
    private static Polynom polynom = new Polynom();

    @BeforeAll
    static void setUpBeforeClass() throws Exception{
        System.out.println("Polynom Test started: ");
    }
    @BeforeEach
    void clearing() {
        polynom.getPoly().clear();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception{
        System.out.println("Polynom Test Ended");
        polynom.getPoly().clear();
    }

    @Test
    void f() {
        System.out.println("***** f() Testing *****");
        String[] monoms = {"2x","-5x^2","3x^3","0.2"};
        double[] answers = {0.2,0.2,8.2,42.2,120.2};
        try{
            for (String i :monoms){
                polynom.add(new Monom(i));
            }
        }catch(Exception e){e.getMessage();}
        for(int i = 0; i < 5; i++){
            if(polynom.f(i) != answers[i]){
                fail("Expected: f("+i+")= "+polynom.f(i)+", Actual: "+ answers[i] + " !");
            }else{
                System.out.println("Expected: f("+i+")= "+polynom.f(i)+", Actual: f("+ i+")= "+ answers[i]);
            }
        }

    }

    @Test
    void add() {// adding Polynom_Able
        System.out.println("***** Polynom + Polynom Testing  *****");
        Polynom polyAdd = new Polynom();
        String[] monomsAdd = {"-2x","20x^2","-3.5x^3","9"};
        try{
            for (String i :monomsAdd){
                polyAdd.add(new Monom(i));
            }
        }catch(Exception e){e.getMessage();}
        polynom.add(polyAdd);
        if(!polynom.toString().equals(polyAdd.toString())){
            fail("polynom: " + polynom.toString()+ "=/= "+ "PolynomAdded: " +polyAdd.toString());
        }else{
            System.out.println("polynom: " + polynom.toString()+ " == "+ "PolynomAdded: " +polyAdd.toString());
        }
    }

    @Test
    void testAdd() { // adding Monoms
        System.out.println("***** Polynom + Monom Testing *****");
        String[] monoms = {"5x","-10x^2","3x^3","9.5"};
        String ans = "+3x^3-10x^2+5x+9.5";
        try{
            for (String i :monoms){
                polynom.add(new Monom(i));
            }
        }catch(Exception e){e.getMessage();}
        if (!polynom.toString().equals(ans)){
            fail("Expected: " + ans +", actual: " + polynom.toString());
        }else{
            System.out.println("Expected: " + ans +", actual: " + polynom.toString());
        }

    }

    @Test
    void substract() {
        System.out.println("***** Substract Testing *****");
        String[] monoms = {"3x","-7x^2","x^3","8"};
        String[] monoms2 = {"x","5x^2","8x^3","-10"};
        String ans = "-7x^3-12x^2+2x+18";
        Polynom minus = new Polynom();
        try{
            for (String i :monoms){
                polynom.add(new Monom(i));
            }
            for (String i :monoms2){
                minus.add(new Monom(i));
            }
            polynom.substract(minus);
        }catch(Exception e){e.getMessage();}
        if (!polynom.toString().equals(ans)){
            fail("Expected: " + ans +", actual: " + polynom.toString());
        }else{
            System.out.println("Expected: " + ans +", actual: " + polynom.toString());
        }
    }

    @Test
    void multiply() {
        System.out.println("***** Multiply Testing  *****");
        String[] monoms = {"2x","+5.5x^2","2.7x^3","-9"};
        String[] monoms2 = {"x","-10"};
        String ans = "+2.7x^4-21.5x^3-53x^2-29x+90";
        Polynom multi = new Polynom();
        try{
            for (String i :monoms){
                polynom.add(new Monom(i));
            }
            for (String i :monoms2){
                multi.add(new Monom(i));
            }
            polynom.multiply(multi);
        }catch(Exception e){e.getMessage();}
        if (!polynom.toString().equals(ans)){
            fail("Expected: " + ans +", actual: " + polynom.toString());
        }else{
            System.out.println("Expected: " + ans +", actual: " + polynom.toString());
        }
    }

    @Test
    void testEquals() throws Exception {
        System.out.println("***** Equals Testing *****");
        String[] monoms = {"2x","+5.5x^2","2.7x^3","-9"};
        Boolean ans = true;

        for (String i :monoms){
            polynom.add(new Monom(i));
        }
        Polynom polynom2 = new Polynom(polynom.toString());
        if (!polynom.equals(polynom2)){
            fail("Expected: " + polynom2.toString() +" actual: " + polynom.toString());
        }else{
            System.out.println("Expected: " + polynom2.toString() +" actual: " + polynom.toString());
        }
    }

    @Test
    void zeroCorrection() {
        System.out.println("***** zeroCorrection Testing *****");
        try {
            Polynom poly = new Polynom("2+0-0x^2");
            System.out.println("Poly size before zeroCorrection: " + poly.getPoly().size());
            if(poly.getPoly().size()==3){
                poly.zeroCorrection();
                if(poly.getPoly().size()!=1){
                    fail("zeroCorrection doesn't work!");
                }else{
                    System.out.println("Poly size after zeroCorrection: " + poly.getPoly().size());
                    System.out.println("zeroCorrection works!");
                }
            }else{
                fail("Test isn't Working");
            }
        }catch (Exception e){e.getMessage();}

    }

    @Test
    void isZero() {
        System.out.println("***** isZero Testing *****");
        try{
            polynom = new Polynom("5x^2+2x+3");
            polynom.substract(new Polynom("5x^2+2x+3"));
            if(!polynom.isZero()){
                fail("Polynom isn't Zero!");
            }else {
                System.out.println("Polynom is Zero!");
            }
        }catch (Exception e){e.getMessage();}
    }

    @Test
    void root() {
        System.out.println("***** Root Testing *****");
        try{
            polynom = new Polynom("-3x^2+4x+4");
            double Expected = -0.6666516133402578;
            if(polynom.root(-1,0,0.0001)!=Expected){
                fail("Expected: " + Expected + ", Actual: " + polynom.root(-1,0,0.0001));
            }
            System.out.println("Expected: " + Expected + ", Actual: " + polynom.root(-1,0,0.0001));
        }catch (Exception e){e.getMessage();fail("Exception Caught!");}


    }

    @Test
    void copy() {
        System.out.println("***** Copy Testing *****");
        try{
            Polynom p = new Polynom("2+x+23.146x^8");
            Polynom pCopy = (Polynom) p.copy();
            if(p!=pCopy){
                Iterator<Monom> iter = p.iteretor();
                Iterator<Monom> iter2 = pCopy.iteretor();
                while(iter.hasNext() && iter2.hasNext()){
                    Monom monom = iter.next();
                    Monom monom2 = iter2.next();
                    if(monom.get_coefficient()!=monom2.get_coefficient() &&
                            monom.get_power()!=monom2.get_power()){
                        fail("Copy didn't work!");
                    }
                }
                System.out.println("Copy Working Fine!");
            }else{
                System.out.println("Same address so must be Equals and that's a problem");
            }
        }catch (Exception e){e.getMessage();}

    }

    @Test
    void derivative() {
        System.out.println("***** Derivative Testing *****");
        try{
            polynom.add(new Polynom("2+x+3.5x^2-1.5x^3"));
            polynom = (Polynom) polynom.derivative();
            String expected = "-4.5x^2+7x+1";
            if(!polynom.toString().contentEquals(expected)){
                fail("Expected: "+ expected + ", Actual: " + polynom.toString());
            }else{
                System.out.println("Expected: "+ expected + ", Actual: " + polynom.toString());
            }
        }catch (Exception e){e.getMessage();}
    }

    @Test
    void area() {
        System.out.println("***** Area Testing *****");
        try{
            polynom = new Polynom("+1.7x^2+1.7x");
            double areaExpected = 7.933333330486421;
            if(polynom.area(-3,0,0.0001) != areaExpected){
                fail("Expected: "+ areaExpected + ", Actual: " + polynom.area(-3,0,0.0001));
            }else{
                System.out.println("Expected: "+ areaExpected + ", Actual: " + polynom.area(-3,0,0.0001));
            }

        }catch (Exception e){e.getMessage();}
    }

    @Test
    void testToString() {
        System.out.println("***** toString Testing  *****");
        String str = "3.5x^6+12x^5+5x^2+6x+1";
        try {
            Polynom p1 = new Polynom(str);
            Polynom p2 = new Polynom(p1.toString());
            if(!p1.equals(p2)){
                fail("toString should be fixed");
            }else System.out.println("toString went successfully\nExpected: "+p1.toString()+"\nActual: "+p2.toString());
        } catch (Exception e) {e.getMessage();}
    }
}