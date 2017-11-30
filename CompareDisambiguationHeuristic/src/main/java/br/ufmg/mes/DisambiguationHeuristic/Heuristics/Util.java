package br.ufmg.mes.DisambiguationHeuristic.Heuristics;
import info.debatty.java.stringsimilarity.NormalizedLevenshtein;

/***
 * 	Useful functions to execute the heuristics
 * @author Talita
 *
 */
public class Util {
	/***
	 *  Levenshtein Similary (1 - Levenshtein Distance) 
	 */
	public static double DistanceLevenshtein(String name1, String name2){
  		 NormalizedLevenshtein lv = new NormalizedLevenshtein();
         //System.out.println(lv.similarity(name1, name2));
  		 return lv.similarity(name1, name2);
	}
	
	/***
	 * Extract the first name
	 * @param name
	 * @return first name
	 */
	public static String FirstName(String name){
        String[] split = name.split(" ");
        String first = split[0];
        return first;
	}
			
	/***
	 * Extract the last name
	 * @param name
	 * @return last name
	 */
	public static String LastName(String name){
		String[] split = name.split(" ");
        String last =  split[split.length - 1];
        return last;
	}
	
	/***
	 * Extract email prefix
	 * @param email
	 * @return
	 */
	public static String PrefixEmail(String email){
        String[] split = email.split("@");
        String newEmail = split[0];
        return newEmail;
	}
	
	/**
	 * Extract the parts of the name
	 * @param label
	 * @return
	 */
	public static String[] PartsName(String label){
		String[] array = null;
		if(label.contains(" ")){
			array = label.split(" ");
		}
		else if(label.contains(",")){
			array = label.split(",");
		}
		else if(label.contains("-")){
			array = label.split("-");
		}
		else if(label.contains("_")){
			array = label.split("_");
		}
		else if(label.contains(".")){
			array = label.split(".");
		}
		else if(label.contains("+")){
			array = label.split("+");
		}
		return array;
	}
	
	/**
	 * Extrct the parts of the email
	 * @param label
	 * @return
	 */
	public static String[] PartsEmail(String label){
		String[] array = null;
		if(label.contains(".")){
			array = label.split(".");
		}
		else if(label.contains("-")){
			array = label.split("-");
		}
		else if(label.contains("_")){
			array = label.split("_");
		}
		else if(label.contains("-")){
			array = label.split("-");
		}
		return array;
	}
}
