
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tabassum
 */
public class Problem {
	// The problem requires just the initial state to start a search;
	// every other information are encapsulated within the State and the Node
	// classes
	State initialState;
       
	// Constructors for the class

	public Problem() {
		super();
	}

	// Note that, although the Problem class has only one field,
	// while constructing a new Problem object, some other problem information
	// are being passed, which are used to set the capacities of the jugs, and
	// the goal amount

	public Problem(State initialBoard) {
		super();

		this.initialState = initialBoard;

		//State.set_capacities(capacity1, capacity2, goalAmount);
	}

	// Method to search a goal state from the initial state, using the
	// Breadth-First Search (BFS) strategy;
	// This method returns a goal node that the search can find first;
	// otherwise returns null (no solution)

	private Node BFS() {
		// The fringe (list of unexpanded nodes in the search tree)
		Queue<Node> fringe = new LinkedList<Node>();

		// The current node that is to be expanded
		Node currentNode;

		// The closed list (set of expanded nodes); required in graph search to
		// avoid expansions of nodes with repeated states
		HashSet<State> closedList = new HashSet<State>();

		// Add a node containing the initial state to the fringe
		fringe.add(new Node(initialState, null, -1, 0, 0));

		// Search until the fringe becomes empty
		while (!fringe.isEmpty()) {

			// Choose a shallowest node from the fringe (nearest node from the
			// root of the tree);
			// The remove() method of the Queue will remove and return the first
			// object from the queue, which is a shallowest unexpanded node
			currentNode = fringe.remove();

			// Check if this node contains a goal state
			if (currentNode.contains_goal_state())
				return currentNode;

			// The node doesn't contain a goal state; so now we may need to
			// expand it, depending on whether it has been expanded before or
			// not

			// Check whether the state of this node has already been
			// reached before;
			// if yes, then no need to expand it;
			// Graph search is being used here; if Tree search were to be used,
			// the following two lines need to be commented out
			if (closedList.contains(currentNode.state))
				continue;

			// The state of this node is encountered for the first time;
			// so, expand the node and add all the successor nodes to the
			// fringe; also, add the state of this node to the closed list
			// to avoid repeated states in future
			ArrayList<Node> newNodes = currentNode.expand_node();
			fringe.addAll(newNodes);
                      //  System.out.println(fringe);
			closedList.add(currentNode.state);
		}

		return null;
	}

	// Method to search a goal state from the initial state, using the
	// A* search strategy;
	// This method returns an optimal goal node;
	// otherwise returns null (no solution)

	private Node A_star() {
		// The fringe (list of unexpanded nodes in the search tree);
		// note the usage of Priority Queue here, unlike BFS
		Queue<Node> fringe = new PriorityQueue<Node>();
                
		// The current node that is to be expanded
		Node currentNode;

		// The closed list (set of expanded nodes); required in graph search to
		// avoid expansions of nodes with repeated states
		HashSet<State> closedList = new HashSet<State>();

		// Add a node containing the initial state to the fringe
		fringe.add(new Node(initialState, null, -1, 0, 0));
                
		// Search until the fringe becomes empty
		while (!fringe.isEmpty()) {
                    
			// Choose a node from the fringe with the lowest evaluation function
			// value (lowest f(n) value)
			// The poll() method of the Priority Queue will remove and return
			// such a node
                       // System.out.println(fringe);
			currentNode = fringe.poll();
                       //  System.out.println(currentNode);
			// Check if this node contains a goal state
			if (currentNode.contains_goal_state())
				return currentNode;
                        

			// The node doesn't contain a goal state; so now we may need to
			// expand it, depending on whether it has been expanded before or
			// not

			// Check whether the state of this node has already been
			// reached before;
			// if yes, then no need to expand it;
			// Graph search is being used here; if Tree search were to be used,
			// the following two lines need to be commented out
			if (closedList.contains(currentNode.state))
				continue;
                             
			// The state of this node is encountered for the first time;
			// so, expand the node and add all the successor nodes to the
			// fringe; also, add the state of this node to the closed list
			// to avoid repeated states in future
			ArrayList<Node> newNodes = currentNode.expand_node();
                       
			fringe.addAll(newNodes);
                       
                        
			closedList.add(currentNode.state);
                        
                        
		}

		// No solution has been found at the search
		return null;
	}

	// The Graph search function

	public void graph_search() {

		Node goalNode;
               
		// Find a goal node with BFS
		//goalNode = BFS();

		// Find a goal node with A* search
		 goalNode = A_star();
                 
		// Print the complete solution

		if (goalNode == null)
     
			System.out.println("No reachable solution");
		else
			goalNode.print_path();
	}
}

