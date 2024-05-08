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

    public static final int NUM_FUNC_POINTERS_SIZE = 8;
    
    private int functionWASMId = 0; // The id of the functions of the compiling program
    private int indentation;
    private int dynamicLink;    // points to the beginning of its dynamic predecessor's frame (who call him)
    private int defaultSize = 4;
    private int stackPointer;   // points to the last occupied position of the memory 
    private int newPointer;     // points to the last occupied position of the heap

    public void programHeader() {
        code.add("(memory 2000)");
        code.add("(global $SP (mut i32) (i32.const 0))          ;; start of stack");     // points the next free address (SP)
        code.add("(global $MP (mut i32) (i32.const 0))          ;; mark pointer");       // points the first address of the current scope (MP)
        code.add("(global $NP (mut i32) (i32.const 131071996))  ;; heap 2000*64*1024-4");
        code.add("(global $swap (mut i32) (i32.const 0))");
        loadFunctions(code);
    }

    public void loadFunctions(StringJoiner code) {
        /*
         * Function that reserves enough memory for the new scope
         */
        code.add("(func $reserveStack (param $size i32)");  // this argument is the maximunMemoy of the new scope
        code.add("  (result i32)");                         // returning value will be the Mark Pointer (MP) of the calling scope <-- Dynamic Link
        code.add("  get_global $MP");
        code.add("  get_global $SP");
        code.add("  set_global $MP");
        code.add("  get_global $SP");
        code.add("  get_local $size");
        code.add("  i32.add");
        code.add("  set_global $SP");
        code.add("  get_global $SP");
        code.add("  get_global $NP");
        code.add("  i32.gt_u");
        code.add("  if");
        code.add("  i32.const 3");
        code.add("  call $exception");
        code.add("  end");
        code.add(")");
        
        /*
         * Function that restores the memory when exiting the scope
         */
        code.add("(func $freeStack (type $_sig_void)"); // TODO esta hecho por nosotros porque el dado por el profe creo q esta algo mal
        code.add("   get_global $MP");
        code.add("   set_global $SP"); // SP <-- MP (the first next free address (SP) will be the first address of the current scope that are we freeing (MP))
        code.add("   get_global $MP"); // MP points to the current DL that points to the previous MP so we restore it
        code.add("   i32.load");
        code.add("   set_global $MP");
        code.add(")");

        /*
        * Copy n
        */
        code.add("(func $copyn (type $_sig_i32i32i32) ;; copy $n i32 slots from $src to $dest");
        code.add("(param $src i32)");           // origin
        code.add("(param $dest i32)");          // destination
        code.add("(param $n i32)");             // n elements
        code.add("block");
        code.add("    loop");
        code.add("    local.get $n");
        code.add("    i32.eqz");
        code.add("    br_if 1");
        code.add("    local.get $n");
        code.add("    i32.const 1");
        code.add("    i32.sub");
        code.add("    local.set $n");
        code.add("    local.get $dest");
        code.add("    local.get $src");
        code.add("    i32.load");
        code.add("    i32.store");
        code.add("    local.get $dest");
        code.add("    i32.const 4");
        code.add("    i32.add");
        code.add("    local.set $dest");
        code.add("    local.get $src");
        code.add("    i32.const 4");
        code.add("    i32.add");
        code.add("    local.set $src");
        code.add("    br 0");
        code.add("    end");
        code.add("end");
        code.add(")");

        /*
         * Power function (base^exp)
         */
        code.add("(module"); // TODO solo hay un module en todo el codigo, no puede ser ese
        code.add("    (func $exponentiation (param $base i32) (param $exponent i32) (param $modulus i32) (result i32)");
        code.add("        (local $result i32)");
        code.add("        (local $base_temp i32)");
        code.add("        (local $exponent_temp i32)");
        code.add("");
        code.add("        (set_local $result i32.const 1)                        ;; Result is initialized to 1");
        code.add("        (set_local $base_temp (i32.rem_s (get_local $base) (get_local $modulus)))  ;; base_temp = base % modulus");
        code.add("        (set_local $exponent_temp (get_local $exponent))      ;; exponent_temp = exponent");
        code.add("");
        code.add("        (block");
        code.add("        (loop");
        code.add("            (br_if  ;; Exit loop if exponent_temp is 0");
        code.add("            (i32.eqz (get_local $exponent_temp))");
        code.add("            )");
        code.add("");
        code.add("            (if");
        code.add("            (result i32)");
        code.add("            (i32.eqz (i32.rem_s (get_local $exponent_temp) (i32.const 2)))  ;; If exponent_temp is even");
        code.add("            (then");
        code.add("                (set_local $base_temp");
        code.add("                (i32.rem_s");
        code.add("                    (i32.mul (get_local $base_temp) (get_local $base_temp))");
        code.add("                    (get_local $modulus)");
        code.add("                )");
        code.add("                )");
        code.add("            )");
        code.add("            (else");
        code.add("                (set_local $result");
        code.add("                (i32.rem_s");
        code.add("                    (i32.mul (get_local $result) (get_local $base_temp))");
        code.add("                    (get_local $modulus)");
        code.add("                )");
        code.add("                )");
        code.add("                (set_local $exponent_temp");
        code.add("                (i32.div_s (get_local $exponent_temp) (i32.const 2))");
        code.add("                )");
        code.add("                (br  ;; Salta al inicio del bucle");
        code.add("                0");
        code.add("                )");
        code.add("            )");
        code.add("            )");
        code.add("            (set_local $exponent_temp");
        code.add("            (i32.div_s (get_local $exponent_temp) (i32.const 2))");
        code.add("            )");
        code.add("            (br  ;; Salta al inicio del bucle");
        code.add("            0");
        code.add("            )");
        code.add("        )");
        code.add("        )");
        code.add("        (get_local $result)");
        code.add("    )");
        code.add("");
        code.add("    (export \"exponentiation\" (func $exponentiation))");
        code.add("    )");

    }

    public void funcHeader(Integer WASMId) { // FIXME Falta la posibilidad de argumentos y el tipo de retorno
        append("(func $%d", WASMId);
        indentation++;
    }

    public void funcResult() { // FIXME Falta la posibilidad de argumentos y el tipo de retorno
        append("(result i32)");
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

    public void reserveStackCall() {
        code.add("call $reserveStack");
    }

    public void freeStackCall() {
        code.add("call $freeStack");
    }

    public void getLocalDirUsingMP(int delta) { // Get the local direction of a identifier using MP
        append("i32.const %d", delta);
        append("global.get $MP");
        append("i32.add");
    }

    public void getLocalDirUsingRef(int delta) { // Get the local direction of a identifier using reference
        append("global.get $MP"); 
        append("i32.const 4"); // MP + 4 its the reference value
        append("i32.add");
        append("i32.load");
        append("i32.const %d", delta);
        append("i32.add");
    }

    public void setDynamicLink(){
        append("global.set $swap");
        append("global.get $MP");
        append("global.get $swap");
        append("i32.store");
    }

    public void setReference(int value) {
        append("global.get $MP");
        append("i32.const 4");
        append("i32.add");
        append("i32.const %d", value);
        append("i32.store");
    }

    public void callFunction(int WASMId) {
        append("call $%d", WASMId);
    }

    public void load() { // TODO quiza este solo hace load si tenemos el getLocalDirUsingMP
        append("global.get $MP");
        append("i32.add");
        append("i32.load");
    }

    public void store() { // TODO arreglarlo
        append("i32.const %d");
        append("call $store_size");
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

    public void conditionalJump(int label) { // Conditional_jump to the label
        append("br_if %d", label);
    }

    public void jump(int label) { // Inconditional_jump to the label
        append("br %d", label);
    }

    public void printCall() { // Inconditional_jump to the label
        append("call $print");
    }

    public void readCall() { // Inconditional_jump to the label
        append("call $read");
    }

    public void exponentiation() {
        append("call $exponentiation");
    }

    public void translateOperator(Operator_T operator) {
        switch (operator) {
            case AND:
                append("i32.and");
                break;
            case DIV:
                append("i32.div_s");
                break;
            case EQ:
                append("i32.eq_s");
                break;
            case GREATER:
                append("i32.gt_s");
                break;
            case GEQ:
                append("i32.ge_s");
                break;
            case LESS:
                append("i32.lt_s");
                break;
            case LEQ:
                append("i32.le_s");
                break;
            case MOD:
                append("i32.rem_s");
                break;
            case MINUS: // Take into account that there is no break
                append("i32.const -1");
            case MULT:
                append("i32.mul_s");
                break;
            case NEQ:
                append("i32.ne_s");
                break;
            case NOT:
                append("i32.eqz");
                break;
            case OR:
                append("i32.or");
                break;
            case PTR:
                break;
            case POW:
                exponentiation();
                break;
            case REFERENCE:
                break;
            case SUB:
                append("i32.sub_s");
                break;
            case SQ_BRACKET: // Take into account that there is no break
                append("i32.mul");
            case FIELD_ACCESS: // Field Access only need to calculate Code_D(left_part) + delta(field) 
            case ADD:
                append("i32.add_s");
                break;
        }
    }

    public String toString() {
        return code.toString();
    }

    public int getAndIncrementId() {
        int ret = this.functionWASMId;
        functionWASMId++;
        return ret;
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
