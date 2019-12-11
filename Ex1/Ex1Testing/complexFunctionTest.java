package Ex1.Ex1Testing;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class complexFunctionTest {

    @BeforeAll
    static void setUpBeforeClass()  {
        System.out.println("Start complexFunction Test \n");
    }

    @AfterAll
    static void tearDownAfterClass()  {
        System.out.println("End complexFunction Test");
    }

    @Test
    void plus() throws Exception {// use cf.plus()
        System.out.println("***** Plus Testing: *****");
        ComplexFunction plus = new ComplexFunction("plus",new Polynom("3x^2+4x+2"),new Polynom("3x"));
        plus.plus(new Polynom("3x"));
        int checkNum = 4;
        double expected = (3*4*4+10*4+2);
        if(plus.f(checkNum)!= expected){
            fail("Expected: "+ expected + ", Actual: " + plus.f(checkNum));
        }else{
            System.out.println("Expected: "+ expected + ", Actual: " + plus.f(checkNum));
        }
    }

    @Test
    void mul() throws Exception {// use cf.mul()
        System.out.println("***** Muliply Testing: *****");
        ComplexFunction mul = new ComplexFunction("plus",new Polynom("3x^2+4x+2"),new Polynom("3x"));
        mul.mul(new Polynom("3x"));
        int checkNum = 4;
        double expected = (3*4*4+7*4+2)*(3*4);
        if(mul.f(checkNum)!= expected){
            fail("Expected: "+ expected + ", Actual: " + mul.f(checkNum));
        }else{
            System.out.println("Expected: "+ expected + ", Actual: " + mul.f(checkNum));
        }
    }

    @Test
    void div() throws Exception {// use cf.div()
        System.out.println("***** Divide Testing: *****");
        ComplexFunction div = new ComplexFunction("div",new Polynom("5x^2"),new Polynom("3x"));
        div.div(new ComplexFunction("plus",new Polynom("2x^2"),new Polynom("3x+1")));
        int checkNum = 4;
        double expected = ((5*4*4)/(12.0))/(2*4*4+3*4+1);
        if(div.f(checkNum)!= expected){
            fail("Expected: "+ expected + ", Actual: " + div.f(checkNum));
        }else{
            System.out.println("Expected: "+ expected + ", Actual: " + div.f(checkNum));
        }
    }

    @Test
    void max() throws Exception {// use cf.max()
        System.out.println("***** Maximum Testing: *****");
        ComplexFunction max = new ComplexFunction("max",new Polynom("5x^2"),new Monom("3x"));
        max.max(new Polynom("4x^2"));
        int checkNum = 4;
        double expected = 5*4*4;
        if(max.f(checkNum)!= expected){
            fail("Expected: "+ expected + ", Actual: " + max.f(checkNum));
        }else{
            System.out.println("Expected: "+ expected + ", Actual: " + max.f(checkNum));
        }
    }

    @Test
    void min() throws Exception {// use cf.min()
        System.out.println("***** Minimum Testing: *****");
        ComplexFunction min = new ComplexFunction("max",new Polynom("5x^2"),new Monom("3x"));
        min.min(new Polynom("4x^3"));
        int checkNum = 4;
        double expected = 5*4*4;
        if(min.f(checkNum)!= expected){
            fail("Expected: "+ expected + ", Actual: " + min.f(checkNum));
        }else{
            System.out.println("Expected: "+ expected + ", Actual: " + min.f(checkNum));
        }
    }

    @Test
    void comp() throws Exception {// use cf.comp()
        System.out.println("***** Comp Testing: *****");
        ComplexFunction comp = new ComplexFunction("comp",new Polynom("5x^2"),new Polynom("3x"));
        comp.comp(new Polynom("4x^2"));
        int checkNum = 4;
        double expected = 5*9*16*4*4*4*4;
        if(comp.f(checkNum)!= expected){
            fail("Expected: "+ expected + ", Actual: " + comp.f(checkNum));
        }else{
            System.out.println("Expected: "+ expected + ", Actual: " + comp.f(checkNum));
        }
    }

    @Test
    void left() throws Exception {
        Polynom p1 = new Polynom("x+1");
        Polynom p2 = new Polynom("2x^2+3x");
        Polynom p3 = new Polynom("4x^4+2");
        ComplexFunction cf = new ComplexFunction("plus",new ComplexFunction("plus",p1,p2),p3);
        ComplexFunction cfLeft = new ComplexFunction(cf.left());
        if(cf.left() == null || cfLeft == null || !cf.left().toString().equals(cfLeft.toString())){
            fail("Left Function isn't working!");
        }else{
            System.out.println("Left Function Working Well");
        }
    }

    @Test
    void right() throws Exception {
        Polynom p1 = new Polynom("x+5");
        Polynom p2 = new Polynom("8x^2+3");
        Polynom p3 = new Polynom("2.5x^3+x+1");
        ComplexFunction cf = new ComplexFunction("plus",new ComplexFunction("plus",p1,p2),p3);
        ComplexFunction cfRight = new ComplexFunction(cf.right());
        if(cf.right() == null || cfRight == null ||!cf.right().toString().equals(cfRight.toString())){
            fail("Right Function isn't working!");
        }else{
            System.out.println("Right Function Working Well");
        }
    }

    @Test
    void getOp() throws Exception {
        String op = "times";
        Operation expected = Operation.Times;
        ComplexFunction cf = new ComplexFunction(op,new Polynom("x+2"),new Polynom("2x^2+4"));
        ComplexFunction cf2 = new ComplexFunction(cf);
        if(cf.getOp() != expected && cf2.getOp() != expected){
            fail("Expected Operation: " + expected + " Actual: " + cf.getOp() +","+cf2.getOp());
        }else {
            System.out.println("Expected Operation: " + expected.name() + " Actual: " + cf.getOp() +","+cf2.getOp());
        }
    }

    @Test
    void f() throws Exception {
        System.out.println("***** Plus.f() Testing: *****");
        ComplexFunction plus1 = new ComplexFunction("plus",new Polynom("3x^2+4x+2"),new Polynom("3x"));
        ComplexFunction plus2 = new ComplexFunction(plus1.initFromString("plus(3x^2+4x,3x+2)"));
        int checkNum = 4;
        double expected = (3*4*4+4*4+2)+(3*4);
        if(plus1.f(checkNum)!= plus2.f(checkNum) || plus1.f(checkNum)!= expected){
            fail("Expected: "+ expected + ", Actual: " + plus1.f(checkNum));
        }else{
            System.out.println("Expected: "+ expected + ", Actual: " + plus1.f(checkNum));
        }
        //-------------------------------------//
        System.out.println("***** Multiply.f() Testing: *****");
        ComplexFunction mul1 = new ComplexFunction("mul",new Polynom("3x^2+4x"),new Polynom("3x+2"));
        ComplexFunction mul2 = new ComplexFunction(mul1.initFromString("mul(3x^2+4x,3x+2)"));
        checkNum = 4;
        expected = (3*4*4+4*4)*(3*4+2);
        if(mul1.f(checkNum)!= mul2.f(checkNum) || mul1.f(checkNum)!= expected){
            fail("Expected: "+ expected + ", Actual: " + mul1.f(checkNum));
        }else{
            System.out.println("Expected: "+ expected + ", Actual: " + mul1.f(checkNum));
        }
        //-------------------------------------//
        System.out.println("***** Divide.f() Testing: *****");
        ComplexFunction div1 = new ComplexFunction("div",new Polynom("5x^2"),new Polynom("3x"));
        ComplexFunction div2 = new ComplexFunction(div1.initFromString("div(5x^2,3x)"));
        checkNum = 4;
        expected = (5*4*4)/(12.0);
        if(div1.f(checkNum)!= div2.f(checkNum) || div1.f(checkNum)!= expected){
            fail("Expected: "+ expected + ", Actual: " + div1.f(checkNum));
        }else{
            System.out.println("Expected: "+ expected + ", Actual: " + div1.f(checkNum));
        }
        //-------------------------------------//
        System.out.println("***** Maximum.f() Testing: *****");
        ComplexFunction max1 = new ComplexFunction("max",new Polynom("5x^2"),new Polynom("3x"));
        ComplexFunction max2 = new ComplexFunction(max1.initFromString("max(5x^2,2x)"));
        checkNum = 4;
        expected = 5*4*4;
        if(max1.f(checkNum)!= max2.f(checkNum) || max1.f(checkNum)!= expected){
            fail("Expected: "+ expected + ", Actual: " + max1.f(checkNum));
        }else{
            System.out.println("Expected: "+ expected + ", Actual: " + max1.f(checkNum));
        }
        //-------------------------------------//
        System.out.println("***** Minimum.f() Testing: *****");
        ComplexFunction min1 = new ComplexFunction("min",new Polynom("5x^2"),new Polynom("3x"));
        ComplexFunction min2 = new ComplexFunction(min1.initFromString("min(8x^2,3x)"));
        checkNum = 4;
        expected = 3*4;
        if(min1.f(checkNum)!= min2.f(checkNum) || min1.f(checkNum)!= expected){
            fail("Expected: "+ expected + ", Actual: " + min1.f(checkNum));
        }else{
            System.out.println("Expected: "+ expected + ", Actual: " + min1.f(checkNum));
        }
        //-------------------------------------//
        System.out.println("***** Comp.f() Testing: *****");
        ComplexFunction comp = new ComplexFunction("comp",new Polynom("5x^2"),new Polynom("3x"));
        checkNum = 4;
        expected = 5*(3*4)*(3*4);
        if(comp.f(checkNum)!= expected){
            fail("Expected: "+ expected + ", Actual: " + comp.f(checkNum));
        }else{
            System.out.println("Expected: "+ expected + ", Actual: " + comp.f(checkNum));
        }
    }

    @Test
    void initFromStringTest() throws Exception {
        System.out.println("***** InitFromString Testing: *****");
        ComplexFunction _init = new ComplexFunction();
        String functionString = "plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)";
        functionString = functionString.replaceAll(" ", "");
        String result = _init.initFromString(functionString).toString();
        ComplexFunction _after = new ComplexFunction(_init.initFromString(functionString));
        if(!result.equals(_after.toString())){
            fail("Expected : " + functionString + ", Actual: " + _after.toString());
        }else{
            System.out.println("Expected : " + functionString + ", Actual: " + _after.toString());
        }

    }

    @Test
    void copy() throws Exception {
        System.out.println("***** Copy Testing: *****");
        ComplexFunction _copy = new ComplexFunction("plus",
                new Polynom("-1.0x^4 +2.4x^2 +3.1"),
                new Polynom("+0.1x^5 -1.2999999999999998x +5.0"));
        String result = _copy.toString();
        ComplexFunction _after = new ComplexFunction(_copy.copy());
        if(!result.equals(_after.toString())){
            fail("Expected : " + result + ", Actual: " + _after.toString());
        }else{
            System.out.println("Expected : " + result + ", Actual: " + _after.toString());
        }
    }

    @Test
    void equals() throws Exception{
        System.out.println("***** Equals Testing: ");
        ComplexFunction cf = new ComplexFunction("plus",new Polynom("2x^2"),new Polynom("2x^2"));
        ComplexFunction cf2 = new ComplexFunction("mul", new Polynom("2x"), new Polynom("2x"));
        if(!cf.equals(cf2)){
            fail("Not Equal Funtions!");
        }else {
            System.out.println("Equal Functions!");
        }
    }

}
