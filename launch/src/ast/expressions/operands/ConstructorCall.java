package ast.expressions.operands;

import ast.Josito;
import ast.preamble.Argument;
import ast.preamble.Constructor;
import ast.types.interfaces.Array_Type;
import ast.types.interfaces.Type;
import exceptions.InvalidTypeException;

public class ConstructorCall extends FunctionCall { 
    
    public ConstructorCall(FunctionCall f, int row) {
        super(f.funcname, f.args, row);
    }

    @Override
    public void checkType() throws Exception {
        super.checkType();
        if (!matchingBind.getClass().equals(Constructor.class))
            throw new InvalidTypeException("Constructor call expected but function call was written");
    }

    @Override
    public void generateAddress(Josito jose) throws Exception {
        jose.createConst(matchingBind.getSize());   // calc new scope size
        jose.reserveStackCall();                    // calc new limits MP and SP of scope
        jose.setDynamicLink();                      // saves previous MP to return in DL of new scope
        jose.setReferenceConstructor(((Constructor) matchingBind).getType().getSize());               // because its a constructor saves the returning reference object
        int i = 0;
        for(Argument declared_arg : matchingBind.getArgumentsList()) {
            Type left_t = declared_arg.getType();
            switch (left_t.getKind()) {
                case INT:
                case BOOL:
                case POINTER:
                    if (!declared_arg.isReference()) {
                        jose.getLocalDirUsingMP(declared_arg.getOffset());  // Code_D of the argument
                        args.get(i).generateValue(jose);                    // Code_E of the parameter
                        jose.store();
                    }
                    else {
                        jose.getLocalDirUsingMP(declared_arg.getOffset());  // Code_D of the argument
                        args.get(i).generateAddress(jose);                  // Code_D of the parameter
                        jose.store();
                    }
                    break;
                case ARRAY:
                    Array_Type cast = (Array_Type)left_t;
                    if (!cast.isDynamic()) {
                        args.get(i).generateValue(jose);                    // Code_E of the parameter
                        jose.getLocalDirUsingMP(declared_arg.getOffset());  // Code_D of the argument
                        jose.createConst(left_t.getSize());                 // N size to copy
                        jose.copy_n();
                    }
                    else {
                        jose.generateDynamicArrayArgument(declared_arg, args.get(i));
                    }
                    break;
                case CLASS:
                case STRUCT:
                    if (!declared_arg.isReference()) {
                        args.get(i).generateValue(jose);                    // Code_E of the parameter
                        jose.getLocalDirUsingMP(declared_arg.getOffset());  // Code_D of the argument
                        jose.createConst(left_t.getSize());                 // N size to copy
                        jose.copy_n();
                    }
                    else {
                        jose.getLocalDirUsingMP(declared_arg.getOffset());  // Code_D of the argument
                        args.get(i).generateAddress(jose);                  // Code_D of the parameter
                        jose.store();
                    }
                    break;
                case CONST:
                    // A declared type left (in the function definition) for an argument can not be a constant
                default:
                    break;
            }
            i++;
        }
        jose.callFunction(matchingBind.getWASMId());                    //calling the WASM function with the unique id
        jose.consumeTrash();
        jose.getReturnAddress(matchingBind.getWASMId());                    //calling the WASM function with the unique id
        // the reference of the returned value is now at top of the stack, so the generateAddress() is complete for functionCall
        jose.freeStackCall(); // FIXME esto podría petar porque el return value está en la pila y estamos llamando a otra función, pero pensamos que no
    } 

    @Override
    public void generateValue(Josito jose) throws Exception { // Code_E 
        jose.createConst(matchingBind.getSize());   // calc new scope size
        jose.reserveStackCall();                    // calc new limits MP and SP of scope
        jose.setDynamicLink();                      // saves previous MP to return in DL of new scope
        jose.setReferenceConstructor(((Constructor) matchingBind).getType().getSize());               // because its a constructor saves the returning reference object
        int i = 0;
        for(Argument declared_arg : matchingBind.getArgumentsList()) {
            Type left_t = declared_arg.getType();
            switch (left_t.getKind()) {
                case INT:
                case BOOL:
                case POINTER:
                    if (!declared_arg.isReference()) {
                        jose.getLocalDirUsingMP(declared_arg.getOffset());  // Code_D of the argument
                        args.get(i).generateValue(jose);                    // Code_E of the parameter
                        jose.store();
                    }
                    else {
                        jose.getLocalDirUsingMP(declared_arg.getOffset());  // Code_D of the argument
                        args.get(i).generateAddress(jose);                  // Code_D of the parameter
                        jose.store();
                    }
                    break;
                case ARRAY:
                    Array_Type cast = (Array_Type)left_t;
                    if (!cast.isDynamic()) {
                        args.get(i).generateValue(jose);                    // Code_E of the parameter
                        jose.getLocalDirUsingMP(declared_arg.getOffset());  // Code_D of the argument
                        jose.createConst(left_t.getSize());                 // N size to copy
                        jose.copy_n();
                    }
                    else {
                        jose.generateDynamicArrayArgument(declared_arg, args.get(i));
                    }
                    break;
                case CLASS:
                case STRUCT:
                    if (!declared_arg.isReference()) {
                        args.get(i).generateValue(jose);                    // Code_E of the parameter
                        jose.getLocalDirUsingMP(declared_arg.getOffset());  // Code_D of the argument
                        jose.createConst(left_t.getSize());                 // N size to copy
                        jose.copy_n();
                    }
                    else {
                        jose.getLocalDirUsingMP(declared_arg.getOffset());  // Code_D of the argument
                        args.get(i).generateAddress(jose);                  // Code_D of the parameter
                        jose.store();
                    }
                    break;
                case CONST:
                    // A declared type left (in the function definition) for an argument can not be a constant
                default:
                    break;
            }
            i++;
        }
        jose.callFunction(matchingBind.getWASMId());                    //calling the WASM function with the unique id
        // return value (if any) is now at top of the stack, so the generateValue() is complete for functionCall
        jose.freeStackCall(); // FIXME esto podría petar porque el return value está en la pila y estamos llamando a otra función, pero pensamos que no
    }
}