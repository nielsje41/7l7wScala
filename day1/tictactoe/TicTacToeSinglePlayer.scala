package day1.tictactoe

import scala.util.Random

/**
  * Created by Niels Bokmans on 21-3-2016.
  */
class TicTacToeSinglePlayer {
  val positions = List[TicTacToeTile](new TicTacToeTile(1), new TicTacToeTile(2), new TicTacToeTile(3),
    new TicTacToeTile(4), new TicTacToeTile(5), new TicTacToeTile(6), new TicTacToeTile(7),
    new TicTacToeTile(8), new TicTacToeTile(9))
  val winningCombinations = Array.ofDim[Int](8, 3)
  val combo1 = winningCombinations.apply(0) //pos 9, 5, 1
  combo1.update(0, 9)
  combo1.update(1, 5)
  combo1.update(2, 1)
  val combo2 = winningCombinations.apply(1) //pos 7, 5, 3
  combo2.update(0, 7)
  combo2.update(1, 5)
  combo2.update(2, 3)
  val combo3 = winningCombinations.apply(2) //pos 7, 8, 9
  combo3.update(0, 7)
  combo3.update(1, 8)
  combo3.update(2, 9)
  val combo4 = winningCombinations.apply(3) //pos 4, 5, 6
  combo4.update(0, 4)
  combo4.update(1, 5)
  combo4.update(2, 6)
  val combo5 = winningCombinations.apply(4) //pos 1, 2, 3
  combo5.update(0, 1)
  combo5.update(1, 2)
  combo5.update(2, 3)
  val combo6 = winningCombinations.apply(5) //pos 7, 4, 1
  combo6.update(0, 7)
  combo6.update(1, 4)
  combo6.update(2, 1)
  val combo7 = winningCombinations.apply(6) //pos 8, 5, 2
  combo7.update(0, 8)
  combo7.update(1, 5)
  combo7.update(2, 2)
  val combo8 = winningCombinations.apply(7) //pos 9, 6, 3
  combo8.update(0, 9)
  combo8.update(1, 6)
  combo8.update(2, 3)

  def processInput(position: Int): Boolean = {
    val tile = getTileAt(position)
    if (tile.mark != TicTacToeMarks.Empty) return false
    tile.mark = TicTacToeMarks.Circle
    if (!gameDone() && hasWinner == -1) selectRandom()
    true
  }

  def winner: String = {
    if (hasWinner == -1) "Het is gelijkspel!" else if (hasWinner == 1) "Speler 1 heeft gewonnen!" else "Speler 2 heeft gewonnen!"
  }

  def hasWinner: Int = {
    for (row <- winningCombinations) {
      val tile1: TicTacToeTile = getTileAt(row(0))
      val tile2: TicTacToeTile = getTileAt(row(1))
      val tile3: TicTacToeTile = getTileAt(row(2))
      if (tile1.mark == TicTacToeMarks.Circle && tile2.mark == TicTacToeMarks.Circle && tile3.mark == TicTacToeMarks.Circle) {
        return 1
      }
      else if (tile1.mark == TicTacToeMarks.Cross && tile2.mark == TicTacToeMarks.Cross && tile3.mark == TicTacToeMarks.Cross) {
        return 2
      }
    }
    -1
  }

  def getTileAt(position: Int): TicTacToeTile = {
    positions.filter(_.myPosition == position).head
  }

  def selectRandom() = {
    Random.shuffle(positions).find(_.mark == TicTacToeMarks.Empty).get.mark = TicTacToeMarks.Cross
  }

  def print() = {
    println(positions.head.toString + " " + positions(1).toString + " " + positions(2).toString)
    println(positions(3).toString + " " + positions(4).toString + " " + positions(5).toString)
    println(positions(6).toString + " " + positions(7).toString + " " + positions(8).toString)
  }

  def gameDone(): Boolean = {
    hasWinner != -1 || positions.count(_.mark == TicTacToeMarks.Empty) == 0
  }
}
