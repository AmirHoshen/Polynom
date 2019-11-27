package Ex1;
import java.util.Stack;

import static Ex1.Operation.*;
public class ComplexFunction implements complex_function{
    private Operation operation;
    public Polynom result;

    public ComplexFunction(String op, Polynom p1, Polynom p2) throws Exception {
        operation = Error;
        result = new Polynom(p1);
        for(Operation operation : Operation.values()){
            if(operation.name().equalsIgnoreCase(op)){
                this.operation = operation;
            }
        }
        switch (operation){
            case Plus:
                this.result.add(p2);
                break;
            case Times:
                break;
            case Divid:
                break;
            case Max:
                break;
            case Min:
                break;
            case Comp:
                break;
            case None:
                break;
            case Error:
                throw new IllegalArgumentException("Operation is undefined!");
        }
    }
    public ComplexFunction(Polynom p1, Polynom p2) {
        switch (Operation.None){
            case Plus:
                break;
            case Times:
                break;
            case Divid:
                break;
            case Max:
                break;
            case Min:
                break;
            case Comp:
                break;
            case None:
                break;
            case Error:
                throw new IllegalArgumentException("Operation is undefined!");
        }
    }

    public static boolean isBalanced(String s){
        Stack<Character> leftChar = new Stack<>();
        Stack<Character> rightChar = new Stack<>();
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)=='(') leftChar.push(s.charAt(i));
            if(s.charAt(i)==')') rightChar.push(s.charAt(i));
            if(leftChar.size()<rightChar.size())return false;
        }
        return leftChar.size() == rightChar.size();
    }

    /** Add to this complex_function the f1 complex_function
     *
     * @param f1 the complex_function which will be added to this complex_function.
     */
    @Override
    public void plus(function f1) throws Exception {
      if(f1 instanceof ComplexFunction)
      {
          //befor need to check if f1 should go and calculate
          //left function
          //right function
          //or just solve it e.g:
        result.add(((ComplexFunction) f1).result);
      }
      else if(f1 instanceof Polynom)
      {
        result.add((Polynom)f1);
      }
      else if(f1 instanceof  Monom)
      {
        result.add((Monom)f1);
      }
    }

    /** Multiply this complex_function with the f1 complex_function
     *
     * @param f1 the complex_function which will be multiply be this complex_function.
     */
    @Override
    public void mul(function f1){
        if(f1 instanceof ComplexFunction)
        {
            //befor need to check if f1 should go and calculate
            //left function
            //right function
            //or just solve it e.g:
            result.multiply(((ComplexFunction) f1).result);
        }
        else if(f1 instanceof Polynom)
        {
            result.multiply((Polynom)f1);
        }
        else if(f1 instanceof  Monom)
        {
            result.multiply((Monom)f1);
        }
    }
    /** Divides this complex_function with the f1 complex_function
     *
     * @param f1 the complex_function which will be divid this complex_function.
     */
    @Override
    public void div(function f1){
        if(f1 instanceof ComplexFunction)
        {

        }
        else if(f1 instanceof Polynom)
        {

        }
        else if(f1 instanceof  Monom)
        {

        }
    }
    /** Computes the maximum over this complex_function and the f1 complex_function
     *
     * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
     */
    @Override
    public void max(function f1){
        if(f1 instanceof ComplexFunction)
        {


        }
        else if(f1 instanceof Polynom)
        {

        }
        else if(f1 instanceof  Monom)
        {

        }
    }
    /** Computes the minimum over this complex_function and the f1 complex_function
     *
     * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
     */
    @Override
    public void min(function f1){
        if(f1 instanceof ComplexFunction)
        {


        }
        else if(f1 instanceof Polynom)
        {

        }
        else if(f1 instanceof  Monom)
        {

        }
    }
    /**
     * This method wrap the f1 complex_function with this function: this.f(f1(x))
     * @param f1 complex function
     */
    @Override
    public void comp(function f1){
        if(f1 instanceof ComplexFunction)
        {

        }
        else if(f1 instanceof Polynom)
        {

        }
        else if(f1 instanceof  Monom)
        {

        }
    }
    /** returns the left side of the complex function - this side should always exists (should NOT be null).
     * @return a function representing the left side of this complex funcation
     */
    @Override
    public function left(){
        return null;
    }
    /** returns the right side of the complex function - this side might not exists (aka equals null).
     * @return a function representing the left side of this complex funcation
     */
    @Override
    public function right(){
        return null;
    }
    /**
     * The complex_function oparation: plus, mul, div, max, min, comp
     * @return op
     */
    @Override
    public Operation getOp(){
        switch(operation){
            case Error:
                return Error;
            case None:
                return None;
            case Max:
                return Max;
            case Min:
                return Min;
            case Comp:
                return Comp;
            case Plus:
                return Plus;
            case Divid:
                return Divid;
            case Times:
                return Times;
        }
        return null;
    }

    @Override
    public double f(double x) {
        double ans = ((Polynom)result).f(x);
        return ans;
    }

    @Override
    public function initFromString(String s) {

        return null;
    }

    @Override
    public function copy() {
        return null;
    }

    public static void main(String[] args){
        String complex = "max(max(max(min(x^6+5x^2+12,5x^7+16x^2+30),17x^3+25x^2+2),9x^5+12x^3+3.54x^2+3),32x^2+15x+90)";
        System.out.println(isBalanced(complex));
        String brackets = "((((()))))";
        System.out.println(isBalanced(brackets));
    }
}
