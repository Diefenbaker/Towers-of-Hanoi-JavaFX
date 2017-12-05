package towersPackage;

import java.io.IOException;
import java.util.ArrayList;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialogs;











import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@SuppressWarnings("deprecation")
public class Towers extends Application {

	@FXML
	private Button t1Button, t2Button, t3Button, undo;
	
	@FXML
	private CheckBox music;
	 
	@FXML
	private Label brick1, brick2, brick3, brick4, brick5, brick6, brick7,
			brick8;
	@FXML
	private VBox stack1, stack2, stack3;
	@FXML
	private BorderPane root;
	@FXML
	private TextField brickText;
	@FXML
	private Button brickOK, reset;
	int moveCount = 0;
	@FXML
	private Label counter;
	
	ObservableList<Object> snapshotLeft = FXCollections.observableArrayList();
	ObservableList<Object> snapshotCentre = FXCollections.observableArrayList();
	ObservableList<Object> snapshotRight = FXCollections.observableArrayList();
	
	
	public void saveMove(){
		
		
		ObservableList<Node> s1 = FXCollections.observableArrayList();
		ObservableList<Node> s2 = FXCollections.observableArrayList();
		ObservableList<Node> s3 = FXCollections.observableArrayList();
		
		if(s1.isEmpty() == false){
		for(int i=1; i<stack1.getChildren().size(); i++){
			s1.add(stack1.getChildren().get(i));
		}
		}
		
		if(s2.isEmpty() == false){
		for(int i=1; i<stack1.getChildren().size(); i++){
			s2.add(stack2.getChildren().get(i));
		}
		}
		
		if(s3.isEmpty() == false){
		for(int i=1; i<stack1.getChildren().size(); i++){
			s3.add(stack3.getChildren().get(i));
		}
		}
		snapshotLeft.add(s1);
		snapshotCentre.add(s2);
		snapshotRight.add(s3);
		
		System.out.println(snapshotLeft.toString());
		System.out.println(snapshotCentre.toString());
		
		
	}
	
	@FXML
	private void undo(ActionEvent e){
		
		if(e.getSource() == undo){
		
			int index = moveCount-1;
			
			ObservableList<Node> t1Bricks = stack1.getChildren();		
			ObservableList<Node> t2Bricks = stack2.getChildren();		//Finding children of VBoxes and entering them into observable lists
			ObservableList<Node> t3Bricks = stack3.getChildren();	
			
			t1Bricks.clear();
			t2Bricks.clear();
			t3Bricks.clear();
			
			t1Bricks.addAll((Node) snapshotLeft.get(index));
		
					
			
		moveCount--;
		
		}
		
		
	}
	
	
	
	/*
	 * DRAG METHOD still work in progress. Contains bug that I can't locate - 
	 * When this method is called and when bricks are in Tower 1 their ID's are not correct, making it impossible 
	 * to find if the brick being dragged is the top brick in Tower. What is happening is that all the ID's are +1, 
	 * except for the last brick in tower which is correct ID. 
	 */
	
	@FXML
	private void dragMove(MouseEvent e){
		
		ObservableList<Node> t1Bricks = stack1.getChildren();		
		ObservableList<Node> t2Bricks = stack2.getChildren();		//Finding children of VBoxes and entering them into observable lists
		ObservableList<Node> t3Bricks = stack3.getChildren();
		
		if((e.getSource() == brick1) || (e.getSource() == brick2) || (e.getSource() == brick3) || (e.getSource() == brick4) || 
				(e.getSource() == brick5) || (e.getSource() == brick6) || (e.getSource() == brick7) || (e.getSource() == brick8)){
			
			Label temp = (Label) e.getSource();
			VBox tempVBox = (VBox) temp.getParent();
			System.out.println(temp.getId());
			
			if(temp.getWidth() == ((Label) tempVBox.getChildren().get(0)).getWidth()){
				
		}
			else{
				Dialogs.create()
				.owner(root)
				.title("Not Possible....")
				.masthead(null)
				.message(
						"Only the top brick may be picked up.")
				.showInformation();
			}
			
			
			
			
		}
		
		
		
	}
	
	
	
	
	
	
	
