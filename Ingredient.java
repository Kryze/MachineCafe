import java.io.Serializable;

public abstract class Ingredient implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int unite;
	private static int stock; 
	
	public Ingredient(int u){
		this.unite=u;
	}

	public static int getStock() {
		return stock;
	}

	public static void setStock(int stock) {
		Ingredient.stock = stock;
	}

	public int getUnite() {
		return unite;
	}

	public void setUnite(int unite) {
		this.unite = unite;
	}
	
}
