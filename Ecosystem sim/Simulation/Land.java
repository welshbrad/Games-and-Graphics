package Simulation;
import java.util.ArrayList;

@Deprecated
public class Land {

	private int height;
	private int width;

	private char[][] grid;
	
	public Land(int height, int width){
		this.height = height;
		this.width = width;
		grid = new char[height][width];
		resetGrid();
	}
	
	public void resetGrid(){
		for(int i = 0; i < height; ++i){
			for (int j = 0; j < width; ++j){
				grid[i][j] = '.';
			}
		}
	}
	
	public void updateGrid(ArrayList<Organism> organisms){
		for(Organism o : organisms){
			grid[o.getPosY()][o.getPosX()] = o.getIcon();
		}
	}
	
	public void drawGrid(){
		for(int i = 0; i < height; ++i){
			for (int j = 0; j < width; ++j){
				System.out.print(grid[i][j]);				
				System.out.print("   ");
			}
			System.out.println("\n");
		}
		resetGrid();
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
}
