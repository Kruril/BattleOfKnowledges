package view;

import application.Main;
import enumeration.Time;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import utils.BackgroundLoader;

public class ModifTimeSP extends StackPane {

    private ImageView imgTitre;

    private Label lblTimer;
    private Label lblInterval;

    private Spinner<Integer> spTimer;
    private Spinner<Integer> spInterval;

    private Button btnValidate;
    private Button btnBack;

    public ModifTimeSP() {

        //BACKGROUND
        this.setBackground(BackgroundLoader.builderBackGround());

        //Top
        StackPane.setAlignment(getImgTitre(), Pos.TOP_CENTER);
        StackPane.setMargin(getImgTitre(), new Insets(20));

        VBox vContainer = new VBox();

        HBox hbTimer = new HBox();
        hbTimer.setSpacing(20.);
        hbTimer.getChildren().addAll(getLblTimer(), getSpTimer());

        HBox hbInterval = new HBox();
        hbInterval.setSpacing(20.);
        hbInterval.getChildren().addAll(getLblInterval(),getSpInterval());

        vContainer.getChildren().addAll(hbTimer,hbInterval);
        vContainer.setSpacing(50);
        vContainer.setMaxSize(500,200);

        StackPane.setAlignment(vContainer, Pos.CENTER);


        StackPane.setAlignment(getBtnValidate(), Pos.BOTTOM_CENTER);
        StackPane.setMargin(getBtnValidate(), new Insets(10,450,50,0));

        StackPane.setAlignment(getBtnBack(), Pos.BOTTOM_CENTER);
        StackPane.setMargin(getBtnBack(), new Insets(0,0,50,450));

        this.getChildren().addAll(getImgTitre(),vContainer,getBtnValidate(),getBtnBack());
    }

    public ImageView getImgTitre() {
        if (imgTitre == null) {
            imgTitre = new ImageView(new Image("images/title/Modify_Time.png"));
        }
        return imgTitre;
    }

    public Label getLblTimer() {
        if (lblTimer == null) {
        	lblTimer = new Label("Time : ");
        	lblTimer.setAlignment(Pos.CENTER_RIGHT);
        	lblTimer.getStyleClass().add("labelBasique");
        }
        return lblTimer;
    }
    public Label getLblInterval() {
        if (lblInterval == null) {
        	lblInterval = new Label("Interval :");
        	lblInterval.getStyleClass().add("labelBasique");
        }
        return lblInterval;
    }
    public Spinner<Integer> getSpTimer() {
        if (spTimer == null) {
        	spTimer = new Spinner<>();

        	SpinnerValueFactory<Integer> valueFactory =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(40,200, Time.TIMER_TIME.getValue());
        	spTimer.setValueFactory(valueFactory);
        	spTimer.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    Time.TIMER_TIME.setValue(spTimer.getValue());
                    System.out.println(Time.TIMER_TIME.getValue());
                }
            });
        	spTimer.setOnScroll(event -> {
        		spTimer.increment();
        	});

        	spTimer.getStyleClass().add("textBox");
        	spTimer.setPrefSize(300,50);
        }
        return spTimer;
    }

    public Spinner<Integer> getSpInterval() {
        if (spInterval == null) {
        	spInterval = new Spinner<>();

        	SpinnerValueFactory<Integer> intervalFactory =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1,30, Time.INTERVAL.getValue());
        	spInterval.setValueFactory(intervalFactory);
        	spInterval.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    Time.INTERVAL.setValue(spInterval.getValue()*1000);
                    System.out.println(Time.INTERVAL.getValue());
                }
            });
        	spInterval.setOnScroll(event -> {
        		spInterval.increment();
        	});

        	spInterval.getStyleClass().add("textBox");
        	spInterval.setPrefSize(300,50);
        }
        return spInterval;
    }
    
    public Button getBtnValidate() {
        if (btnValidate == null) {
        	btnValidate = new Button("Validate");
        	btnValidate.setBackground(BackgroundLoader.buildBtnBackGround());
        	btnValidate.getStyleClass().addAll("buttonBasic");
        	btnValidate.setId("big-button");
        	
        	btnValidate.setOnAction(event -> {
        		Time.TIMER_TIME.setValue(spTimer.getValue());
        		Time.INTERVAL.setValue(spInterval.getValue()*1000);
        		Main.switchScene(new AdminChoiceSP());
        		}
        	);
        	
            
        }
        return btnValidate;
    }
    public Button getBtnBack() {
        if (btnBack == null) {
            btnBack = new Button("Back");
            btnBack.setBackground(BackgroundLoader.buildBtnBackGround());
            btnBack.getStyleClass().add("buttonBasic");
            btnBack.setId("big-button");
            btnBack.setOnAction(event -> Main.switchScene(new AdminChoiceSP()));
        }
        return btnBack;
    }
}
