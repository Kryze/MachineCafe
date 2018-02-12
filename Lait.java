import java.io.Serializable;

public class Lait extends Ingredient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int stockLait = 10;
	
	/**
	 * Constructeur de la classe lait
	 * On retire 1 du stock a chaque nouvelle classe
	 * @param u, le nombre d'unite de lait
	 */
	
	public Lait(int u) {
		super(u);
		// TODO Auto-generated constructor stub
	}
	
	public static int getStockLait() {
		return stockLait;
	}

	public static void setStockLait(int stockLait) {
		Lait.stockLait = stockLait;
	}



}
