import java.io.Serializable;

public class Chocolat extends Ingredient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int stockChocolat = 10;
	
	/**
	 * Constructeur de la classe chocolat
	 * On retire 1 du stock a chaque nouvelle classe
	 * @param u, le nombre d'unite de chocolat
	 */
	public Chocolat(int u) {
		super(u);
		// TODO Auto-generated constructor stub
	}
	
	public static int getStockChocolat() {
		return stockChocolat;
	}

	public static void setStockChocolat(int stockChocolat) {
		Chocolat.stockChocolat = stockChocolat;
	}



}
