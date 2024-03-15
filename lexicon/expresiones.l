character  = ([A-Z]|[a-z])
positiveDigit = [1-9]
digit = ({digitoPositivo}|0)
integerNumber = {digitoPositivo}{digito}*
binary = 0b[0-1]+
hexadecimal = 0x([0-9]|[a-f])+
separator = [ \t\r\b\n]
comment = \/\/[^\n]* //FIXME Ojo con este y el siguiente
multiLineComment = \/\*[^(\*\/)]*
identificador = {letra}({letra}|{digito})*
integerType = int
sumOperator = \+
substractionOperator = \-
multiplicationOperator = \*
divisionOperator = \/
powerOperator = \^
openParenthesis = \(
closeParenthesis = \)
lessOperator = <
greaterOperator = >
equalOperator = \=
lessOrEqualOperator = <\=
greaterOrEqualOperator = >\=
notEqualOperator = \!\=
booleanType = bul
andOperator = and
notOperator = \!
trueValue = tru
falseValue = fols
comma  = ,
colon = :
semicolon = ;
classType = clas
openBracket = \{
closeBracket = \}
fieldAccessOperator = .
structType = estrut
openSquareBracket = \[
closeSquareBracket = \]
functionType = func
pointerOperator = ~
newOperator = niu
typedef = taipdef
define = difain
asignationOperator = :\=
if = if
else = els
switch = suich
case = queis
break = breic
default = difolt
while = guail
continue = continiu
cin = cein
cout = ceaut
return = return

%%
{integerNumber}             {return ops.integerNumberUnit();}
{binary}                    {return ops.binaryUnit();}
{hexadecimal}               {return ops.hexadecimalUnit();}
{separator}                 {}
{comment}                   {}
{multiLineComment}          {}
{operadorSuma}              {return ops.unidadSuma();}
{operadorResta}             {return ops.unidadResta();}
{operadorMultiplicacion}    {return ops.unidadMul();}
{operadorDivision}          {return ops.unidadDiv();}