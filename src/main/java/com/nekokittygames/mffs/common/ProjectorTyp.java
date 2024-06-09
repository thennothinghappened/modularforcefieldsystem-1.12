/*  
    Copyright (C) 2012 Thunderdark

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
    Contributors:
    
    Thunderdark
    Matchlighter

 */

package com.nekokittygames.mffs.common;

import com.nekokittygames.mffs.common.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public enum ProjectorTyp {

	wall(1, "Wall", "AA AA BB ", ModItems.MODULE_WALL, true),
	deflector(2, "Deflector", "AAAABAAAA", ModItems.MODULE_DEFLECTOR, true),
	tube(3, "Tube", "AAA B AAA", ModItems.MODULE_TUBE, false),
	cube(4, "Cube", "B B A B B", ModItems.MODULE_CUBE, false),
	sphere(5, "Sphere", " B BAB B ", ModItems.MODULE_SPHERE, false),
	containment(6, "Containment", "BBBBABBBB", ModItems.MODULE_CONTAINMENT, false),
	AdvCube(7, "Adv. Cube", "AAAACAAAA", ModItems.MODULE_ADVCUBE, false),
	diagonallywall(8, "Diagonal Wall", "A A B A A", ModItems.MODULE_DIAGONAL_WALL, true);

	public String displayName;
	public String recipe;
	public Item item;
	public int ProTyp;
	public boolean Blockdropper;

	private ProjectorTyp(int ProTyp, String dispNm, String recipe, Item item,
			boolean Blockdropper) {

		this.displayName = dispNm;
		this.recipe = recipe;
		this.item = item;
		this.ProTyp = ProTyp;
		this.Blockdropper = Blockdropper;
	}

	public static ProjectorTyp TypfromItem(Item item) {
		for (ProjectorTyp mach : ProjectorTyp.values()) {
			if (mach.item == item) {
				return mach;
			}
		}
		return null;
	}

	public static void initialize() {

		for (ProjectorTyp mach : ProjectorTyp.values()) {
			generateRecipesFor(mach);
		}
	}

	public static String getDisplayName(ProjectorTyp mach) {

		return "MFFS Projector Module  " + mach.displayName;
	}

	public static void generateRecipesFor(ProjectorTyp mach) {
		String[] recipeSplit = new String[] { mach.recipe.substring(0, 3),
				mach.recipe.substring(3, 6), mach.recipe.substring(6, 9) };

///		RecipesFactory.IndRecipes.put(mach.item,GameRegistry.addShapedRecipe(new ItemStack(mach.item, 1), recipeSplit,
//
//		'C', ModularForceFieldSystem.MFFSProjectorTypCube, 'B', Blocks.OBSIDIAN,
//				'A', ModularForceFieldSystem.MFFSitemFocusmatix
//
//		));

	}

}