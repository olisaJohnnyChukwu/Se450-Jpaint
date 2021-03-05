package Shape;

import java.awt.Graphics2D;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;
import java.util.*;

import Command.ShapeList;
import Stategy.ImoveStrategy;
import Stategy.move;
import Stategy.negativeDisplacement;
import Stategy.positiveDisplacement;

public class Ellipse implements Ishape {
	private shapeProperties properties;
	
	private setColorMap map= new setColorMap();
	private  UUID uuid=UUID.randomUUID();
	private  String Id;
	
	

	
	public Ellipse(shapeProperties properties) {
		super();
		this.properties = properties;
		Id=uuid.toString();
	}

	@Override
	public void Draw(PaintCanvasBase canvas) {
		// TODO Auto-generated method stub
		Graphics2D graphics2d = canvas.getGraphics2D();
		EnumMap<ShapeColor,java.awt.Color> m=map.getMap();
		graphics2d.setColor(m.get(properties.getColor()));
		
		if (properties.getShade().equals(ShapeShadingType.OUTLINE)) {
			graphics2d.drawOval(properties.getX(), properties.getY(), properties.getWidth(),properties.getHeight());
		
		 }else if (properties.getShade().equals(ShapeShadingType.FILLED_IN)) {
        	graphics2d.fillOval(properties.getX(), properties.getY(), properties.getWidth(),properties.getHeight());
        	
        }else if (properties.getShade().equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
        	graphics2d.fillOval(properties.getX(), properties.getY(), properties.getWidth(),properties.getHeight());
        	graphics2d.drawOval(properties.getX(), properties.getY(), properties.getWidth(),properties.getHeight());
        	
        }
		

	}

	@Override
	public ShapeType ShapeName() {
		// TODO Auto-generated method stub
		return ShapeType.ELLIPSE;
	}

	@Override
	public shapeProperties getProperties() {
		// TODO Auto-generated method stub
		return properties;
	}
	
	public String getId() {
		// TODO Auto-generated method stub
		return Id;
	}

	@Override
	public void setProperties(shapeProperties properties) {
		this.properties=properties;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		Ishape that=(Ishape)o;
		
		return  getId().equals(that.getId());
	}

	
	@Override
	public void positivedisplacement(int dx,int dy){
		// TODO Auto-generated method stub
		move move=new move(dx,dy,this);
		ImoveStrategy moveStrategy=new positiveDisplacement();
		move.displace(moveStrategy);
		
	}

	
	@Override
	public void negativedisplacement(int dx,int dy) {
		// TODO Auto-generated method stub
		move move=new move(dx,dy,this);
		ImoveStrategy moveStrategy=new negativeDisplacement();
		move.displace(moveStrategy);
		
	}

	
	@Override
	public Ishape copy(Ishape shape,int x) {
		// TODO Auto-generated method stub
		shapeProperties prop=shape.getProperties();
		int a=prop.getX()+x;
		int b=prop.getY()-x;
		int w=prop.getWidth();
		int h=prop.getHeight();
		ShapeShadingType shade=prop.getShade();
		ShapeType shapetype=prop.getType();
		ShapeColor shapecolor=prop.getColor();
		shapeProperties prop2=new shapeProperties(a, b, w, h, shade, shapecolor, shapetype);
		IshapeFactory factory=new CreateShapeFactory();
		Ishape copy=factory.CreateShape2(prop2,shapetype);
		return copy;
	}

	@Override
	public void delete(Ishape shape, ShapeList shapelist) {
		// TODO Auto-generated method stub
		shapelist.RemoveShape(shape);
		
	}

	@Override
	public void addtolist(Ishape shape, ShapeList shapelist) {
		// TODO Auto-generated method stub
		shapelist.AddShape(shape);
		
	}
	

}
