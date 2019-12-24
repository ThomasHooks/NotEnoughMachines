package com.kilroy790.notenoughmachines.gui;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class MillstoneScreen extends ContainerScreen<MillstoneContainer>{

	public MillstoneScreen(MillstoneContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		
		super(screenContainer, inv, titleIn);
		
		//Set the max size of the screen
		this.xSize = 173;	//180
        this.ySize = 151;	//152
		}
	
	
	private ResourceLocation millstoneGUI = new ResourceLocation(NotEnoughMachines.modid, "textures/gui/millstone_gui.png");
	
	
	@Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

	
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    	this.font.drawString("Millstone", 8.0f, 6.0f, 4210752);
    	this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 2), 4210752);
    	
    	//this.font.drawString("Inventory", 8.0f, 62.0f, 4210752);
        //this.drawString(Minecraft.getInstance().fontRenderer, "Energy", 10, 10, 0xffffff);
    }

    
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
               
        this.minecraft.getTextureManager().bindTexture(millstoneGUI);
        
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
        
        //draw the progress bar
        int progressBar = (int)(container.getProgress() * 16) / 200;
        //args in order {top left corner's X the image is drawn, top left corner's Y the image is drawn, X pos in the texture, y pos of the texture, X size of texture, y size of texture}
        this.blit(relX + 79, relY + 31, 181, 1, 16, progressBar);
    }
}











