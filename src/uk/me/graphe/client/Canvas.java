package uk.me.graphe.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Canvas extends Composite{
	private static UiBinderCanvas uiBinder = GWT.create(UiBinderCanvas.class);
	interface UiBinderCanvas extends UiBinder<Widget, Canvas> {}

	@UiField
	public CanvasWrapper canvasPanel;
	
	public final Graphemeui parent;
	
	public int lMouseDown[];
	private int lMouseMove[];
		
	private static final int X = 0, Y = 1;

	private boolean isMouseDown;
	
	public Canvas (Graphemeui gUI)
	{
		initWidget(uiBinder.createAndBindUi(this));
		this.parent = gUI;

		lMouseDown = new int[2];
		lMouseMove = new int[2];
	}
	
	@UiHandler("canvasPanel")
	void onMouseDown(MouseDownEvent e)
	{
		isMouseDown = true;
		
		lMouseDown[X] = getMouseX(e.getX());
		lMouseDown[Y] = getMouseY(e.getY());
		
		lMouseMove[X] = lMouseDown[X];
		lMouseMove[Y] = lMouseDown[Y];
		
		switch (parent.tools.currentTool)
		{
			case addEdge:
				if (!parent.selectedVertices.contains(parent.graphManager.getDrawableAt(lMouseDown[X], lMouseDown[Y])))
				{
					parent.toggleSelectedVertexAt(lMouseDown[X], lMouseDown[Y]);
				}
				parent.drawing.setUILine(lMouseDown[X], lMouseDown[Y], lMouseDown[X], lMouseDown[Y]);

				break;
			case move:
				if (parent.selectedVertices.size() < 1)
				{
					parent.toggleSelectedVertexAt(lMouseDown[X], lMouseDown[Y]); // try to select vertex.
				}
				break;
		}
		
		parent.tooltip.hide();
	}
	
	@UiHandler("canvasPanel")
	void onMouseMove(MouseMoveEvent e)
	{
		int x = getMouseX(e.getX());
        int y = getMouseY(e.getY());
        
		if (isMouseDown)
		{	 
            switch (parent.tools.currentTool)
            {
	            case addEdge:        	
	            	VertexDrawable vHover = parent.graphManager.getDrawableAt(x, y);
	            	if ((vHover == null) && (parent.selectedVertices.size() == 2))
	            	{
	            		parent.selectedVertices.get(1).setHilighted(false);
	            		parent.selectedVertices.remove(1);
	            	}
	            	else if (!parent.selectedVertices.contains(vHover))
					{
						parent.toggleSelectedVertexAt(x, y);
					}
	            	parent.drawing.setUILine(lMouseDown[X], lMouseDown[Y], x, y);
	            	parent.graphManager.invalidate();
	            	break;
	            case move:
					if (parent.selectedVertices.size() > 0)
					{
						for (VertexDrawable vd : parent.selectedVertices)
						{
							int xC = vd.getCenterX() -(lMouseMove[X] - x);
							int yC = vd.getCenterY() -(lMouseMove[Y] -y);
							
							parent.moveNode(vd, xC, yC);
						}
					}
					else
					{
						parent.pan(-(lMouseDown[X] - x), -(lMouseDown[Y] -y));
					}
					break;
            }
		}
		else
		{
			VertexDrawable vd = parent.graphManager.getDrawableAt(x, y);
			
			if (vd != null)
			{				
				parent.tooltip.setPopupPosition((e.getX() + 60), (e.getY() + 140));
				parent.tooltip.setText(vd.getLabel());
				parent.tooltip.show();
			}
			else
			{
				parent.tooltip.hide();
			}
		}
		
		lMouseMove[X] = x;
		lMouseMove[Y] = y;
	}
	
	@UiHandler("canvasPanel")
	void onMouseOut (MouseOutEvent e)
	{
		isMouseDown = false;
		parent.drawing.hideUIline();
		parent.tooltip.hide();
	}
	
	@UiHandler("canvasPanel")
	void onMouseUp (MouseUpEvent e)
	{
		isMouseDown = false;
		
		switch (parent.tools.currentTool)
		{
			case addVertex:
				parent.dialog.show(DialogType.vertexName,"", e.getX(), e.getY());
				break;
			case addEdge:
				parent.drawing.hideUIline();
				if (parent.selectedVertices.size() == 2) 
				{
					parent.dialog.show(DialogType.edgeWeight,"", e.getX(), e.getY());
				}				
				else if ((lMouseDown[X] != lMouseMove[X]) || (lMouseDown[Y] != lMouseMove[Y]))
				{
					parent.clearSelectedObjects();
				}
				
				parent.graphManager.invalidate();	
				break;
			case move:
				if (parent.selectedVertices.size() == 1)
				{
					parent.clearSelectedObjects(); //deselect the moved vertex if only one moved.
				}
				break;
			case select:
				if (e.isControlKeyDown())
				{
					parent.toggleSelectedObjectAt(lMouseDown[X], lMouseDown[Y]);
				}
				else
				{
					parent.clearSelectedObjects(); // clearing because we are selecting object on own.
					parent.toggleSelectedObjectAt(lMouseDown[X], lMouseDown[Y]);
				}
				break;
			case zoom:
				if (e.isControlKeyDown())
				{
					parent.zoomOut();
				}
				else
				{
					parent.zoomIn();
				}
				break;
		}
	}
	
	private int getMouseX (int x)
	{
		return (int)(x / parent.drawing.getZoom()) - parent.drawing.getOffsetX();
	}
	
	private int getMouseY (int y)
	{
		return (int)(y / parent.drawing.getZoom()) - parent.drawing.getOffsetY();
	}
}
