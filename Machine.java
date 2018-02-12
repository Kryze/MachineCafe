import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Classe Machine, qui represente la machine a cafe
 *
 */
public class Machine implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Nombre de boissons autorisées dans la machine
	private final static int NOMBREBOISSONS = 5;
	// Tableau contenant les informations des boissons
	private Boisson[] boissons;
	// Nombre de boissons 
	private int slotBoisson; 

	/**
	 * Constructeur qui initialise la machine
	 * On inscrit la taille du tableau des boissons
	 */
	private Machine(){
		this.boissons = new Boisson[NOMBREBOISSONS];
		this.slotBoisson=0;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		Machine m = chargeMachine();
		Scanner sc = new Scanner(System.in);
		boolean encours = true;
		//slotBoisson indique le nombre de boissons dans la machine
		
		System.out.println("-- Bienvenue dans la Miagine à café 3025 --");
		while(encours){
			System.out.println("Que souhaitez-vous faire ?");
			System.out.println("(1) Acheter une boisson");
			System.out.println("(2) Ajouter une boisson");
			System.out.println("(3) Modifier une boisson");
			System.out.println("(4) Supprimer une boisson");
			System.out.println("(5) Ajouter un ingrédient");
			System.out.println("(6) Vérifier stock ingrédients");
			System.out.println("(0) Quitter");
			switch(sc.next()){
			// Commande pour quitter
			case "0":
				System.out.println("Vous avez choisi de quitter");
				try
				{
				   FileOutputStream machineSaveOutput = new FileOutputStream("machine.ser");
				   ObjectOutputStream machineObjetOutput = new ObjectOutputStream(machineSaveOutput);
				   machineObjetOutput.writeObject(m);
				   machineObjetOutput.close();
				}
				catch (Exception e)
				{
				    System.out.println("Le fichier n'a pas pu être sauvegardée"); 
				}
				encours=false;
				break;
			// Commane pour l'achat de boissons
			case "1":
				// S'il y a au moins une boisson
				if(m.slotBoisson>0){
					int i=0;
					System.out.println("Voici la liste des boissons disponibles : ");
					for (Boisson b : m.getBoissons()){
						if(b != null){
							System.out.println("("+i+") "+b.getNomBoisson()+" - prix : "+b.getPrix());
							System.out.println("Ingredients :");
							System.out.println("	Cafe : "+b.getIngredients().get(0).getUnite());
							System.out.println("	Lait : "+b.getIngredients().get(1).getUnite());
							System.out.println("	Chocolat : "+b.getIngredients().get(2).getUnite());
							System.out.println("	Sucre : "+b.getIngredients().get(3).getUnite());
							i++;
						}
					}
					System.out.println("Quel boisson souhaitez-vous acheter ?");
					// On vérifie que la valeur est numérique
					while(!sc.hasNextInt()){
						
						sc.next();
					}
					int boissonAcheter = sc.nextInt();
					System.out.println("Insérer monnaie");
					while(!sc.hasNextInt()){
						
						sc.next();
					}
					int monnaie = sc.nextInt();
					if(boissonAcheter <NOMBREBOISSONS && m.getBoissonsValue(boissonAcheter)!=null){
						Boisson vendu = m.getBoissonsValue(boissonAcheter);
						if(vendu.getPrix()<=monnaie){
							System.out.println("Combien de sucre voulez-vous ajouter/retirer ? Il y a actuellement "+vendu.getIngredients().get(3).getUnite()+" dose(s) de sucre.");
							while(!sc.hasNextInt()){
								
								sc.next();
							}
							int dose = sc.nextInt();
							if(Boisson.verifierStock(vendu.getIngredients().get(0).getUnite(),vendu.getIngredients().get(1).getUnite(),vendu.getIngredients().get(2).getUnite(),vendu.getIngredients().get(3).getUnite()+dose)){
								Cafe.setStockCafe(Cafe.getStockCafe()-vendu.getIngredients().get(0).getUnite());
								Lait.setStockLait(Lait.getStockLait()-vendu.getIngredients().get(1).getUnite());
								Chocolat.setStockChocolat(Chocolat.getStockChocolat()-vendu.getIngredients().get(2).getUnite());
								Sucre.setStockSucre(Sucre.getStockSucre()-vendu.getIngredients().get(3).getUnite());
								monnaie=monnaie-vendu.getPrix();
								System.out.println("Boisson délivré");
								System.out.println("Rendu de la monnaie : "+monnaie+"€");
							}
							else{
								System.out.println("Pas assez d'ingrédients");
							}
						}
						else{
							System.out.println("Vous n'avez pas assez d'argent");
							System.out.println("Rendu de la monnaie : "+monnaie+"€");
						}
					}
					else{
						System.out.println("La boisson choisie n'existe pas/plus");
						System.out.println("Rendu de la monnaie : "+monnaie+"€");
					}

				}
				else{
					System.out.println("Aucune boisson disponible");
				}
				break;
			case "2":
				// Si il reste une place de libre pour ajouter une boisson
				if(m.slotBoisson<NOMBREBOISSONS){
					boolean nomunique=true;
					System.out.println("Veuillez nommer la boisson que vous souhaitez ajouter");
					String nomBoisson=sc.next();
					System.out.println("Veuillez indiquez le prix de votre boisson");
					while(!sc.hasNextInt()){
						
						sc.next();
					}
					int prix = sc.nextInt();
					// On ajouter la boisson dans la liste
					m.setBoissons(Boisson.ajouterBoisson(nomBoisson, prix),m.slotBoisson);
					// On ajoute 1 à la liste des boissons
					m.slotBoisson+=1;
					System.out.println("Boisson correctement ajoutée");
				}
				break;
			case "3":
				if(m.slotBoisson>0){
					int i=0;
					System.out.println("Voici la liste des boissons modifiables : ");
					for (Boisson b : m.getBoissons()){
						if(b != null){
							System.out.println("("+i+") "+b.getNomBoisson()+" - prix : "+b.getPrix());
							System.out.println("Ingredients :");
							System.out.println("	Cafe : "+b.getIngredients().get(0).getUnite());
							System.out.println("	Lait : "+b.getIngredients().get(1).getUnite());
							System.out.println("	Chocolat : "+b.getIngredients().get(2).getUnite());
							System.out.println("	Sucre : "+b.getIngredients().get(3).getUnite());
							i++;
						}
					}
					System.out.println("Quel boisson souhaitez-vous modifier ?");
					while(!sc.hasNextInt()){
						
						sc.next();
					}
					int boissonModifiable = sc.nextInt();
					Boisson modif = m.getBoissonsValue(boissonModifiable);
					System.out.println("Vous voulez modifier :");
					System.out.println("(0) Le cafe");
					System.out.println("(1) Le chocolat");
					System.out.println("(2) Le lait");
					System.out.println("(3) Le sucre");
					System.out.println("(4) Le prix");
					while(!sc.hasNextInt()){
						
						sc.next();
					}
					int quoimodifier = sc.nextInt();
					System.out.println("Nouvelle valeur ?");
					while(!sc.hasNextInt()){
						
						sc.next();
					}
					int valeur = sc.nextInt();
					if(quoimodifier>=0 && quoimodifier<4)
						modif.getIngredients().get(quoimodifier).setUnite(valeur);
					else
						modif.setPrix(valeur);
				}
				break;
			case "4":
				if(m.slotBoisson>0){
					int i=0;
					System.out.println("Voici la liste des boissons supprimables : ");
					for (Boisson b : m.getBoissons()){
						if(b != null){
							System.out.println("("+i+") "+b.getNomBoisson()+" - prix : "+b.getPrix());
						}
					}
					System.out.println("Indiquez le numéro de la boisson a supprimer");
					while(!sc.hasNextInt()){
						
						sc.next();
					}
					int idboisson = sc.nextInt();
					if(idboisson>=0 && idboisson<getLength(m.getBoissons())){
						m.setBoissons(null, idboisson);
						m.slotBoisson=m.slotBoisson-1;
					}
					else{
						System.out.println("Vous avez spécifié un numéro de boisson inexistant");
					}
				}
				else{
					System.out.println("Aucune boisson présente, impossible de supprimer rien ! :)");
				}
				break;
			case "5":
				System.out.println("Quel ingredient souhaité vous ajouter ?");
				System.out.println("(0) Cafe");
				System.out.println("(1) Lait");
				System.out.println("(2) Chocolat");
				System.out.println("(3) Sucre");

				String ing = sc.next();

				System.out.println("Quel quantité ?");
				
				while(!sc.hasNextInt()){
					
					sc.next();
				}
				int q = sc.nextInt();
				if(q>0){
				switch(ing){
				case "0":
					Cafe.setStockCafe(Cafe.getStockCafe()+q);
					System.out.println("Stock de cafe mis à jour a : "+Cafe.getStockCafe());
					break;
				case "1":
					Lait.setStockLait(Lait.getStockLait()+q);
					System.out.println("Stock de Lait mis à jour a : "+Lait.getStockLait());
					break;
				case "2":
					Chocolat.setStockChocolat(Chocolat.getStockChocolat()+q);
					System.out.println("Stock de chocolat mis à jour a : "+Chocolat.getStockChocolat());
					break;
				case "3":
					Sucre.setStockSucre(Sucre.getStockSucre()+q);
					System.out.println("Stock de sucre mis à jour a : "+Sucre.getStockSucre());
					break;
				default:
					System.out.println("Choix inconnu");
					break;
					}
				}
				else{
					System.out.println("Impossible de retirer des ingrédients");
				}

				break;
			case "6":
				System.out.println("Le stock des ingédients est :");
				System.out.println("	Cafe : "+Cafe.getStockCafe());
				System.out.println("	Lait : "+Lait.getStockLait());
				System.out.println("	Chocolat : "+Chocolat.getStockChocolat());
				System.out.println("	Sucre : "+Sucre.getStockSucre());
				break;
			default:
				System.out.println("Votre choix n'apparait pas dans la liste, retour à la liste");
				break;
			}
		}
	}

	public static <Boisson> int getLength(Boisson[] arr){
		int count = 0;
		for(Boisson el : arr)
			if (el != null)
				++count;
		return count;
	}

	public Boisson[] getBoissons() {
		return boissons;
	}

	public Boisson getBoissonsValue(int i) {
		return boissons[i];
	}

	public void setBoissonsArray(Boisson[] bs) {
		this.boissons = bs;
	}

	public void setBoissons(Boisson b, int indice) {
		this.boissons[indice] = b;
	}
	
	public static Machine chargeMachine() throws ClassNotFoundException, IOException{
		File f = new File("machine.ser");
		Machine m;
		if(f.isFile() && f.exists()){
		FileInputStream machineSaveInput = new FileInputStream(f);
		ObjectInputStream machineObjetInput = new ObjectInputStream(machineSaveInput);
		m = (Machine) machineObjetInput.readObject(); 
		machineObjetInput.close();
		System.out.println("La machine a été rempli de 10 ingrédients de chaque.");
		}
		else{
			m = new Machine();
		}
		return m;
	}

}
