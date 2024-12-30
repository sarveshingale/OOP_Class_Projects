public class Solution implements Comparable<Solution> { 
    public Solution(String name, String word, int x, int y, Direction direction) {
        this.name = name;
        this.word = word;
        this.x = x;
        this.y = y;;
        this.direction = direction;
    }
    @Override
    public String toString() {
        return String.format("In %s: %s found at (%d,%d,%s)", name, word, x, y, direction);
    }
	
	@Override public int compareTo(Solution solution) {
		int result = this.name.compareTo(solution.name);
		if(result == 0) {
			result = this.word.compareTo(solution.word);
		}
		return result;
	}
	
    private String name;
    private String word;
    private int x;
    private int y;
    private Direction direction;
}
