/**
 * Clasa ce extinde clasa Cell si 
 * implementeaza metodele getOrder
 * si visit. Aceste celule imi bordeaza
 * matricea si ma ajuta sa verific cazul
 * de pasire in exterior.
 * 
 * @author Cristian Pavel - 324 CB
 *
 */
public class FrontierCell extends Cell {

	public FrontierCell(int x, int y) {
		super(x, y);
	}

	@Override
	public int getOrder() {
		
		return Integer.MAX_VALUE - 1;
	}

	@Override
	public int visit() throws HeroOutOfGroundException {
		throw new HeroOutOfGroundException();
		
	}

}
