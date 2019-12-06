package Ex1;

import java.util.Stack;

import static Ex1.Operation.*;
public class ComplexFunction implements complex_function{
    private Operation operation;
    private function left;
    private function right;

    /***
     * Empty ComplexFunction Constructor
     * which is default to be null in its left and right functions and Error Operation
     */
    public ComplexFunction(){
        operation = Error;
        left = null;
        right = null;
    }

    /***
     * most useful constructor builds the ComplexFunction to the fullest
     * at the beginning the String get checked for it's value and compared with different Operation names
     * in addition it would check for the words mul or div and not only 'Times' or 'Divid' from the Operation enum
     * and after that the functions left and right gets to be put in there place according to the order inserted
     *  using break to stop extra search!
     * @param op - String with the operation word to be preformed on the functions left and right
     * @param left - ComplexFunction/Monom/Polynom function to be inserted in the left function of ComplexFunction
     * @param right - ComplexFunction/Monom/Polynom function to be inserted in the right function of ComplexFunction
     */
    public ComplexFunction(String op, function left, function right)  {
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
        this.left = left;
        this.right = right;
    }

    /***
     * Does The Same as the ComplexFunction(String ,function, function) but takes the operation directly
     * @param op - Operation to be preformed on the functions left and right
     * @param left - ComplexFunction/Monom/Polynom function to be inserted in the left function of ComplexFunction
     * @param right - ComplexFunction/Monom/Polynom function to be inserted in the right function of ComplexFunction
     */
    public ComplexFunction(Operation op, function left, function right)  {
        this.operation = op;
        this.left = left;
        this.right = right;
    }

    public ComplexFunction(function function) {
        if(function instanceof ComplexFunction){
            this.operation = ((ComplexFunction) function).getOp();
            this.left = new ComplexFunction( ((ComplexFunction) function).left());
            this.right = new ComplexFunction(((ComplexFunction) function).right());
        }else if(function instanceof Polynom){
            this.operation = None;
            this.left = function;
            this.right = null;
        }else if(function instanceof Monom){
            this.operation = None;
            this.left = function;
            this.right = null;
        }
    }

