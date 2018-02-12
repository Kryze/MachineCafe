import java.io.Serializable;

public class Cafe extends Ingredient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int stockCafe = 10;
	/**
	 * Constructeur de la classe café
	 * On retire 1 du stock a chaque nouvelle classe
	 * @param u, le nombre d'unite de café
	 */
	public Cafe(int u) {
		super(u);
		// TODO Auto-generated constructor stub
	}
	public static int getStockCafe() {
		return stockCafe;
	}
	public static void setStockCafe(int stockCafe) {
		Cafe.stockCafe = stockCafe;
	}


	

}
