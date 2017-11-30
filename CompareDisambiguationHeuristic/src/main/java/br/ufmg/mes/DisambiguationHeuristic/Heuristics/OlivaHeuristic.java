package br.ufmg.mes.DisambiguationHeuristic.Heuristics;

import br.ufmg.mes.DisambiguationHeuristic.Model.Heuristic;

/***
 *  Oliva Heuristic - *** This heuristic was disregarded in our studies and experiments **
 * @author Talita	
 *
 */
public class OlivaHeuristic implements Heuristic {
	
	/***
	 * Execute Oliva heuristic, comparing two authors (name and e-mail)
	 * @param name1  Author name 1
	 * @param email1 Author email 1
	 * @param name2  Author name 2
	 * @param email2 Author email 2
	 * @return True - If are equals
	 */
	public boolean Execute(String nameAuthor1, String email1, String nameAuthor2, String email2) {		
		String emailAuthor1 = Util.PrefixEmail(email1);
		String emailAuthor2 = Util.PrefixEmail(email2);
		
		nameAuthor1 = Normalize(nameAuthor1); 
		nameAuthor2 = Normalize(nameAuthor2);
		emailAuthor1 = Normalize(emailAuthor1);
		emailAuthor2 = Normalize(emailAuthor2);
		
		if(emailAuthor1.equals(emailAuthor2)){
			return true;
		}
		
		if(nameAuthor1.equals(nameAuthor2)){
			return true;
		}
		
		return false;
	}

	/***
	 * Normalize label
	 * @param label
	 * @return label normalized
	 */
	public String Normalize(String label){
		label = label.replaceAll("[ÂÀÁÄÃ]", "A");
	    label = label.replaceAll("[âãàáä]", "a");
	    label = label.replaceAll("[ÊÈÉË]", "E");
	    label = label.replaceAll("[êèéë]", "e");
	    label = label.replaceAll("ÎÍÌÏ", "I");
	    label = label.replaceAll("îíìï", "i");
	    label = label.replaceAll("[ÔÕÒÓÖ]", "O");
	    label = label.replaceAll("[ôõòóö]", "o");
	    label = label.replaceAll("[ÛÙÚÜ]", "U");
	    label = label.replaceAll("[ûúùü]", "u");
	    label = label.replaceAll("[ýÿ]", "y");
	    label = label.replaceAll("Ý", "Y");
	    label = label.replaceAll("ñ", "n");
	    label = label.replaceAll("Ñ", "N");
		
	    label = label.replaceAll("\\s+", " ");
		label = label.trim();
		label = label.toLowerCase();
		
		return label;
	}
}
