
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Piece {
    private final boolean color;
    private BufferedImage img;
    
    public Piece(boolean isWhite, String img_file) {
        this.color = isWhite;
        
        try {
            if (this.img == null) {
              this.img = ImageIO.read(getClass().getResource(img_file));
            }
          } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
          }
    }
    
    

    
    public boolean getColor() {
        return color;
    }
    
    public Image getImage() {
        return img;
    }
    
    public void draw(Graphics g, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();
        
        g.drawImage(this.img, x, y, null);
    }
    
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
      ArrayList<Square> c = new ArrayList<Square>();
      int a = 0;
      int d = 0;
      int la = 3;
      int ld = 3;
      if(start.getRow()==0)
        a++;
      if(start.getRow()==7)
        la--;
      if(start.getCol()==0)
        d++;
      if(start.getCol()==7)
        ld--;
      for(int i = a; i < la; i++){
        for(int j = d; j < ld; j++){
          c.add(board[start.getRow()-1+i][start.getCol()+j]);
        }
      }
      return c;
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    public ArrayList<Square> getLegalMoves(Board b, Square start){
      ArrayList<Square> c = new ArrayList<Square>();
      if(b.getTurn() == !start.getColor() && !b.getTurn() == start.getColor()){
        return c;
      }
      for(int i = 0; i < 3; i++){
        for(int j = 0; j < 3; j++){
          if(start.getRow()-1+j >= 0 && start.getRow()-1+j< 8 && start.getCol()-1+j >= 0 && start.getCol()-1+j< 8 
          &&(!b.getSquareArray()[start.getRow()-1+i][start.getCol()-1+j].isOccupied() || b.getSquareArray()[start.getRow()-1+i][start.getCol()-1+j].getOccupyingPiece().getColor() != color)){
              c.add(b.getSquareArray()[start.getRow()-1+i][start.getCol()-1+j]);
          }
        }
      }
      
      
    	return c;
    }
}