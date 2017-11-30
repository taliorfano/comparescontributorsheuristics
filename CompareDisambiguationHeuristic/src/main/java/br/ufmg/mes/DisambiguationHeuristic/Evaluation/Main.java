package br.ufmg.mes.DisambiguationHeuristic.Evaluation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ufmg.mes.DisambiguationHeuristic.Common.ManageFiles;

/**
 * 	Evaluates results found by Bird, Canfora, Simple, Robles e Goeminne heuristics 
 * @author Talita
 *
 */
public class Main {
	public static void main(String[] args) throws Exception {
		
		try{
			System.out.println("Digite o caminho do diretorio onde estao os dados a serem analisados:  "+
	    			"\n(Exemplo: C:\\Users\\Talita\\Documents\\Mestrado - UFMG\\MES\\Trabalho Final\\resultado\\elastic\\): ");
			
			
			ArrayList<String> authors;
			Scanner scanner = new Scanner(System.in);
			String path = "";
			path = scanner.nextLine();
			
			// Facilitador para testes manuais
			//String path = "C:\\Users\\Talita\\Documents\\Mestrado - UFMG\\MES\\Trabalho Final\\resultado\\elastic\\";
			// String path = "C:\\Users\\Talita\\Documents\\Mestrado - UFMG\\MES\\Trabalho Final\\resultado\\joda\\";
			// String path = "C:\\Users\\Talita\\Documents\\Mestrado - UFMG\\MES\\Trabalho Final\\resultado\\powershell\\";
			
			authors = ManageFiles.readAuthorsCsvFile(path+"authors.csv");
			
			ArrayList<List<String>> bird84 = ManageFiles.readAuthorsAliasCsvFile(path+"alias_bird84.csv");
			ArrayList<List<String>> bird87 = ManageFiles.readAuthorsAliasCsvFile(path+"alias_bird87.csv");
			ArrayList<List<String>> bird90 = ManageFiles.readAuthorsAliasCsvFile(path+"alias_bird90.csv");
			ArrayList<List<String>> bird93 = ManageFiles.readAuthorsAliasCsvFile(path+"alias_bird93.csv");
			ArrayList<List<String>> bird96 = ManageFiles.readAuthorsAliasCsvFile(path+"alias_bird96.csv");
			ArrayList<List<String>> bird99 = ManageFiles.readAuthorsAliasCsvFile(path+"alias_bird99.csv");
			
			ArrayList<List<String>> canfora = ManageFiles.readAuthorsAliasCsvFile(path+"alias_canfora.csv");
			
			ArrayList<List<String>> naiveAdapted4 = ManageFiles.readAuthorsAliasCsvFile(path+"alias_naive_adapted4.csv");
			ArrayList<List<String>> naiveAdapted5 = ManageFiles.readAuthorsAliasCsvFile(path+"alias_naive_adapted5.csv");
			ArrayList<List<String>> naiveAdapted6 = ManageFiles.readAuthorsAliasCsvFile(path+"alias_naive_adapted6.csv");
			ArrayList<List<String>> naiveAdapted7 = ManageFiles.readAuthorsAliasCsvFile(path+"alias_naive_adapted7.csv");
			ArrayList<List<String>> naiveAdapted8 = ManageFiles.readAuthorsAliasCsvFile(path+"alias_naive_adapted8.csv");
			
			ArrayList<List<String>> robles = ManageFiles.readAuthorsAliasCsvFile(path+"alias_robles.csv");
			
			ArrayList<List<String>> goeminne = ManageFiles.readAuthorsAliasCsvFile(path+"alias_goeminne.csv");
			
			System.out.println("Digite o nome e a extensao do arquivo com a base de referência ('oraculo'):  "+
	    			"\n(Exemplo: elasticsearch_authors.csv");
			
			scanner = new Scanner(System.in); 
			String nameFile = scanner.nextLine();
			ArrayList<List<String>> oraculo = ManageFiles.readAuthorsAliasCsvFile(path+nameFile);
			//ArrayList<List<String>> oraculo = ManageFiles.readAuthorsAliasCsvFile(path+"elasticsearch_authors.csv");
			// ArrayList<List<String>> oraculo = ManageFiles.readAuthorsAliasCsvFile(path+"powershell_authors.csv");
			//ArrayList<List<String>> oraculo = ManageFiles.readAuthorsAliasCsvFile(path+"joda_authors.csv");
			
			System.out.println("Resultado: "+ "TP: "+"      " +
					" TN: "+"      "+" FP: "+"      "+" FN: "+"      " + 
					" Precision: " +"      " + "Recall: " +"      ");
			
			EvaluateResults evaluatebird84 = new EvaluateResults();
			evaluatebird84.CompareResults(authors, oraculo, bird84);
			
			System.out.println("Resultado Bird 0.84: "+ " "+evaluatebird84.getTP()+
					" "+evaluatebird84.getTN()+" "+evaluatebird84.getFP()+
					" "+evaluatebird84.getFN() + 
					" " + (evaluatebird84.getTP()/(evaluatebird84.getTP()+evaluatebird84.getFP())) +
					" " + (evaluatebird84.getTP()/(evaluatebird84.getTP()+evaluatebird84.getFN())));
			
			EvaluateResults evaluatebird87 = new EvaluateResults();
			evaluatebird87.CompareResults(authors, oraculo, bird87);
			
			System.out.println("Resultado Bird 0.87: "+ ""+evaluatebird87.getTP()+
					" "+evaluatebird87.getTN()+" "+evaluatebird87.getFP()+" "+evaluatebird87.getFN() +
					" " + (evaluatebird87.getTP()/(evaluatebird87.getTP()+evaluatebird87.getFP())) +
					" " + (evaluatebird87.getTP()/(evaluatebird87.getTP()+evaluatebird87.getFN())));
			
			EvaluateResults evaluateBird90 = new EvaluateResults();
			evaluateBird90.CompareResults(authors, oraculo, bird90);
			
			System.out.println("Resultado Bird 0.90: "+ " "+evaluateBird90.getTP()+
					" "+evaluateBird90.getTN()+" "+evaluateBird90.getFP()+" "+evaluateBird90.getFN()+
					" " + (evaluateBird90.getTP()/(evaluateBird90.getTP()+evaluateBird90.getFP())) +
					" " + (evaluateBird90.getTP()/(evaluateBird90.getTP()+evaluateBird90.getFN())));
			
			EvaluateResults evaluateBird93 = new EvaluateResults();
			evaluateBird93.CompareResults(authors, oraculo, bird93);
			
			System.out.println("Resultado Bird 0.93: "+ " "+evaluateBird93.getTP()+
					" "+evaluateBird93.getTN()+" "+evaluateBird93.getFP()+" "+evaluateBird93.getFN() +
					" " + (evaluateBird93.getTP()/(evaluateBird93.getTP()+evaluateBird93.getFP())) +
					" " + (evaluateBird93.getTP()/(evaluateBird93.getTP()+evaluateBird93.getFN())));
			
			EvaluateResults evaluateBird96 = new EvaluateResults();
			evaluateBird96.CompareResults(authors, oraculo, bird96);
			
			System.out.println("Resultado Bird 0.96: "+ ""+evaluateBird96.getTP()+
					" "+evaluateBird96.getTN()+" "+evaluateBird96.getFP()+" "+evaluateBird96.getFN()+
					" " + (evaluateBird96.getTP()/(evaluateBird96.getTP()+evaluateBird96.getFP())) +
					" " + (evaluateBird96.getTP()/(evaluateBird96.getTP()+evaluateBird96.getFN())));
			
			EvaluateResults evaluateBird99 = new EvaluateResults();
			evaluateBird99.CompareResults(authors, oraculo, bird99);
			
			System.out.println("Resultado Bird 0.99: "+ ""+evaluateBird99.getTP()+
					" "+evaluateBird99.getTN()+" "+evaluateBird99.getFP()+" "+evaluateBird99.getFN()+
					" " + (evaluateBird99.getTP()/(evaluateBird99.getTP()+evaluateBird99.getFP())) +
					" " + (evaluateBird99.getTP()/(evaluateBird99.getTP()+evaluateBird99.getFN())));
			
			EvaluateResults evaluateCanfora = new EvaluateResults();
			evaluateCanfora.CompareResults(authors, oraculo, canfora);
			
			//  String.format("%.2f", valor)
			
			System.out.println("Resultado Canfora: "+ ""+evaluateCanfora.getTP()+
					" "+evaluateCanfora.getTN()+" "+evaluateCanfora.getFP()+" "+evaluateCanfora.getFN()+
					" " + (evaluateCanfora.getTP()/(evaluateCanfora.getTP()+evaluateCanfora.getFP())) +
					" " + (evaluateCanfora.getTP()/(evaluateCanfora.getTP()+evaluateCanfora.getFN())));
			
			EvaluateResults evaluateNaiveAdapted4 = new EvaluateResults();
			evaluateNaiveAdapted4.CompareResults(authors, oraculo, naiveAdapted4 );
			
			System.out.println("Resultado Naive 4: "+ ""+evaluateNaiveAdapted4.getTP()+
					" "+evaluateNaiveAdapted4.getTN()+" "+evaluateNaiveAdapted4.getFP()+
					" "+evaluateNaiveAdapted4.getFN() +
					" " + (evaluateNaiveAdapted4.getTP()/(evaluateNaiveAdapted4.getTP()+evaluateNaiveAdapted4.getFP())) +
					" " + (evaluateNaiveAdapted4.getTP()/(evaluateNaiveAdapted4.getTP()+evaluateNaiveAdapted4.getFN())));
			
			EvaluateResults evaluateNaiveAdapted5 = new EvaluateResults();
			evaluateNaiveAdapted5.CompareResults(authors, oraculo, naiveAdapted5);
			
			System.out.println("Resultado Naive 5: "+ ""+evaluateNaiveAdapted5.getTP()+
					" "+evaluateNaiveAdapted5.getTN()+"" +evaluateNaiveAdapted5.getFP()+
					" "+evaluateNaiveAdapted5.getFN() + 
					" " + (evaluateNaiveAdapted5.getTP()/(evaluateNaiveAdapted5.getTP()+evaluateNaiveAdapted5.getFP())) +
					" " + (evaluateNaiveAdapted5.getTP()/(evaluateNaiveAdapted5.getTP()+evaluateNaiveAdapted5.getFN())));
			
			EvaluateResults evaluateNaiveAdapted6 = new EvaluateResults();
			evaluateNaiveAdapted6.CompareResults(authors, oraculo, naiveAdapted6);
			
			System.out.println("Resultado Naive 6: "+ ""+evaluateNaiveAdapted6.getTP()+
					" "+evaluateNaiveAdapted6.getTN()+" "+evaluateNaiveAdapted6.getFP()+
					" "+evaluateNaiveAdapted6.getFN() +
					" " + (evaluateNaiveAdapted6.getTP()/(evaluateNaiveAdapted6.getTP()+evaluateNaiveAdapted6.getFP())) +
					" " + (evaluateNaiveAdapted6.getTP()/(evaluateNaiveAdapted6.getTP()+evaluateNaiveAdapted6.getFN())));
					
			EvaluateResults evaluateNaiveAdapted7 = new EvaluateResults();
			evaluateNaiveAdapted7.CompareResults(authors, oraculo, naiveAdapted7);
			
			System.out.println("Resultado Naive 7: "+ ""+evaluateNaiveAdapted7.getTP()+
					" "+evaluateNaiveAdapted7.getTN()+" "+evaluateNaiveAdapted7.getFP()+
					" "+evaluateNaiveAdapted7.getFN() +
					" " + (evaluateNaiveAdapted7.getTP()/(evaluateNaiveAdapted7.getTP()+evaluateNaiveAdapted7.getFP())) +
					" " + (evaluateNaiveAdapted7.getTP()/(evaluateNaiveAdapted7.getTP()+evaluateNaiveAdapted7.getFN())));
			
			EvaluateResults evaluateNaiveAdapted8 = new EvaluateResults();
			evaluateNaiveAdapted8.CompareResults(authors, oraculo, naiveAdapted8);
			
			System.out.println("Resultado Naive 8: "+ ""+evaluateNaiveAdapted8.getTP()+
					" "+evaluateNaiveAdapted8.getTN()+" "+evaluateNaiveAdapted8.getFP()+
					" "+evaluateNaiveAdapted8.getFN() + 
					" " + (evaluateNaiveAdapted8.getTP()/(evaluateNaiveAdapted8.getTP()+evaluateNaiveAdapted8.getFP())) +
					" " + (evaluateNaiveAdapted8.getTP()/(evaluateNaiveAdapted8.getTP()+evaluateNaiveAdapted8.getFN())));
			
			EvaluateResults evaluateRobles = new EvaluateResults();
			evaluateRobles.CompareResults(authors, oraculo, robles);
			
			System.out.println("Resultado Robles: "+ ""+evaluateRobles.getTP()+
					" "+evaluateRobles.getTN()+" "+evaluateRobles.getFP()+
					" "+evaluateRobles.getFN() + 
					" " + (evaluateRobles.getTP()/(evaluateRobles.getTP()+evaluateRobles.getFP())) +
					" " + (evaluateRobles.getTP()/(evaluateRobles.getTP()+evaluateRobles.getFN())));
			
			EvaluateResults evaluateGoeminne = new EvaluateResults();
			evaluateGoeminne.CompareResults(authors, oraculo, goeminne);
			
			System.out.println("Resultado Goeminne: "+ ""+evaluateGoeminne.getTP()+
					" "+evaluateGoeminne.getTN()+" "+evaluateGoeminne.getFP()+
					" "+evaluateGoeminne.getFN() + 
					" " + (evaluateGoeminne.getTP()/(evaluateGoeminne.getTP()+evaluateGoeminne.getFP())) +
					" " + (evaluateGoeminne.getTP()/(evaluateGoeminne.getTP()+evaluateGoeminne.getFN())));
			
		}
		catch(Exception e){
			System.out.print("Caminho invalido !\n"+e.getMessage());
		}
		
	}
}
