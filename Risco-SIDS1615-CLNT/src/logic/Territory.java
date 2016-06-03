package logic;

import java.awt.Polygon;
import java.util.ArrayList;

public class Territory extends Polygon implements java.io.Serializable{
	private int id;
	private int owner;
	private String name;
	private int xName,yName,xTroops,yTroops;
	private int army;
	
	public enum Continent {NAMERICA,SAMERICA,AFRICA,EUROPE,ASIA,OCEANIA};
	private Continent continent;
	
	private int[] xCoords;
	private int[] yCoords;
	private int points;
	
	
	private ArrayList<Integer> neighbours;
	
	public Territory(int id, Continent continent, String name,int xName, int yName, int xTroops, int yTroops,
			int[] xCoords,int[] yCoords, int points ){
		
		super(xCoords, yCoords, points);
		
		this.id = id;
		this.owner = 0;
		this.continent = continent;
		this.name=name;
		this.xName = xName;
		this.yName = yName;
		this.xTroops = xTroops;
		this.yTroops = yTroops;
		this.xCoords = xCoords;
		this.yCoords = yCoords;
		this.points = points;
		
		//Defaults
		this.army = 0;
		this.neighbours = new ArrayList<Integer>();
		
	}
	
	public void setNeighbour(int neighbor){
		this.neighbours.add(neighbor);
	}
	
	public ArrayList<Integer> getNeighbours(){
		
		return this.neighbours;
	}
	/*
	public int[] getAttackNeighbours(){
		
		int [] value;
		
		int total = this.neighbours.size();
		value = new int[total];
		
		for(int i = 0; i< this.neighbours.size(); ++i){
			value [i] = this.neighbours.get(i).id;
		}
		
		
		return value;
	}
	
	public void click(){
		this.clicked = !clicked;
	}
	
	public void setAsTarget(){
		this.target = true;
	}
	
	public boolean isTarget(){
		return this.target;
	}
	
	public boolean isClicked(){
		return this.clicked;
	}
	
	
	public void resetClickTarger(){
		this.clicked = false;
		this.target = false;
	}
	*/
	public int getId(){
		return this.id;
	}
	
	public int getXName(){
		return this.xName;
	}
	
	public int getYName(){
		return this.yName;
	}
	
	public int getXTroops(){
		return this.xTroops;
	}
	
	public int getYTroops(){
		return this.yTroops;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getArmy(){
		return this.army;
	}
	
	/*
	public int getTempArmy(){
		return this.tempArmy;
	}
	
	public void setColor(TColor c){
		this.color = c;
	}
	*/
	public int getOwner(){
		return this.owner;
	}
	
	public void setOwner(int playerId){
		this.owner = playerId;
	}
	/*
	public void setOcupied(){
		this.ocupied = true;
	}
	
	public boolean isOcupied(){
		return this.ocupied;
	}
	
	public void addArmy(int n){
		this.army += n;
	}
	
	public void removeArmy(int n){
		this.army -= n;
	}
	
	public void addTempArmy(int n){
		this.tempArmy += n;
	}
	
	public void removeTempArmy(int n){
		this.tempArmy -= n;
	}
	
	public void setTempArmy(int n){
		this.tempArmy = n;
	}
	
	public void deploy(){
		this.army += this.tempArmy;
		this.tempArmy = 0;
	}
	*/
	public Territory.Continent getContinent(){
		
		return this.continent;
	}
	
}