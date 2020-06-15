package com.kilroy790.notenoughmachines.client.gui.screen;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.containers.FilterContainer;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




@OnlyIn(Dist.CLIENT)
public class FilterScreen extends ContainerScreen<FilterContainer> {

	private static final ResourceLocation GUI = new ResourceLocation(NotEnoughMachines.MODID, "textures/gui/filter_gui2.png");
	
	
	
	public FilterScreen(FilterContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.guiLeft = 0;
		this.guiTop = 0;
		this.xSize = 174;
        this.ySize = 186;
	}

	
	
	@Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

	
	
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    	String title = this.title.getFormattedText();
		this.font.drawString(title, (float)(this.xSize / 2 - this.font.getStringWidth(title) / 2), 6.0F, 4210752);
		this.font.drawString(new TranslationTextComponent("container.notenoughtmachines.filter2").getFormattedText(), 6.0f, 36.0f, 4210752); //34
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 4), 4210752);
    }

	
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    	RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
	}
}







