package com.github.thomashooks.notenoughmachines.client.gui.screens;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.inventory.TripHammerMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TripHammerScreen extends AbstractContainerScreen<TripHammerMenu>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(NotEnoughMachines.MOD_ID, "textures/gui/double_results_gui.png");

    public TripHammerScreen(TripHammerMenu menu, Inventory playerInventory, Component title)
    {
        super(menu, playerInventory, title);
        this.imageWidth = 174;
        this.imageHeight = 132;
        this.titleLabelX = 8;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY)
    {
        renderBackground(guiGraphics);
        int xPos = (this.width - this.imageWidth) / 2;
        int yPos = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(TEXTURE, xPos, yPos, 0, 0, this.imageWidth, this.imageHeight);

        //draw the progress bar
        guiGraphics.blit(TEXTURE, xPos + 70, yPos + 20, 181, 1, 16, menu.getProgressBar());
    }
}
