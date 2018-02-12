import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe repr�sentant une boisson
 * nomBoisson - Le nom de la boisson
 * prix - Le prix de la boisson
 * Ingredients - Les ingredients pr�sents dans la boisson
 */

//TODO
// 5 Boissons
// +/- sucre
// Persistance des ingredients/boissons
// Valider les inputs
// Duplication de code entre Ajouter/modifer ingredients
public class Boisson implements Comparable<Boisson>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomBoisson;
	private int prix;
	private ArrayList<Ingredient> Ingredients;
	
	/**
	 * Constructeur 
	 * @param n nom de la boisson
	 * @param p prix de la boisson
	 * @param ing les ingredients
	 */
	private Boisson(String n,int p,ArrayList<Ingredient> ing){
		this.nomBoisson=n;
		this.prix=p;
		this.Ingredients=ing;
	}
	
	public int compareTo(Boisson b) {
        return toString().compareTo(b.nomBoisson);
    }
	
	/**
	 * M�thode qui ajoute une boisson
	 * @param n le nom de la boisson
	 * @param p le prix
	 * @return
	 */
	public static Boisson ajouterBoisson(String n,int p){
		
		// Ajout du Scanner d'entr�es
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez choisir les ingr�dients de votre boisson");
		boolean boissonVide=true;
		Boisson b =null;
		while(boissonVide){
		// Unit�s de caf�s � ajouter
		System.out.println("Unit� de caf� ? (0 pour aucun)");
		while(!sc.hasNextInt()){
			sc.next();
		}
		int uCafe = sc.nextInt();
		// Unit�s de Lait � ajouter
		System.out.println("Unit� de lait ? (0 pour aucun)");
		while(!sc.hasNextInt()){
			sc.next();
		}
		int uLait = sc.nextInt();
		// Unit�s de Chocolat � ajouter
		System.out.println("Unit� de chocolat ? (0 pour aucun)");
		while(!sc.hasNextInt()){
			sc.next();
		}
		int uChocolat = sc.nextInt();
		// Unit�s de Sucre � ajouter
		System.out.println("Unit� de sucre ? (0 pour aucun)");
		while(!sc.hasNextInt()){
			sc.next();
		}
		int uSucre = sc.nextInt();
		// Creation des diff�rentes classes
		if(uCafe!=0 || uLait!=0 || uChocolat!=0){
			boissonVide=false;
			Cafe c = new Cafe(uCafe);
			Lait l = new Lait(uLait);
			Chocolat ch = new Chocolat(uChocolat);
			Sucre s = new Sucre(uSucre);
		
			// Creation de la liste des ingr�dients
			ArrayList<Ingredient> i = new ArrayList<Ingredient>();
			// Ajout de chaque ingr�dients
			i.add(c);
			i.add(l);
			i.add(ch);
			i.add(s);
			// Cr�ation de la boisson
			b = new Boisson(n,p,i);
			
		}
		else{
			System.out.println("Il faut au moins un ingr�dient");
		}
		}
		return b;
	}
	
	
	
	/**
	 * Methode qui renvoie le nom de la boisson
	 * @return nomBoisson, le nom de la boisson
	 */
	public String getNomBoisson() {
		return nomBoisson;
	}
	
	/**
	 * Methode qui set le nom de la boisson
	 * @param nomBoisson le nom de la boisson
	 */
	public void setNomBoisson(String nomBoisson) {
		this.nomBoisson = nomBoisson;
	}

	/**
	 * Methode qui renvoie le prix
	 * @return prix, le prix de la boisson
	 */
	public int getPrix() {
		return prix;
	}

	/**
	 * Methode qui set le prix 
	 * @param prix le prix de la boisson
	 */
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	/**
	 * Methode qui retourne l'array list des ingredients
	 * @return Ingredients, les ingredients de la boisson
	 */
	public ArrayList<Ingredient> getIngredients() {
		return Ingredients;
	}

	/**
	 * Methode qui de modifier la liste des ingredients
	 * @param ingredients, la nouvelle liste d'ingr�dients
	 */
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		Ingredients = ingredients;
	}
	
	public static boolean verifierStock(int cafe,int lait,int chocolat,int sucre){
		boolean stock = false;
		if(Cafe.getStockCafe()-cafe>=0 && Lait.getStockLait()-lait>=0 && Chocolat.getStockChocolat()-chocolat>=0 && Sucre.getStockSucre()-sucre>=0){
			stock = true;
		}
		return stock;
	}
	
	
}
