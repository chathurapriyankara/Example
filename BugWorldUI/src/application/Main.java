package application;
	
import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;

public class Main extends Application {
	private ArrayList<BugWorldObject> objects = new ArrayList<>();
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 600;
	
	public Main() {
		addThings();
	}
	
	private void addThings() {
		objects.add(new Bee(100,150,"img/bee.jpg"));
		objects.add(new Ant(100,200,"img/ant.jpg"));
		objects.add(new Bee(100,150,"img/bee.jpg"));
		objects.add(new Ant(100,200,"img/ant.jpg"));
		objects.add(new Bee(100,150,"img/bee.jpg"));
		objects.add(new Ant(100,200,"img/ant.jpg"));
		objects.add(new Tree(50,250,"img/tree.jpg"));
		objects.add(new Grass(400,400,"img/grass.png"));
		objects.add(new Tree(500,100,"img/tree.jpg"));
		objects.add(new Grass(50,60,"img/grass.png"));
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Bug World");
		Group viewGroup = new Group();
		try {
			Timeline tl = new Timeline();
			for(BugWorldObject obj: objects) {
				ImageView i = getImage(obj);
				obj.setiView(i);
				viewGroup.getChildren().add(i);
				if(obj instanceof Bug) {
					//Build the animation
					KeyFrame frame = getFrame(i, (Bug)obj);
					tl.getKeyFrames().add(frame);
				}
			}
			tl.setCycleCount(javafx.animation.Animation.INDEFINITE);
			tl.play();
			Scene scene = new Scene(viewGroup,WINDOW_WIDTH,WINDOW_HEIGHT);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Create images at the bug positions and bug image path
	private ImageView getImage(BugWorldObject obj) {
		try {
			FileInputStream input  = new FileInputStream(obj.getImagePath());
			Image image = new Image(input);
			ImageView imageView = new ImageView(image);
			imageView.setX(obj.getX());
			imageView.setY(obj.getY());
			imageView.setFitHeight(40);
			imageView.setFitWidth(40);
			return imageView;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private KeyFrame getFrame(ImageView iv, Bug b) {
		KeyFrame frame = new KeyFrame(Duration.millis(15), e->{
			Bounds boundsInScene = iv.localToScene(iv.getBoundsInLocal());
			double dy = 0;
			double dx = 0;
			int r  = b.getRandomDir();
			
			if(r == 1) {
				dy = -1;
				dx = 0;
			} else if(r == 2) {
				dy = -1;
				dx = 1;
			} else if(r == 3) {
				dx = 1;
				dy = 0;
			} else if(r == 4) {
				dy = 1;
				dx = 1;
			} else if(r == 5) {
				dx = 0;
				dy = 1;
			} else if(r == 6) {
				dx = -1;
				dy = 1;
			} else if(r == 7) {
				dy = 0;
				dx = -1;
			} else if(r == 8) {
				dy = -1;
				dx = -1;
			}		
			
			if(boundsInScene.getMinY() <= 0 && (r==8||r==1 || r==2)) {
				b.setRandomDir((int) (Math.random()*7)+1);
			} else if(boundsInScene.getMinX() >= 560 && (r==2||r==3 || r==4)) {
				b.setRandomDir((int) (Math.random()*7)+1);
			} else if(boundsInScene.getMinY() >= 560 && (r==4||r==5 || r==6)) {
				b.setRandomDir((int) (Math.random()*7)+1);
			} else if(boundsInScene.getMinX() < 0 && (r==6||r==7 || r==8)) {
				b.setRandomDir((int) (Math.random()*7)+1);
			}
			
			
			b.setY(boundsInScene.getMinY());
			b.setX(boundsInScene.getMinX());
			
			//This will move the bug within the frame
			iv.setTranslateX(iv.getTranslateX()+dx);
			iv.setTranslateY(iv.getTranslateY()+dy);
			
//			Bounds b1 = iv.getBoundsInParent();
//			for(BugWorldObject obj: objects) {
//				Bounds b2 = obj.getiView().getBoundsInParent();
//				if(b != obj && b1.intersects(b2)) {
//					
//				}
//			}
		});
		
		return frame;
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
