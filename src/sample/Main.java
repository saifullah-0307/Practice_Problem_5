package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;



import javax.swing.*;
import java.awt.*;

public class Main extends Application {

    private BorderPane root;
    private HBox titleHbox, nameHBox, phoneHbox, addressHbox, orderDetailHbox, bottomHbox;
    private VBox customerVbox, sizeVbox, crustVbox, toppingVbox, centerVbox;
    private FlowPane toppingsFlowpane;

    private Stage stage;

    private Button okBtn, cancelBtn;
    private Label titleLb, nameLb, phoneLb, addressLb, sizeLb, crustLb, toppingsLb;
    private TextField nameTf, phoneTf, addressTf;
    private RadioButton smallRb, mediumRb, largeRb, thinRb, thickRb;
    private ToggleGroup sizeTg, crustTg;
    private CheckBox pepperoniCbox, mushroomsCbox, sausageCbox, tomatoesCbox, linguicaCbox, anchoviesCbox, olivesCbox;




    @Override
    public void start(Stage primaryStage) throws Exception{
        titleLb = new Label("Order your Pizza now");
        titleLb.setFont(new Font(36));
        titleHbox = new HBox(titleLb);

        nameLb = GetLabel("Name:");
        nameTf = GetTextField("Enter Customer Name");
        nameHBox = new HBox(20,nameLb  , nameTf);

        phoneLb = GetLabel("Phone:");
        phoneTf = GetTextField("Enter Customer Phone Number");
        phoneHbox = new HBox(20,phoneLb , phoneTf);

        addressLb = GetLabel("Address:");
        addressTf = GetTextField("Enter Customer Address");
        addressHbox = new HBox(20,addressLb , addressTf);

        customerVbox = new VBox(20,nameHBox , phoneHbox , addressHbox);



        Label sizelb = new Label("Size");
        smallRb = new RadioButton("small");
        mediumRb = new RadioButton("medium");
        largeRb = new RadioButton("large");
        largeRb.setSelected(true);

        ToggleGroup groupSize = new ToggleGroup();
        smallRb.setToggleGroup(groupSize);
        mediumRb.setToggleGroup(groupSize);
        largeRb.setToggleGroup(groupSize);

        sizeVbox = new VBox(sizelb , smallRb , mediumRb , largeRb);

        Label crustLb = new Label("Crust");
        thinRb = new RadioButton("Thin");
        thickRb = new RadioButton("Thick");
        thickRb.setSelected(true);
        ToggleGroup crustgroup = new ToggleGroup();
        thinRb.setToggleGroup(crustgroup);
        thickRb.setToggleGroup(crustgroup);

        crustVbox = new VBox(crustLb , thinRb , thickRb);

        Label Toppings = new Label("Toppings");
        pepperoniCbox = new CheckBox("Pepperoni");
        mushroomsCbox = new CheckBox("Mushrooms");
        sausageCbox = new CheckBox("Sausage");
        tomatoesCbox = new CheckBox("Tomatoes");
        linguicaCbox = new CheckBox("Linguica");
        anchoviesCbox = new CheckBox("Anchovies");
        olivesCbox = new CheckBox("Olives");

        FlowPane toppingsFlowpane = new FlowPane(Orientation.VERTICAL ,pepperoniCbox , mushroomsCbox,
                sausageCbox, tomatoesCbox,linguicaCbox, anchoviesCbox , olivesCbox);

        toppingsFlowpane.setPadding(new Insets(10,0,10,0));
        toppingsFlowpane.setHgap(20);
        toppingsFlowpane.setVgap(10);
        toppingsFlowpane.setPrefWrapLength(100);

        VBox toppingVbox = new VBox(toppingsLb , toppingsFlowpane);

        HBox orderDetailHbox = new HBox(50 , sizeVbox , crustVbox , toppingVbox);

        VBox centerVbox = new VBox(orderDetailHbox , customerVbox);


        Button okBtn = new Button("OK");
        okBtn.setPrefWidth(80);
        okBtn.setOnAction(e -> okBtn_Click() );

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setPrefWidth(80);
        cancelBtn.setOnAction(e -> cancelBtn_Click() );

        
        Region spacer = new Region();

        HBox bottomHbox = new HBox(10, spacer , okBtn , cancelBtn);
        bottomHbox.setPadding(new Insets(20,10,20,10));

        root = new BorderPane();
        root.setTop(titleHbox);
        root.setCenter(centerVbox);
        root.setBottom(bottomHbox);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Pizza order");
    }

    private void okBtn_Click() {

        String message = "Customer: \n \n";
        message += "\t" + nameTf.getText() + "\n";
        message += "\t" + addressTf.getText() + "\n";
        message+= "\t" + phoneTf.getText() + "\n\n";
        message += "You have ordered a pizza";

        if(smallRb.isSelected()){
            message += "Small";
        }
        if(mediumRb.isSelected()){
            message += "Medium";
        }
        if(largeRb.isSelected()){
            message += "Large";
        }

        if (thinRb.isSelected()) {

            message += "thin crust pizza with ";
        }
        if (thickRb.isSelected())  {

           message += "thick crust pizza with ";
        }

        String toppings = "";
        toppings = buildToppings(pepperoniCbox, toppings);
        toppings = buildToppings( sausageCbox, toppings);
        toppings = buildToppings(linguicaCbox, toppings);
        toppings = buildToppings(olivesCbox, toppings);
        toppings = buildToppings(tomatoesCbox, toppings);
        toppings = buildToppings(mushroomsCbox, toppings);
        toppings = buildToppings(anchoviesCbox, toppings);

        if (toppings.equals("")) {

            message += "no toppings.";
        }
          else{

              message += "the following toppings:\n"
                + toppings;

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("order Details");
          }

}

    private TextField GetTextField(String PromtText) {
        TextField nTf = new TextField();
        nTf.setPromptText(PromtText);
        nTf.setFont(Font.font("Arial", 17));
        nTf.setPrefColumnCount(20);
        return nTf;
    }

    private Label GetLabel(String LabelText) {
        Label nLb = new Label(LabelText);
        nLb.setPrefWidth(100);
        nLb.setFont(Font.font("Arial", 17));
        return nLb;
    }

    public String buildToppings(CheckBox chk, String msg) {

            if (chk.isSelected())
             {
                 if (!msg.equals("")){

                msg += ", ";
                 }
             msg += chk.getText();
             }
             return msg;

    }


        public void cancelBtn_Click()
    {

            stage.close();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
