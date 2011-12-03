package silAST.expressions.terms

import silAST.programs.NodeFactory
import silAST.source.SourceLocation
import silAST.symbols.logical.quantification.BoundVariable
import collection.mutable.HashSet
import silAST.domains.DomainFunction
import silAST.expressions.util.{DTermSequence, GTermSequence}


trait DTermFactory extends NodeFactory with GTermFactory{

  /////////////////////////////////////////////////////////////////////////
  def makeBoundVariableTerm(sl : SourceLocation, v : BoundVariable) : BoundVariableTerm =
  {
    require(boundVariables.contains(v))
    addTerm(new BoundVariableTerm(sl,v))
  }

  /////////////////////////////////////////////////////////////////////////
  def makeDDomainFunctionApplicationTerm(sl : SourceLocation, f : DomainFunction, a : DTermSequence) : DDomainFunctionApplicationTerm =
  {
    require(termSequences.contains(a))
    require(domainFunctions(f.name) == f)

    a match{
      case a : GTermSequence => makeGDomainFunctionApplicationTerm(sl,f,a)
      case _ => addTerm(new DDomainFunctionApplicationTermC(sl,f,a))
    }
  }
  /////////////////////////////////////////////////////////////////////////
  protected[silAST] val boundVariables = new HashSet[BoundVariable]
}