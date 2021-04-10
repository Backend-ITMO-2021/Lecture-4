import org.scalatest.funsuite.AnyFunSuite
import ru.ifmo.backend_2021._
import ru.ifmo.backend_2021.expressions._

import scala.util.{Failure, Success, Try}

class ExpressionsTest extends AnyFunSuite {
  test("basicTests") {
    testExpression("10", "10", new Const(10), x => 10);
    testExpression("x", "x", new Variable("x"), x => x);
    testExpression("(x + 2)", "x + 2", new Add(new Variable("x"), new Const(2)), x => x + 2);
    testExpression("(2 - x)", "2 - x", new Subtract(new Const(2), new Variable("x")), x => 2 - x);
    testExpression("(3 * x)", "3 * x", new Multiply(new Const(3), new Variable("x")), x => 3 * x);
    testExpression("(x + x)", "x + x", new Add(new Variable("x"), new Variable("x")), x => x + x);
    testExpression("(x / -2)", "x / -2", new Divide(new Variable("x"), new Const(-2)), x => -x / 2);
    testExpression("(x + x)", "x + x", new Add(new Variable("x"), new Variable("x")), x => x + x);
    testExpression("(2 + x)", "2 + x", new Add(new Const(2), new Variable("x")), x => 2 + x);
    testExpression("(x + 2)", "x + 2", new Add(new Variable("x"), new Const(2)), x => x + 2);
    testExpression("((1 + 2) + 3)", "1 + 2 + 3", new Add(new Add(new Const(1), new Const(2)), new Const(3)), x => 1 + 2 + 3);
    testExpression("(1 + (2 + 3))", "1 + 2 + 3", new Add(new Const(1), new Add(new Const(2), new Const(3))), x => 1 + 2 + 3);
    testExpression("((1 - 2) - 3)", "1 - 2 - 3", new Subtract(new Subtract(new Const(1), new Const(2)), new Const(3)), x => 1 - 2 - 3);
    testExpression("(1 - (2 - 3))", "1 - (2 - 3)", new Subtract(new Const(1), new Subtract(new Const(2), new Const(3))), x => 1 - (2 - 3));
    testExpression("((1 * 2) * 3)", "1 * 2 * 3", new Multiply(new Multiply(new Const(1), new Const(2)), new Const(3)), x => 1 * 2 * 3);
    testExpression("(1 * (2 * 3))", "1 * 2 * 3", new Multiply(new Const(1), new Multiply(new Const(2), new Const(3))), x => 1 * 2 * 3);
    testExpression("((10 / 2) / 3)", "10 / 2 / 3", new Divide(new Divide(new Const(10), new Const(2)), new Const(3)), x => 10 / 2 / 3);
    testExpression("(10 / (3 / 2))", "10 / (3 / 2)", new Divide(new Const(10), new Divide(new Const(3), new Const(2))), x => 10 / (3 / 2));
    testExpression(
      "((x * x) + ((x - 1) / 10))",
      "x * x + (x - 1) / 10",
      new Add(
        new Multiply(new Variable("x"), new Variable("x")),
        new Divide(new Subtract(new Variable("x"), new Const(1)), new Const(10))
      ),
      x => x * x + (x - 1) / 10
    );
    testExpression("(x * -1000000000)", "x * -1000000000", new Multiply(new Variable("x"), new Const(-1_000_000_000)), x => x * -1000000000);
    testExpression("(10 / x)", "10 / x", new Divide(new Const(10), new Variable("x")), x => 10 / x);
    testExpression("(x / x)", "x / x", new Divide(new Variable("x"), new Variable("x")), x => x / x);
  }

  test("equals and hashcodes") {
    val vx = new Variable("x")
    val vy = new Variable("y")
    val vz = new Variable("z")
    val c2 = new Const(2)
    val expressionA = new Divide(new Subtract(vy, vx), new Multiply(c2, vx))
    val expressionB = new Divide(new Subtract(vy, vx), new Multiply(c2, vx))
    val expressionC = new Divide(new Subtract(vy, vx), new Multiply(c2, vz))
    assert(expressionA.equals(expressionB))
    assert(expressionB.equals(expressionA))
    assert(expressionA.hashCode() == expressionB.hashCode())
    assert(!expressionA.equals(expressionC))
    assert(!expressionC.equals(expressionA))
    assert(expressionA.hashCode() != expressionC.hashCode())
  }

