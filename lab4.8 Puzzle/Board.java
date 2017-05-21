import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.lang.*;
import edu.princeton.cs.algs4.StdRandom;

public class Board {
    private int[][] board;
    private int n;
    private int blankRow;
    private int blankCol;

    public Board(int[][] blocks) {// construct a board from an n-by-n array of blocks
      int n = blocks.length;
      for (int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
          board[i][j] = blocks[i][j];
        }
      }
      blankRow = -1;
      blankCol = -1;
      for (int i = 0; i < n; i ++)
        for (int j = 0; j < n; j++)
          if (board[i][j] == 0) {
            blankRow = i;
            blankCol = j;
          }
    }

    public int dimension() {// board dimension n
      return n;
    }
    public int hamming() {
      int cnt = 0;
      int num = 1;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (board[i][j] != num) cnt++;// number of blocks out of place
          num++;
        }
      }
      return cnt;
    }
    public int manhattan() {// sum of Manhattan distances between blocks and goal
      int cnt = 0;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (board[i][j] == 0) continue;
          if (board[i][j] == 1) {
            cnt += Math.abs(0 - i) + Math.abs(0 - j);
          } else if (board[i][j] == 2) {
            cnt += Math.abs(0 - i) + Math.abs(1 - j);
          } else if (board[i][j] == 3) {
            cnt += Math.abs(0 - i) + Math.abs(2 - j);
          } else if (board[i][j] == 4) {
            cnt += Math.abs(1 - i) + Math.abs(0 - j);
          } else if (board[i][j] == 5) {
            cnt += Math.abs(1 - i) + Math.abs(1 - j);
          } else if (board[i][j] == 6) {
            cnt += Math.abs(1 - i) + Math.abs(2 - j);
          } else if (board[i][j] == 7) {
            cnt += Math.abs(2 - i) + Math.abs(0 - j);
          } else if (board[i][j] == 8) {
            cnt += Math.abs(2 - i) + Math.abs(1 - j);
          }
        }
      }
      return cnt;
    }
    public boolean isGoal() {// is this board the goal board?
      int[][] arr = {{1,2,3},{4,5,6},{7,8,}};
      Board goal = new Board(arr);
      return goal.equals(board);
    }
    public Board twin() {// a board that is obtained by exchanging any pair of blocks
      int x1, x2, y1, y2;
      while (true) {
        x1 = StdRandom.uniform(0, 2);
        y1 = StdRandom.uniform(0, 2);
        x2 = StdRandom.uniform(0, 2);
        y2 = StdRandom.uniform(0, 2);
        if (x1 == x2 && y1 == y2) continue;
        else break;
      }
      int tmp;
      tmp = board[x1][y1];
      board[x1][y1] = board[x2][y2];
      board[x2][y2] = tmp;
      return new Board(board);
    }
    public boolean equals(Object y) {// does this board equal y?
      if (y == this)
         return true;
      if (y == null)
         return false;
      if (this.getClass() != y.getClass())
         return false;
      Board that = (Board) y;
      if (this.blankCol != that.blankCol)
         return false;
      if (this.blankRow != that.blankRow)
         return false;
      if (this.n != that.n)
         return false;
      for (int row = 0; row < n; row++)
          for (int col = 0; col < n; col++)
              if (this.board[row][col] != that.board[row][col])
                 return false;
      return true;
    }
    private int[][] copyOf(int[][] a) {
      int[][] res = new int[a.length][];
      for (int row = 0; row < a.length; row++)
        res[row] = a[row].clone();
      return res;
    }
    private void swap(int[][] matirx, int rowA, int colA, int rowB, int colB) {
      int tmp = matirx[rowA][colA];
      matirx[rowA][colA] = matirx[rowB][colB];
      matirx[rowB][colB] = tmp;
    }
    public Iterable<Board> neighbors() {// all neighboring boards
      List<Board> neighbors = new LinkedList<Board>();
      if (blankRow > 0) {
        int[][] north = copyOf(board);
        swap(north, blankRow, blankCol, blankRow - 1, blankCol);
        neighbors.add(new Board(north));
      }
      if (blankRow < n - 1) {
        int[][] south = copyOf(board);
        swap(south, blankRow, blankCol, blankRow + 1, blankCol);
        neighbors.add(new Board(south));
      }
      if (blankCol > 0) {
        int[][] west = copyOf(board);
        swap(west, blankRow, blankCol, blankRow, blankCol - 1);
        neighbors.add(new Board(west));
      }
      if (blankCol < n - 1) {
        int[][] east = copyOf(board);
        swap(east, blankRow, blankCol, blankRow, blankCol + 1);
        neighbors.add(new Board(east));
      }
      return neighbors;
    }
    public String toString() {// string representation of this board (in the output format specified below)
      String out = Integer.toString(dimension());
      for (int i = 0; i < dimension(); i++)
        for (int j = 0; j < dimension(); j++)
          out += " " + board[i][j];
        out += "\n";
      return out;
    }

    public static void main(String[] args) {}
}
