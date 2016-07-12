
public class Tower {
	
	private int size;
	
	public Tower(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return size;
	}
	
	@Override
	public String toString() {
		String tower = "";
		for(int i = 0; i < size; i++) {
			tower += "";
		}
		return tower;
	}
	
}
