
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tabassum
 */
// Data structure for states of the problem
public class State {
	// Static fields, since they are not required for a particular state
	// representation; rather these fields are used by all the states in the
	// state space graph
	//static int capacity1, capacity2, targetAmount;
        int temp;

	// A state of the problem is represented as the pair (jug1, jug2)
	int[][] board = new int[3][3];
        int[][] newboard = new int[3][3];
         int goal[][]={{1,2,3},{4,5,6},{7,8,0}};

	// Constructors for the class

	public State() {
		super();
	}

	public State(int[][] board) {
		super();
                this.board=board;
                
		
	}
        
	// Method to fix the maximum capacities of the jugs, along with the target
	// amount at any of the jugs

	

	// Methods for the six possible actions from a state;
	// Each one tries to apply a particular action on this state, and returns
	// the corresponding successor state if possible, or null if not

	public State tile_up() {
            //copy board
            for ( int i = 0; i < 3; ++i ) {
                 for ( int j = 0; j < 3;++j ) {
                     newboard[i][j]= board[i][j];
                 }
            }
		for ( int i = 0; i < 3; ++i ) {
                 for ( int j = 0; j < 3;++j ) {
                  if ( newboard[i][j]==0 && i!=0) {
                       
                       //  System.out.println("up");  
                          newboard[i][j]=newboard[i-1][j];
                          newboard[i-1][j]=0;
                          /*
                       for ( int k = 0; k < 3; ++k ) {
                      for ( int l = 0; l < 3;++l ) {
                         System.out.print(newboard[k][l]);
                            }
                            System.out.println();
                         }*/
                          return new State(newboard);
                      
            // Found the correct i,j - print them or return them or whatever
                         }
                  
                         }
                    }
                
		return null;
	}

	public State tile_down() {
            //copy board
            for ( int i = 0; i < 3; ++i ) {
                 for ( int j = 0; j < 3;++j ) {
                     newboard[i][j]= board[i][j];
                 }
            }
		for ( int i = 0; i < 3; ++i ) {
                 for ( int j = 0; j < 3; ++j ) {
                  if ( newboard[i][j] == 0 && i!=2) {  
                         
                          newboard[i][j]=newboard[i+1][j];
                          newboard[i+1][j]=0;
                          
                     /*   System.out.println("down");  
                       for ( int k = 0; k < 3; ++k ) {
                      for ( int l = 0; l < 3;++l ) {
                         System.out.print(newboard[k][l]);
                            }
                            System.out.println();
                         }*/
                          return new State(newboard);
                      
                           //return new State(newboard);
                      
            // Found the correct i,j - print them or return them or whatever
                   } 
                }
              }

		return null;
	}

	public State tile_left() {
            //copy board
            for ( int i = 0; i < 3; ++i ) {
                 for ( int j = 0; j < 3;++j ) {
                     newboard[i][j]= board[i][j];
                 }
            }
		for ( int i = 0; i < 3; ++i ) {
                 for ( int j = 0; j < 3; ++j ) {
                  if ( newboard[i][j] == 0 && j!=0) {
                      
                         
                          newboard[i][j]=newboard[i][j-1];
                          newboard[i][j-1]=0;
                      /*   System.out.println("left");  
                       for ( int k = 0; k < 3; ++k ) {
                      for ( int l = 0; l < 3;++l ) {
                         System.out.print(newboard[k][l]);
                            }
                            System.out.println();
                         }*/
                          return new State(newboard);
                      
                      
            // Found the correct i,j - print them or return them or whatever
                     } 
                  }
               }

		return null;
	}

	public State tile_right() {
            //copy board
            for ( int i = 0; i < 3; ++i ) {
                 for ( int j = 0; j < 3;++j ) {
                     newboard[i][j]= board[i][j];
                 }
            }
		for ( int i = 0; i < 3; ++i ) {
                 for ( int j = 0; j < 3; ++j ) {
                  if ( newboard[i][j] == 0 && j!=2 ) {
                       
                          newboard[i][j]=newboard[i][j+1];
                          newboard[i][j+1]=0;
                          
                       /*  System.out.println("right");  
                       for ( int k = 0; k < 3; ++k ) {
                      for ( int l = 0; l < 3;++l ) {
                         System.out.print(newboard[k][l]);
                            }
                            System.out.println();
                         }*/
                          return new State(newboard);
                      
                      
            // Found the correct i,j - print them or return them or whatever
                       } 
                      }
                }
                    

		return null;
	}

	

	// A heuristic function for the state;
	// this function should return some definite heuristic value for this state;
	// left as empty here, which is also an admissible heuristic;
	// why? (check theory class lectures for the answer)

	public int heuristic_function() {
		return 0;
	}

	// Method to check if this state is a goal state

	public boolean goal_test() {
                
		return board == goal;
	}
        
        //misplaced tiles
        
        public int misplaced()
        {
             int count = 0;
              int expected=0;
           for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {

                int value = board[row][col];
                expected++;
                 if (value != 0 && value != expected) {
                     count++;
                 }
           
        }
      }
     return count; 
   }
        
        
        
        ///misplaced row column
       public int row_column()
        {
             int count = 0;
            // int count2 = 0;
             int expected=0;

        for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {

                int value = board[row][col];
                expected++;

                if (value != 0 && value != expected) {
                    
                    if(Math.abs(row - getRow(board,value))>0)
                        count++;
                   if( Math.abs(col  - getCol(board,value))>0)
                            count++ ;
                }
            }
        }

        return count;
        }
        
        //manhattan distance
        
        public int manhattan()
        {
             int count = 0;
             int expected=0;

        for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {

                int value = board[row][col];
                expected++;

                if (value != 0 && value != expected) {
                    // Manhattan distance is the sum of the absolute values of
                    // the horizontal and the vertical distance
                    count += Math.abs(row
                            - getRow(board,value))
                            + Math.abs(col
                                    - getCol(board,value));
                }
            }
        }

        return count;
            
        }
      
        
        public int getCol(int[][] a, int value) {
        for (int row = 0; row < a.length; row++) {
            for (int col = 0; col < a[row].length; col++) {
                if (a[row][col] == value) {
                    return col;
                }
            }
        }
        return -1;
    }
         public int getRow(int[][] a, int value) {
        for (int row = 0; row < a.length; row++) {
            for (int col = 0; col < a[row].length; col++) {
                if (a[row][col] == value) {
                    return row;
                }
            }
        }
        return -1;
    }   

	// Method to check if this state is equal to an Object obj;
	// Required at Graph Search

	//@Override
	public boolean equals(Object obj) {

		State rhs = (State) obj;

		return board == rhs.board ;
	}

	// Method to generate a hash code for this state;
	// Required at Graph Search

	@Override
	public int hashCode() {
		return Objects.hash((Object[]) board);
	}

	// String representation of this state

	@Override
	public String toString() {
		return "(" + Arrays.deepToString(board) + ")";
	}
}

