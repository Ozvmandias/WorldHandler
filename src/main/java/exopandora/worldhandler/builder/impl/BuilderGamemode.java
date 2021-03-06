package exopandora.worldhandler.builder.impl;

import exopandora.worldhandler.builder.CommandBuilder;
import exopandora.worldhandler.builder.CommandSyntax;
import exopandora.worldhandler.builder.types.ArgumentType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BuilderGamemode extends CommandBuilder
{
	public BuilderGamemode()
	{
		
	}
	
	public BuilderGamemode(EnumGamemode mode)
	{
		this.setMode(mode);
	}
	
	public BuilderGamemode(EnumGamemode mode, String player)
	{
		this(mode);
		this.setPlayer(player);
	}
	
	public void setMode(EnumGamemode mode)
	{
		this.setNode(0, mode.toString());
	}
	
	public void setPlayer(String player)
	{
		this.setNode(1, player);
	}
	
	@Override
	public String getCommandName()
	{
		return "gamemode";
	}

	@Override
	public final CommandSyntax getSyntax()
	{
		CommandSyntax syntax = new CommandSyntax();
		
		syntax.addRequired("mode", ArgumentType.STRING);
		syntax.addOptional("player", ArgumentType.STRING);
		
		return syntax;
	}
	
	@OnlyIn(Dist.CLIENT)
	public static enum EnumGamemode
	{
		SURVIVAL,
		CREATIVE,
		ADVENTURE,
		SPECTATOR;
		
		@Override
		public String toString()
		{
			return this.name().toLowerCase();
		}
	}
}
