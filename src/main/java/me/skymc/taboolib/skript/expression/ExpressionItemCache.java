package me.skymc.taboolib.skript.expression;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.skymc.taboolib.inventory.ItemUtils;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

/**
 * @author sky
 * @since 2018-02-28 15:45:49
 */
public class ExpressionItemCache extends SimpleExpression<ItemStack> {
	
	private Expression<String> name;

	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, ParseResult arg3) {
		name = (Expression<String>) args[0];
		return true;
	}

	@Override
	public String toString(Event e, boolean arg1) {
		return this.getClass().getName();
	}

	@Override
	protected ItemStack[] get(Event e) {
		ItemStack item = ItemUtils.getCacheItem(name.getSingle(e));
		return new ItemStack[] { item == null ? null : item.clone() };
	}
}
