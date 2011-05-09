
package uk.me.graphe.client.dialogs;

import uk.me.graphe.client.Graphemeui;
import uk.me.graphe.shared.Tools;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoadingDialog extends PopupPanel
{
	private static LoadingDialog sInstance;
	
	private final Graphemeui parent;
	private final Image animation;

	public LoadingDialog (Graphemeui gui)
	{
		this.parent = gui;
		this.setAnimationEnabled(true);
		this.setGlassEnabled(true);
		this.setModal(true);
		this.setStyleName("loadingDialog");
		
		animation = new Image("images/loading.gif");
		
		super.add(animation);		
	}

	public static LoadingDialog getInstance (Graphemeui gui)
	{
		if (sInstance == null) sInstance = new LoadingDialog(gui);
        return sInstance;
	}
	
	public void show (String s)
	{
		parent.isHotkeysEnabled = false;
		super.center();
		super.show();		
	}
	
	public void hide (String s)
	{
		parent.isHotkeysEnabled = true;
		super.hide();
	}
}
