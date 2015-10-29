package net.doctorocclusion.festivities4.entity.lights;

public enum EnumBulbColor
{
	WARM("warm", 0xFFF4E0),
	COOL("cool", 0xE0F4FF),
	WHITE("white", 0xFFFFFF),
	DARK_BLUE("blue", 0x0000AA),
	DARK_GREEN("green", 0x00AA00),
	DARK_AQUA("aqua", 0x00AAAA),
	DARK_RED("red", 0xAA0000),
	DARK_PURPLE("purple", 0xAA00AA),
	GOLD("gold", 0xFFAA00),
	GRAY("gray", 0xAAAAAA),
	DARK_GRAY("dark_gray", 0x555555),
	BLUE("light_blue", 0x5555FF),
	GREEN("light_green", 0x55FF55),
	AQUA("light_aqua", 0x55FFFF),
	RED("light_red", 0xFF5555),
	LIGHT_PURPLE("light_purple", 0xFF55FF),
	YELLOW("yellow", 0xFFFF55);
	
	public final String name;
	public final int color;
	
	EnumBulbColor(String name, int color)
	{
		this.name = name;
		this.color = color;
	}
}