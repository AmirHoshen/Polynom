package myMath;
import java.util.Comparator;
/**
 * This class represents a simple "<Monom" of shape a*x^b, where a is a real
 * number(double) and b is an integer (summed a none negative), see:
 * https://en.wikipedia.org/wiki/Monomial The class implements function and
 * support simple operations as: construction, value at x, derivative, add and
 * multiply.
 * source:
 * <https://www.vogella.com/tutorials/JavaRegularExpressions/article.html>
 * https://regexr.com/
 * @author Amir Hoshen & Peleg Zoborovsky
 */
public class Monom implements function {

	public static final Monom ZERO = new Monom(0, 0);
	public static final Monom MINUS1 = new Monom(-1, 0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();

	public static Comparator<Monom> getComp() {
		return _Comp;
	}

	/**
	 * method checks if <Monom's are equals as an expression 
	 * could be by a deviation of epsilon defined in this class and this will return true
	 * @param m <Monom to compare with
	 * @return true if they are equals otherwise false
	 */
	public boolean equals(Object a) {
		if(a instanceof Monom) {
			Monom a1 = (Monom)a;
			double epsDiffrence;
			if(a1._coefficient==this._coefficient)return true;
			else if(a1._coefficient>=this._coefficient) {
				epsDiffrence = a1._coefficient - this._coefficient;
			}else {
				 epsDiffrence = this._coefficient - a1._coefficient;
			}
			if(epsDiffrence<=EPSILON)return true;
		}
		return false;
	}
	
	/**
	 * gets the <Monom coefficient
	 * @return <Monom coefficient
	 */
	public double get_coefficient() {
		return this._coefficient;
	}
	
	/**
	 * gets the <Monom exponent
	 * @return <Monom exponent
	 */
	public int get_power() {
		return this._power;
	}

	/**
	 * initializing a new <Monom given 2 parameters a and b
	 * if illegal input have been inserted exception will appear during program runtime
	 * @param a is the <Monom coefficient
	 * @param b is the <Monom exponent
	 */
	public Monom(double a, int b) {//default constructor
		this.set_coefficient(a);
		try{this.set_power(b);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * copy constructor generating a new <Monom
	 * @param ot
	 */
	public Monom(Monom ot) {
		this.set_coefficient(ot._coefficient);
		try{this.set_power(ot._power);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * method will eventually generate a <Monom type object if and only if the String received is legal
	 * regular expression defined as a*x^b in first if where a is a real number and b is an Integer
	 * @param s is a String the method <Monom requested to build
	 */
	public Monom(String str) throws Exception {//string constructor
		str = str.replaceAll("X", "x");
		if(str.matches("(?=.+)([+-]?[0-9]*[.]?[0-9]*(?:\\*?x?(?:\\^[0-9]+)?)?)*"))
		{
			str = str.replaceAll("\\*", "");
			this.set_coefficient(getCoef(str));
			try {this.set_power(getPow(str));}
			catch(Exception e) {System.out.println(e.getMessage());}
		}
		else throw new Exception("Monom must be from shape a*x^b while b must be positive");
	}
	
	/**
	 *this function of type y=f(x), where both y and x are real numbers.
	 *@param x this is the value of x
	 *@return the value of this function on axis x
	 */
	@Override
	public double f(double x) {
		return( Math.pow(x, this._power)*this._coefficient);
	}
	
	/** 
	 * Performing this <Monom differentiation
	 * @return  the derivative <Monom of this object
	 */
	public Monom derivative() {
		if (this.get_power() == 0) {
			return getNewZeroMonom();
		}
		return new Monom(this.get_coefficient() * this.get_power(), this.get_power() - 1);
	}
	
	/**
	 * checks if the <Monom coefficient is 0
	 * @return true if it is otherwise false
	 */
	public boolean isZero() {
		return this.get_coefficient() == 0;
	}

	/**
	 * adding one <Monom to another <Monom only if they have the same power
	 * otherwise throws exception
	 * @param m <Monom type whom this method adds
	 */
	public void add(Monom m) {
		if (this._coefficient == 0 || m._coefficient == 0) {
			this._coefficient += m._coefficient;
		} else if (m.get_power() == this._power) {
			this._coefficient = m._coefficient + this._coefficient;
		} else {
			throw new IllegalArgumentException("ERR power of both monoms are different, "
					+ "given monoms power is: " + m.get_power() + " & " + this._power);
		}
	}
	
	/**
	 * Subtract m from this monom
	 * @param m the monom I subtract from this monom
	 * @throws RuntimeException if the power of my monom isn't the same as m power
	 * @return new Monom m subtract from my monom 
	 */
	public Monom substract(Monom m){
		if(m.get_power()==this.get_power()) {
			this._coefficient -=m.get_coefficient();
			return new Monom(this.get_coefficient(),this.get_power() );
		}
		else if(m.get_coefficient()==0) return new Monom(this.get_coefficient(),this.get_power() );
		else if(this.isZero()) return new Monom(m._coefficient,m.get_power() );
		else
			throw new IllegalArgumentException( "the power must be equals or the monom must be zero");
	}
	
	/**
	 * multiplying one <Monom with another one:
	 * <Monoms coefficient multiplied and exponent added from one to the other
	 * @param d is the <Monom type object to multiply
	 */
	public void multiply(Monom d) {
		if (this._coefficient == 0 || d._coefficient == 0) {
			this._coefficient = 0;
		} else {
			this._coefficient *= d._coefficient;
			this._power += d._power;
		}
	}
	
	/**
	 * this method return a String who's represent the <Monom object 
	 */
	public String toString() {
		String ans = "";
		if (_coefficient == 0)
			return ans = "0";
		if (_power == 0) {
			return ans = Double.toString((_coefficient));
		} else if (_power == 1) {
			return ans = Double.toString((_coefficient)) + "x";
		} else {
			return ans += (Double.toString((_coefficient))) + "*x^" + (Integer.toString(this._power));
		}
	}
	

	// * Private Methods and Data **

	/**
	 * class variables for the <Monom coefficient and exponent
	 */
	private double _coefficient;
	private int _power;
	
	/**
	 * 	help function that get string and sets the coefficient
	 * @param str
	 * @return this <Monom coefficient
	 */
	private double getCoef(String str) {
		if(str.startsWith("x")) return 1;
		else if(str.startsWith("-x")) return -1;
		else if(!str.contains("x")) return Double.parseDouble(str);
		else return Double.parseDouble(str.split("x")[0]);
	}

	/**
	 * help function that get string and sets the power
	 * @param str
	 * @return this <Monom power
	 * @throws Exception 
	 */
	private int getPow(String str) throws Exception {
		if(!str.contains("x")) return 0;
		else if(!str.contains("^")) return 1;
		else return Integer.parseInt(str.split("\\^")[1]);
	}
	
	/**
	 * generating a new Zero <Monom 
	 * @return this new generated <Monom
	 */
	private static Monom getNewZeroMonom() {
		return new Monom(ZERO);
	}
	
	/**
	 * setting this <Monom coefficient
	 * @param a is a real number for the<Monom coefficient
	 */
	private void set_coefficient(double a) {
		this._coefficient = a;
	}
	
	/**
	 * setting this <Monom exponent
	 * @param p is an Integer for the <Monom power throws exception if negative
	 */
	private void set_power(int p) {
		if (p < 0) {
			throw new RuntimeException("ERR the power of Monom should not be negative, got: " + p);
		}
		this._power = p;
	}
}