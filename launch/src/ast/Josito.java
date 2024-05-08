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
        code.add("  local.get $initial_pos");
        code.add("  local.get $size");
        code.add("  i32.add");
        code.add("  i32.const 4");
        code.add("  i32.sub");
        code.add("  local.set $cur_pos");
        code.add("  local.set $cur_size (i32.const 0))");
        code.add("  (block");
        code.add("    (loop");
        code.add("      (if (i32.lt (local.get $cur_pos) (local.get $initial_pos))");
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
        code.add("      i32.sub");
        code.add("      local.set $cur_pos");
        code.add("");
        code.add("      (br $loop)");
        code.add("    )");
        code.add("  )");
        code.add(")");

        /*
         * Power function (base^exp)
         */
        code.add("(module");
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

    public void load(int size) {
        append("i32.const %d", size);
        append("call $load_size");
    }

    public void store(int size) {
        append("i32.const %d", size);
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
            case SQ_BRACKET:
                // TODO
                break;
            case SUB:
                append("i32.sub_s");
                break;
            case FIELD_ACCESS: // Field Access only need to calculate Code_D(left_part) + delta(field) 
            case ADD:
                append("i32.add_s");
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
