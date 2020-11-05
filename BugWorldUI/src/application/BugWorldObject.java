package application;

import javafx.scene.image.ImageView;

public interface BugWorldObject {
	public double getX();
	public double getY();
	public String getImagePath();
	public void setiView(ImageView iView);
	public ImageView getiView();
}
