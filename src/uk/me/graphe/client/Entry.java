package uk.me.graphe.client;

import uk.me.graphe.client.Graphemeui;
import uk.me.graphe.client.UserPanel;
import uk.me.graphe.client.ClientOT;
import uk.me.graphe.client.dialogs.LoadingDialog;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;


public class Entry implements EntryPoint
{
	public final RootPanel mainPanel = RootPanel.get("canvas");
	public final RootPanel navButtons = RootPanel.get("nav");
	public final HomePage loginPage = new HomePage();
	//public final Graphlist graphsPage = new Graphlist();
	public final Graphemeui editorPage = new Graphemeui();
	
	public Entry ()
	{
		
	}
	
    public void onModuleLoad ()
    {
    	mainPanel.add(loginPage);
    	LoadingDialog d = new LoadingDialog(editorPage);
    	d.show("");
    	//editorPage.show();
    	//ClientOT.getInstance().requestGraph();
    	/*ClientOT.getInstance().connect();
        
        
        
        if(Window.Location.getParameter("action") == "userauth"){
        	
        	UserPanel.verify();
            
        }else if(Window.Location.getHash().substring(1) != ""){
        	editorPage.show();
        	ClientOT.getInstance().requestGraph();
        }else{
        	UserPanel.show();
    	
        }*/
    	
    }
	
	
}
