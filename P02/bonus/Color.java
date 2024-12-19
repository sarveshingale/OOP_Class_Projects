
public enum Color {
    LIME(0X32CD32), PINK(0XFFC0CB), TEAL(0X008080), ORANGE(0XFFA500);
	
	private Color(int rgb) {
		
		this.rgb = rgb;
	}
	
	@Override
	public String toString() {
		// Extracting individual color components for ANSI escape sequence
		int red = (rgb >> 16) & 0xff;
		int green = (rgb >> 8) & 0xff;
		int blue = rgb & 0xff;
		String escapeSequence = String.format("\033[38;2;%d;%d;%dm", red, green, blue);
		return name() + escapeSequence + " (0x" + String.format("%X", rgb) + ")" + "\033[0m";
	}
	
	
	
	private int rgb;
		
}