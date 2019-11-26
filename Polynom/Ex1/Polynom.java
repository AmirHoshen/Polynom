package Ex1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{

	/**
	 * this class ArrayList <Polynom 
	 */
	private ArrayList<Monom> poly;
	/***
	 * @return this class <Polynom
	 */
	public ArrayList<Monom> getPoly() {
		return poly;
	}
	/***
	 * Initializing class <Ploynom with a given <Polynom parameter
	 * @param poly is a given <Polynom 
	 */
	public void setPoly(ArrayList<Monom> poly) {
		this.poly = poly;
	}
	/**
	 * Creating a zero (empty <Polynom) using array list
	 */
	public Polynom() {
		poly = new ArrayList<>(0);
	}
	
	public Polynom(Polynom p) {//copy constractor
		poly = new ArrayList<Monom>();
		Iterator<Monom> it = p.iteretor();
		while(it.hasNext()) {
			Monom a = new Monom(it.next());
			add(a);
		}
	}
	
	public Polynom(String str) throws Exception {//string constractor
		str = str.replaceAll("X", "x");
		if(str.matches("(?=.+)([+-]?[0-9]*[.]?[0-9]*(?:\\*?x?(?:\\^[0-9]+)?)?)*"))
		{
			this.poly = new ArrayList<Monom>(0);
			str = str.replaceAll("\\-", "+-");
			str = str.replaceAll("\\*", "");
			if(str.charAt(0)=='+') {
				str=str.substring(1);
			}
			for(String m :str.split("\\+")) poly.add(new Monom(m));
		}
		else System.err.println("insert unvaild polynom");
		Monom_Comperator mc = new Monom_Comperator();
		poly.sort(mc);	

	}



	/***
	 * method will calculate a given double x set on
	 * this <Polynom x variable
	 * @param x type double for calculating the value of <Polynom
	 * @return <Polynom value after setting x
	 */
	@Override
	public double f(double x) {
		Iterator<Monom> iter = poly.iterator();
		double ans = 0;
		while(iter.hasNext()) {
			Monom run = iter.next();
			ans += run.f(x);
		}

		return ans;
	}

	/***
	 * adding a <Polynom to this.polynom
	 * and removing 0's caused from subtract e.g(-x+x) 
	 *@param p1 is the <Polynom that will be added to this.polynom
	 */
	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> iter = p1.iteretor();
		while(iter.hasNext()) {
			Monom run = iter.next();
			add(run);
		}
		zeroCorrection();
	}

	/***
	 * adding a <Monom to this.polynom, sort the <Polynom
	 * and remove 0's from it if exist
	 * @param m1 is a <Monom whom will be added to this.polynom
	 */
	@Override
	public void add(Monom m1) {
		Iterator<Monom> iter = iteretor();
		while(iter.hasNext() && !poly.isEmpty()) {
			Monom run = iter.next();
			if(run.get_power()==m1.get_power()) {
				run.add(new Monom(m1));
				return;
			}
		}
		poly.add(new Monom(m1));
		Monom_Comperator mc = new Monom_Comperator();
		this.poly.sort(mc);
		zeroCorrection();
	}

	/***
	 * subtracting p1 from this.polynom
	 * and removing 0's from the resulted <Polynom
	 * @param p1 is a given <Polynom
	 */
	@Override
	public void substract(Polynom_able p1) {
		Iterator<Monom> iter = p1.iteretor();
		while(iter.hasNext()) {
			Monom run = iter.next();
			this.add(new Monom(0-run.get_coefficient(),run.get_power()));
		}
		zeroCorrection();
	}

	/**
	 * multiplying this polynom with p1 polynom using iterator on all polynom variables
	 * using tmp1 tmp2 & tmp3 Monom for calculation for not changing this polynom monom's variables
	 * answer will be put at this polynom after calculation.
	 * /*<Polynom <ans is Initialized with 0's first after the first while's loop
	 * filling <ans with the result multiplication
	 * last while loop ensure all 0's will be removed from <Polynom <ans 
	 * @param p1 is a polynom we need to multiply with this polynom
	 */
	@Override
	public void multiply(Polynom_able p1) {
		Polynom ans = new Polynom();
		Iterator<Monom> iter_p1 = p1.iteretor();

		while (iter_p1.hasNext()) {
			Monom tmp1 = new Monom(iter_p1.next());
			Iterator<Monom> poly_iter = this.iteretor();
			while (poly_iter.hasNext()) {
				Monom tmp2= new Monom (tmp1);
				Monom tmp3 = new Monom (poly_iter.next());
				tmp2.multiply(tmp3);
				if (tmp2.get_coefficient()==0)
					poly_iter.remove();
				ans.add(tmp2);
			}
		}

		this.poly = ans.poly; 
		Iterator<Monom> poly_iter2 = this.iteretor();

		while (poly_iter2.hasNext()) {
			Monom n1 = new Monom(poly_iter2.next());
			if (n1.get_coefficient()==0) {
				poly_iter2.remove();
			}
		}
	}

	public boolean equals(Polynom_able p1) {
		if(p1==null) {
			throw new IllegalArgumentException("No argument, cannot calculate");
		}else {
			double x = Math.random()*100;
			if(f(x)==p1.f(x)) {
				Iterator<Monom> iter1 = poly.iterator();
				Iterator<Monom> iter2 = p1.iteretor();
				while(iter1.hasNext() && iter2.hasNext()) {
					Monom run1 = iter1.next();
					Monom run2 = iter2.next();
					if(run1.get_coefficient()!= run2.get_coefficient() 
							&& run1.get_power() !=run2.get_power()) return false;
				}
				if(!iter1.hasNext() && !iter2.hasNext()) return true;
			}else {
				return false;
			}
		}
		return false;
	}

	/***
	 * removing every 0 <Monom from the <Polynom 
	 * using <Monom type iterator to run over the <Polynom
	 */
	public void zeroCorrection() {
		Iterator<Monom> iter = iteretor();
		while(iter.hasNext()) {
			Monom run = iter.next();
			if(run.get_coefficient()==0) {
				iter.remove();
			}
		}

		Monom_Comperator mc = new Monom_Comperator();
		this.poly.sort(mc);
	}

	/**
	 * method is looking for zero's in polynom using iterator to move over each monom in this polynom
	 * if monom coefficient not equals zero return false 
	 * other was return true
	 */
	@Override
	public boolean isZero() {
		if(this.poly != null) {
			Iterator<Monom> iter = poly.iterator();
			while(iter.hasNext()) {
				Monom run = iter.next();
				if(run.get_coefficient()!=0) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Compute a value xTag (x0<=xTag<=x1) for with |f(xTag)| <eps
	 * assuming (f(x0)*f(x1)<=0, else should throws runtimeException 
	 * computes f(xTag) such that:
	 * 	(i) x0<=xTag<=x1 && 
	 * 	(ii) |f(xTag)|<eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps>0 (positive) representing the epsilon range the solution should be within.
	 * @return an approximated value (root) for this (cont.) function 
	 */
	@Override
	public double root(double x0, double x1, double eps) {// Algorithm for Finding Root
		if(f(x0) * f(x1) > 0) {
			throw new IllegalArgumentException("Wrong Values");
		}else {
			double xTag = 0;
			int runs = 50;
			for(int i = 1 ; i< runs ; i++){
				xTag = (x1 - x0)/ (this.f(x1)-this.f(x0)) * this.f(x1);
				if(Math.abs(xTag) < eps)
					return x1;
				x0 = x1;
				x1= x1 - xTag;
			}
			return xTag;
		}
	}

	/**
	 * copy constructor copying this polynom into p
	 */
	@Override
	public Polynom_able copy() {

		Polynom_able p = new Polynom();
		Iterator<Monom> iter = poly.iterator();
		while(iter.hasNext()) {
			Monom run = iter.next();
			p.add(run);
		}
		return p;
	}

	/**
	 * method will result this polynom derivative into _tmp
	 */
	@Override
	public Polynom_able derivative() {
		Polynom_able _temp = new Polynom();
		Iterator<Monom> iter = iteretor();
		while(iter.hasNext()) {
			Monom monom = iter.next();
			_temp.add(monom.derivative());
		}
		return _temp;
	}

	/**
	 * This function computes Riemann's Integral over this Polynom starting from x0,
	 * till x1 using epsilon(eps) size steps, We used:
	 * https://en.wikipedia.org/wiki/Riemann_integral
	 * 
	 * @param x0 the first point of the domain
	 * @param x1 the second point of the domain
	 * @param eps a very small number
	 * @return the approximated area above the x-axis below this Polynom and between
	 *         the [x0,x1] range.
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		double ans = 0;
		double i = 0;
		if(x0<x1) {
			for (i = x0; i < x1; i = i + eps) {
				double y = i + (eps / 2);
				if (f(y) > 0)
					ans = ans + (f(y) * eps);
			}
		}else {
			for (i = x1; i < x0; i = i + eps) {
				double y = i + (eps / 2);
				if (f(y) > 0)
					ans = ans + (f(y) * eps);
			}
		}
		return ans;
	}

	/**
	 * iterator type monom for each monom in polynom
	 */
	@Override
	public Iterator<Monom> iteretor() {
		Iterator<Monom> iter = poly.iterator();
		return iter;
	}

	/**
	 * string the result polynom using decimal format 
	 * print coefficient number  precision with max 2 numbers after decimal point
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.####");

		String Polinom = "";
		for( Monom monom : this.poly) {
			if(monom.get_coefficient() > 0) {
				if(monom.get_power() == 0) {
					Polinom += "+" + df.format(monom.get_coefficient());
				}else if(monom.get_power()==1) {
					Polinom += "+" + df.format(monom.get_coefficient()) + "x";
				}else {
					Polinom += "+" + df.format(monom.get_coefficient())+"x^" + Integer.toString(monom.get_power());
				}
			}else if(monom.get_coefficient() < 0) {
				if(monom.get_power() == 0) {
					Polinom += "-" + df.format(-1*monom.get_coefficient());
				}else if(monom.get_power()==1) {
					Polinom += "-" + df.format(-1*monom.get_coefficient()) + "x";
				}else {
					Polinom += "-" + df.format(-1*monom.get_coefficient())+"x^" + Integer.toString(monom.get_power());
				}
			}
		}
		return Polinom;
	}

	@Override
	public function initFromString(String s) {
		return null;
	}

	/**
	 * this method is overridden in monom class
	 * for multiplying each monom in polynom 
	 */
	public void multiply(Monom m1) {

	}

}
