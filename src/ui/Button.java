package ui;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import state.StateManager;
import math.Vector2f;
import math.AABB;
import util.KeyHandler;
import util.MouseHandler;
import graphic.Sprite;

public class Button {
    
    private String label;
    private int lbWidth;
    private int lbHeight;

    private BufferedImage image;
    private BufferedImage hoverImage;
    private BufferedImage pressedImage;

    private Vector2f iPos;
    private Vector2f lbPos;

    private AABB bounds;
    private boolean hovering = false;
    private int hoverSize;
    private ArrayList<ClickedEvent> events;

    private boolean clicked = false;
    private boolean pressed = false;
    private boolean canHover = true;
    private boolean drawString = true;

    private float pressedtime;

    public Button(String label, BufferedImage image, Font font, Vector2f pos, int buttonSize) {
        this(label, image, font, pos, buttonSize, -1);
    }

    public Button(String label, BufferedImage image, Font font, Vector2f pos, int buttonWidth, int buttonHeight) {
        StateManager.g.setFont(font);
        FontMetrics met = StateManager.g.getFontMetrics(font);
        int height = met.getHeight();
        int width = met.stringWidth(label);

        if(buttonWidth == -1) buttonWidth = buttonHeight;

        this.label = label;

        this.image = createButton(label, image, font, width + buttonWidth, height + buttonHeight, buttonWidth, buttonHeight);
        this.iPos = new Vector2f(pos.x - this.image.getWidth() / 2, pos.y - this.image.getHeight() / 2);
        this.bounds = new AABB(iPos, this.image.getWidth(), this.image.getHeight());
        

        events = new ArrayList<ClickedEvent>();
        this.canHover = false;
        this.drawString = false;
    }

    public BufferedImage createButton(String label, BufferedImage image, Font font, int width, int height, int buttonWidth, int buttonHeight) {
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        if(image.getWidth() != width || image.getHeight() != height) {
            image = resizeImage(image, width, height);
        }

        Graphics g = result.getGraphics();
        g.drawImage(image, 0, 0, width, height, null);

        g.setFont(font);
        g.drawString(label, buttonWidth / 2, (height - buttonHeight));

        g.dispose();

        return result;
    }

    private BufferedImage resizeImage(BufferedImage image, int width, int height) {
        Image temp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = result.createGraphics();

        g.drawImage(temp, 0, 0, null);
        g.dispose();

        return result;
    }

    // ******************************************** END ************************************************************

    public void addHoverImage(BufferedImage image) {
        this.hoverImage = image;
        this.canHover = true;
    }

    public void addPressedImage(BufferedImage image) {
        this.pressedImage = image;
    }

    public void setHoverSize(int size) { this.hoverSize = size; }
	public boolean getHovering() { return hovering; }
    public void setHover(boolean b) { this.canHover = b; }
    public void addEvent(ClickedEvent e) { events.add(e);}

    public int getWidth() { return (int) bounds.getWidth(); }
    public int getHeight() { return (int) bounds.getHeight(); }
    public Vector2f getPos() { return bounds.getPos(); }

    public void update(double time) {
        if(pressedImage != null && pressed && pressedtime + 300 < time / 1000000) {
            pressed = false;
        }
    }

    private void hover(int value) {
        if(hoverImage == null) {
            iPos.x -= value / 2;
            iPos.y -= value / 2;
            float iWidth = value + bounds.getWidth();
            float iHeight = value + bounds.getHeight();
            this.bounds = new AABB(iPos, (int) iWidth, (int) iHeight);

            lbPos.x -= value / 2;
            lbPos.y -= value / 2;
            lbWidth += value / 3;
            lbHeight += value / 3;
            
        }
        
        hovering = true;
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        if(bounds.inside(mouse.getX(), mouse.getY())) {
            if(canHover && !hovering) {
                hover(hoverSize);
            }
            if(mouse.getButton() == 1 && !clicked) {
                clicked = true;
                pressed = true;

                pressedtime = System.nanoTime() / 1000000;

                for(int i = 0; i < events.size(); i++) {
                    events.get(i).action(1);
                }
            } else if(mouse.getButton() == -1) {
                clicked = false;
            }
        } else if(canHover && hovering) {
            hover(-hoverSize);
            hovering = false;
        }
    }

    public void render(Graphics2D g) {
        if(drawString) {
            Sprite.drawArray(g, label, lbPos, lbWidth, lbHeight);
        }

        if(canHover && hoverImage != null && hovering) {
            g.drawImage(hoverImage, (int) iPos.x, (int) iPos.y, (int) bounds.getWidth(), (int) bounds.getHeight(), null);
        } else if(pressedImage != null && pressed) {
            g.drawImage(pressedImage, (int) iPos.x, (int) iPos.y, (int) bounds.getWidth(), (int) bounds.getHeight(), null);
        } else {
            g.drawImage(image, (int) iPos.x, (int) iPos.y, (int) bounds.getWidth(), (int) bounds.getHeight(), null);
        }
        
    }

    public interface ClickedEvent {
        void action(int mouseButton);
    }

    public boolean isChecked() {
        return false;
    }
}