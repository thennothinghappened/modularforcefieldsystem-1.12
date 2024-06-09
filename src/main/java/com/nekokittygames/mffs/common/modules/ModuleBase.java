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
    Thunderdark - initial implementation
 */

package com.nekokittygames.mffs.common.modules;

import com.nekokittygames.mffs.api.PointXYZ;
import com.nekokittygames.mffs.common.*;
import com.nekokittygames.mffs.common.item.ItemMFFSBase;
import com.nekokittygames.mffs.common.tileentity.TileEntityProjector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public abstract class ModuleBase extends ItemMFFSBase {

	private static List<ModuleBase> instances = new ArrayList<ModuleBase>();

	public static List<ModuleBase> get_instances() {
		return instances;
	}

	public int getForceFieldModuleType() {
		return forceFieldModuleType;
	}

	public void setForceFieldModuleType(int forceFieldModuleType) {
		this.forceFieldModuleType = forceFieldModuleType;
	}

	int forceFieldModuleType;

	public ModuleBase(final String itemName) {
		super(itemName);
		setMaxStackSize(8);
		instances.add(this);
	}

	@Override
	public boolean isRepairable() {
		return false;
	}

	public abstract boolean supportsDistance();

	public abstract boolean supportsStrength();

	public abstract boolean supportsMatrix();

	/**
	 * Assume that Y+ (up) is the direction that the projector is facing. Return
	 * points local to the projector. e.g. (0,1,0) would be right in front of
	 * the projector
	 * 
	 * @param projector
	 * @return Set of PointXYZ.
	 */
	public abstract void calculateField(IModularProjector projector,
			Set<PointXYZ> fieldPoints);

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		TileEntity tileEntity = world.getTileEntity(pos);

		if (!world.isRemote) {
			if (tileEntity instanceof TileEntityProjector) {

				final TileEntityProjector projector = (TileEntityProjector) tileEntity;

				if (!SecurityHelper.isAccessGranted(tileEntity, player,
						world, SecurityRight.EB)) {
					return EnumActionResult.FAIL;
				}

				if (projector.getStackInSlot(1).isEmpty()) {

					final ItemStack itemStack = player.getHeldItem(hand).splitStack(1);

					projector.setInventorySlotContents(1, itemStack);

					Functions.ChattoPlayer(
						player,
						"projectorModule.installed",
						Optional.ofNullable(ProjectorTyp.TypfromItem(itemStack.getItem()))
							.map(type -> type.displayName)
							.orElse("[[Unknown Module Name!!]]")
					);

					projector.checkslots();
					return EnumActionResult.SUCCESS;

				} else {
					Functions.ChattoPlayer(player, "projectorModule.notEmpty");
					return EnumActionResult.FAIL;
				}
			}
		}
		return EnumActionResult.FAIL;
	}


	public ForceFieldTyps getForceFieldTyps() {
		return ForceFieldTyps.Default;
	}

	public abstract boolean supportsOption(Item item);

}