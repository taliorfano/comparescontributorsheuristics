package br.ufmg.mes.DisambiguationHeuristic.Controller;

import org.eclipse.jgit.api.Git;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import br.ufmg.mes.DisambiguationHeuristic.Model.Author;
import br.ufmg.mes.DisambiguationHeuristic.Common.ManageFiles;
import br.ufmg.mes.DisambiguationHeuristic.Heuristics.BirdHeuristic;
import br.ufmg.mes.DisambiguationHeuristic.Heuristics.CanforaHeuristic;
import br.ufmg.mes.DisambiguationHeuristic.Heuristics.GoeminneHeuristic;
import br.ufmg.mes.DisambiguationHeuristic.Heuristics.NaiveHeuristicAdapted;
import br.ufmg.mes.DisambiguationHeuristic.Heuristics.RoblesHeuristic;

/**
 *	Disambiguation Heuristics 
 *	@author Talita Santana Orfano
 */
public class Main 
{
    public static void main( String[] args ) throws Exception
    {         
   		String URL = null;
    	ArrayList<Author> authorsList = new ArrayList<Author>();
    	DataCollectGit dataCollectGit = new DataCollectGit();
    	Scanner scanner = new Scanner(System.in);
    	System.out.print("Digite a URL do repositorio: ");
    	URL = scanner.nextLine();
    	Git git = dataCollectGit.CollectGit(URL);
    	
       /*    	
         *  Facilitador de testes manuais
         * 
         * 
       FileRepositoryBuilder builder = new FileRepositoryBuilder();
       Repository repository = builder.setGitDir(new File(("C://Users//Talita//git//joda-time//.git"))).readEnvironment() 
				.findGitDir() // scan up the file system tree
				.build();
    	Repository repository = builder.setGitDir(new File(("C://Users//Talita//git//elasticsearch//.git"))).readEnvironment() 
				.findGitDir() // scan up the file system tree
				.build();
        Repository repository = builder.setGitDir(new File(("C://Users//Talita//git//powershell//.git"))).readEnvironment() 
				.findGitDir() // scan up the file system tree
				.build();
		Git git = new Git(repository);
		*/
		
    	if(git == null)
    	{
    		System.out.println("Erro na criação do repositório para analise.");
    		return;
    	}
    	else{
	    	dataCollectGit.CollectAuthors(authorsList, git);
    	}
    	
    	System.out.println("Digite o caminho do diretorio para salvar os resultados:  "+
    			"\n(Exemplo: C:\\Users\\Talita\\Documents\\Mestrado - UFMG\\MES\\Trabalho Final\\resultado\\ )");
    	 String path = scanner.nextLine();
    	
    	/*
    	 	Facilitador de testes manuais
    	String path = "C:\\Users\\Talita\\Documents\\Mestrado - UFMG\\MES\\Trabalho Final\\resultado\\elastic\\";
        String path = "C:\\Users\\Talita\\Documents\\Mestrado - UFMG\\MES\\Trabalho Final\\resultado\\powershell\\";
    	String path = "C:\\Users\\Talita\\Documents\\Mestrado - UFMG\\MES\\Trabalho Final\\resultado\\joda\\";
    	 */
    	 
    	ManageFiles.createAuthorCsvFile(authorsList, path+"authors.csv");
    	
    	// Bird Heuristic, threashold de 0,84 to 0,99
		BirdHeuristic bird84 = new BirdHeuristic(0.84);
		BirdHeuristic bird87 = new BirdHeuristic(0.87);
		BirdHeuristic bird90 = new BirdHeuristic(0.90);
		BirdHeuristic bird93 = new BirdHeuristic(0.93);
		BirdHeuristic bird96 = new BirdHeuristic(0.96);
		BirdHeuristic bird99 = new BirdHeuristic(0.99);		
		
		// Canfora Heuristic
		CanforaHeuristic canfora = new CanforaHeuristic();
		
		// Simple/Naive Heuristic, lenght 4 to 8		
		NaiveHeuristicAdapted naiveAdapted4 = new NaiveHeuristicAdapted(4);
		NaiveHeuristicAdapted naiveAdapted5 = new NaiveHeuristicAdapted(5);
		NaiveHeuristicAdapted naiveAdapted6 = new NaiveHeuristicAdapted(6);
		NaiveHeuristicAdapted naiveAdapted7 = new NaiveHeuristicAdapted(7);
		NaiveHeuristicAdapted naiveAdapted8 = new NaiveHeuristicAdapted(8);
		
		RoblesHeuristic robles = new RoblesHeuristic();
		GoeminneHeuristic goeminne = new GoeminneHeuristic();
		
		ArrayList<List<String>> aliasIdentifiedBird84 = new ArrayList<List<String>>();
    	ArrayList<List<String>> aliasIdentifiedBird87 = new ArrayList<List<String>>();
    	ArrayList<List<String>> aliasIdentifiedBird90 = new ArrayList<List<String>>();
    	ArrayList<List<String>> aliasIdentifiedBird93 = new ArrayList<List<String>>();
    	ArrayList<List<String>> aliasIdentifiedBird96 = new ArrayList<List<String>>();
    	ArrayList<List<String>> aliasIdentifiedBird99 = new ArrayList<List<String>>();
    	
    	ArrayList<List<String>> aliasIdentifiedCanfora = new ArrayList<List<String>>();
    	
    	ArrayList<List<String>> aliasIdentifiedNaive4 = new ArrayList<List<String>>();
    	ArrayList<List<String>> aliasIdentifiedNaive5 = new ArrayList<List<String>>();
    	ArrayList<List<String>> aliasIdentifiedNaive6 = new ArrayList<List<String>>();
    	ArrayList<List<String>> aliasIdentifiedNaive7 = new ArrayList<List<String>>();
    	ArrayList<List<String>> aliasIdentifiedNaive8 = new ArrayList<List<String>>();
    	
    	ArrayList<List<String>> aliasIdentifiedNaiveAdapted4 = new ArrayList<List<String>>();
    	ArrayList<List<String>> aliasIdentifiedNaiveAdapted5 = new ArrayList<List<String>>();
    	ArrayList<List<String>> aliasIdentifiedNaiveAdapted6 = new ArrayList<List<String>>();
    	ArrayList<List<String>> aliasIdentifiedNaiveAdapted7 = new ArrayList<List<String>>();
    	ArrayList<List<String>> aliasIdentifiedNaiveAdapted8 = new ArrayList<List<String>>();
    	
    	ArrayList<List<String>> aliasIdentifiedRobles = new ArrayList<List<String>>();
    	ArrayList<List<String>> aliasIdentifiedGoeminne = new ArrayList<List<String>>();
    	
    	// Looking ambiguity between contributors
    	aliasIdentifiedBird84 = new SearchAliasHeuristics().ExploreAuthors(authorsList, bird84);
    	aliasIdentifiedBird87 = new SearchAliasHeuristics().ExploreAuthors(authorsList, bird87);
    	aliasIdentifiedBird90 = new SearchAliasHeuristics().ExploreAuthors(authorsList, bird90);
    	aliasIdentifiedBird93 = new SearchAliasHeuristics().ExploreAuthors(authorsList, bird93);
    	aliasIdentifiedBird96 = new SearchAliasHeuristics().ExploreAuthors(authorsList, bird96);
    	aliasIdentifiedBird99 = new SearchAliasHeuristics().ExploreAuthors(authorsList, bird99);
   	
    	aliasIdentifiedCanfora = new SearchAliasHeuristics().ExploreAuthors(authorsList, canfora);
    	
    	aliasIdentifiedNaiveAdapted4 = new SearchAliasHeuristics().ExploreAuthors(authorsList, naiveAdapted4);
    	aliasIdentifiedNaiveAdapted5 = new SearchAliasHeuristics().ExploreAuthors(authorsList, naiveAdapted5);
    	aliasIdentifiedNaiveAdapted6 = new SearchAliasHeuristics().ExploreAuthors(authorsList, naiveAdapted6);
    	aliasIdentifiedNaiveAdapted7 = new SearchAliasHeuristics().ExploreAuthors(authorsList, naiveAdapted7);
    	aliasIdentifiedNaiveAdapted8 = new SearchAliasHeuristics().ExploreAuthors(authorsList, naiveAdapted8);
    	 	
    	aliasIdentifiedRobles = new SearchAliasHeuristics().ExploreAuthors(authorsList, robles);
    	aliasIdentifiedGoeminne = new SearchAliasHeuristics().ExploreAuthors(authorsList, goeminne);
    	
    	
    	// Create CSV Files    	
    	
    	// Bird threshold 0.84, 0.87, 0.90, 0.93 e 0.96
    	ManageFiles.createAuthorshipCsvFile(aliasIdentifiedBird84, path+"alias_bird84.csv");
    	ManageFiles.createAuthorshipCsvFile(aliasIdentifiedBird87, path+"alias_bird87.csv");
    	ManageFiles.createAuthorshipCsvFile(aliasIdentifiedBird90, path+"alias_bird90.csv");
    	ManageFiles.createAuthorshipCsvFile(aliasIdentifiedBird93, path+"alias_bird93.csv");
    	ManageFiles.createAuthorshipCsvFile(aliasIdentifiedBird96, path+"alias_bird96.csv");
    	ManageFiles.createAuthorshipCsvFile(aliasIdentifiedBird99, path+"alias_bird99.csv");
    	
    	ManageFiles.createAuthorshipCsvFile(aliasIdentifiedCanfora, path+"alias_canfora.csv");
    	
    	ManageFiles.createAuthorshipCsvFile(aliasIdentifiedNaive4, path+"alias_naive4.csv");
    	ManageFiles.createAuthorshipCsvFile(aliasIdentifiedNaive5, path+"alias_naive5.csv");
    	ManageFiles.createAuthorshipCsvFile(aliasIdentifiedNaive6, path+"alias_naive6.csv");
    	ManageFiles.createAuthorshipCsvFile(aliasIdentifiedNaive7, path+"alias_naive7.csv");
    	ManageFiles.createAuthorshipCsvFile(aliasIdentifiedNaive8, path+"alias_naive8.csv");
    	
    	ManageFiles.createAuthorshipCsvFile(aliasIdentifiedNaiveAdapted4, path+"alias_naive_adapted4.csv");
    	ManageFiles.createAuthorshipCsvFile(aliasIdentifiedNaiveAdapted5, path+"alias_naive_adapted5.csv");
    	ManageFiles.createAuthorshipCsvFile(aliasIdentifiedNaiveAdapted6, path+"alias_naive_adapted6.csv");
    	ManageFiles.createAuthorshipCsvFile(aliasIdentifiedNaiveAdapted7, path+"alias_naive_adapted7.csv");
    	ManageFiles.createAuthorshipCsvFile(aliasIdentifiedNaiveAdapted8, path+"alias_naive_adapted8.csv");
    	
    	ManageFiles.createAuthorshipCsvFile(aliasIdentifiedRobles, path+"alias_robles.csv");
    	ManageFiles.createAuthorshipCsvFile(aliasIdentifiedGoeminne, path+"alias_goeminne.csv");
    }
}