  test("generatedTests") {
    val vx = new Variable("x")
    val c1 = new Const(1)
    val c2 = new Const(2)

    testExpression("(2 + 1)", "2 + 1", new Add(c2, c1), (x) => 2 + 1)
    testExpression("(x - 1)", "x - 1", new Subtract(vx, c1), (x) => x - 1)
    testExpression("(1 * 2)", "1 * 2", new Multiply(c1, c2), (x) => 1 * 2)
    testExpression("(x / 1)", "x / 1", new Divide(vx, c1), (x) => x / 1)
    testExpression("(1 + (2 + 1))", "1 + 2 + 1", new Add(c1, new Add(c2, c1)), (x) => 1 + 2 + 1)
    testExpression("(x - (x - 1))", "x - (x - 1)", new Subtract(vx, new Subtract(vx, c1)), (x) => x - (x - 1))
    testExpression("(2 * (x / 1))", "2 * (x / 1)", new Multiply(c2, new Divide(vx, c1)), (x) => 2 * (x / 1))
    testExpression("(2 / (x - 1))", "2 / (x - 1)", new Divide(c2, new Subtract(vx, c1)), (x) => 2 / (x - 1))
    testExpression("((1 * 2) + x)", "1 * 2 + x", new Add(new Multiply(c1, c2), vx), (x) => 1 * 2 + x)
    testExpression("((x - 1) - 2)", "x - 1 - 2", new Subtract(new Subtract(vx, c1), c2), (x) => x - 1 - 2)
    testExpression("((x / 1) * 2)", "x / 1 * 2", new Multiply(new Divide(vx, c1), c2), (x) => x / 1 * 2)
    testExpression("((2 + 1) / 1)", "(2 + 1) / 1", new Divide(new Add(c2, c1), c1), (x) => (2 + 1) / 1)
    testExpression("(1 + (1 + (2 + 1)))", "1 + 1 + 2 + 1", new Add(c1, new Add(c1, new Add(c2, c1))), (x) => 1 + 1 + 2 + 1)
    testExpression("(x - ((1 * 2) + x))", "x - (1 * 2 + x)", new Subtract(vx, new Add(new Multiply(c1, c2), vx)), (x) => x - (1 * 2 + x))
    testExpression("(x * (2 / (x - 1)))", "x * (2 / (x - 1))", new Multiply(vx, new Divide(c2, new Subtract(vx, c1))), (x) => x * (2 / (x - 1)))
    testExpression("(x / (1 + (2 + 1)))", "x / (1 + 2 + 1)", new Divide(vx, new Add(c1, new Add(c2, c1))), (x) => x / (1 + 2 + 1))
    testExpression("((1 * 2) + (2 + 1))", "1 * 2 + 2 + 1", new Add(new Multiply(c1, c2), new Add(c2, c1)), (x) => 1 * 2 + 2 + 1)
    testExpression("((2 + 1) - (2 + 1))", "2 + 1 - (2 + 1)", new Subtract(new Add(c2, c1), new Add(c2, c1)), (x) => 2 + 1 - (2 + 1))
    testExpression("((x - 1) * (x / 1))", "(x - 1) * (x / 1)", new Multiply(new Subtract(vx, c1), new Divide(vx, c1)), (x) => (x - 1) * (x / 1))
    testExpression("((x - 1) / (1 * 2))", "(x - 1) / (1 * 2)", new Divide(new Subtract(vx, c1), new Multiply(c1, c2)), (x) => (x - 1) / (1 * 2))
    testExpression("(((x - 1) - 2) + x)", "x - 1 - 2 + x", new Add(new Subtract(new Subtract(vx, c1), c2), vx), (x) => x - 1 - 2 + x)
    testExpression("(((1 * 2) + x) - 1)", "1 * 2 + x - 1", new Subtract(new Add(new Multiply(c1, c2), vx), c1), (x) => 1 * 2 + x - 1)
    testExpression("(((2 + 1) / 1) * x)", "(2 + 1) / 1 * x", new Multiply(new Divide(new Add(c2, c1), c1), vx), (x) => (2 + 1) / 1 * x)
    testExpression("((2 / (x - 1)) / 2)", "2 / (x - 1) / 2", new Divide(new Divide(c2, new Subtract(vx, c1)), c2), (x) => 2 / (x - 1) / 2)
  }


