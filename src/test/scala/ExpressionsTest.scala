import org.scalatest.funsuite.AnyFunSuite

class ExpressionsTest extends AnyFunSuite{
 test("basicTests") {
   testExpression("10", "10", ???, _ => 10) // Yet to be implemented
 }


  def testExpression(fullString: String, shortString: String, classes: Nothing, actual: Int => Int) = ???
}
