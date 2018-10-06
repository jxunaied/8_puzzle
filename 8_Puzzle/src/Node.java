import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tabassum
 */


// Data structure for search-tree nodes
public class Node implements Comparable<Node> {

	State state; // The state that this node contains
	Node parent; // The parent node of this node at the search-tree
	int action; // The action that was applied to get to this node from
				// the parent node
	int pathCost; // The total path cost incurred on the path from the
					// root node up-to this node at the search-tree
	int depth; // The depth of this node at the search-tree

	// Constructors for the class

	public Node() {
		super();
	}
        

	public Node(State state, Node parent, int action, int pathCost, int depth) {
		super();

		this.state = state;
		this.parent = parent;
		this.action = action;
		this.pathCost = pathCost;
		this.depth = depth;
	}

	// Method to check if this node contains a goal state

	public boolean contains_goal_state() {
            
		return state.goal_test();
	}

	// The evaluation function ( f(n) = g(n) + h(n) ) for this node;
	// check theory class lectures for the concept of evaluation function

	public int evaluation_function() {
		return pathCost + state.heuristic_function();
	}
        
        // Method to get the state that this node contains

	
	// Method to expand this node in the search-tree;
	// returns a list of all the successor nodes that can be generated from this
	// node

	public ArrayList<Node> expand_node() {

		// The list that will contain the successor nodes
		ArrayList<Node> successorNodes = new ArrayList<Node>();

		// A State variable to contain the various successor states from the
		// state that this node contains
		State sucessorState;

		// Apply the various actions successively;
		// if a successor state can be generated (!= null), then construct a
		// node corresponding to that state, and add it to the successor list.
		// It is assumed that each action costs 1

		// Some random costs for the actions
		//int actionCost0 = 100, actionCost1 = 50, actionCost2 = 75,
				//actionCost3 = 200, actionCost4 = 500, actionCost5 = 400;

		sucessorState = state.tile_up();
                //System.out.println(sucessorState);
		if (sucessorState != null)
			successorNodes.add(new Node(sucessorState, this, 0, pathCost
					+ 1, depth + 1));

		sucessorState = state.tile_down();
		if (sucessorState != null)
			successorNodes.add(new Node(sucessorState, this, 1, pathCost
					+ 1, depth + 1));

		sucessorState = state.tile_left();
		if (sucessorState != null)
			successorNodes.add(new Node(sucessorState, this, 2, pathCost
					+ 1, depth + 1));

		sucessorState = state.tile_right();
		if (sucessorState != null)
			successorNodes.add(new Node(sucessorState, this, 3, pathCost
					+ 1, depth + 1));

		       //   System.out.println(successorNodes);
		return successorNodes;
	}

	// Method to print the complete path from the root node of the search-tree
	// to this node;
	// Note the recursive structure of tracing back up-to the root node from
	// this node, and then printing node information while the recursive calls
	// return successively
	public void print_path() {

		if (parent != null)
			parent.print_path();

		System.out.println(this);
	}

	// String representation of this node

	@Override
	public String toString() {
		String s = "";

		s += "Action: ";
		if (action == 0)
			s += "tile up";
		else if (action == 1)
			s += "tile down";
		else if (action == 2)
			s += "tile left";
		else if (action == 3)
			s += "tile right";
		
		else if (action == -1)
			s += "None (Initial state)";
		else
			s += "Invalid action";

		s += "\n";

		s += "State = " + state + "\n";

		s += "Path cost = " + pathCost + "\n";

		s += "Node depth = " + depth + "\n";

		return s;
	}

	// This method is required to compare Node objects;
	// At the compareTo method of any class, you need to specify the criteria on
	// the basis of which you intend to compare objects of that class.

	// At the A* search, we need to compare the nodes at the fringe according to
	// their evaluation function value; hence we will use the
	// evaluation_function() as the basis of comparison.

	// The definition of the compareTo() method requires a long coverage to
	// explain; so just remember that, if you need a lowest to highest ordering,
	// use the following return format:
	// this.comparisonBasis - anotherNode.comparisonBasis;
	// for a higher to lower ordering, the return format should be reversed.

	// Since we need to pick the node with the lowest evaluation function value
	// at any time from the fringe, we'll use the first return format

	@Override
	public int compareTo(Node anotherNode) {
		return evaluation_function() - anotherNode.evaluation_function();
	}
}


