package view.multiplayer;


import application.Main;
import enumeration.TypeGame;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import utils.utility.BackgroundLoader;
import view.MainPageSP;


public class MultiplayerBP extends StackPane{
		private Label lblTitle;
	    private Button btnCreate, btnJoin,btnBack;
	    
	    public MultiplayerBP() {
	    	this.setBackground(BackgroundLoader.builderBackGround());
	    	
	    	StackPane.setAlignment(getLblTitle(), Pos.TOP_CENTER);
	    	
	    	StackPane.setAlignment(getBtnCreate(),Pos.CENTER);
	        StackPane.setMargin(getBtnCreate(), new Insets(-200,0,0,0));

	        StackPane.setAlignment(getBtnJoin(),Pos.CENTER);
	        StackPane.setMargin(getBtnJoin(), new Insets(200,0,0,0));
	        
	        StackPane.setAlignment(getBtnBack(), Pos.BOTTOM_CENTER);
	        StackPane.setMargin(getBtnBack(), new Insets(10));
	        
	        this.getChildren().addAll(getBtnCreate(),getBtnJoin(),getBtnBack(),getLblTitle());

	    }

		public Button getBtnCreate() {
			if(btnCreate==null) {
				btnCreate=new Button("Create a server");
				btnCreate.setBackground(BackgroundLoader.buildBtnBackGround());
	            btnCreate.getStyleClass().add("buttonBasic");
	            btnCreate.setId("big-button");
	            btnCreate.setOnAction(event -> {
					Main.switchScene(new MultiPlayerRoomServer());
					Main.getServer().start();
					TypeGame.TYPEGAME.setValue("SERVER");
				});
	            
			}
			return btnCreate;
		}

		public Button getBtnJoin() {
			if(btnJoin==null) {
				btnJoin=new Button("Join a server");
				btnJoin.setBackground(BackgroundLoader.buildBtnBackGround());
	            btnJoin.getStyleClass().add("buttonBasic");
	            btnJoin.setId("big-button");
	            btnJoin.setOnAction(event -> {
	            	Main.switchScene(new JoinGame());
	            	TypeGame.TYPEGAME.setValue("CLIENT");
	            });
			}
			return btnJoin;
		}

		public Button getBtnBack() {
			if (btnBack == null) {
	            btnBack = new Button("Back");
	            btnBack.setBackground(BackgroundLoader.buildBtnBackGround());
	            btnBack.getStyleClass().add("buttonBasic");
	            btnBack.setId("medium-button");
	            btnBack.setOnAction(event -> Main.switchScene(new MainPageSP()));
	        }
	        return btnBack;
		}

		public Label getLblTitle() {
			if(lblTitle==null) {
				lblTitle=new Label("MultiPlayer");
				lblTitle.getStyleClass().add("labelTitle");
			}
			return lblTitle;
		}
	    
	    

	   
	
}
