package br.ufmg.mes.DisambiguationHeuristic.Evaluation;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.mes.DisambiguationHeuristic.Model.Author;

/***
 * 	Evaluates results found by heuristics with the base of reference
 * @author Talita
 *
 */
public class EvaluateResults {

	// True positive
	private double TP;
	
	// False positive
	private double FP;
	
	// False negative
	private double FN;
	
	// True negative
	private double TN;
	
	public EvaluateResults(){
		this.FN = this.FP = this.TN = this.TP = 0;
	}
	
	/***
	 * 
	 * @param base of reference Lista com autores e alias do oraculo
	 * @param alg     			Lista com autores e alias identificados pelo algoritmo
	 */
	public void CompareResults(ArrayList<String> authorsList, ArrayList<List<String>> oraculo, ArrayList<List<String>> alg){
		String[] authors = authorsList.toArray(new String[0]);
		boolean isAliasAlg = false;
		boolean isAliasOraculo = false;
		
		for(int i = 0; i < authors.length; i++){
    		for(int j = i +1; j < authors.length; j++){
    			isAliasAlg = this.isAlias(authors[i], authors[j], alg);
    			isAliasOraculo = this.isAlias(authors[i], authors[j], oraculo);
    			
    			if(isAliasAlg == true && isAliasOraculo == true){
    				this.TP++;
    			}
    			else if(isAliasAlg == true && isAliasOraculo == false){
    				this.FP++;
    				///System.out.prdoubleln("FP: "+ authors[i]+" "+ authors[j]);
    			}
    			else if(isAliasAlg == false && isAliasOraculo == true){
    				this.FN++;
    				//System.out.prdoubleln("FN: "+ authors[i]+" "+ authors[j]);
    			}
    			else{
    				this.TN++;
    			}
    		}
		}
	}
	
	/**
	 * Verify if email1 and email2 are ambiguous
	 * @param email1
	 * @param email2
	 * @param emails
	 * @return
	 */
	public boolean isAlias(String email1, String email2, ArrayList<List<String>> emails){
		for (List<String> list: emails) {
    		if (list.contains(email1) && list.contains(email2)) {
    			return true;
	        }
	    }
		return false;
	}

	public double getTP() {
		return TP;
	}

	public double getFP() {
		return FP;
	}

	public double getFN() {
		return FN;
	}
	
	public double getTN() {
		return TN;
	}
}