  def testExpression(full: String, mini: String, actual: Expression, expected: Int => Int) = {
    for (i <- 0 to 10) assertResult(evaluate(expected, i), s"f($i)")(evaluate(actual.evaluate, i))
    checkEqualsAndToString(full, mini, actual)
  }

  protected def checkEqualsAndToString(full: String, mini: String, expression: Expression): Unit = {
    val actualToString = expression.toString
    checkToString("toString", full, actualToString)
    checkToString("toMiniString", mini, expression.toMiniString)
    assert(expression.equals(expression), "Equals to this")
    assert(!expression.equals(null), "Equals to null")
  }

  private def checkToString(method: String, expected: String, actual: String): Unit =
    assertResult(expected, method)(actual)

  private def evaluate(expression: Int => Int, x: Int) =
    Try {
      expression(x)
    } match {
      case Failure(ar) if ar.isInstanceOf[ArithmeticException] => None
      case Success(value) => Some(value)
    }

  test("basicTests parse") {
    testParse("10", new Const(10), x => 10);
    testParse("( (( x)  ))", new Variable("x"), x => x);
    testParse("( ( (( x)  ))+2)  ", new Add(new Variable("x"), new Const(2)), x => x + 2);
    testParse("( 2-( (( x)  )))  ", new Subtract(new Const(2), new Variable("x")), x => 2 - x);
    testParse("( 3*( (( x)  )))  ", new Multiply(new Const(3), new Variable("x")), x => 3 * x);
    testParse("( ( (( x)  ))+( (( x)  )))  ", new Add(new Variable("x"), new Variable("x")), x => x + x);
    testParse(" ( (( x)  ))/-2  ", new Divide(new Variable("x"), new Const(-2)), x => -x / 2);
    testParse("( ( (( x)  ))+( (( x)  )))  ", new Add(new Variable("x"), new Variable("x")), x => x + x);
    testParse("( 2+( (( x)  )))  ", new Add(new Const(2), new Variable("x")), x => 2 + x);
    testParse("( ( (( x)  ))+2)  ", new Add(new Variable("x"), new Const(2)), x => x + 2);
    testParse("( ( 1+2)  +3)  ", new Add(new Add(new Const(1), new Const(2)), new Const(3)), x => 1 + 2 + 3);
    testParse("( 1+( 2+3)  )  ", new Add(new Const(1), new Add(new Const(2), new Const(3))), x => 1 + 2 + 3);
    testParse("( ( 1-2)  -3)  ", new Subtract(new Subtract(new Const(1), new Const(2)), new Const(3)), x => 1 - 2 - 3);
    testParse("( 1-( 2-3)  )  ", new Subtract(new Const(1), new Subtract(new Const(2), new Const(3))), x => 1 - (2 - 3));
    testParse("( ( 1*2)  *3)  ", new Multiply(new Multiply(new Const(1), new Const(2)), new Const(3)), x => 1 * 2 * 3);
    testParse("( 1*( 2*3)  )  ", new Multiply(new Const(1), new Multiply(new Const(2), new Const(3))), x => 1 * 2 * 3);
    testParse("  10/2  /3  ", new Divide(new Divide(new Const(10), new Const(2)), new Const(3)), x => 10 / 2 / 3);
    testParse(" 10/ (3/2)    ", new Divide(new Const(10), new Divide(new Const(3), new Const(2))), x => 10 / (3 / 2));
    testParse("( ( ( (( x)  ))*( (( x)  )))  + ( ( (( x)  ))-1)  /10  )",
      new Add(
        new Multiply(new Variable("x"), new Variable("x")),
        new Divide(new Subtract(new Variable("x"), new Const(1)), new Const(10))
      ),
      x => x * x + (x - 1) / 10
    );
    testParse("( ( (( x)  ))*-1000000000) ", new Multiply(new Variable("x"), new Const(-1_000_000_000)), x => x * -1000000000);
    testParse(" 10/( (( x)  ))  ", new Divide(new Const(10), new Variable("x")), x => 10 / x);
    testParse(" ( (( x)  ))/( (( x)  ))  ", new Divide(new Variable("x"), new Variable("x")), x => x / x);
  }

  def testParse(value: String, actual: Expression, expected: Int => Int): Unit = {
    val parsed = ExpressionParser.parse(value)
    for (i <- 0 to 10) assertResult(evaluate(expected, i), s"f($i)")(evaluate(parsed.evaluate, i))
  }

