package Utils;

public class LogicCalc {
    String equation;
    public boolean solveEquation(String valueOfEquation) {
        equation = valueOfEquation;
        System.out.println(equation);
        while (!(equation.equals("0") || equation.equals("1"))) {
            if(removeBrackets())continue;
            if(removeNegation())continue;
            if(removeAlternative())continue;
            if(removeConjunction())continue;
            if(removeImplication())continue;
            removeEquivalence();
        }
        if (equation.equals("0")) {
            return false;
        }else if (equation.equals("1")) {
            return true;
        }else{
            return false;
        }

    }

    private boolean removeNegation(){
        String previous = equation;
        equation = equation.replace("~0", "1");
        equation = equation.replace("~1", "0");

        if(equation.equals(previous)){
            return false;
        }else{
            return true;
        }
    }
    private boolean removeBrackets(){
        String previous = equation;
        equation = equation.replace("(0)", "0");
        equation = equation.replace("(1)", "1");
        if(equation.equals(previous)){
            return false;
        }else{
            return true;
        }
    }
    private boolean removeImplication(){
        String previous = equation;
        equation = equation.replace("0>0", "1");
        equation = equation.replace("0>1", "1");
        equation = equation.replace("1>0", "0");
        equation = equation.replace("1>1", "1");
        if(equation.equals(previous)){
            return false;
        }else{
            return true;
        }
    }
    private boolean removeAlternative(){
        String previous = equation;
        equation = equation.replace("0|0", "0");
        equation = equation.replace("0|1", "1");
        equation = equation.replace("1|0", "1");
        equation = equation.replace("1|1", "1");
        if(equation.equals(previous)){
            return false;
        }else{
            return true;
        }
    }
    private boolean removeConjunction(){
        String previous = equation;
        equation = equation.replace("0&0", "0");
        equation = equation.replace("0&1", "0");
        equation = equation.replace("1&0", "0");
        equation = equation.replace("1&1", "1");
        if(equation.equals(previous)){
            return false;
        }else{
            return true;
        }
    }
    private boolean removeEquivalence(){
        String previous = equation;
        equation = equation.replace("0=0", "1");
        equation = equation.replace("0=1", "0");
        equation = equation.replace("1=0", "1");
        equation = equation.replace("1=1", "1");
        if(equation.equals(previous)){
            return false;
        }else{
            return true;
        }
    }
}
