package Simulation;
import java.awt.Color;
import java.util.ArrayList;


public class Organism {

	protected float ageYears;
	protected boolean fertile;
	protected int posX;
	protected int posY;
	protected int width = 10, height = 10;
	
	
	protected int landWidth;
	protected int landHeight;
	
	static protected int idCount = 0;
	protected int id = -1;
	
	protected ArrayList<Organism> organismArrayList;
	protected static ArrayList<Integer> deleteArrayList;
	
	
	public Organism(){
		deleteArrayList = new ArrayList<Integer>();
		this.ageYears = 0;
		this.fertile = false;
		id = idCount;
		idCount++;
	}
	
	public Organism(int posX, int posY){
		this();
		this.posX = posX;
		this.posY = posY;
	}
	
	public void update(){
		ageYears += 0.01;
	}
	
	/**
	 * Destroys an Organism object by id
	 * @param id
	 */
	protected void killByID(int id){
		if(!deleteArrayList.contains(id))
			deleteArrayList.add(id);
	}
	

	//Getters and Setters 
	
	public int getID(){
		return id;
	}
	
	public void setOrganismReference(ArrayList<Organism> organisms){
		organismArrayList = organisms;
	}
	
	public void setLandWidth(int width){
		landWidth = width;
	}
	
	public void setLandHeight(int height){
		landHeight = height;
	}
	
	public float getAgeYears() {
		return ageYears;
	}

	public void setAgeYears(int ageYears) {
		this.ageYears = ageYears;
	}

	public boolean isFertile() {
		return fertile;
	}

	public void setFertile(boolean fertile) {
		this.fertile = fertile;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public char getIcon(){
		return 'O';
	}

	public ArrayList<Integer> getDeletedIDs() {
		return deleteArrayList;
	}

	public Color getColor(){
		return Color.BLACK;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
	
}
