
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * An abstraction of a computing machine that reads instructions and
 * executes them. It has an instruction set, and registers on which
 * calculations are performed.
 * 
 * @author Arthur Nunes-Harwitt
 * @author Danny Waheibi
 */

public class Machine {


    /**
     * Construct a Machine.
     *
     */
    public Machine() {}

    /**
     * Construct a Machine and load instruction from the file name
     * specified.
     *
     * @param filename the String representing the file name
     */
    public Machine(String filename) {
        load(filename);
    }

    private interface Instruction {
        /**
         * Run this instruction on the Machine, using the Machine's
         * registers.
         */
        void execute();

        /**
         * Show the instruction using text so it can be understood
         * by a person.
         * @return a short String describing what this instruction will do
         */
        @Override
        String toString();
    }

    private int maxReg = 0;
    private double[] register;
    private List<Instruction> instrSeq = new ArrayList<Instruction>();

    /**
     * Reset the Machine; this method deletes any program in the
     * machine.
     *
     */
    public void clear() {
        maxReg = 0;
        register = null;
        instrSeq.clear();
    }

    /**
     * Generate a listing of a program on standard output by calling
     * the toString() method, in order, on each instruction in the
     * instruction sequence
     *
     */
    public void displayInstructions() {
        for (Instruction i: instrSeq){
            System.out.println(i);
        }
    }

    /**
     * Execute an instruction sequence by executing in order each
     * instruction.
     * 
     */
    public void execute() {
        register = new double[maxReg+1];
        for (Instruction i: instrSeq){
            i.execute();
        }
    }

    /**
     * Display the contents of all the registers in ascending order.
     * 
     */
    public void probe(){
        System.out.print("Registers:");
        for (double val : register){
            System.out.print(" "+val);
        }
        System.out.println();
    }
    
    /**
     * Add an Add instruction to the instruction sequence, and update
     * the maxReg to be the current maximum.
     *
     * @param it the integer representing the target register
     * @param i1 the integer representing the first source register
     * @param i2 the integer representing the second source register
     * 
     */
    public void addAdd(int it, int i1, int i2){
        instrSeq.add(new Add(it, i1, i2));
        maxReg += 1;
    }

    /**
     * Add a Subtract instruction to the instruction sequence, and update
     * the maxReg to be the current maximum.
     *
     * @param it the integer representing the target register
     * @param i1 the integer representing the first source register
     * @param i2 the integer representing the second source register
     * 
     */
    public void addSub(int it, int i1, int i2){
        instrSeq.add(new Subtract(it, i1, i2));
        maxReg += 1;
    }

    /**
     * Add a Multiply instruction to the instruction sequence, and update
     * the maxReg to be the current maximum.
     *
     * @param it the integer representing the target register
     * @param i1 the integer representing the first source register
     * @param i2 the integer representing the second source register
     * 
     */
    public void addMul(int it, int i1, int i2){
        instrSeq.add(new Multiply(it, i1, i2));
        maxReg += 1;
    }

    /**
     * Add a Divide instruction to the instruction sequence, and update
     * the maxReg to be the current maximum.
     *
     * @param it the integer representing the target register
     * @param i1 the integer representing the first source register
     * @param i2 the integer representing the second source register
     * 
     */
    public void addDiv(int it, int i1, int i2){
        instrSeq.add(new Divide(it, i1, i2));
        maxReg += 1;
    }

    /**
     * Add a Negate instruction to the instruction sequence, and update
     * the maxReg to be the current maximum.
     *
     * @param it the integer representing the target register
     * @param i the integer representing the first source register
     * 
     */
    public void addNeg(int it, int i){
        instrSeq.add(new Negate(it, i));
        maxReg += 1;
    }

    /**
     * Add a SquareRoot instruction to the instruction sequence, and update
     * the maxReg to be the current maximum.
     *
     * @param it the integer representing the target register
     * @param i the integer representing the first source register
     * 
     */
    public void addSqrt(int it, int i){
        instrSeq.add(new SquareRoot(it, i));
        maxReg += 1;
    }

    /**
     * Add a LoadIm instruction to the instruction sequence, and update
     * the maxReg to be the current maximum.
     *
     * @param it the integer representing the target register
     * @param num the double that goes into the target register
     * 
     */
    public void addLdi(int it, double num){
        instrSeq.add(new LoadIm(it, num));
        maxReg += 1;
    }

    /**
     * Add a Print instruction to the instruction sequence, and update
     * the maxReg to be the current maximum.
     *
     * @param i the integer representing the source register
     * 
     */
    public void addPrint(int i){
        instrSeq.add(new Print(i));
    }


