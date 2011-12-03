package silAST.expressions.util

import silAST.ASTNode
import silAST.source.SourceLocation
import silAST.expressions.{GExpression, DExpression, PExpression, Expression}

/////////////////////////////////////////////////////////////////
sealed class ExpressionSequence private[silAST](
                                                 val sl: SourceLocation,
                                                 val args: Seq[Expression]
                                                 ) extends ASTNode(sl) with Seq[Expression] {
  override def apply(idx: Int) = args(idx)

  override def iterator = args.iterator

  override val length = args.length
  override val toString = "(" + args.mkString(",") + ")"
  override val subNodes = args
}

/////////////////////////////////////////////////////////////////
sealed trait PExpressionSequence extends ExpressionSequence with Seq[PExpression] {
  override val args: Seq[PExpression] = pArgs
  protected val pArgs: Seq[PExpression]

  override def apply(idx: Int) = args(idx)

  override def iterator = args.iterator
}

/////////////////////////////////////////////////////////////////
private final class PExpressionSequenceC private[silAST](
                                                          sl: SourceLocation,
                                                          args: Seq[PExpression]
                                                          ) extends ExpressionSequence(sl, args) with PExpressionSequence {
  override val pArgs = args
}


/////////////////////////////////////////////////////////////////
sealed trait DExpressionSequence extends ExpressionSequence with Seq[DExpression] {
  override val args: Seq[DExpression] = dArgs
  protected val dArgs: Seq[DExpression]

  override def apply(idx: Int) = args(idx)

  override def iterator = args.iterator
}

/////////////////////////////////////////////////////////////////
private final class DExpressionSequenceC private[silAST](
                                                          sl: SourceLocation,
                                                          args: Seq[DExpression]
                                                          ) extends ExpressionSequence(sl, args) with DExpressionSequence {
  override val dArgs = args
}

/////////////////////////////////////////////////////////////////
final class GExpressionSequence private[silAST](
                                                 sl: SourceLocation,
                                                 override val args: Seq[GExpression]
                                                 ) extends ExpressionSequence(sl, args) with PExpressionSequence with DExpressionSequence with Seq[GExpression] {
  override val pArgs = args
  override val dArgs = args

  override def apply(idx: Int) = args(idx)

  override def iterator = args.iterator
}
