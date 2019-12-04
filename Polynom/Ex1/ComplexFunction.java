package Ex1;
import java.util.Stack;

import static Ex1.Operation.*;
public class ComplexFunction implements complex_function{
    private Operation operation;
    private ComplexFunction left;
    private ComplexFunction right;
    public Polynom result;//change after div operation is written complexFunc

    public ComplexFunction(String op, function f1, function f2)  {
        this.operation=Error;
        for(Operation operation : Operation.values()){
            if(op.equalsIgnoreCase("mul")) {
                this.operation = Times;
                break;
            }
            if(op.equalsIgnoreCase("div")) {
                this.operation = Divid;
                break;
            }
            if(operation.name().equalsIgnoreCase(op)){
                this.operation = operation;
                break;
            }
        }
        left = new ComplexFunction(f1);
        right = new ComplexFunction(f2);
        result = new Polynom();
        switch (operation){
            case Plus:
                if(left!=null)
                    if(left.result != null)
                        result.add(left.result);
                if(right!=null)
                    if(right.result != null)
                        result.add(right.result);
                break;
            case Times:
                if(left!=null)
                    if(left.result != null)
                        result.add(left.result);//if add was multiply it would be zero .
                if(right!=null)
                    if(right.result != null)
                        result.multiply(right.result);
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
                if(f1 != null && f2 == null){
                    if(left.operation==None){
                        result = left.result;
                    }else{
                        throw new IllegalArgumentException("Instructions are not clear Cant Continue!");
                    }
                }else{
                    throw new IllegalArgumentException("Instructions are not clear Cant Continue!");
                }
                break;
            case Error:
                throw new IllegalArgumentException("Operation is undefined!");
        }
    }
    public ComplexFunction(Operation operation, function f1 , function f2){
        ComplexFunction _temp =  new ComplexFunction(operation.name(),f1,f2);
        this.operation = _temp.operation;
        this.left = _temp.left;
        this.right = _temp.right;
        this.result = _temp.result;
    }

    public ComplexFunction(function function) {
        if(function instanceof ComplexFunction){
            this.operation = ((ComplexFunction) function).getOp();
            this.left = new ComplexFunction( ((ComplexFunction) function).left());
            this.right = new ComplexFunction(((ComplexFunction) function).right());
            this.result = ((ComplexFunction) function).result;
        }else if(function instanceof Polynom){
            this.operation = None;
            this.left = null;
            this.right = null;
            this.result = new Polynom((Polynom) function);
        }else if(function instanceof Monom){
            this.operation = None;
            this.left = null;
            this.right = null;
            this.result = new Polynom((Monom) function);
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
    public void plus(function f1)  {
//      if(f1 instanceof ComplexFunction)
//      {
//          //befor need to check if f1 should go and calculate
//          //left function
//          //right function
//          //or just solve it e.g:
//        result.add(((ComplexFunction) f1).result);
//      }
//      else if(f1 instanceof Polynom)
//      {
//        result.add((Polynom)f1);
//      }
//      else if(f1 instanceof  Monom)
//      {
//        result.add((Monom)f1);
//      }
    }

    /** Multiply this complex_function with the f1 complex_function
     *
     * @param f1 the complex_function which will be multiply be this complex_function.
     */
    @Override
    public void mul(function f1){

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
        return this.left;
    }
    /** returns the right side of the complex function - this side might not exists (aka equals null).
     * @return a function representing the left side of this complex funcation
     */
    @Override
    public function right(){
        return this.right;
    }
    /**
     * The complex_function oparation: plus, mul, div, max, min, comp
     * @return op
     */
    @Override
    public Operation getOp(){
        return this.operation;
    }

    @Override
    public double f(double x) {
        double ans = ((Polynom)result).f(x);
        return ans;
    }

    @Override
    public function initFromString(String s) {
        s=s.replaceAll(" ","");
        if(s.contains("(") && s.contains(")") && isBalanced(s)){
            int indexOfOp = s.indexOf('(');
            int indexOfComma = commaFinder(s);
            int indexOfEnd = s.lastIndexOf(')');
            return new ComplexFunction(s.substring(0,indexOfOp),
                    initFromString(s.substring(indexOfOp+1,indexOfComma)),
                    initFromString(s.substring(indexOfComma+1,indexOfEnd)));
        }else{
            try {
                return new ComplexFunction(new Polynom(s));
            }catch (Exception e){e.getMessage();}
        }
        return null;// idk if it would get this far
    }
    public int commaFinder(String s){
        int opening = 0;
        int comma = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i)=='(')
                opening++;
            if(s.charAt(i)==')')
                opening--;
            if(opening==1 && s.charAt(i)==','){
                comma = i;
                break;
            }
        }
        return comma;
    }

    @Override
    public function copy() {

        return null;
    }
    @Override
    public String toString(){//should be recursive but cant
        String ans = "f(x)= "+this.operation.name()+"(";
        if(left == null && right == null){
            ans += result.toString()+")";
        }
        if(left!=null)
            ans+= helpToString(left) + ",";
        if(right!=null)
            ans+= helpToString(right) + ")";
        return ans;
    }
    public String helpToString(ComplexFunction cf){
        String ans ="";
        if(cf!= null && cf.operation != None){
            ans+= cf.operation.name()+"(";
            if(cf.left!=null  && cf.left.operation!=None){
                ans += helpToString(cf.left) + ",";
            }
            if (cf.right!=null && cf.right.operation!= None){
                ans +=helpToString(cf.right) + ")";
            }
            if(cf.left!= null && cf.right!=null ) {
                if (cf.left.operation == None)
                    ans += cf.left.result.toString() + ",";
                if (cf.right.operation == None)
                    ans += cf.right.result.toString() + ")";
            }
        }else{
            ans+=cf.result.toString();
        }
        return ans;
    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ComplexFunction))
            return false;

        ComplexFunction other = (ComplexFunction) obj;

        for (int i = 1; i <=10; i++) {
            if(this.f(i)!=other.f(i))
                return false;
        }
        return true;
    }

    public static void main(String[] args){
        String complex = "max(max(max(min(x^6+5x^2+12,5x^7+16x^2+30),17x^3+25x^2+2),9x^5+12x^3+3.54x^2+3),32x^2+15x+90)";
        System.out.println(isBalanced(complex));
        String brackets = "((((()))))";
        System.out.println(isBalanced(brackets));
    }
}