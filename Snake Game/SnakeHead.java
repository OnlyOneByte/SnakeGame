
public class SnakeHead extends SnakeBody{
	
	private String dir;

	public SnakeHead(int x, int y, String dir) {
		super(x, y);
		this.dir = dir;
	}
	
	public void setDir(String dir){
		this.dir = dir;
	}
	
	public String getDir(){return dir;}

}
