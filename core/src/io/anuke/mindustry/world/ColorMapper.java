package io.anuke.mindustry.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.IntMap.Entry;
import com.badlogic.gdx.utils.ObjectIntMap;

import io.anuke.mindustry.world.blocks.Blocks;

public class ColorMapper{
	private static ObjectIntMap<Block> reverseColors = new ObjectIntMap<>();
	private static Array<BlockPair> pairs = new Array<>();
	private static IntMap<BlockPair> colors = map(
		"323232", pair(Blocks.stone),
		"646464", pair(Blocks.stone, Blocks.stoneblock),
		"50965a", pair(Blocks.grass),
		"5ab464", pair(Blocks.grass, Blocks.grassblock),
		"506eb4", pair(Blocks.water),
		"465a96", pair(Blocks.deepwater),
		"252525", pair(Blocks.blackstone),
		"575757", pair(Blocks.blackstone, Blocks.blackstoneblock),
		"988a67", pair(Blocks.sand),
		"e5d8bb", pair(Blocks.sand, Blocks.sandblock),
		"c2d1d2", pair(Blocks.snow),
		"c4e3e7", pair(Blocks.ice),
		"f7feff", pair(Blocks.snow, Blocks.snowblock),
		"6e501e", pair(Blocks.dirt),
		"ed5334", pair(Blocks.lava),
		"292929", pair(Blocks.oil)
	);
	
	public static BlockPair get(int color){
		return colors.get(color);
	}
	
	public static IntMap<BlockPair> getColors(){
		return colors;
	}
	
	public static Array<BlockPair> getPairs(){
		return pairs;
	}
	
	public static int getColor(Block block){
		return reverseColors.get(block, 0);
	}
	
	private static BlockPair pair(Block floor, Block wall){
		return new BlockPair(floor, wall);
	}
	
	private static BlockPair pair(Block floor){
		return new BlockPair(floor, Blocks.air);
	}
	
	private static IntMap<BlockPair> map(Object...objects){
		IntMap<BlockPair> colors = new IntMap<>();
		for(int i = 0; i < objects.length/2; i ++){
			colors.put(Color.rgba8888(Color.valueOf((String)objects[i*2])), (BlockPair)objects[i*2+1]);
			pairs.add((BlockPair)objects[i*2+1]);
		}
		for(Entry<BlockPair> e : colors.entries()){
			reverseColors.put(e.value.wall == Blocks.air ? e.value.floor : e.value.wall, e.key);
		}
		return colors;
	}
	
	public static class BlockPair{
		public final Block floor, wall;
		
		public Block dominant(){
			return wall == Blocks.air ? floor : wall;
		}
		
		private BlockPair(Block floor, Block wall){
			this.floor = floor;
			this.wall = wall;
		}
	}
}
