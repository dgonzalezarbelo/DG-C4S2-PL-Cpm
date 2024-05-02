package ast.types;

public class Pointer_Type extends Envelope_Type {
    
    public Pointer_Type(Type type, int row) {
        super(type, row);
    }

    public String toString() {return inner_type.toString() + "~";}

    @Override
    public boolean equals(Type other) {
        if (other == null)
            return false;
        else if(other.getClass().isAssignableFrom(Envelope_Type.class))
            return this.getInnerType().equals(((Envelope_Type) other).getInnerType());
        else
            return false;
    }
}

/* No borreis el comentario
    int~ a = new int;
    int a[4] = new int[4];
    int~~ a = new int~;
    int~ a = new int[4];
    int~~ a = new int[4];

    int ~b = new int[4];
    int~~ a = b;
    int~ a = int[4];
    
    int~~ a = new (int[4])~;
*/

/*  Pointer examples: 

    int~ a[4] = niu int~[4];
    int~~ a = niu int~;
    int a[1][4] = niu int[1][4];
    
    (int[4])~ a = new int[4];
    
    

*/

/*
puntero a un puntero de arrays de enteros
    int (*a)[4];
    int* a [4]

    int** arrayPtrPtr = new int*[arraySize];

    
 */

