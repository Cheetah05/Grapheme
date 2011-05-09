package uk.me.graphe.client;

import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HomePage extends HorizontalPanel
{
	private final VerticalPanel pnlLogin;
	private final TextBox txtOpenId;
	
	
	public HomePage ()
	{
		pnlLogin = new VerticalPanel();
		txtOpenId = new TextBox();
		
		txtOpenId.setText("Type and press enter to login...");
		txtOpenId.setStyleName("txtOpenId");
		
		txtOpenId.addFocusHandler(new FocusHandler()
		{
			@Override
			public void onFocus(FocusEvent arg0)
			{
				if (txtOpenId.getText().equals("Type and press enter to login..."))
				{
					txtOpenId.setText("");
				}
				else if (txtOpenId.getText().trim().equals(""))
				{
					txtOpenId.setText("Type and press enter to login...");
				}
			}
		});
		
		txtOpenId.addKeyDownHandler(new KeyDownHandler()
		{
			@Override
			public void onKeyDown(KeyDownEvent e) 
			{
				if (e.getNativeKeyCode() == KeyCodes.KEY_ENTER)
				{
					// TODO: Login here
				}
			}			
		});		

		pnlLogin.add(txtOpenId);		
		pnlLogin.setStyleName("loginBox");

		this.add(pnlLogin);
		this.setStyleName("homePage");
	}
}
