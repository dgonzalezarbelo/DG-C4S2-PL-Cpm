package ast.expressions.operands;

import ast.Josito;
import ast.preamble.Constructor;
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
        this.generateArgumentsPlacement(jose);
        jose.createConst(matchingBind.getSize());   // calc new scope size
        jose.reserveStackCall();                    // calc new limits MP and SP of scope
        jose.setDynamicLink();                      // saves previous MP to return in DL of new scope
        jose.setReferenceConstructor(((Constructor) matchingBind).getType().getSize());               // because its a constructor saves the returning reference object
        jose.callFunction(matchingBind.getWASMId());                    //calling the WASM function with the unique id
        jose.consumeTrash();
        jose.getReturnAddress(matchingBind.getWASMId());                //calling the WASM function with the unique id
        // the reference of the returned value is now at top of the stack, so the generateAddress() is complete for functionCall
        jose.freeStackCall();
    } 

    @Override
    public void generateValue(Josito jose) throws Exception { // Code_E 
        this.generateArgumentsPlacement(jose);
        jose.createConst(matchingBind.getSize());   // calc new scope size
        jose.reserveStackCall();                    // calc new limits MP and SP of scope
        jose.setDynamicLink();                      // saves previous MP to return in DL of new scope
        jose.setReferenceConstructor(((Constructor) matchingBind).getType().getSize());     // because its a constructor saves the returning reference object
        jose.callFunction(matchingBind.getWASMId());                                        //calling the WASM function with the unique id
        // return value (if any) is now at top of the stack, so the generateValue() is complete for functionCall
        jose.freeStackCall();
    }
}