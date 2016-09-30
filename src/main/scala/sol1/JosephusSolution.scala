package sol1

import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}

/**
  * Created by sambitmishra on 9/28/16.
  * TASK:
  * Create an application that takes in 2 parameters: the number of people in the circle (n)
  * and the step rate (k).  For example, if k is the step rate, then you skip k-1 people and
  * eliminate the kth person.  The output of the program should be the place you need to
  * stand in the circle to be the last person left.
  */
object JosephusSolution {

  def main(args: Array[String]) = {
    val res = Try(require((args.length == 2) && (args(0).toInt > 0) && (args(1).toInt > 0)))

    res match {
      case Success(_) =>
        val safePos = findSafePosition(args(0).toInt, args(1).toInt)
        println(s"N:${args(0).toInt}, K:${args(1).toInt}, Safe position is: $safePos")
      case Failure(_) =>
        println(
          """
            | USAGE:  run n k, where n and k are positive integers.
            | Example: run 8 5""".stripMargin)
    }
  }

  /**
    * Finds the safe position given n people and k step rate. In this approach, we rely on a boolean
    * array, initially filled with "true" values. We mark an eliminated position by changing the
    * value to "false" for that position in this array. For start position(which must not be
    * eliminated) we check the value in this array, and keep on skipping the position till we find a
    * valid(true) position. We stop when n-1 persons are eliminated, revealing the last survivor's
    * position.
    * Note that, this will not scale to multi-node as implemented. To scale out, an Akka/Actor based
    * solution might work. TODO: Sambit to take a stab at it.
    * @param n is the number of people.
    * @param k is the step rate. K is counted from the starting position and skips k-1 people.
    * @return the safe position, the first position being 1, not 0.
    */
  def findSafePosition(n: Int, k: Int): Int = {
    val origPos = Array.fill(n)(true)  // we can squeeze out some more juice by using bit array

    /* mimic a circular buffer */
    def nextPos(i: Int): Int = if (i == n - 1) 0 else i + 1

    @tailrec
    def aliveAtOrBeyond(i: Int): Int = if (origPos(i)) i else aliveAtOrBeyond(nextPos(i))

    @tailrec
    def skip(i: Int, k: Int): Int =
      if (k == 0) aliveAtOrBeyond(i) else skip(nextPos(aliveAtOrBeyond(i)), k - 1)

    /**
      * To be threadsafe, mutate within the guarded block
      *
      * @param i position to eliminate
      * @param bodyCount before this kill
      * @return new body after this kill
      */
    def killAt(i: Int, bodyCount: Int): Int = synchronized {
      origPos(i) = false
      bodyCount + 1
    }

    @tailrec
    def eliminateNextGuy(startPos: Int, bodyCount: Int): Int = {
      if (bodyCount == n - 1) aliveAtOrBeyond(startPos) + 1 // last man, +1 for 0-based indexing
      else {
        val nextToDie = skip(startPos, k - 1)     // find him
        val bc = killAt(nextToDie, bodyCount)     // kill him
        eliminateNextGuy(nextPos(nextToDie), bc)  // find the next guy
      }
    }
    // Let the game start from start position 0, with body count 0
    eliminateNextGuy(0, 0)
  }
}
