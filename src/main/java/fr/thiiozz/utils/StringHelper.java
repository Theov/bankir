package fr.thiiozz.utils;

public class StringHelper {
	
	public static long convertirChaineVersEntier(String chaine){
		long valeurEntiere = 0;
		
		try{
			valeurEntiere = Integer.valueOf(chaine);
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
		
		return valeurEntiere;
	}
}
