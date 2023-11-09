package com.kilroy790.notenoughmachines.client.gui.screen;

import com.kilroy790.notenoughmachines.client.NEMTextures;
import com.kilroy790.notenoughmachines.containers.TripHammerContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




@OnlyIn(Dist.CLIENT)
public class TripHammerScreen extends ContainerScreen<TripHammerContainer> 
{	
	public TripHammerScreen(TripHammerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) 
	{
		super(screenContainer, inv, titleIn);
		this.xSize = 174;
        this.ySize = 132;
	}
	
	
	
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) 
	{
		this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
	}
	
	
	
	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) 
	{
		String title = this.title.getString();	//getFormattedText
		this.font.drawString(matrixStack, title, (float)(this.xSize/2 - this.font.getStringWidth(title)/2), 6.0F, 4210752);
        this.font.drawString(matrixStack, this.playerInventory.getDisplayName().getString(), 8.0F, (float)(this.ySize - 96 + 4), 4210752);
	}
	
	
	
	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) 
	{
        this.minecraft.getTextureManager().bindTexture(NEMTextures.TRIPHAMMER_GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.xSize, this.ySize);
        
        int progressBar = container.getProgress();
        this.blit(matrixStack, relX + 70, relY + 20, 181, 1, 16, progressBar);
	}
}







