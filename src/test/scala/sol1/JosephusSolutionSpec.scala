import org.scalatest.{FlatSpec, ShouldMatchers}
import java.io.ByteArrayOutputStream

/**
  * Created by sambitmishra on 9/29/16.
  */

trait StdoutTesting {
  /**
    * Captures and returns all stdout output. Mainly for println testing.
    * @param f function with no input or return
    * @return the text printed to stdout
    */
  def captureStdout(f: => Unit): String = {
    val stream = new ByteArrayOutputStream()
    Console.withOut(stream)(f)
    stream.toString
  }
}

class JosephusSolutionSpec extends FlatSpec with ShouldMatchers with StdoutTesting {

  import sol1.JosephusSolution._

  // Invalid parameters
  "JoshphusSolution Main App" should "show usage for no-args" in {
    val stdOut = captureStdout{ main(Array())}
    stdOut should
      include("USAGE:  run n k, where n and k are positive integers")
  }
  it should "show usage for insufficient args=1" in {
    val stdOut = captureStdout{ main(Array("2"))}
    stdOut should
      include("USAGE:  run n k, where n and k are positive integers")
  }
  it should "show usage for more than 2 args" in {
    val stdOut = captureStdout{ main(Array("2", "4", "5"))}
    stdOut should
      include("USAGE:  run n k, where n and k are positive integers")
  }
  it should "show usage for args with 0 value" in {
    val stdOut = captureStdout{ main(Array("0", "4"))}
    stdOut should
      include("USAGE:  run n k, where n and k are positive integers")
  }
  it should "show usage for n < 1" in {
    val stdOut = captureStdout{ main(Array("-2", "4"))}
    stdOut should
      include("USAGE:  run n k, where n and k are positive integers")
  }
  it should "show usage for k < 1" in {
    val stdOut = captureStdout{ main(Array("3", "-4"))}
    stdOut should
      include("USAGE:  run n k, where n and k are positive integers")
  }

  "findSafePosition function" should "work for lower bound inputs 1 and 1" in {
    val res = findSafePosition(1, 1)
    res should === (1)
  }
  it should "work for n=100,000,000, k=1" in {
    val res = findSafePosition(100000000, 1)
    res should === (100000000)
  }
  it should "work for n=1000,000, k=2" in {
    val res = findSafePosition(1000000, 2)
    res should === (951425)
  }
  it should "work for n=1000,000, k=100" in {
    val res = findSafePosition(1000000, 100)
    res should === (138899)
  }
  it should "work for n=1, k=100" in {
    val res = findSafePosition(1, 100)
    res should === (1)
  }
  it should "work for n=k even case, n=4, k=4" in {
    val res = findSafePosition(4,4)
    res should === (2)
  }
  it should "work for n=k odd case, n=5, k=5" in {
    val res = findSafePosition(5,5)
    res should === (2)
  }
  it should "return 3 for input 3 and 2" in {
    val res = findSafePosition(3, 2)
    res should === (3)
  }

}
