package xyz.openthinks.vimixer.ui.resources;

import javafx.scene.image.Image;

public class ResourceLoader {
	public static final Image APP_ICON;
	
	static{
		
		APP_ICON = new Image(ResourceLoader.class.getResourceAsStream("vimixer.png"));
		
	}
}
