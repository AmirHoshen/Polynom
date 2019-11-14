package myMath;

public class PolynomTest {
	public static void main(String[] args) throws Exception {
		test1();
		test2();
	}
	public static void test1() throws Exception {
		System.out.println("*****************Test1: should not throw exception*****************");
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
		p1.derivative();
		String s2 = p1.derivative().toString();
		System.out.println("Derivative: "+  s2);
		System.out.println("\n***** Building from Strings *****");
		String Goodpoly = "-3x^4-4.435X^3+5.9*x^2+x-2";
		String Complex = "13+23+4x+6x+4X^2-7.61x^10"+Goodpoly;
		Polynom goodEx = new Polynom(Goodpoly);
		Polynom goodExToString = new Polynom(goodEx.toString());
		Polynom complexPoly = new Polynom(Complex);
		System.out.println("String: " + Goodpoly + "\npolynom : " + goodEx.toString() + "\nPolynom from toString: " + goodExToString.toString());
		System.out.println("Complex String: " + Complex + "\ntoString: " + complexPoly.toString());
		System.out.println();
		Polynom p3 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		for(int i=0 ; i<monoms.length; i++) {
			Monom m = new Monom(monoms[i]);
			p1.add(m);
			p3.add(m);
		}
		System.out.println("p1: "+p1.toString());
		Polynom p4 = new Polynom(p1);
		System.out.println("p4 is: "+p4);
		System.out.println("p3: "+p3.toString());
		System.out.println("\n***** Polynom Methods *****");
		System.out.println("\n***equals***");
		System.out.println("p1: "+p1+", p4: "+p4+"\nis equals: should be true\nanswer: "+p1.equals(p4));
		System.out.println("p1: "+p1+", p2: "+p2+"\nis equals: should be false\nanswer: "+p1.equals(p2));
		System.out.println();
		System.out.println("\n***substract***");
		p1.substract(p4);
		System.out.println("p1-p4 should be zero, answer: "+p1.isZero());
		p1.add(p4);
		p1.substract(p2);
		System.out.println("p1-p2 should not be zero, answer: "+p1.isZero());
		System.out.println();
		System.out.println("p1: "+p1.toString()+"\np2: "+p2.toString()+"\np3: "+p3.toString()+"\np4: "+p4.toString());
		System.out.println("\n***f(x)***");
		Polynom[] poly = {p1,p2,p3,p4};
		for(int i=0; i<poly.length;i++) {
			System.out.println("p"+(i+1)+" after f(2.5) is: "+poly[i].f(2.5));
		}
		System.out.println("\n***Root***");
		Monom m3 = new Monom(-2,0);
		p2.add(m3);
		System.out.println("p2 is: "+p2.toString());
		System.out.println("p2 root is: "+p2.root(-7, 0, 0.0001)+"\nwhile root input is(-7,0,0.0001)");
		System.out.println("p4 is: "+p4.toString());
		System.out.println("p4 root is: "+p4.root(7, 1, 0.0001)+"\nwhile root input is(7,1,0.0001)");
		System.out.println("\n***Area***");
		System.out.println("p2 is: "+p2.toString());
		System.out.println("p2 area is: "+p2.area(-3, 0, 0.0001)+"\nwhile area input is(-3,0,0.0001)");
		System.out.println("p4 is: "+p4.toString());
		System.out.println("p4 area is: "+p4.area(-2, 1, 0.0001)+"\nwhile area input is(-2,1,0.0001)");
	}


	public static void test2() throws Exception {
		System.out.println();
		System.out.println("*****************Test2: should throw exception*****************");
		String s = "2.x";
		String s1 = " 50 x^2";
		Polynom p1 = new Polynom(s);
		Polynom p2 = new Polynom(s1);
		System.out.println("p1 after String recieved is: "+p1.toString()+" \tbecause input was: "+s);
		System.out.println("p1 after String recieved is: "+p2.toString()+" \tbecause input was: "+s1);
		String[] monoms = {"1","x^","x^2","6x^2","-0","x+s","$2+1 ", "0.5x^2"};
		String[] monoms1 = {"","","","","","","",""};
		for(int i=0 ; i<monoms.length; i++) {
			Monom m = new Monom(monoms[i]);
			Monom m1 = new Monom(monoms1[i]);
			p1.add(m);
			System.out.println(p1.toString());
			p1.add(m1);
			System.out.println(p1.toString());
		}
		System.out.println(p1.toString());
		System.out.println(p1);
		try {
			System.out.println("\n***Area***");
			System.out.println("p1 is: "+p1.toString());
			System.out.println("p1 area is: "+p1.area(3, 2.5, 0.0001)+"\nwhile area input is(3,2.5,0.0001)");
			System.out.println("p2 is: "+p2.toString());
			System.out.println("p2 area is: "+p2.area(10, 5, 0.0001)+"\nwhile area input is(10,5,0.0001)");
			System.out.println("\n***Root***");
			Monom m3 = new Monom(-2,0);
			p2.add(m3);
			System.out.println("p1 is: "+p1.toString());
			System.out.println("p1 root is: "+p1.root(0.5, 1, 0.0001)+"\nwhile root input is(0.5,1,0.0001)");
			System.out.println("p2 is: "+p2.toString());
			System.out.println("p2 root is: "+p2.root(1, 6, 0.0001)+"\nwhile root input is(1,6,0.0001)");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}