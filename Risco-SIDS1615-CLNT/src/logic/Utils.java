package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Utils implements java.io.Serializable{

	public ArrayList<Integer> throwDices(int n, boolean attacker){
		int m = 0;
		if(attacker && n > 3){	
			m = 3;
		}
		else if(!attacker && n > 2){
			m = 2;
		}
		else{
			m = n;
		}
		
		ArrayList<Integer> dices = new ArrayList<Integer>();
		Random rn = new Random();
		for(int i = 0; i < m;++i){
			int dice = rn.nextInt(6) + 1;
			dices.add(dice);
		}

		return dices;
	}
	
	public ArrayList<Integer> combateLosses(ArrayList<Integer> attacker, ArrayList<Integer> defender){
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		values.add(0);
		values.add(0);
		int minArmies = 0;
		
		System.out.println("YEEEEEEEEEEEEEEEEEEEEEEEEEE");
		System.out.println(attacker.size());
		System.out.println(defender.size());
		
		if(attacker.size() < defender.size()){
			minArmies = attacker.size();
			
		}
		else{
			minArmies = defender.size();
			
		}
		
		System.out.println(minArmies);
		
		
			
		defender = getBestTroops(defender,minArmies);
		attacker = getBestTroops(attacker,minArmies);
		
		System.out.println("YEEEEEEEEEEEEEEEEEEEEEEEEEE2");
		System.out.println(attacker.size());
		System.out.println(defender.size());
		
		
		//compare
		for(int j = 0; j < minArmies; ++j){
			
			if(attacker.get(j) > defender.get(j)){
				int n = values.get(0);
				++n;
				values.set(0, n);
			}
			else{
				int n = values.get(1);
				++n;
				values.set(1, n);
			}
		}
		
		return values;
	}
	
	//filter wanted
	public ArrayList<Integer> getBestTroops(ArrayList<Integer> attacker, int n){
		
		System.out.println("WOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+n);
		for(int i = 0; i< attacker.size();++i){
			System.out.println("total" +attacker.get(i));
		}
		ArrayList<Integer> stuff = attacker;
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		int min = 0;
		int minId = 0;
		if(n > 0){
			for(int i = 0; i < stuff.size();++i){
				if(stuff.get(i)>min){
					min = stuff.get(i);
					minId = i;
				}
			}
			
			values.add(min);
			stuff.remove(minId);
		}
		 min = 0;
		 minId = 0;
		
		if(n > 1){
			for(int i = 0; i < stuff.size();++i){
				if(stuff.get(i)>min){
					min = stuff.get(i);
					minId = i;
				}
			}
			
			values.add(min);
			stuff.remove(minId);
		}
		
		 min = 0;
		 minId = 0;
		
		if(n > 2){
			for(int i = 0; i < stuff.size();++i){
				if(stuff.get(i)>min){
					min = stuff.get(i);
					minId = i;
				}
			}
			
			values.add(min);
			stuff.remove(minId);
		}
		
		
		for(int i = 0; i < attacker.size();++i){
			values.add(attacker.get(i));
		}
		
		for(int i = 0; i< values.size();++i){
			System.out.println("best" +values.get(i));
		}
		
		return values;
	}
	
}