    /***
     * Using two Stacks to count how many brackets are in the string and if they're in the right order
     * @param s - String to be check for brackets delivered from initToString()
     * @return - returns true if the brackets are in order and in the right amount false if not
     */
    public static boolean isBalanced(String s){
        Stack<Character> leftChar = new Stack<>();
        Stack<Character> rightChar = new Stack<>();
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i)=='(') leftChar.push(s.charAt(i));
            if(s.charAt(i)==')') rightChar.push(s.charAt(i));
            if(leftChar.size() < rightChar.size())
                return false;
        }
        return leftChar.size() == rightChar.size();
    }

    /** Add to this complex_function the f1 complex_function
     * @param f1 the complex_function which will be added to this complex_function.
     */
    @Override
    public void plus(function f1)  {
        if(right!=null){
            ComplexFunction prevComplex = new ComplexFunction(getOp(),left(),right());
            this.left =  prevComplex;
        }
        this.right = new ComplexFunction(f1);
        this.operation = Plus;
    }

    /** Multiply this complex_function with the f1 complex_function
     * @param f1 the complex_function which will be multiply be this complex_function.
     */
    @Override
    public void mul(function f1){
        if(right!=null){
            ComplexFunction prevComplex = new ComplexFunction(getOp(),left(),right());
            this.left =  prevComplex;
        }
        this.right = new ComplexFunction(f1);
        this.operation = Times;

    }
    /** Divides this complex_function with the f1 complex_function
     * @param f1 the complex_function which will be divid this complex_function.
     */
    @Override
    public void div(function f1){
        if(right!=null){
            ComplexFunction prevComplex = new ComplexFunction(getOp(),left(),right());
            this.left =  prevComplex;
        }
        this.right = new ComplexFunction(f1);
        this.operation = Divid;

    }
    /** Computes the maximum over this complex_function and the f1 complex_function
     * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
     */
    @Override
    public void max(function f1){
        if(right!=null){
            ComplexFunction prevComplex = new ComplexFunction(getOp(),left(),right());
            this.left =  prevComplex;
        }
        this.right = new ComplexFunction(f1);
        this.operation = Max;
    }
    /** Computes the minimum over this complex_function and the f1 complex_function
     * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
     */
    @Override
    public void min(function f1){
        if(right!=null){
            ComplexFunction prevComplex = new ComplexFunction(getOp(),left(),right());
            this.left =  prevComplex;
        }
        this.right = new ComplexFunction(f1);
        this.operation = Min;
    }
    /**
     * This method wrap the f1 complex_function with this function: this.f(f1(x))
     * @param f1 complex function
     */
    @Override
    public void comp(function f1){
        if(right!=null){
            ComplexFunction prevComplex = new ComplexFunction(getOp(),left(),right());
            this.left =  prevComplex;
        }
        this.right = new ComplexFunction(f1);
        this.operation = Comp;
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
     * The complex_function operation: plus, mul, div, max, min, comp ,none
     * @return op - returns Operation of the ComplexFunction
     */
    @Override
    public Operation getOp(){
        return this.operation;
    }

    /***
     *
     * @param x - the number to be in the f(x) to compute the final result
     * @return - by the last operation it would decide which action to preform and recursively get the number from the
     * left and right functions according to the ComplexFunction order of build
     * @throws ArithmeticException if there is an attempt to divide a number with zero
     */
    @Override
    public double f(double x) {
        switch (operation) {
            case Plus:
                return left.f(x) + right.f(x);
            case Times:
                return left.f(x) * right.f(x);
            case Divid:
                if(right.f(x) != 0 )
                    return left.f(x) / right.f(x);
                else
                    throw new ArithmeticException("Can't Divide with zero");
            case Min:
                return Math.min(left.f(x),right.f(x));
            case Max:
                return Math.max(left.f(x),right.f(x));
            case Comp:
                return left.f(right.f(x));
            default:
                throw new RuntimeException("Operation is undefined!");
        }
    }

    /***
     * returns ComplexFunction with only Polynom if we get a string with only Polynom string and no brackets
     * and if it contains any brackets it would get the operation and look for the first opening brackets (excluded)
     * to indicate where to start the left function and for the appropriate comma (excluded) to end the left side
     * with the help of the comma we take the string all the way to the last bracket
     * not included to define the right function.
     * @param s - String used to build a ComplexFunction
     * @return - returns ComplexFunction
     */
    @Override
    public function initFromString(String s) {
        s=s.replaceAll(" ","");
        if(s.contains("(") && s.contains(")") && isBalanced(s)){
            int indexOfFirstBracket = s.indexOf('(');
            int indexOfComma = commaFinder(s);
            int indexOfLastBracket = s.lastIndexOf(')');
            return new ComplexFunction(s.substring(0,indexOfFirstBracket),
                    initFromString(s.substring(indexOfFirstBracket+1,indexOfComma)),
                    initFromString(s.substring(indexOfComma+1,indexOfLastBracket)));
        }else{
            try {
                return new ComplexFunction(new Polynom(s));
            }catch (Exception e){e.getMessage();}
        }
        return null;// never would get here anyway but makes no errors because of try/catch
    }

    /***
     * commaFinder is looking for the comma that belongs to the first bracket
     * we count the first bracket and each bracket after the first makes the comma after it invisible and only after
     * it would get the closing bracket it would go back to keep looking for the comma of the first bracket
     * using break to stop extra search and less operations
     * @param s - String to be searched for the comma of the first and last brackets
     * @return - returns index of the right comma in the string
     */
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
    /***
     * creates deep copy of the ComplexFunction
     * @return - returns ComplexFunction with it's fields copied
     */
    @Override
    public function copy() {
        ComplexFunction _temp = new ComplexFunction(operation,left,right);
        return _temp;
    }

    /***
     * Creates a String with each operation and the inner left and right String of the functions according to their
     * toString() function if it a Polynom or Monom Would be using their own toString to create a string of the object.
     * @return String with the Operation open brackets might be another operation or a Polynom or a Monom String
     * followed by a comma and another Operation or Polynom or Monom String and a closing brackets
     */
    @Override
    public String toString(){
        String ans = Operation(operation)+"(" + left.toString();
        if(right!= null)
            ans += "," + right.toString() + ")";
        else
            ans += ")";
        return ans;
    }

    /***
     * Operation returns the string according to its relevant Case
     * Example: if operation = Plus it would return the String "plus"
     * @param operation - takes Operation and converts it into a String
     * @return - String of the equivalent operation
     */
    public String Operation(Operation operation){
        switch (operation){
            case None:
                return "none";
            case Plus:
                return "plus";
            case Times:
                return "mul";
            case Comp:
                return "comp";
            case Max:
                return "max";
            case Divid:
                return "div";
            case Min:
                return "min";
            default:
                return "Error";
        }
    }
}
