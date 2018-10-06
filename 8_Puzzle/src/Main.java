/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tabassum
 */
public class Main {

	public static void main(String[] args) {

		// Parameters of the problem
		int[][] arr={{7,2,4},{5,0,6},{8,3,1}};  
                //int goal[][]={{1,2,3},{4,5,6},{7,8,0}};
                
		// Construct a new problem with the provided parameters
		Problem problem = new Problem(new State(arr));
                

		// Perform Graph search to find a goal state
		problem.graph_search();
                
	}
        

}