    /**
     * Read from the file, parse the text representation of machine
     * instructions, and add the corresponding instruction to the
     * instruction list.
     * @param filename the string representing the name of the file to
     * read in
     *
     */
    public void load(String filename){
        try (FileInputStream fileStr = new FileInputStream(filename)) {
            Scanner in = new Scanner(fileStr);
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] tokens = line.split(",? +");
                switch(tokens[0]){
                case "ADD":
                    addAdd(Integer.parseInt(tokens[1].substring(1)),
                           Integer.parseInt(tokens[2].substring(1)),
                           Integer.parseInt(tokens[3].substring(1)));
                    break;
                case "SUB":
                    addSub(Integer.parseInt(tokens[1].substring(1)),
                           Integer.parseInt(tokens[2].substring(1)),
                           Integer.parseInt(tokens[3].substring(1)));
                    break;
                case "MUL":
                    addMul(Integer.parseInt(tokens[1].substring(1)),
                           Integer.parseInt(tokens[2].substring(1)),
                           Integer.parseInt(tokens[3].substring(1)));
                    break;
                case "DIV":
                    addDiv(Integer.parseInt(tokens[1].substring(1)),
                           Integer.parseInt(tokens[2].substring(1)),
                           Integer.parseInt(tokens[3].substring(1)));
                    break;
                case "NEG":
                    addNeg(Integer.parseInt(tokens[1].substring(1)),
                           Integer.parseInt(tokens[2].substring(1)));
                    break;
                case "SQRT":
                    addSqrt(Integer.parseInt(tokens[1].substring(1)),
                            Integer.parseInt(tokens[2].substring(1)));
                    break;
                case "LDI":
                    addLdi(Integer.parseInt(tokens[1].substring(1)),
                           Double.parseDouble(tokens[2]));
                    break;
                case "PRINT":
                    addPrint(Integer.parseInt(tokens[1].substring(1)));
                    break;
                default:
                    Errors.error("Illegal assembly instruction", null);
                }
            }
        }
        catch( IOException ioe ) {
            System.err.println( "Could not open file " + filename );
        }
    }

    /**
     * The ADD instruction
     */
    private class Add implements Instruction {
        private int it, i1, i2;
        
        /**
         * Construct an Add instruction.
         * @param it the integer representing the target register
         * @param i1 the integer representing the first source register
         * @param i2 the integer representing the second source register
         *
         */
        public Add(int it, int i1, int i2){
            this.it = it;
            this.i1 = i1;
            this.i2= i2;
        }

        /**
         * Run the microsteps for the ADD instruction.
         */
        @Override
        public void execute() {
            register[it] = register[i1] + register[i2];
        }
        
        /**
         * Show the ADD instruction as plain text.
         * @return "ADD"
         */
        @Override
        public String toString() {
            return "ADD R"+it+", R"+i1+", R"+i2;
        }
    }

    private class Subtract implements Instruction {
        private int it, i1, i2;
        /**
         * Construct a Subtract instruction.
         * @param it the integer representing the target register
         * @param i1 the integer representing the first source register
         * @param i2 the integer representing the second source register
         *
         */
        public Subtract(int it, int i1, int i2){
            this.it = it;
            this.i1 = i1;
            this.i2 = i2;
        }
        
        @Override
        public void execute() {
            register[it] = register[i1] - register[i2];
        }
        @Override
        public String toString() {
            return "SUB R"+it+", R"+i1+", R"+i2;
        }
    }

    private class Multiply implements Instruction {
        private int it, i1, i2;
        /**
         * Construct a Multiply instruction.
         * @param it the integer representing the target register
         * @param i1 the integer representing the first source register
         * @param i2 the integer representing the second source register
         *
         */
        public Multiply(int it, int i1, int i2){
            this.it = it;
            this.i1 = i1;
            this.i2 = i2;
        }
        
        @Override
        public void execute() {
            register[it] = register[i1] * register[i2];
        }
        @Override
        public String toString() {
            return "MUL R"+it+", R"+i1+", R"+i2;
        }
    }

    private class Divide implements Instruction {
        private int it, i1, i2;
        /**
         * Construct a Divide instruction.
         * @param it the integer representing the target register
         * @param i1 the integer representing the first source register
         * @param i2 the integer representing the second source register
         *
         */
        public Divide(int it, int i1, int i2){
            this.it = it;
            this.i1 = i1;
            this.i2 = i2;
        }
        
        @Override
        public void execute() {
            register[it] = register[i1] / register[i2];
        }
        @Override
        public String toString() {
            return "DIV R"+it+", R"+i1+", R"+i2;
        }
    }
    
    private class Negate implements Instruction {
        private int it, i;
        /**
         * Construct a Negate instruction.
         * @param it the integer representing the target register
         * @param i the integer representing the source register
         *
         */
        public Negate(int it, int i){
            this.it = it;
            this.i = i;
        }
        
        @Override
        public void execute() {
            register[it] = -(register[i]);
        }
        @Override
        public String toString() {
            return "NEG R"+it+", R"+i;
        }
    }
    
    private class SquareRoot implements Instruction {
        private int it, i;
        /**
         * Construct a SquareRoot instruction.
         * @param it the integer representing the target register
         * @param i the integer representing the source register
         *
         */
        public SquareRoot(int it, int i){
            this.it = it;
            this.i = i;
        }
        @Override
        public void execute() {
            register[it] = Math.sqrt(register[i]);
        }
        @Override
        public String toString() {
            return "SQRT R"+it+", R"+i;
        }
    }
    
    
    private class LoadIm implements Instruction {
        int it;
        double num;
        /**
         * Construct a LoadIm instruction.
         * @param it the integer representing the target register
         * @param num the double that goes into the target register
         *
         */
        public LoadIm(int it, double num) {
            this.it = it;
            this.num = num;
        }
        @Override
        public void execute() {
            register[it] = num;
        }
        @Override
        public String toString() {
            return "LDI R"+it+", "+num;
        }
    }
    
    
    private class Print implements Instruction {
        private int i;
        /**
         * Construct a Print instruction.
         * @param i the integer representing the source register
         *
         */
        public Print(int i) {
            this.i = i;
        }
        @Override
        public void execute() { 
            System.out.println(register[i]);
        }
        @Override
        public String toString() { 
            return "PRINT R"+i; 
        }
    }
    
    
    /**
     * A single command line argument is expected which is a file
     * name.  The Machine constructor is invoked on the file name, and
     * then the code is executed.
     *
     * @param args the String array corresponding to the command line
     * arguments.
     *
     */
    public static void main(String[] args){
        if (args.length == 1) {
            Machine m = new Machine(args[0]);
            m.execute();
        } else {
            System.out.println("Usage: java Machine filename");
        }
    }
    
}