	@FXML
	private void towerClick(MouseEvent e){
		
		counter.setText("Moves: " + moveCount);			//Adding text to label displaying amount of moves
		
	
		ObservableList<Node> t1Bricks = stack1.getChildren();		
		ObservableList<Node> t2Bricks = stack2.getChildren();		//Finding children of VBoxes and entering them into observable lists
		ObservableList<Node> t3Bricks = stack3.getChildren();
		
		
		
		
		if(e.getSource()==stack1) {							//if the VBox clicked is that containing Tower 1
			if (t2Button.getText() == "Active"				//check to see if Tower 2 button or Tower 3 button has text set to "Active"
					|| t3Button.getText() == "Active") {
				if (t2Button.getText() == "Active") {		//if one of them are set to active, find out if it's Tower 2 button

					if (t1Bricks.isEmpty() == false) {		//if Tower 1 is not empty.....	
															//run a logic test, ie make sure the brick being added is not larger than the current top brick
						Node temp2 = t2Bricks.get(0);			//creating a node and assigning the top bricks attributes to it.....
						String a = t1Bricks.get(0).getId();		//Creating four strings, one each for both ID's....
						String b = temp2.getId();				
						String num1 = Character.toString(a.charAt(5));		//and one each to store the sixth character of the ID's
						String num2 = Character.toString(b.charAt(5));		//ie the number - brick6 - 6 would be assigned to string....

						int one = Integer.parseInt(num1);			//parsing the numbers stored in strings to two integers
						int two = Integer.parseInt(num2);

						if (two < one) {							//running test, if the chosen brick is smaller than the top brick...
							stack1.getChildren().add(0, temp2);		//add the chosen brick to Tower 1's bricks....
							t2Button.setText("Tower 2");			//reset Tower 2 buttons text
							moveCount++;							//increment the move counter
							counter.setText("Moves: " + moveCount);	//set the new move counter value to label in game...
							resetButtons();							//calling method that resets button colours to white
						} else {									//else if the chosen brick is bigger than top brick
							Dialogs.create()
									.owner(root)
									.title("Not Possible....")
									.masthead(null)					//send an error message to user indicating problem
									.message(
											"You cannot place on a smaller brick")
									.showInformation();
							resetButtons();							//reset colours
							t2Button.setText("Tower 2");			//reset text
						}
					} else {										//else if Tower 1 is empty
						Node temp2 = t2Bricks.get(0);				//add brick without running size test
						stack1.getChildren().add(0, temp2);
						t2Button.setText("Tower 2");
						moveCount++;
						resetButtons();
						counter.setText("Moves: " + moveCount);
					}

				} else{			//else if Tower 3 button text is set as "Active" repeat the above while changing the VBoxes etc

					if (t1Bricks.isEmpty() == false) {

						Node temp3 = t3Bricks.get(0);
						String a = t1Bricks.get(0).getId();
						String b = temp3.getId();
						String num1 = Character.toString(a.charAt(5));
						String num2 = Character.toString(b.charAt(5));

						int one = Integer.parseInt(num1);
						int two = Integer.parseInt(num2);

						if (two < one) {
							stack1.getChildren().add(0, temp3);
							t3Button.setText("Tower 3");
							moveCount++;
							resetButtons();
							counter.setText("Moves: " + moveCount);
						} else {
							Dialogs.create()
									.owner(root)
									.title("Not Possible....")
									.masthead(null)
									.message(
											"You cannot place on a smaller brick")
									.showInformation();
							resetButtons();
							t2Button.setText("Tower 2");
						}
					} else {
						Node temp3 = t3Bricks.get(0);
						stack1.getChildren().add(0, temp3);
						t3Button.setText("Tower 3");
						moveCount++;
						resetButtons();
						counter.setText("Moves: " + moveCount);
					}
				}
			} else {
				if (t1Bricks.isEmpty() == true) {
					Dialogs.create().owner(root).title("Not Possible....")
							.masthead(null).message("This Tower is empty!")
							.showInformation();
					
				} else {
					t1Button.setText("Active");
					t1Button.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
					t2Button.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, null, null)));
					t3Button.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, null, null)));
					
				}
			}
		}
		
		stack2.setOnMouseClicked(f -> {			//if Tower 2 is clicked, same as above

			if (t1Button.getText() == "Active"
					|| t3Button.getText() == "Active") {

				if (t1Button.getText() == "Active") {

					if (t2Bricks.isEmpty() == false) {

						Node temp = t1Bricks.get(0);
						String a = t2Bricks.get(0).getId();
						String b = temp.getId();

						String num1 = Character.toString(a.charAt(5));
						String num2 = Character.toString(b.charAt(5));

						int one = Integer.parseInt(num1);
						int two = Integer.parseInt(num2);

						if (two < one) {
							stack2.getChildren().add(0, temp);
							t1Button.setText("Tower 1");
							moveCount++;
							resetButtons();
							counter.setText("Moves: " + moveCount);
							gameStatus();
							
						} else {
							Dialogs.create()
									.owner(root)
									.title("Not Possible....")
									.masthead(null)
									.message(
											"You cannot place on a smaller brick")
									.showInformation();
							resetButtons();
							t1Button.setText("Tower 1");
						}
					} else {
						Node temp = t1Bricks.get(0);
						stack2.getChildren().add(0, temp);
						t1Button.setText("Tower 1");
						moveCount++;
						resetButtons();
						counter.setText("Moves: " + moveCount);
						gameStatus();
					}
				} else {
					if (t2Bricks.isEmpty() == false) {

						Node temp3 = t3Bricks.get(0);
						String a = t2Bricks.get(0).getId();
						String b = temp3.getId();

						String num1 = Character.toString(a.charAt(5));
						String num2 = Character.toString(b.charAt(5));

						int one = Integer.parseInt(num1);
						int two = Integer.parseInt(num2);

						if (two < one) {
							stack2.getChildren().add(0, temp3);
							t3Button.setText("Tower 3");
							moveCount++;
							resetButtons();
							counter.setText("Moves: " + moveCount);
							gameStatus();
						} else {
							Dialogs.create()
									.owner(root)
									.title("Not Possible....")
									.masthead(null)
									.message(
											"You cannot place on a smaller brick")
									.showInformation();
							resetButtons();
							t3Button.setText("Tower 3");
						}
					} else {
						Node temp = t3Bricks.get(0);
						stack2.getChildren().add(temp);
						t3Button.setText("Tower 3");
						moveCount++;
						resetButtons();
						counter.setText("Moves: " + moveCount);
						gameStatus();
					}
				}
			} else {
				if (t2Bricks.isEmpty() == true) {
					Dialogs.create().owner(root).title("Not Possible....")
							.masthead(null).message("This Tower is empty!")
							.showInformation();
				} else {
					t2Button.setText("Active");
					t2Button.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
					t1Button.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, null, null)));
					t3Button.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, null, null)));
				}
			}
		});

		// ///////////////////////////////////////////////////////////

		stack3.setOnMouseClicked(g -> {		//if Tower 3 is clicked, same as above

			if (t1Button.getText() == "Active"
					|| t2Button.getText() == "Active") {

				if (t1Button.getText() == "Active") {

					if (t3Bricks.isEmpty() == false) {

						Node temp = t1Bricks.get(0);
						String a = t3Bricks.get(0).getId();
						String b = temp.getId();

						String num1 = Character.toString(a.charAt(5));
						String num2 = Character.toString(b.charAt(5));

						int one = Integer.parseInt(num1);
						int two = Integer.parseInt(num2);

						if (two < one) {
							stack3.getChildren().add(0, temp);
							t1Button.setText("Tower 1");
							moveCount++;
							resetButtons();
							counter.setText("Moves: " + moveCount);
						} else {
							Dialogs.create()
									.owner(root)
									.title("Not Possible....")
									.masthead(null)
									.message(
											"You cannot place on a smaller brick")
									.showInformation();
							resetButtons();
							t1Button.setText("Tower 1");
						}
					} else {
						Node temp = t1Bricks.get(0);
						stack3.getChildren().add(0, temp);
						t1Button.setText("Tower 1");
						moveCount++;
						resetButtons();
						counter.setText("Moves: " + moveCount);
					}
				} else {
					if (t3Bricks.isEmpty() == false) {

						Node temp = t2Bricks.get(0);
						String a = t3Bricks.get(0).getId();
						String b = temp.getId();

						String num1 = Character.toString(a.charAt(5));
						String num2 = Character.toString(b.charAt(5));

						int one = Integer.parseInt(num1);
						int two = Integer.parseInt(num2);

						if (two < one) {
							t3Bricks.add(0, temp);
							t2Button.setText("Tower 2");
							moveCount++;
							resetButtons();
							counter.setText("Moves: " + moveCount);
						} else {
							Dialogs.create()
									.owner(root)
									.title("Not Possible....")
									.masthead(null)
									.message(
											"You cannot place on a smaller brick")
									.showInformation();
							resetButtons();
							t2Button.setText("Tower 2");
						}
					} else {
						Node temp = t2Bricks.get(0);
						stack3.getChildren().add(temp);
						t2Button.setText("Tower 2");
						moveCount++;
						resetButtons();
						counter.setText("Moves: " + moveCount);
					}
				}
			} else {
				if (t3Bricks.isEmpty() == true) {
					Dialogs.create().owner(root).title("Not Possible....")
							.masthead(null).message("This Tower is empty!")
							.showInformation();
				} else {
					t3Button.setText("Active");
					t3Button.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
					t1Button.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, null, null)));
					t2Button.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, null, null)));
				}
			}
		});
			
	}
	
	
	
	
	
	/*
	 * Creating a method that will allow music to play and pause in app through use of CheckBox,
	 * if box is ticked music plays, else music is paused. 
	 * CONTAINS BUG(box needs to be ticked, unticked and ticked again before music launches)	
	 */
	@FXML
	public void playMusic(){		
		
		Object resource = getClass().getResource("Jakatta - The Other World.mp3"); 
		Media track = new Media(resource.toString());		
		MediaPlayer musicPlayer = new MediaPlayer(track);
		
		EventHandler<ActionEvent> playMusic = e -> {
		if(music.isSelected()){
			musicPlayer.play();
		}
		else{
			musicPlayer.pause();
		}
		};
		
		music.setOnAction(playMusic);
		
	}
	
	
	
	
	/*
	 * Method that checks if the game has been completed. This method is called any time a brick is added to 
	 * Tower 2. It checks by seeing if Tower 1 and Tower 3 are both empty, if they are all bricks then must be
	 * in Tower 2, meaning the user has finished the game. Sends a message to user when criteria met.
	 *
	 */
