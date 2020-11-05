package application;

import javafx.scene.image.ImageView;

public class Plant implements BugWorldObject{
	private double x;
	private double y;
	private String imagePath;
	private ImageView iView;
	
	public Plant(double x, double y, String imagePath) {
		this.x = x;
		this.y = y;
		this.imagePath = imagePath;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public ImageView getiView() {
		return iView;
	}

	public void setiView(ImageView iView) {
		this.iView = iView;
	}
}
