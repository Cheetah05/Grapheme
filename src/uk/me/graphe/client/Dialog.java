package uk.me.graphe.client;

import uk.me.graphe.shared.Tools;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Dialog extends PopupPanel
{
	private final Graphemeui parent;
	private final VerticalPanel pnlCont;
	//private final HorizontalPanel pnlBtns;
	private final Label lblTitle;
	private final TextBox txtParam;
	//private final Button btnOk, btnCancel;
	
	private boolean isOk;
	
	private DialogType currentType;
	
	public Dialog (Graphemeui gUI)
	{
		this.parent = gUI;
		
		this.addStyleName("helpDialog");
		this.addStyleName("paramDialog");
		this.addStyleName("errorDialog");
		
		this.setAnimationEnabled(true);
				
		pnlCont = new VerticalPanel();
		//pnlBtns = new HorizontalPanel();
		lblTitle = new Label();
		txtParam = new TextBox();
		/*btnOk = new Button("Set");
		btnCancel = new Button("Cancel");
		
		pnlBtns.add(btnOk);
		pnlBtns.add(btnCancel);
		
		btnOk.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent arg0)
			{
				ok();
			}			
		});
		
		btnCancel.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent arg0)
			{
				cancel();
			}			
		});*/
		
		lblTitle.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent arg0)
			{
				if ((parent.dialog.getType() == DialogType.help) || (parent.dialog.getType() == DialogType.error))
				{
					parent.isHotkeysEnabled = true;
					parent.dialog.hide();
				}
			}			
		});
		
		txtParam.addKeyDownHandler(new KeyDownHandler()
		{
			@Override
			public void onKeyDown(KeyDownEvent arg0)
			{
				switch (currentType)
				{
					case edgeWeight:
						// TODO: text validation
						break;
					case graphName:
						// TODO: text validation
						break;
					case vertexName:
						// TODO: text validation
						
						break;
				}				
			}
		});
		
		txtParam.addKeyUpHandler(new KeyUpHandler()
		{
			public void onKeyUp(KeyUpEvent e)
			{
				if (e.getNativeKeyCode() == KeyCodes.KEY_ESCAPE)
				{
					cancel();
				}
				else if (e.getNativeKeyCode() == KeyCodes.KEY_ENTER)
				{
					ok();
				}
				else
				{
					switch (currentType)
					{
						case edgeWeight:
							break;
						case graphName:
							if (txtParam.getText().trim().length() > 0)
							{
								//btnOk.setEnabled(true);
								isOk = true;
							}
							else
							{
								//btnOk.setEnabled(false);
								isOk = false;
							}
							break;
						case vertexName:
							if (txtParam.getText().trim().length() > 0)
							{
								//btnOk.setEnabled(true);
								isOk = true;
							}
							else
							{
								//btnOk.setEnabled(false);
								isOk = false;
							}
							break;
					}				
				}
			}
		});
		
		this.add(pnlCont);
	}

	public void show (DialogType type, String initialValue, int left, int top)
	{		
		if (this.isVisible()) this.hide();
		
		pnlCont.clear();
		
		currentType = type;
		
		switch (type)
		{
			case edgeWeight:
				parent.isHotkeysEnabled = false;
				this.setTitle("Edge weight");
				this.setAutoHideEnabled(false);
				this.setGlassEnabled(true);
				this.setStyleName("paramDialog");
				lblTitle.setText("Edge weight:");
				txtParam.setText(initialValue);
				pnlCont.add(lblTitle);
				pnlCont.add(txtParam);
				/*pnlCont.add(pnlBtns);
				btnOk.setText("Add");
				btnOk.setEnabled(true);*/
				isOk = true;
				this.setPopupPosition(left, top);
				break;
			case error:
				parent.isHotkeysEnabled = false;
				this.setTitle("Error occured");
				this.setAutoHideEnabled(true);
				this.setGlassEnabled(true);
				this.setStyleName("errorDialog");
				lblTitle.setText(initialValue);
				pnlCont.add(lblTitle);
				this.center();
				break;
			case graphName:
				parent.isHotkeysEnabled = false;
				this.setTitle("Graph name");
				this.setAutoHideEnabled(false);
				this.setGlassEnabled(true);
				this.setStyleName("paramDialog");
				lblTitle.setText("Graph name:");
				txtParam.setText(initialValue);				
				if (initialValue.length() > 0)
				{
					//btnOk.setEnabled(true);
					isOk = true;
				}
				else
				{
					//btnOk.setEnabled(false);
					isOk = false;
				}	
				pnlCont.add(lblTitle);
				pnlCont.add(txtParam);
				//pnlCont.add(pnlBtns);
				this.center();
				break;
			case help:
				parent.isHotkeysEnabled = true;
				this.setTitle("Help");
				this.setAutoHideEnabled(true);
				this.setGlassEnabled(false);
				this.setStyleName("helpDialog");
				lblTitle.setText(initialValue);
				pnlCont.add(lblTitle);
				this.setPopupPosition(left, top);
				break;
			case vertexName:
				parent.isHotkeysEnabled = false;
				this.setTitle("Vertex name");
				this.setAutoHideEnabled(false);
				this.setGlassEnabled(true);
				this.setStyleName("paramDialog");
				lblTitle.setText("Vertex name:");
				txtParam.setText(initialValue);				
				if (initialValue.length() > 0)
				{
					//btnOk.setEnabled(true);
					isOk = true;
				}
				else
				{
					//btnOk.setEnabled(false);
					isOk = false;
				}		
				pnlCont.add(lblTitle);
				pnlCont.add(txtParam);
				//pnlCont.add(pnlBtns);
				this.setPopupPosition(left, top);
				break;
		}
		
		this.show();
		
		txtParam.setFocus(true);
	}
	
	public DialogType getType()
	{
		return currentType;
	}
	
	private void ok()
	{
		if (isOk)
		{
			parent.isHotkeysEnabled = true;
			parent.dialog.hide();
			
			switch (currentType)
			{
				case edgeWeight:
					parent.addEdge(parent.selectedVertices.get(0), parent.selectedVertices.get(1), Integer.parseInt(txtParam.getText()));
					parent.clearSelectedObjects();
					parent.tools.setTool(Tools.addEdge);
					break;
				case graphName:
					// TODO: set the graph name here
					break;
				case vertexName:
					parent.addVertex(txtParam.getText());
					parent.tools.setTool(Tools.addVertex);
					break;
			}
		}
	}
	
	private void cancel()
	{
		parent.isHotkeysEnabled = true;
		parent.dialog.hide();		
	}
}
