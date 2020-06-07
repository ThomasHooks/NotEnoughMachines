package com.kilroy790.notenoughmachines.client.gui.screen;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.containers.TripHammerContainer;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




@OnlyIn(Dist.CLIENT)
public class TripHammerScreen extends ContainerScreen<TripHammerContainer> {
	
	
	private static final ResourceLocation GUI_LOCATION = new ResourceLocation(NotEnoughMachines.MODID, "textures/gui/triphammer_gui.png");
	
	
	public TripHammerScreen(TripHammerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.xSize = 174;
        this.ySize = 132;
	}
	
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.font.drawString(this.title.getFormattedText(), 8.0F, 6.0F, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 4), 4210752);
	}
	
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        this.minecraft.getTextureManager().bindTexture(GUI_LOCATION);
        
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
	}
}
