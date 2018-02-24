import java.util.Comparator;
/**
 * Clasa ce implementeaza interfata Comparator.
 * Ma ajuta sa sortez celulele in ordine crescatoare,
 * dupa nr de ordine dat de functia getOrder().
 * 
 * @author Cristian Pavel - 324 CB
 *
 */
public class Move implements Comparator<Cell>{

	
	@Override
	public int compare(Cell o1, Cell o2) {
		

		return o1.getOrder() - o2.getOrder();
	}

}
