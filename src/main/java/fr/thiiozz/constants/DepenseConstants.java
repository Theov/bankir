package fr.thiiozz.constants;

public class DepenseConstants {
	// Template
	public final static String templateDepenses = "depenses";
	
	//Routes
	public final static String routeAfficher = "/depenses";
	public final static String routeSupprimer = routeAfficher + "/supprimer";
	public final static String routeAjouter = routeAfficher + "/ajouter";
	
	// Paramétres urls
	public final static String parametreAction = "action";
	public final static String parametreActionDecore = "?" + parametreAction + "=";
	public final static String parametreId = "id";
	
	public final static String actionAfficher = "afficher";
	public final static String actionAjouterOk = "ajouterOk";
	public final static String actionAjouterKo = "ajouterKo";
	public final static String actionSupprimerOk = "supprimerOk";
	public final static String actionSupprimerKo = "supprimerKo";
	public final static String actionOffre = "offert";
	public final static String actionRembourse = "rembourse";
	public final static String actionModifier = "modifier";
	public final static String actionAjouterErreurFormulaire = "ajouterFormKo";
	
	// Modele attributs
	public final static String toutesLesDepensesModelAttribut = "depenses";
	
	// Message
	private final static String messageParDefaut = "";
	private final static String messageSuppresionOk = "dépense supprimée !";
	private final static String messageSuppresionKo = "Erreur lors de la suppresion de la dépense !";
	private final static String messageAjoutOk = "dépense ajoutée !";
	private final static String messageAjoutKo = "Erreur lors de l'ajout de la dépense !";
	private final static String messageAjoutFormKo  = "Les valeurs saisies sont érronées !";
	private final static String messageOffre  = "Etat d'offre de la dépense modifié !";
	private final static String messageRembourser  = "Dépénse remboursées !";
	private final static String messageModifier  = "Dépense modifiée !";

	

	
	
	
	public static String getMessage(String action) {
		String message;
		
		switch (action) {
		case actionSupprimerOk:
			message = messageSuppresionOk;
			break;
			
		case actionSupprimerKo:
			message = messageSuppresionKo;
			break;
			
		case actionAjouterOk:
			message = messageAjoutOk;
			break;
			
		case actionAjouterKo:
			message = messageAjoutKo;
			break;
		
		case actionAjouterErreurFormulaire:
			message = messageAjoutFormKo;
			break;
		
		case actionModifier:
			message = messageModifier;
			break;
		
		case actionOffre:
			message = messageOffre;
			break;
		
		case actionRembourse:
			message = messageRembourser;
			break;
			
		default:
			message = messageParDefaut;
			break;
		}
		
		return message;
	}
}
