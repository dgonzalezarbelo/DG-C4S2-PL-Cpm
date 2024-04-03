package lexicon;

import errors.GestionErroresTiny;

%%
%cup
%line
%column
%class AnalizadorLexicoTiny
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErroresTiny errores;
  public String lexeme() {return yytext();}
  public int row() {return yyline+1;}
  public int col() {return yycolumn+1;}
  public void fijaGestionErrores(GestionErroresTiny errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

character  = ([A-Z]|[a-z])
positiveDigit = [1-9]
digit = ({positiveDigit}|0)
integerNumber = (({positiveDigit}{digit}*)|0)
binary = 0b[0-1]+
hexadecimal = 0x([0-9]|[a-f])+
separator = [ \t\r\b\n]
comment = \/\/[^\n]*
multiLineComment = \/\*[^(\*\/)]*
identificator = {character}({character}|{digit})*
integerType = int
sumOperator = \+
substractionOperator = \-
multiplicationOperator = \*
divisionOperator = \/
modulusOperator = %
powerOperator = \^
openParenthesis = \(
closeParenthesis = \)
lessOperator = <
greaterOperator = >
equalOperator = \=\=
lessOrEqualOperator = <\=
greaterOrEqualOperator = >\=
notEqualOperator = \!\=
booleanType = bul
andOperator = and
orOperator = or
notOperator = \!
trueValue = tru
falseValue = fols
comma  = ,
colon = :
semicolon = ;
classType = clas
openBracket = \{
closeBracket = \}
fieldAccessOperator = \.
structType = estrut
openSquareBracket = \[
closeSquareBracket = \]
functionType = func
pointerOperator = \~
referenceOperator = \&
newOperator = niu
typedef = taipdef
define = difain
assignationOperator = :\=
if = if
else = els
switch = suich
case = queis
break = breic
default = difolt
while = guail
for = for
continue = continiu
cin = cein
cout = ceaut
return = return
this = dis
public = public
private = praivet
main = mein

%%
{separator}                     {}
{comment}                       {}
{multiLineComment}              {}
{integerNumber}                 {return ops.integerUnit();}
{integerType}                   {return ops.int_tUnit();}
{binary}                        {return ops.binaryUnit();}
{hexadecimal}                   {return ops.hexUnit();}
{sumOperator}                   {return ops.sum_opUnit();}
{substractionOperator}          {return ops.subs_opUnit();}
{multiplicationOperator}        {return ops.mult_opUnit();}
{divisionOperator}              {return ops.div_opUnit();}
{modulusOperator}               {return ops.mod_opUnit();}
{powerOperator}                 {return ops.pow_opUnit();}
{openParenthesis}               {return ops.parentesis_openUnit();}
{closeParenthesis}              {return ops.parentesis_closeUnit();}
{lessOperator}                  {return ops.less_opUnit();}
{greaterOperator}               {return ops.greater_opUnit();}
{equalOperator}                 {return ops.equal_opUnit();}
{lessOrEqualOperator}           {return ops.less_or_eq_opUnit();}
{greaterOrEqualOperator}        {return ops.greater_or_eqUnit();}
{notEqualOperator}              {return ops.not_equal_opUnit();}
{booleanType}                   {return ops.boolean_tUnit();}
{andOperator}                   {return ops.and_opUnit();}
{orOperator}                    {return ops.or_opUnit();}
{notOperator}                   {return ops.not_opUnit();}
{trueValue}                     {return ops.trueUnit();}
{falseValue}                    {return ops.falseUnit();}
{comma}                         {return ops.commaUnit();}
{colon}                         {return ops.colonUnit();}       
{semicolon}                     {return ops.semicolonUnit();}
{classType}                     {return ops.class_tUnit();}
{structType}                    {return ops.struct_tUnit();}
{fieldAccessOperator}           {return ops.field_accessUnit();}
{openBracket}                   {return ops.bracket_openUnit();}
{closeBracket}                  {return ops.bracket_closeUnit();}
{openSquareBracket}             {return ops.sq_bracket_openUnit();}
{closeSquareBracket}            {return ops.sq_bracket_closeUnit();}
{functionType}                  {return ops.func_tUnit();}
{pointerOperator}               {return ops.pointer_opUnit();}
{referenceOperator}             {return ops.reference_opUnit();}
{newOperator}                   {return ops.new_opUnit();}
{typedef}                       {return ops.typedefUnit();}
{define}                        {return ops.defineUnit();}
{assignationOperator}           {return ops.assignation_opUnit();}
{if}                            {return ops.ifUnit();}
{else}                          {return ops.elseUnit();}
{switch}                        {return ops.switchUnit();}
{case}                          {return ops.caseUnit();}
{break}                         {return ops.breakUnit();}
{default}                       {return ops.defaultUnit();}
{while}                         {return ops.whileUnit();}
{for}                           {return ops.forUnit();}
{continue}                      {return ops.continueUnit();}
{cin}                           {return ops.cinUnit();}
{cout}                          {return ops.coutUnit();}
{return}                        {return ops.returnUnit();}
{this}                          {return ops.thisUnit();}
{public}                        {return ops.privateUnit();}
{private}                       {return ops.privateUnit();}
{main}                          {return ops.mainUnit();}
{identificator}                 {return ops.idUnit();}
[^]                             {ops.error();}