  test("generatedTests parse") {
    val vx = new Variable("x")
    val c1 = new Const(1)
    val c2 = new Const(2)

    testParse("( 2+1)  ", new Add(c2, c1), (x) => 2 + 1)
    testParse("( ( (( x)  ))-1)  ", new Subtract(vx, c1), (x) => x - 1)
    testParse("( 1*2)  ", new Multiply(c1, c2), (x) => 1 * 2)
    testParse(" ( (( x)  ))/1  ", new Divide(vx, c1), (x) => x / 1)
    testParse("( 1+( 2+1)  )  ", new Add(c1, new Add(c2, c1)), (x) => 1 + 2 + 1)
    testParse("( ( (( x)  ))-( ( (( x)  ))-1)  )  ", new Subtract(vx, new Subtract(vx, c1)), (x) => x - (x - 1))
    testParse("( 2* ( (( x)  ))/1  )  ", new Multiply(c2, new Divide(vx, c1)), (x) => 2 * (x / 1))
    testParse(" 2/( ( (( x)  ))-1)    ", new Divide(c2, new Subtract(vx, c1)), (x) => 2 / (x - 1))
    testParse("( ( 1*2)  +( (( x)  )))  ", new Add(new Multiply(c1, c2), vx), (x) => 1 * 2 + x)
    testParse("( ( ( (( x)  ))-1)  -2)  ", new Subtract(new Subtract(vx, c1), c2), (x) => x - 1 - 2)
    testParse("(  ( (( x)  ))/1  *2)  ", new Multiply(new Divide(vx, c1), c2), (x) => x / 1 * 2)
    testParse(" ( 2+1)  /1  ", new Divide(new Add(c2, c1), c1), (x) => (2 + 1) / 1)
    testParse("( 1+( 1+( 2+1)  )  )  ", new Add(c1, new Add(c1, new Add(c2, c1))), (x) => 1 + 1 + 2 + 1)
    testParse("( ( (( x)  ))-( ( 1*2)  +( (( x)  )))  )  ", new Subtract(vx, new Add(new Multiply(c1, c2), vx)), (x) => x - (1 * 2 + x))
    testParse(" ( (( x)  ))/( 1+( 2+1)  )    ", new Divide(vx, new Add(c1, new Add(c2, c1))), (x) => x / (1 + 2 + 1))
    testParse("( ( 1*2)  +( 2+1)  )  ", new Add(new Multiply(c1, c2), new Add(c2, c1)), (x) => 1 * 2 + 2 + 1)
    testParse("( ( 2+1)  -( 2+1)  )  ", new Subtract(new Add(c2, c1), new Add(c2, c1)), (x) => 2 + 1 - (2 + 1))
    testParse("( ( ( (( x)  ))-1)  * ( (( x)  ))/1  )  ", new Multiply(new Subtract(vx, c1), new Divide(vx, c1)), (x) => (x - 1) * (x / 1))
    testParse(" ( ( (( x)  ))-1)  /( 1*2)    ", new Divide(new Subtract(vx, c1), new Multiply(c1, c2)), (x) => (x - 1) / (1 * 2))
    testParse("( ( ( ( (( x)  ))-1)  -2)  +( (( x)  )))  ", new Add(new Subtract(new Subtract(vx, c1), c2), vx), (x) => x - 1 - 2 + x)
    testParse("( ( ( 1*2)  +( (( x)  )))  -1)  ", new Subtract(new Add(new Multiply(c1, c2), vx), c1), (x) => 1 * 2 + x - 1)
    testParse("(  ( 2+1)  /1  *( (( x)  )))  ", new Multiply(new Divide(new Add(c2, c1), c1), vx), (x) => (2 + 1) / 1 * x)
    testParse("  2/( ( (( x)  ))-1)    /2  ", new Divide(new Divide(c2, new Subtract(vx, c1)), c2), (x) => 2 / (x - 1) / 2)
  }

  test("complex parse tests") {
    testParseComplex("-x", x => -x)
    testParseComplex("(-asbdajsdhaskdahskdashdkjashqqq + (-aaa))", x => -2*x)
  }

  def testParseComplex(value: String, expected: Int => Int): Unit = {
    val parsed = ExpressionParser.parse(value)
    for (i <- 0 to 10) assertResult(evaluate(expected, i), s"f($i)")(evaluate(parsed.evaluate, i))
  }
}