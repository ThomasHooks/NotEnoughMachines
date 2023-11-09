package com.kilroy790.notenoughmachines.client.gui.screen;

import com.kilroy790.notenoughmachines.client.NEMTextures;
import com.kilroy790.notenoughmachines.containers.RollingMillContainer;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




@OnlyIn(Dist.CLIENT)
public class RollingMillScreen extends ContainerScreen<RollingMillContainer>
{
	public RollingMillScreen(RollingMillContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) 
	{
		super(screenContainer, inv, titleIn);
		
		this.xSize = 174;
		this.ySize = 129;
	}
	
	
	
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) 
	{
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
	}
	
	
	
	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) 
	{
		String title = this.title.getString();
		this.font.drawString(matrixStack, title, (float)this.xSize / 2.0f - (float)this.font.getStringWidth(title) / 2.0f, 6.0F, 4210752);
		this.font.drawString(matrixStack, this.playerInventory.getDisplayName().getString(), 8.0F, (float)(this.ySize - 96 + 2), 4210752);
	}

	
	
	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) 
	{ 
		this.minecraft.getTextureManager().bindTexture(NEMTextures.ROLLING_MILL_GUI);
		int relX = (this.width - this.xSize) / 2;
		int relY = (this.height - this.ySize) / 2;
		this.blit(matrixStack, relX, relY, 0, 0, this.xSize, this.ySize);
		
		//draw the progress bar
		int progressBar = container.getProgress();
		this.blit(matrixStack, relX + 80, relY + 17, 181, 1, 16, progressBar);
	}
}



