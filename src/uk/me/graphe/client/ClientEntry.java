package uk.me.graphe.client;

import uk.me.graphe.client.communications.ServerChannel;
import uk.me.graphe.client.dialogs.LoadingDialog;
import uk.me.graphe.shared.messages.UserAuthMessage;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;


public class ClientEntry implements EntryPoint
{
	public static final LoadingDialog loadingScreen = LoadingDialog.getInstance(null);
	
	private static final RootPanel screen = RootPanel.get("canvas");
	private static final RootPanel nav = RootPanel.get("nav");
	private static final HomePage login = new HomePage();
	private static final Graphemeui editor = new Graphemeui();

	@Override
	public void onModuleLoad ()
	{
		ClientOT.getInstance().connect();
		
        if (Window.Location.getParameter("action") == "userauth")
        {
        	loadingScreen.show("");

    		Timer timer = new Timer()
    		{
    		    public void run ()
    		    {
    		    	UserAuthMessage uam = new UserAuthMessage();
    		    	uam.setOpenIdUrl(Window.Location.getQueryString());
    		    	uam.setAuthKey(Window.Location.getParameter("authKey"));
    		    	uam.setId(Window.Location.getParameter("openid.identity"));
    				ServerChannel.getInstance().send(uam.toJson());
    			}
    		}; 
		    timer.schedule(1000);
		    
		    // TODO: Show graph list object here.....of course it won't show any graphs until you receive a response from here.
        }
        else if (Window.Location.getHash().substring(1) != "")
        {
            // TODO: Get graph here if logged in otherwise show login page.
        }
        else
        {
            screen.add(login);
        }
	}

	public static void displayGraphList (String list, String nList)
	{
		loadingScreen.hide();
		
		 Window.alert("trying to display graphs now john.");
	    Window.alert(list);
	    Window.alert(nList);	   
	}

	public static void displayGraph (int id)
	{
		loadingScreen.show("");

		// TODO: Remove graph list from screen here.
		
		editor.show();
		ClientOT.getInstance().requestGraph(id);
	}
}
