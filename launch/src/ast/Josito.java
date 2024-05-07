package ast;

import java.util.StringJoiner;

import ast.expressions.Expression.Operator_T;

public class Josito {
    private static StringJoiner code = new StringJoiner("\n");
    // Just
    // Other
    // Sintax
    // Interpreter
    // Translator
    // Output
    
    private int indentation;
    private int dynamicLink;    // points to the beginning of its dynamic predecessor's frame (who call him)
    private int defaultSize = 4;
    private int stackPointer;   // points to the last occupied position of the memory 
    private int newPointer;     // points to the last occupied position of the heap

    public void programHeader() {
        code.add("(memory 2000)");
        code.add("(global $SP (mut i32) (i32.const 0)) ;; start of stack");
        code.add("(global $MP (mut i32) (i32.const 0)) ;; mark pointer");
        code.add("(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4");
        code.add("(func $reserveStack (param $size i32)");
        code.add("(result i32)");
        code.add("get_global $MP");
        code.add("get_global $SP");
        code.add("set_global $MP");
        code.add("get_global $SP");
        code.add("get_local $size");
        code.add("i32.add");
        code.add("set_global $SP");
        code.add("get_global $SP");
        code.add("get_global $NP");
        code.add("i32.gt_u");
        code.add("if");
        code.add("i32.const 3");
        code.add("call $exception");
        code.add("end");
        code.add(")");
        loadFunctions(code);
    }

    public void loadFunctions(StringJoiner code) {
        /*
         * Function that loads into the memory stack a variable of size n (for example, a Class or Struct instance)
         */
        code.add("(func $load_size (param $size i32)"); //FIXME Comprobar esta funcion
        code.add("  (local $cur_size i32)");
        code.add("  (local $cur_pos i32)");
        code.add("  local.set $cur_pos ;; Initial position has already been pushed");
        code.add("  local.set $cur_size (i32.const 0))");
        code.add("  (block");
        code.add("    (loop");
        code.add("      (if (i32.ge_s (local.get $cur_size) (local.get $size))");
        code.add("        (then");
        code.add("          (br $end_loop)");
        code.add("        )");
        code.add("      )");
        code.add("");
        code.add("      local.get $cur_pos");
        code.add("      i32.load");
        code.add("");
        code.add("      local.get $cur_pos");
        code.add("      i32.const 4");
        code.add("      i32.add");
        code.add("      local.set $cur_pos");
        code.add("");
        code.add("      local.get $cur_size");
        code.add("      i32.const 4");
        code.add("      i32.add");
        code.add("      local.set $cur_size");
        code.add("");
        code.add("      (br $loop)");
        code.add("    )");
        code.add("  )");
        code.add(")");

        /*
         * Function that store into the memory a variable of size n (for example, a Class or Struct instance), with the values already loaded into the stack
         */
        code.add("(func $store_size (param $size i32)"); //FIXME Comprobar esta funcion
        code.add("  (local $cur_size i32)");
        code.add("  (local $initial_pos i32)");
        code.add("  (local $cur_pos i32)");
        code.add("  (local $aux i32)        ;; Variable to de-stack elements, push the address and re-stack them to then store");
        code.add("  local.set $initial_pos  ;; Initial position has already been pushed");
        
        code.add("  local.set $cur_size (i32.const 0))");
        code.add("  (block");
        code.add("    (loop");
        code.add("      (if (i32.ge_s (local.get $cur_size) (local.get $size))");
        code.add("        (then");
        code.add("          (br $end_loop)");
        code.add("        )");
        code.add("      )");
        code.add("");
        code.add("      local.set $aux");
        code.add("      local.get $cur_pos");
        code.add("      local.get $aux");
        code.add("      i32.store");
        code.add("");
        code.add("      local.get $cur_pos");
        code.add("      i32.const 4");
        code.add("      i32.add");
        code.add("      local.set $cur_pos");
        code.add("");
        code.add("      local.get $cur_size");
        code.add("      i32.const 4");
        code.add("      i32.add");
        code.add("      local.set $cur_size");
        code.add("");
        code.add("      (br $loop)");
        code.add("    )");
        code.add("  )");
        code.add(")");
    }

    public void funcHeader(String name) { // FIXME Falta la posibilidad de argumentos y el tipo de retorno
        append("(func $%s", name);
        indentation++;
    }

    public void funcTail() {
        indentation--;
        append(")");
    }

    public void addArgument() {
        
    }

    public void createConst(int val) { //TODO dani, esto no tiene indentate
        append("i32.const %d", val);
    }

    public void createIdentifier(int delta) { //TODO dani, esto no tiene indentate
        append("i32.const %d", delta);
        append("i32.const %d", dynamicLink); // TODO obtener inicio del marco de id
        append("i32.add");
    }

    public void load() {
        append("i32.load");
    }

    public void load(int init_pos, int size) {
        if (size == defaultSize)
            load();
        else {
            int end_pos = init_pos + size - 4;
            append("i32.const %d", init_pos); //FIXME No se si el orden de apilar es este
            append("i32.const %d", size);
            append("call $load_size");
        }
    }

    public void store() {
        append("i32.store");
    }

    public void store(int init_pos, int size) {

    }

    public void ifInit() {
        append("if");
    }

    public void elseInit() {
        append("else");
    }

    public void endBlock() {
        append("end");
    }

    public void loopInit() {
        append("block");
        append("loop");
    }

    public void eqZero() {
        append("i32.eqz");
    }

    public void conditionalJump(int label) { // conditional-jump to the label
        append("br_if %d", label);
    }

    public void jump(int label) { // inconditional-jump to the label
        append("br %d", label);
    }

    public void printCall() { // inconditional-jump to the label
        append("call $print");
    }

    public void readCall() { // inconditional-jump to the label
        append("call $read");
    }

    // AND, DIV, EQ, FIELD_ACCESS, GREATER, GEQ, LESS, LEQ, MINUS, MOD, MULT, NEQ, NOT, OR, PTR, POW, REFERENCE, SQ_BRACKET, SUB, ADD }

    public void translateOperator(Operator_T operator) {
        switch (operator) {
            case Operator_T.AND:
                break;
            case Operator_T.DIV:
                break;
            case Operator_T.EQ:
                break;
            case Operator_T.FIELD_ACCESS: // Field Access only need to calculate Code_D(left_part) + delta(field) 
                append("i32.add");
                break;
            case Operator_T.GREATER:
                break;
            case Operator_T.GEQ:
                break;
            case Operator_T.LESS:
                break;
            case Operator_T.LEQ:
                break;
            case Operator_T.MINUS:
                break;
            case Operator_T.MOD:
                break;
            case Operator_T.MULT:
                break;
            case Operator_T.NEQ:
                break;
            case Operator_T.NOT:
                break;
            case Operator_T.OR:
                break;
            case Operator_T.PTR:
                break;
            case Operator_T.POW:
                break;
            case Operator_T.REFERENCE:
                break;
            case Operator_T.SQ_BRACKET:
                break;
            case Operator_T.SUB:
                break;
            case Operator_T.ADD:
                append("i32.add");
                break;
        }
    }

    public String toString() {
        return code.toString();
    }
    
    private String indentate(String instruction) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentation; i++)
            sb.append("  ");
        sb.append(instruction);
        return sb.toString();
    }

    private void append(String instruction) {
        code.add(indentate(instruction));
    }

    private void append(String instruction, Object... args) {
        append(String.format(instruction, args));
    }
}
