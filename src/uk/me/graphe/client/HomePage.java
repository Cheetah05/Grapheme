package uk.me.graphe.client;

import uk.me.graphe.client.communications.ServerChannel;
import uk.me.graphe.shared.messages.UserAuthMessage;

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
		            if (txtOpenId.getText().trim().equals(""))
		            {
		            	// TODO: Display some error here.
		            }
		            else
		            {
		            	ClientEntry.loadingScreen.show("");
			        	UserAuthMessage uam = new UserAuthMessage(txtOpenId.getText());
						ServerChannel.getInstance().send(uam.toJson());
		            }
				}
			}			
		});		

		pnlLogin.add(txtOpenId);		
		pnlLogin.setStyleName("loginBox");

		this.add(pnlLogin);
		this.setStyleName("homePage");
	}
}
