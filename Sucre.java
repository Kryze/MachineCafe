import java.io.Serializable;

public class Sucre extends Ingredient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int stockSucre = 10;
	
	public Sucre(int u) {
		super(u);
		// TODO Auto-generated constructor stub
	}
	
	public static int getStockSucre() {
		return stockSucre;
	}

	public static void setStockSucre(int stockSucre) {
		Sucre.stockSucre = stockSucre;
	}


}
