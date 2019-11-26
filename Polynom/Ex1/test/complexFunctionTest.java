package Ex1.test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.function;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class complexFunctionTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("Start complexFunction Test \n");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("End complexFunction Test");
    }

    @Test
    void plus() {
    }

    @Test
    void mul() {
    }

    @Test
    void div() {
    }

    @Test
    void max() {
    }

    @Test
    void min() {
    }

    @Test
    void comp() {
    }

    @Test
    void left() {
    }

    @Test
    void right() {
    }

    @Test
    void getOp() {
    }

    @Test
    void f() {
    }

    @Test
    void initFromString() {
    }

    @Test
    void copy() {
    }
    public static Functions_GUI FunctionsFactory() throws Exception {
        Functions_GUI ans = new Functions_GUI();
        String s1 = "3.1 +2.4x^2 -x^4";
        String s2 = "5 +2x -3.3x +0.1x^5";
        String[] s3 = {"x +3","x -2", "x -4"};
        Polynom p1 = new Polynom(s1);
        Polynom p2 = new Polynom(s2);
        Polynom p3 = new Polynom(s3[0]);
        ComplexFunction cf3 = new ComplexFunction(p3);
        for(int i=1;i<s3.length;i++) {
            cf3.mul(new Polynom(s3[i]));
        }

        ComplexFunction cf = new ComplexFunction("plus", p1,p2);
        ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x +1"),cf3);
        cf4.plus(new Monom("2"));
        ans.add(cf.copy());
        ans.add(cf4.copy());
        cf.div(p1);
        ans.add(cf.copy());
        String s = cf.toString();
        function cf5 = cf4.initFromString(s1);
        function cf6 = cf4.initFromString(s2);
        ans.add(cf5.copy());
        ans.add(cf6.copy());
        ComplexFunction max = new ComplexFunction(ans.get(0).copy());
        ComplexFunction min = new ComplexFunction(ans.get(0).copy());
        for(int i=1;i<ans.size();i++) {
            max.max(ans.get(i));
            min.min(ans.get(i));
        }
        ans.add(max);
        ans.add(min);

        return ans;
    }
}