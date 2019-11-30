
import Ex1.ComplexFunction;
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
    void plus() throws Exception {
        Polynom p1 = new Polynom("x+2");
        Polynom p2 = new Polynom("5x+5");
        ComplexFunction cf = new ComplexFunction("Plus",p1,p2);
        p1.add(p2);
        if(!p1.toString().equals(cf.toString())){
            fail("Not Equal Strings of Plus");
        }else{
            System.out.println("Equal String of Plus");
        }
    }

    @Test
    void mul() throws Exception {
        Polynom p1 = new Polynom("2x^2+2");
        Polynom p2 = new Polynom("x+5");
        ComplexFunction cf = new ComplexFunction("Times",p1,p2);
        p1.multiply(p2);
        if(!p1.toString().equals(cf.toString())){
            fail("Not Equal Strings of Multiply");
        }else{
            System.out.println("Equal String of Multiply");
        }
    }

    @Test
    void div() throws Exception {
    }

    @Test
    void max() throws Exception {
    }

    @Test
    void min() throws Exception {
    }

    @Test
    void comp() throws Exception {
    }

    @Test
    void left() throws Exception {
        Polynom p1 = new Polynom();
        Polynom p2 = new Polynom();
        Polynom p3 = new Polynom();
        ComplexFunction cf = new ComplexFunction("plus",new ComplexFunction("plus",p1,p2),p3);
        ComplexFunction cfLeft = new ComplexFunction(cf.left());

    }

    @Test
    void right() throws Exception {
    }

    @Test
    void getOp() throws Exception {
    }

    @Test
    void f() throws Exception {
    }

    @Test
    void initFromString() throws Exception {
    }

    @Test
    void copy() throws Exception {
    }
@Test
     void FunctionsFactory() throws Exception {
        //Functions_GUI ans = new Functions_GUI();
        String s1 = "-x^4+2x^3+x+2";
        String s2 = "x+2";
        String[] s3 = {"x+3","x-2", "x-4"};
        Polynom p1 = new Polynom(s1);
        Polynom p2 = new Polynom(s2);
        Polynom p3 = new Polynom(s3[0]);
        //ComplexFunction cf3 = new ComplexFunction(p3);
        for(int i=1;i<s3.length;i++) {
         //  cf3.mul(new Polynom(s3[i]));
        }

        ComplexFunction cf = new ComplexFunction("plus", p1,p2);
        //ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x +1"),cf3);
       // cf4.plus(new Monom("2"));
       // cf4.plus(new Polynom("x+2"));
        //ans.add(cf.copy());
        //ans.add(cf4.copy());
        cf.div(p1);
        cf.plus(new Polynom("3x^5+2x+3"));
        //ans.add(cf.copy());
        String s = cf.toString();
        System.out.println(s);
        //function cf5 = cf4.initFromString(s1);
        //function cf6 = cf4.initFromString(s2);
        //ans.add(cf5.copy());
        //ans.add(cf6.copy());
        //ComplexFunction max = new ComplexFunction(ans.get(0).copy());
        //ComplexFunction min = new ComplexFunction(ans.get(0).copy());
        //for(int i=1;i<ans.size();i++) {
        //    max.max(ans.get(i));
        //    min.min(ans.get(i));
        //}
        //ans.add(max);
        //ans.add(min);

        //return ans;
    }
}
