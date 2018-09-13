package cu.uci.coj.utils.tabledecorator;

import org.displaytag.decorator.TableDecorator;

import cu.uci.coj.model.entities.Entry;

public class newsTableDecorator extends TableDecorator {

	/**
	 * Constructor que asigna el formato, según documentación hace más
	 * eficiente la clase
	 */
	public newsTableDecorator() {
		super();

	}

	/**
	 * Método para regresar nulos
	 *
	 * @return <code>null</code>
	 */
	public String getNullValue() {
		return null;
	}

	public int getId() {
		return (((Entry) this.getCurrentRowObject()).getId());
	}

	public String getText() {
		return (((Entry) this.getCurrentRowObject()).getText());
	}

}
