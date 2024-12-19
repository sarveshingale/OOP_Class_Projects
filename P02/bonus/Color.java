
public enum Color {
    LIME(0X32CD32), PINK(0XFFC0CB), TEAL(0X008080), ORANGE(0XFFA500);
	
	private Color(int rgb) {
		
		this.rgb = rgb;
	}
	
	@Override
	public String toString() {
		return name() + " (0x" + String.format("%x", rgb) + ")";
	}
	
	private int rgb;
		
}