public void gameStatus(){
		
		ObservableList<Node> t1Bricks = stack1.getChildren();
		ObservableList<Node> t3Bricks = stack3.getChildren();
		
		if(t1Bricks.isEmpty()==true && t3Bricks.isEmpty()==true){
		
			Dialogs.create()
			.owner(root)
			.title("Congratulations!")
			.masthead(null)
			.message(
					"You completed the game in "+moveCount+" moves.")
			.showInformation();
			
		}

}
		

public void resetButtons(){
	
	t1Button.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, null, null)));
	t2Button.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, null, null)));		//setting tower buttons to a uniform colour, showing none are active
	t3Button.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, null, null)));
	
}



	

/*
 * This method is identical to the towerClick in most ways, aside from adding to ObservableLists rather than directly to VBoxes and
 * one or two other minute differences. Styling and actions are identical.
 */

@FXML
private void clickMove(ActionEvent e) {

	counter.setText("Moves: " + moveCount);			//Adding text to label displaying amount of moves
	
	resetButtons();

	ObservableList<Node> t1Bricks = stack1.getChildren();
	ObservableList<Node> t2Bricks = stack2.getChildren();					//Finding children of VBoxes and entering them into observable lists
	ObservableList<Node> t3Bricks = stack3.getChildren();
	

	if (e.getSource() == t1Button) {			//if Tower 1 button is pressed
		if (t2Button.getText() == "Active"				//finding if Tower 2 or Tower 3 buttons are set as active
				|| t3Button.getText() == "Active") { 	//if a button is marked as active,
			if (t2Button.getText() == "Active") {		//and it's Tower 1

				if (t1Bricks.isEmpty() == false) {		//if Tower 1 is not empty

					Node temp2 = t2Bricks.get(0);
					String a = t1Bricks.get(0).getId();
					String b = temp2.getId();
					String num1 = Character.toString(a.charAt(5));
					String num2 = Character.toString(b.charAt(5));

					int one = Integer.parseInt(num1);
					int two = Integer.parseInt(num2);

					if (two < one) {
						t1Bricks.add(0, temp2);
						t2Button.setText("Tower 2");
						moveCount++;
						counter.setText("Moves: " + moveCount);
						gameStatus();
						saveMove();
					} else {
						Dialogs.create()
								.owner(root)
								.title("Not Possible....")
								.masthead(null)
								.message(
										"You cannot place on a smaller brick")
								.showInformation();
						t2Button.setText("Tower 2");
					}
				} else {
					Node temp2 = t2Bricks.get(0);
					t1Bricks.add(0, temp2);
					t2Button.setText("Tower 2");
					moveCount++;
					counter.setText("Moves: " + moveCount);
					gameStatus();
					saveMove();
				}

			} else {

				if (t1Bricks.isEmpty() == false) {

					Node temp3 = t3Bricks.get(0);
					String a = t1Bricks.get(0).getId();
					String b = temp3.getId();
					String num1 = Character.toString(a.charAt(5));
					String num2 = Character.toString(b.charAt(5));

					int one = Integer.parseInt(num1);
					int two = Integer.parseInt(num2);

					if (two < one) {
						t1Bricks.add(0, temp3);
						t3Button.setText("Tower 3");
						moveCount++;
						counter.setText("Moves: " + moveCount);
						gameStatus();
						saveMove();
					} else {
						Dialogs.create()
								.owner(root)
								.title("Not Possible....")
								.masthead(null)
								.message(
										"You cannot place on a smaller brick")
								.showInformation();
						t2Button.setText("Tower 2");
					}
				} else {
					Node temp3 = t3Bricks.get(0);
					t1Bricks.add(0, temp3);
					t3Button.setText("Tower 3");
					moveCount++;
					counter.setText("Moves: " + moveCount);
					gameStatus();
					saveMove();
				}
			}
		} else {
			if (t1Bricks.isEmpty() == true) {
				Dialogs.create().owner(root).title("Not Possible....")
						.masthead(null).message("This Tower is empty!")
						.showInformation();
			} else {
				t1Button.setText("Active");
				t1Button.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
				t2Button.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, null, null)));
				t3Button.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, null, null)));
			}
		}
	}

	t2Button.setOnMouseClicked(f -> {

		if (t1Button.getText() == "Active"
				|| t3Button.getText() == "Active") {

			if (t1Button.getText() == "Active") {

				if (t2Bricks.isEmpty() == false) {

					Node temp = t1Bricks.get(0);
					String a = t2Bricks.get(0).getId();
					String b = temp.getId();

					String num1 = Character.toString(a.charAt(5));
					String num2 = Character.toString(b.charAt(5));

					int one = Integer.parseInt(num1);
					int two = Integer.parseInt(num2);

					if (two < one) {
						t2Bricks.add(0, temp);
						t1Button.setText("Tower 1");
						moveCount++;
						counter.setText("Moves: " + moveCount);
						gameStatus();
						saveMove();
					} else {
						Dialogs.create()
								.owner(root)
								.title("Not Possible....")
								.masthead(null)
								.message(
										"You cannot place on a smaller brick")
								.showInformation();
						t1Button.setText("Tower 1");
					}
				} else {
					Node temp = t1Bricks.get(0);
					t2Bricks.add(0, temp);
					t1Button.setText("Tower 1");
					moveCount++;
					counter.setText("Moves: " + moveCount);
					gameStatus();
					saveMove();
				}
			} else {
				if (t2Bricks.isEmpty() == false) {

					Node temp3 = t3Bricks.get(0);
					String a = t2Bricks.get(0).getId();
					String b = temp3.getId();

					String num1 = Character.toString(a.charAt(5));
					String num2 = Character.toString(b.charAt(5));

					int one = Integer.parseInt(num1);
					int two = Integer.parseInt(num2);

					if (two < one) {
						t2Bricks.add(0, temp3);
						t3Button.setText("Tower 3");
						moveCount++;
						counter.setText("Moves: " + moveCount);
						gameStatus();
						saveMove();
					} else {
						Dialogs.create()
								.owner(root)
								.title("Not Possible....")
								.masthead(null)
								.message(
										"You cannot place on a smaller brick")
								.showInformation();
						t3Button.setText("Tower 3");
					}
				} else {
					Node temp = t3Bricks.get(0);
					t2Bricks.add(temp);
					t3Button.setText("Tower 3");
					moveCount++;
					counter.setText("Moves: " + moveCount);
					gameStatus();
					saveMove();
				}
			}
		} else {
			if (t2Bricks.isEmpty() == true) {
				Dialogs.create().owner(root).title("Not Possible....")
						.masthead(null).message("This Tower is empty!")
						.showInformation();
			} else {
				t2Button.setText("Active");
				t2Button.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
				t1Button.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, null, null)));
				t3Button.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, null, null)));
			}
		}
	});

	// ///////////////////////////////////////////////////////////

	t3Button.setOnMouseClicked(g -> {

		if (t1Button.getText() == "Active"
				|| t2Button.getText() == "Active") {

			if (t1Button.getText() == "Active") {

				if (t3Bricks.isEmpty() == false) {

					Node temp = t1Bricks.get(0);
					String a = t3Bricks.get(0).getId();
					String b = temp.getId();

					String num1 = Character.toString(a.charAt(5));
					String num2 = Character.toString(b.charAt(5));

					int one = Integer.parseInt(num1);
					int two = Integer.parseInt(num2);

					if (two < one) {
						t3Bricks.add(0, temp);
						t1Button.setText("Tower 1");
						moveCount++;
						counter.setText("Moves: " + moveCount);
						gameStatus();
						saveMove();
					} else {
						Dialogs.create()
								.owner(root)
								.title("Not Possible....")
								.masthead(null)
								.message(
										"You cannot place on a smaller brick")
								.showInformation();
						t1Button.setText("Tower 1");
					}
				} else {
					Node temp = t1Bricks.get(0);
					t3Bricks.add(0, temp);
					t1Button.setText("Tower 1");
					moveCount++;
					counter.setText("Moves: " + moveCount);
					gameStatus();
					saveMove();
				}
			} else {
				if (t3Bricks.isEmpty() == false) {

					Node temp = t2Bricks.get(0);
					String a = t3Bricks.get(0).getId();
					String b = temp.getId();

					String num1 = Character.toString(a.charAt(5));
					String num2 = Character.toString(b.charAt(5));

					int one = Integer.parseInt(num1);
					int two = Integer.parseInt(num2);

					if (two < one) {
						t3Bricks.add(0, temp);
						t2Button.setText("Tower 2");
						moveCount++;
						counter.setText("Moves: " + moveCount);
						gameStatus();
						saveMove();
					} else {
						Dialogs.create()
								.owner(root)
								.title("Not Possible....")
								.masthead(null)
								.message(
										"You cannot place on a smaller brick")
								.showInformation();
						t2Button.setText("Tower 2");
					}
				} else {
					Node temp = t2Bricks.get(0);
					t3Bricks.add(temp);
					t2Button.setText("Tower 2");
					moveCount++;
					counter.setText("Moves: " + moveCount);
					gameStatus();
					saveMove();
				}
			}
		} else {
			if (t3Bricks.isEmpty() == true) {
				Dialogs.create().owner(root).title("Not Possible....")
						.masthead(null).message("This Tower is empty!")
						.showInformation();
			} else {
				t3Button.setText("Active");
				t3Button.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
				t1Button.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, null, null)));
				t2Button.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, null, null)));
			}
		}
	});
}
	
	

	/*
	 * Method that allows the user to select the number of bricks to use in game (min of 3, max of 8). 
	 * The method also includes the reset function, which will revert to the users selected amount of 
	 * bricks if applicable.
	 */
	@FXML
	private void brickSelect(ActionEvent e) {

		if (e.getSource() == brickOK) {		//if the OK brick relating to the users input is clicked

			ObservableList<Node> t1Bricks = stack1.getChildren();	//Creating an observable list which will be used to add bricks later

			String inputText = brickText.getText();		//taking in what is stored in the textfield in game

			if (inputText.isEmpty() == false) {		//if user has entered an amount...

				int inputNum = Integer.parseInt(inputText);	//parse the string to an int....

				if (inputNum < 3 || inputNum > 8) {			//if the user has entered a number less than 3 or more than 8....
					Dialogs.create()
							.owner(root)
							.title("Not Possible....")
							.masthead(null)
							.message(						//send user a message telling them of the mistake
									"Please check your selected number of bricks.")
							.showInformation();
				
				} else {	//otherwise

					stack1.getChildren().clear();
					stack2.getChildren().clear();		//clear all three Towers of their bricks....
					stack3.getChildren().clear();
					
					resetButtons();						//reset Button colours
					
					t1Button.setText("Tower 1");
					t2Button.setText("Tower 2");		//reset Button text
					t3Button.setText("Tower 3");

					moveCount = 0;						//reset movecounter to 0
					
					
					/*
					 * using else if statement to determine the number of bricks to add to Tower 1
					 */
				
					if (inputNum == 3) {
						t1Bricks.add(0, brick1);
						t1Bricks.add(1, brick2);
						t1Bricks.add(2, brick3);
					} else if (inputNum == 4) {
						t1Bricks.add(0, brick1);
						t1Bricks.add(1, brick2);
						t1Bricks.add(2, brick3);
						t1Bricks.add(3, brick4);
					} else if (inputNum == 5) {
						t1Bricks.add(0, brick1);
						t1Bricks.add(1, brick2);
						t1Bricks.add(2, brick3);
						t1Bricks.add(3, brick4);
						t1Bricks.add(4, brick5);
					} else if (inputNum == 6) {
						t1Bricks.add(0, brick1);
						t1Bricks.add(1, brick2);
						t1Bricks.add(2, brick3);
						t1Bricks.add(3, brick4);
						t1Bricks.add(4, brick5);
						t1Bricks.add(5, brick6);
					} else if (inputNum == 7) {
						t1Bricks.add(0, brick1);
						t1Bricks.add(1, brick2);
						t1Bricks.add(2, brick3);
						t1Bricks.add(3, brick4);
						t1Bricks.add(4, brick5);
						t1Bricks.add(5, brick6);
						t1Bricks.add(6, brick7);
					} else {
						t1Bricks.add(0, brick1);
						t1Bricks.add(1, brick2);
						t1Bricks.add(2, brick3);
						t1Bricks.add(3, brick4);
						t1Bricks.add(4, brick5);
						t1Bricks.add(5, brick6);
						t1Bricks.add(6, brick7);
						t1Bricks.add(7, brick8);
					}

				}
			} else {			// else if the OK button has been fired but no text is in 
				Dialogs.create()	//textfield send this message....
						.owner(root)
						.title("Not Possible....")	
						.masthead(null)
						.message("Nothing appears to have been entered, please try again.")
						.showInformation();
			}
		}
		
		
		
		/*This code deals with the reset function, it first throws a message warning the user that this 
		 * cannot be undone before allowing them to proceed. If they select Yes the textfield is first checked
		 * to see if the user has already entered a desired number of bricks, if so that number of bricks is readded, if not
		 * the default number (8) is added.
		 * NOT WORKING (The users response to warning message could not be resolved, as it stands the message 
		 * appears, but regardless of what the user selects the reset function is fired
		 */

		if (e.getSource() == reset) {

			Action userChoice = Dialogs
					.create()
					.owner(root)
					.title("Restart Game")
					.masthead(
							"Selecting Yes will restart your game, this cannot be undone....")
					.message("Do you want to restart?").showConfirm();

			//if(userChoice.){

			ObservableList<Node> t1Bricks = stack1.getChildren();
			String inputText = brickText.getText();
			moveCount = 0;

			if (inputText.isEmpty() == false) {

				int inputNum = Integer.parseInt(inputText);

				stack1.getChildren().clear();
				stack2.getChildren().clear();
				stack3.getChildren().clear();
				t1Button.setText("Tower 1");
				t2Button.setText("Tower 2");
				t3Button.setText("Tower 3");
				moveCount = 0;
				counter.setText("Moves: " + moveCount);

				if (inputNum == 3) {
					t1Bricks.add(0, brick1);
					t1Bricks.add(1, brick2);
					t1Bricks.add(2, brick3);
				} else if (inputNum == 4) {
					t1Bricks.add(0, brick1);
					t1Bricks.add(1, brick2);
					t1Bricks.add(2, brick3);
					t1Bricks.add(3, brick4);
				} else if (inputNum == 5) {
					t1Bricks.add(0, brick1);
					t1Bricks.add(1, brick2);
					t1Bricks.add(2, brick3);
					t1Bricks.add(3, brick4);
					t1Bricks.add(4, brick5);
				} else if (inputNum == 6) {
					t1Bricks.add(0, brick1);
					t1Bricks.add(1, brick2);
					t1Bricks.add(2, brick3);
					t1Bricks.add(3, brick4);
					t1Bricks.add(4, brick5);
					t1Bricks.add(5, brick6);
				} else if (inputNum == 7) {
					t1Bricks.add(0, brick1);
					t1Bricks.add(1, brick2);
					t1Bricks.add(2, brick3);
					t1Bricks.add(3, brick4);
					t1Bricks.add(4, brick5);
					t1Bricks.add(5, brick6);
					t1Bricks.add(6, brick7);
				} else {
					t1Bricks.add(0, brick1);
					t1Bricks.add(1, brick2);
					t1Bricks.add(2, brick3);
					t1Bricks.add(3, brick4);
					t1Bricks.add(4, brick5);
					t1Bricks.add(5, brick6);
					t1Bricks.add(6, brick7);
					t1Bricks.add(7, brick8);
				}
			} else {

				stack1.getChildren().clear();
				stack2.getChildren().clear();
				stack3.getChildren().clear();

				t1Bricks.add(0, brick1);
				t1Bricks.add(1, brick2);
				t1Bricks.add(2, brick3);
				t1Bricks.add(3, brick4);
				t1Bricks.add(4, brick5);
				t1Bricks.add(5, brick6);
				t1Bricks.add(6, brick7);
				t1Bricks.add(7, brick8);
			}
		} 
		/*
		else {
			
		}
		}
		*/
		}
	

	

	
@Override
	public void start(Stage mainWin) { 
		
			
		
		try {
			BorderPane root = FXMLLoader.load(getClass().getResource(
					"Towers.fxml"));
			root.getStylesheets().add(
					getClass().getResource("skin.css").toExternalForm());

			Scene s = new Scene(root, 780, 390);
			mainWin.setScene(s);
			mainWin.setTitle("Towers of Hanoi");
			mainWin.setResizable(false);
			mainWin.show();
		} catch (IOException e1) {
		}
		
		
		
		
		
		
	}

	public static void main(String[] args) {

		launch(args);
	}

}
