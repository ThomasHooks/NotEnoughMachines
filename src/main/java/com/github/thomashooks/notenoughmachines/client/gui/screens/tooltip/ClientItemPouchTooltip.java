package com.github.thomashooks.notenoughmachines.client.gui.screens.tooltip;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.inventory.tooltip.ItemPouchTooltip;
import com.github.thomashooks.notenoughmachines.world.item.PouchItem;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ClientItemPouchTooltip implements ClientTooltipComponent
{
    public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(NotEnoughMachines.MOD_ID,"textures/gui/item_pouch_gui.png");
    private static final int MARGIN_Y = 4;
    private static final int BORDER_WIDTH = 1;
    private static final int TEX_SIZE = 128;
    private static final int SLOT_SIZE_X = 18;
    private static final int SLOT_SIZE_Y = 20;
    private final NonNullList<ItemStack> items;
    private final int slot;
    private final int weight;

    public ClientItemPouchTooltip(ItemPouchTooltip tooltip)
    {
        this.items = tooltip.getItems();
        this.slot = tooltip.getSlot();
        this.weight = tooltip.getWeight();
    }

    @Override
    public int getHeight() { return this.gridSizeY() * SLOT_SIZE_Y + 2 * BORDER_WIDTH + MARGIN_Y; }

    @Override
    public int getWidth(@NotNull Font font) { return this.gridSizeX() * SLOT_SIZE_X + 2 * BORDER_WIDTH; }

    @Override
    public void renderImage(@NotNull Font font, int xPos, int yPos, @NotNull GuiGraphics guiGraphics)
    {
        int gridSizeX = this.gridSizeX();
        int gridSizeY = this.gridSizeY();
        boolean isFull = this.weight >= PouchItem.MAX_WEIGHT;
        int slotIndex = 0;

        for(int y = 0; y < gridSizeY; ++y)
        {
            for(int x = 0; x < gridSizeX; ++x)
            {
                int slotXPos = xPos + x * SLOT_SIZE_X + BORDER_WIDTH;
                int slotYPos = yPos + y * SLOT_SIZE_Y + BORDER_WIDTH;
                this.renderSlot(slotXPos, slotYPos, slotIndex++, isFull, guiGraphics, font);
            }
        }
        this.drawBorder(xPos, yPos, gridSizeX, gridSizeY, guiGraphics);
    }

    private void renderSlot(int slotXpos, int slotYPos, int slotIndex, boolean itemPouchIsFull, GuiGraphics guiGraphics, Font font)
    {
        if (slotIndex >= this.items.size())
            this.blit(guiGraphics, slotXpos, slotYPos, itemPouchIsFull ? ClientItemPouchTooltip.Texture.BLOCKED_SLOT : ClientItemPouchTooltip.Texture.SLOT);
        else
        {
            ItemStack itemstack = this.items.get(slotIndex);
            this.blit(guiGraphics, slotXpos, slotYPos, ClientItemPouchTooltip.Texture.SLOT);
            guiGraphics.renderItem(itemstack, slotXpos + BORDER_WIDTH, slotYPos + BORDER_WIDTH, slotIndex);
            guiGraphics.renderItemDecorations(font, itemstack, slotXpos + BORDER_WIDTH, slotYPos + BORDER_WIDTH);
            if (slotIndex == this.slot)
                AbstractContainerScreen.renderSlotHighlight(guiGraphics, slotXpos + BORDER_WIDTH, slotYPos + BORDER_WIDTH, 0);
        }
    }

    private void drawBorder(int xPos, int yPos, int gridSizeX, int gridSizeY, GuiGraphics guiGraphics)
    {
        this.blit(guiGraphics, xPos, yPos, ClientItemPouchTooltip.Texture.BORDER_CORNER_TOP);
        this.blit(guiGraphics, xPos + gridSizeX * SLOT_SIZE_X + BORDER_WIDTH, yPos, ClientItemPouchTooltip.Texture.BORDER_CORNER_TOP);

        for(int x = 0; x < gridSizeX; ++x)
        {
            this.blit(guiGraphics, xPos + BORDER_WIDTH + x * SLOT_SIZE_X, yPos, ClientItemPouchTooltip.Texture.BORDER_HORIZONTAL_TOP);
            this.blit(guiGraphics, xPos + BORDER_WIDTH + x * SLOT_SIZE_X, yPos + gridSizeY * SLOT_SIZE_Y, ClientItemPouchTooltip.Texture.BORDER_HORIZONTAL_BOTTOM);
        }

        for(int y = 0; y < gridSizeY; ++y)
        {
            this.blit(guiGraphics, xPos, yPos + y * SLOT_SIZE_Y + BORDER_WIDTH, ClientItemPouchTooltip.Texture.BORDER_VERTICAL);
            this.blit(guiGraphics, xPos + gridSizeX * SLOT_SIZE_X + BORDER_WIDTH, yPos + y * SLOT_SIZE_Y + BORDER_WIDTH, ClientItemPouchTooltip.Texture.BORDER_VERTICAL);
        }

        this.blit(guiGraphics, xPos, yPos + gridSizeY * SLOT_SIZE_Y, ClientItemPouchTooltip.Texture.BORDER_CORNER_BOTTOM);
        this.blit(guiGraphics, xPos + gridSizeX * SLOT_SIZE_X + BORDER_WIDTH, yPos + gridSizeY * SLOT_SIZE_Y, ClientItemPouchTooltip.Texture.BORDER_CORNER_BOTTOM);
    }

    private void blit(GuiGraphics guiGraphics, int xPos, int yPos, ClientItemPouchTooltip.Texture texture)
    {
        guiGraphics.blit(TEXTURE_LOCATION, xPos, yPos, 0, (float)texture.x, (float)texture.y, texture.w, texture.h, TEX_SIZE, TEX_SIZE);
    }

    private int gridSizeX() { return Math.max(2, (int)Math.ceil(Math.sqrt((double)this.items.size() + 1.0D))); }

    private int gridSizeY() { return (int)Math.ceil(((double)this.items.size() + 1.0D) / (double)this.gridSizeX()); }

    @OnlyIn(Dist.CLIENT)
    enum Texture
    {
        SLOT(0, 0, 18, 20),
        BLOCKED_SLOT(0, 40, 18, 20),
        BORDER_VERTICAL(0, 18, 1, 20),
        BORDER_HORIZONTAL_TOP(0, 20, 18, 1),
        BORDER_HORIZONTAL_BOTTOM(0, 60, 18, 1),
        BORDER_CORNER_TOP(0, 20, 1, 1),
        BORDER_CORNER_BOTTOM(0, 60, 1, 1);

        public final int x;
        public final int y;
        public final int w;
        public final int h;

        Texture(int x, int y, int width, int height)
        {
            this.x = x;
            this.y = y;
            this.w = width;
            this.h = height;
        }
    }
}
