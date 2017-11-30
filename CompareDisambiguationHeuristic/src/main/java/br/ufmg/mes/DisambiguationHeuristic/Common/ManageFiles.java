package br.ufmg.mes.DisambiguationHeuristic.Common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import br.ufmg.mes.DisambiguationHeuristic.Model.Author;

/**
 * 	Manipula os arquivos CVS
 * @author Talita
 *
 */
public class ManageFiles {
    /*
	 * Cria CSV contendo informacoes de autoria 
	 * author1: email_alias1, email_alias2,email_ alias3
	 * author2: email_alias1
	 */
	public static void createAuthorshipCsvFile(ArrayList<List<String>>authorsList, String path) {
		try {
			// Criação de um buffer para a escrita em uma stream
			BufferedWriter bufferwriter = new BufferedWriter(new FileWriter(path));
			bufferwriter.write("Email \n");
			
			for (List<String> authors : authorsList) {
				for(String author: authors){
					bufferwriter.write(author+";");
				}
				bufferwriter.write("\n");
			}
			bufferwriter.close();
		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/*
	 * 	Cria CSV com a lista de autores que contribuiram no repositorio analisado
	 */
	public static void createAuthorCsvFile(ArrayList<Author> authorsList, String path) {
		try {
			// Criação de um buffer para a escrita em uma stream
			BufferedWriter bufferwriter = new BufferedWriter(new FileWriter(path));
			bufferwriter.write("Autor; Email \n");
			
			for (Author key : authorsList) {
				bufferwriter.write(""+key.getName()+";"+key.getEmail()+"\n"); 
			}
			
			bufferwriter.close();
		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Lê arquivo CSV e trata de acordo com sua caracteristica
	 */
	public static ArrayList<List<String>> readAuthorsAliasCsvFile(String path) {
		ArrayList<List<String>> authorsAlias = new ArrayList<List<String>>();
		try {
			
			BufferedReader StrR = new BufferedReader(new FileReader(path));
			String Str;
			String[] TableLine;
			boolean primeiraLinha = true;
			List<String> authorsLine = new ArrayList<String>();

			while ((Str = StrR.readLine()) != null) {
				// Divide a linha lida em um array de String passando como
				// parametro o divisor ";".
				TableLine = Str.split(";");
				if (primeiraLinha) {
					primeiraLinha = false;
					continue;
				}
				authorsLine = Arrays.asList(TableLine);
				authorsAlias.add(authorsLine);
				//System.out.println("\n");
			}
			StrR.close();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.print("Caminho invalido !\n"+e.getMessage());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return authorsAlias;
	}

	/*
	 * Lê arquivo CSV e trata de acordo com sua caracteristica
	 */
	public static ArrayList<String> readAuthorsCsvFile(String path) {
		ArrayList<String> authorsLine = new ArrayList<String>();
		try {
			
			BufferedReader StrR = new BufferedReader(new FileReader(path));
			String Str;
			String[] TableLine;
			boolean primeiraLinha = true;

			while ((Str = StrR.readLine()) != null) {
				// Divide a linha lida em um array de String passando como
				// parametro o divisor ";".
				TableLine = Str.split(";");
				if (primeiraLinha) {
					primeiraLinha = false;
					continue;
				}
				if(!authorsLine.contains(TableLine[1])){
					authorsLine.add(TableLine[1].toLowerCase());
				}
				//System.out.println("\n");
			}
			StrR.close();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.print("Caminho invalido !\n"+e.getMessage());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return authorsLine;
	}
	
}

