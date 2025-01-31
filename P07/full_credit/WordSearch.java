import java.util.Arrays;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordSearch {
    private static final String usage = "usage: java WordSearch [-h] [-v] [#threads] [#puzzles] [puzzleFile]...";

    public WordSearch(List<String> args) {
    
        // Validate the command line arguments
        if(args.size() > 0 && args.get(0).equals("-h")) {
            System.out.println(usage);
            System.exit(0);
        }
        if(args.size() > 0 && args.get(0).equals("-v")) {
             verbose = true;
             args.remove(0);
        } else {
             verbose = false;
        }
        int threads = 0;
        try {
            // Can't assign NUM_THREADS here because javac worries
            // it may not be assigned a value
            threads = Integer.parseInt(args.get(0));
            args.remove(0);
        } catch(Exception e) {
            System.err.println("Error: Invalid number of threads\n" + usage);
            System.exit(-1);
        }
        NUM_THREADS = threads;
        int numPuzzles = 0;
        try {
            // Can't assign NUM_THREADS here because javac worries
            // it may not be assigned a value
            numPuzzles = Integer.parseInt(args.get(0));
            args.remove(0);
        } catch(Exception e) {
            System.err.println("Error: Invalid number of puzzles\n" + usage);
            System.exit(-1);
        }

        // Load the puzzles!
        for(String puzzleName : args) {
            try(BufferedReader br = new BufferedReader(new FileReader(puzzleName))) {
                puzzles.add(new Puzzle(puzzleName, br));
            } catch(IOException e) {
                System.err.println("Unable to load puzzle " + puzzleName);
            }
        }
        
        // Verify all puzzles loaded successfully
        // No error is printed, as a message should be printed for each failed load above
        if(puzzles.size() != args.size()) System.exit(-3);
        
        // Delete or duplicate puzzles to get the right number
        if(numPuzzles < puzzles.size()) puzzles.subList(numPuzzles, puzzles.size()).clear();
        else if (numPuzzles > puzzles.size()) {
            for(int i=puzzles.size(); i<numPuzzles; ++i)
                puzzles.add(puzzles.get(i%puzzles.size()));
        }
        NUM_PUZZLES = puzzles.size();
        
        // -------- All Puzzles Loaded --------
    }
    
    // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    // Modify THIS method to divide up the puzzles among your threads!
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    public void solve() {
        System.err.println ("\n" + NUM_PUZZLES + " puzzles with " 
            + NUM_THREADS + " threads"); // Show the # puzzles and threads
        // Solve all puzzles
		int effectiveNumThreads = 0;
		int split = 0;
		int end = 0;
		int lastStart = 0;
		int rem = 0;
		if(NUM_PUZZLES <= NUM_THREADS) {
			split = 1;
			effectiveNumThreads = NUM_PUZZLES;
		}
		else {
			split = NUM_PUZZLES / NUM_THREADS;
			rem = NUM_PUZZLES % NUM_THREADS; // till i = rem - 1 you do split + 1
			effectiveNumThreads = NUM_THREADS;
		}
		
		Thread[] threads = new Thread[effectiveNumThreads];
		for(int i = 0; i < effectiveNumThreads; i++) {
						
			final int firstPuzzle = lastStart;
			end = (i < rem) ? (lastStart + split + 1) : (lastStart + split);
			final int lastPuzzlePlusOne = end;
			final int threadID = i;
			
			Thread thread = new Thread(() -> solve(threadID, firstPuzzle, lastPuzzlePlusOne));
			threads[i] = thread;
			threads[i].start();
			lastStart = lastPuzzlePlusOne;
		}
		
		for(int i = 0; i < effectiveNumThreads; i++) {
			
				try {
					threads[i].join();
				}
				catch(InterruptedException e) {
					System.err.println(e.getMessage());
				}
			
		}			
			//solve(0, 0, NUM_PUZZLES);
    }

    public void solve(int threadID, int firstPuzzle, int lastPuzzlePlusOne) {
        System.err.println("Thread " + threadID + ": " + firstPuzzle + "-" + (lastPuzzlePlusOne-1));
        for(int i=firstPuzzle; i<lastPuzzlePlusOne; ++i) {
			Puzzle p = null;
			synchronized(lock) {
				p = puzzles.get(i);
			}
            Solver solver = new Solver(p);
            for(String word : p.getWords()) {
                try {
					
					Solution s = solver.solve(word);
					
                    if(s == null) System.err.println("#### Failed to solve " + p.name() + " for '" + word + "'");
                    else {
						synchronized(lock) {
							solutions.add(s);
						}
					}
                } catch (Exception e) {
                    System.err.println("#### Exception solving " + p.name() 
                        + " for " + word + ": " + e.getMessage());
                }
            }
        }
        
        // -------- All Puzzles Solved --------
    }
    public void printSolutions() {
        for(Solution s : solutions)
            System.out.println(s);
    }
    public static void main(String[] args) {
        WordSearch ws = new WordSearch(new LinkedList<>(Arrays.asList(args)));
        ws.solve();
        if(ws.verbose) ws.printSolutions();
    }

    public final int NUM_THREADS;
    public final int NUM_PUZZLES;
    public final boolean verbose;
	
	private static Object lock = new Object();
    private List<Puzzle> puzzles = new ArrayList<>();;
    private SortedSet<Solution> solutions = new TreeSet<>();
}

// time java WordSearch 1 2000 ../puzzle??.txt (To get between 30-45 seconds runtime on my machine)