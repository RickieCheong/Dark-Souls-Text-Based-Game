package game.items;

import edu.monash.fit2099.engine.Item;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {

	/**
	 * The Item that is portable
	 *
	 * @param name The name of portable Item
	 * @param displayChar The Letter Char
	 */
	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}
}
