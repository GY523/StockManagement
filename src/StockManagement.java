/*
8/4/2024 12:52pm
A Stock Management System that uses 5 class: Product(abs), Refrigerator, Tv,
UserInfo and StockManagement(app)

*/
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StockManagement extends Application{
	private static Stage primaryStage;
	private static UserInfo user;
	private Scene homeScene;
	private Scene manageScene;
	private Scene addProductScene;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		System.out.println(user.getId());
		System.out.println(user.getName());
		/*ArrayList<Product> products= new ArrayList<Product>();
		String opt,dummy;
		int index=-1, choice=0;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to SMS");
		DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/MM/yyyy \tHH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println("Login date and time: " + dtf.format(now));
		*/
	}
	
	public void start(Stage primaryStage) {
		this.primaryStage= primaryStage;
		user= new UserInfo();
		ObservableList<Product> products = FXCollections.observableArrayList();
		products.add(new Refrigerator(1, "Mitsubishi 200", 255, 5, "door","Dark Gray", 4.5));
		products.add(new Tv(3, "LG platinum", 599, 3, "LED","Full HD"	,24));
		products.add(new Refrigerator(2, "Mitsubishi 300", 299, 4, "door","Gray", 5.0));
		products.add(new Tv(4, "LG Lollipop", 899, 4, "LED","Full HD"	,40 ));
		products.add(new Oven(5,"Oven Electrolux", 549.0, 3, "Electric","Black",2000));
		products.add(new Oven(6,"Oven Toshiba",799.0, 3, "Microwave","Gray",2200));
		
		//event handler of the navigation button for 3 scene: home, add, manage
		EventHandler<ActionEvent> naviHomeHandler=e->{
			if(!primaryStage.getScene().equals(homeScene)) primaryStage.setScene(homeScene);
		};
		
		EventHandler<ActionEvent> naviAddProductHandler=e->{
			if(!primaryStage.getScene().equals(addProductScene)) primaryStage.setScene(addProductScene);
		};
		
		EventHandler<ActionEvent> naviManageHandler=e->{
			if(!primaryStage.getScene().equals(manageScene)) primaryStage.setScene(manageScene);
		};
		
		this.manageScene= createManageScene(products, naviHomeHandler, naviAddProductHandler, naviManageHandler);
		this.addProductScene= createAddProductScene(products, naviHomeHandler, naviAddProductHandler, naviManageHandler);
		this.homeScene= createHomeScene(naviHomeHandler, naviAddProductHandler, naviManageHandler);
		Scene promptScene= createPromptScene(homeScene,addProductScene);
		Scene loginScene = createLoginScene(promptScene);
		
		primaryStage.setTitle("Stock Management");
		primaryStage.setScene(loginScene);
		primaryStage.show();
	}
	
    private static Scene createLoginScene(Scene promptScene) {
        // Create labels and text fields for login scene
        Label nameLabel = new Label("Please Enter Your Name");
        nameLabel.setPrefSize(175,10);
        nameLabel.setStyle("-fx-font-size: 16px;");

        Label firstNameLabel = new Label("First Name:");
        firstNameLabel.setPrefSize(109, 62);
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        firstNameField.setPrefSize(200, 40);

        Label surnameLabel = new Label("Surname:");
        surnameLabel.setPrefSize(109, 62);
        TextField surnameField = new TextField();
        surnameField.setPromptText("Surname");
        surnameField.setPrefSize(200, 40);

        Button loginButton = new Button("Login");
        loginButton.setPrefSize(83, 39);
        loginButton.setOnAction(e -> {
            String firstName = firstNameField.getText().trim();
            String surname = surnameField.getText().trim();

            if (firstName.isEmpty() || surname.isEmpty()) {
                showAlert("Please enter both first name and surname.");
                return; // Stop executing the rest of the codes if user didnt enter both 1st and 2nd name
            } else {
                user.generateUserId(firstName, surname);
                showAlert("Login Successful!\n\nUser Name: " + firstName + " " + surname + "\nUser ID: " + user.getId());
                // Move to next scene
                primaryStage.setScene(promptScene);
            }
        });

        // Create layout for login scene
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.add(firstNameLabel, 0, 0);
        gridPane.add(firstNameField, 1, 0);
        gridPane.add(surnameLabel, 0, 1);
        gridPane.add(surnameField, 1, 1);
        gridPane.add(loginButton, 1, 2);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(nameLabel, gridPane);

        return new Scene(root, 1024,768);
    }
    
    private static Scene createPromptScene(Scene homeScene,Scene addProductScene) {
        // Create labels and buttons for product scene
        Label titleLabel = new Label("Would you like to add a product?");
        titleLabel.setStyle("-fx-font-size: 16px;");

        Button yesButton = new Button("Yes");
        yesButton.setOnAction(e -> {
            // Handle Yes button action
            // For now, simply show a message
            primaryStage.setScene(addProductScene);
        });

        Button noButton = new Button("No");
        noButton.setOnAction(e -> {
            // Handle No button action
            // For now, simply exit the application
            primaryStage.setScene(homeScene);
        });

        // Create layout for product scene
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(yesButton, noButton);

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(titleLabel, buttonBox);

        return new Scene(root, 1024, 768);
    }

    private static Scene createHomeScene(EventHandler<ActionEvent> naviHome,
    		EventHandler<ActionEvent> naviAddProduct, EventHandler<ActionEvent> naviManage) {
    	// Create buttons
        Button homeButton = new Button("Home");
        Button addProductButton = new Button("Add Product");
        Button manageStockButton = new Button("Manage Stock");

        // Create labels for ID and Name
        Label idLabel = new Label("ID:");
        Label userIdLabel = new Label(user.getId());
        Label nameLabel = new Label("Name:");
        Label userNameLabel = new Label(user.getName());

        // Apply CSS style to make labels bold and increase font size
        idLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");

        // Set font size for user ID and user name labels
        userIdLabel.setStyle("-fx-font-size: 18px;");
        userNameLabel.setStyle("-fx-font-size: 18px;");

        // Create layout for home page
        GridPane gridPane = new GridPane();
        //gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 250, 10, 20)); // Adjust padding as needed

        // Add buttons to an HBox and position it above the labels
        HBox buttonBox = new HBox(10); // 10 is the spacing between buttons
        //buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(homeButton, addProductButton, manageStockButton);
        gridPane.add(buttonBox, 0, 0, 4, 1); // Span four columns for buttons

        // Add labels to the grid pane with specific grid positions
        gridPane.add(idLabel, 0, 1);
        gridPane.add(userIdLabel, 1, 1);
        gridPane.add(nameLabel, 2, 1);
        gridPane.add(userNameLabel, 3, 1);

        // Create GroupBox
        VBox groupBox = new VBox(5); // Spacing between labels
        //groupBox.setAlignment(Pos.TOP_LEFT);
        groupBox.setPadding(new Insets(10, 0, 0, 0)); // Adjust padding as needed

        // Add group member labels to GroupBox
        String[] groupMembers = {"Bernard Goh Pek Hong   2300075", "Chwa Guang Yao             2106423", 
        		"Tan Yong Han                  2300290", 
        		"Yee Qi Hong                    2300052"};
        for (String member : groupMembers) {
            Label memberLabel = new Label(member);
            memberLabel.setStyle("-fx-font-size: 16px;");
            groupBox.getChildren().add(memberLabel);
        }
        
        // Add GroupBox to the grid pane
        gridPane.add(groupBox, 0, 2, 4, 1); // Span four columns for GroupBox
        VBox root = new VBox();
        root.getChildren().add(gridPane);
        
        //Add event handler to 3 navigation button
        homeButton.setOnAction(naviHome);
		addProductButton.setOnAction(naviAddProduct);
		manageStockButton.setOnAction(naviManage);
        
        return new Scene(root, 1024, 768);
    }
    
    private static Scene createAddProductScene(ObservableList<Product> products, EventHandler<ActionEvent> naviHome,
    		EventHandler<ActionEvent> naviAddProduct, EventHandler<ActionEvent> naviManage) {
    	BorderPane pane = new BorderPane();
		 //top
		 HBox pane4Top = new HBox(15);
		 
		 Button home = new Button("HOME");
		 Button AddP = new Button("ADD PRODUCT");
		 Button MStock = new Button("MANAGE STOCK");
		 
		 pane4Top.setAlignment(Pos.TOP_LEFT);
		 pane4Top.setPadding(new Insets(5));
		 
		 pane4Top.getChildren().addAll(home, AddP, MStock);
		 
		 //center for TV
		 GridPane pane4Center = new GridPane();
		 pane4Center.setVgap(10);
		 pane4Center.setHgap(10);
		 
		 Text tt = new Text("Enter details for new product");
		 TextField number = new TextField();
		 TextField name = new TextField();
		 TextField price = new TextField();
		 TextField quantity = new TextField();
		 TextField screen_t = new TextField();
		 TextField resolution = new TextField();
		 TextField display_s = new TextField();
		 
		 pane4Center.add(tt, 0, 0);
		 pane4Center.add(new Label("Number:"), 0, 1);
		 pane4Center.add(number,1,1);
		 pane4Center.add(new Label("Name:"), 0, 2);
		 pane4Center.add(name,1,2);
		 pane4Center.add(new Label("Price:"), 0, 3);
		 pane4Center.add(price,1,3);
		 pane4Center.add(new Label("Quantity:"), 0, 4);
		 pane4Center.add(quantity,1,4);
		 pane4Center.add(new Label("Screen Type:"), 0, 5);
		 pane4Center.add(screen_t,1,5);
		 pane4Center.add(new Label("Resolution:"), 0, 6);
		 pane4Center.add(resolution,1,6);
		 pane4Center.add(new Label("Display Size:"), 0, 7);
		 pane4Center.add(display_s,1,7);
		 
		 pane.setCenter(pane4Center);
		 
		 //center for refri
		 GridPane pane4Center2 = new GridPane();
		 pane4Center2.setVgap(10);
		 pane4Center2.setHgap(10);
		 
		 Text t = new Text("Enter details for new product");
		 
		 TextField number1 = new TextField();
		 TextField name1 = new TextField();
		 TextField price1 = new TextField();
		 TextField quantity1 = new TextField();
		 TextField design = new TextField();
		 TextField color = new TextField();
		 TextField capacity = new TextField();
		 
		 pane4Center2.add(t, 0, 0);
		 pane4Center2.add(new Label("Number:"), 0, 1);
		 pane4Center2.add(number1,1,1);
		 pane4Center2.add(new Label("Name:"), 0, 2);
		 pane4Center2.add(name1,1,2);
		 pane4Center2.add(new Label("Price:"), 0, 3);
		 pane4Center2.add(price1,1,3);
		 pane4Center2.add(new Label("Quantity:"), 0, 4);
		 pane4Center2.add(quantity1,1,4);
		 pane4Center2.add(new Label("Design:"), 0, 5);
		 pane4Center2.add(design,1,5);
		 pane4Center2.add(new Label("Color:"), 0, 6);
		 pane4Center2.add(color,1,6);
		 pane4Center2.add(new Label("Capacity:"), 0, 7);
		 pane4Center2.add(capacity,1,7);
		 
		 //right
		 VBox pane4Right = new VBox(10);
		 Text text = new Text("Select type:");
		 RadioButton tv = new RadioButton("TV");
		 RadioButton refri = new RadioButton("Refrigerator");
		 
		 Button add = new Button("ADD");
		 
		 ToggleGroup group = new ToggleGroup();
		 tv.setToggleGroup(group);
		 tv.setSelected(true);
		 refri.setToggleGroup(group);
		 
		 tv.setOnAction(e ->{
			 if(tv.isSelected())
				 pane.setCenter(pane4Center);
		 });
		 refri.setOnAction(e ->{
			 if(refri.isSelected())
				 pane.setCenter(pane4Center2);
		 });
		 
		 //event for adding 
		add.setOnAction(e -> { if(tv.isSelected()) {
								String tvName = name.getText();
								String screenType = screen_t.getText();
								String tvRes = resolution.getText();
								int displayS = Integer.parseInt((display_s.getText()));
								int tvQuan = Integer.parseInt(quantity.getText());
								double tvPrice = Double.parseDouble(price.getText());
								int tvNum = Integer.parseInt(number.getText());
								
								products.add(new Tv(tvNum, tvName, tvPrice, tvQuan, screenType, tvRes,displayS));
								
		}
			 					});
		add.setOnAction(e -> { if(refri.isSelected()) {
								String refName = name1.getText();
								String refDesign = design.getText();
								String refColor = color.getText();
								double refCap = Integer.parseInt(capacity.getText());
								int refQuan = Integer.parseInt(quantity1.getText());
								double refPrice = Double.parseDouble(price1.getText());
								int refNum = Integer.parseInt(number1.getText());
								
								products.add(new Refrigerator(refNum, refName, refPrice, refQuan, refDesign,refColor,refCap));
		}
				 				});
		//event for navigation
		home.setOnAction(naviHome);
		AddP.setOnAction(naviAddProduct);
		MStock.setOnAction(naviManage);
		 
		 //vbox.setStyle("-fx-border-color: black");
		 pane4Right.getChildren().addAll(text,tv,refri,add);
		 pane4Right.setPadding(new Insets(10));

		 
		 //botton
		 HBox pane4Bottom = new HBox();		 
		 Text id = new Text("ID: ");
		 Text uname = new Text("Name: ");
		 Text tf = new Text(user.getId());
		 Text usern = new Text(user.getName());
		 
		 pane4Bottom.getChildren().addAll(id, tf, uname, usern);
		 pane4Bottom.setAlignment(Pos.CENTER);
		 pane4Bottom.setPadding(new Insets(5));
		 
		 //set pane
		 pane.setTop(pane4Top);
		 pane.setRight(pane4Right);
		 pane.setBottom(pane4Bottom);
		 pane.setPadding(new Insets(10));
		 
		 Scene scene = new Scene(pane, 1024,768);
		 return scene;
    }
    
    private static Scene createManageScene(ObservableList<Product> products, EventHandler<ActionEvent> naviHome,
    		EventHandler<ActionEvent> naviAddProduct, EventHandler<ActionEvent> naviManage) {
    	//Scene-Manage Stock(MS)		
		//MS top => paneForNavi: hbox(bthHome,btnProduct,btnStock)
		HBox paneForNavi= new HBox(10);
		Button btnHome= new Button("Home");
		Button btnProduct= new Button("Add Product");
		Button btnStock = new Button("Manage Stock");
		paneForNavi.getChildren().addAll(btnHome,btnProduct,btnStock);
		paneForNavi.setPadding(new Insets(5.0));
		
		//MS bottom => paneForName: hbox(textId,textName)
		HBox paneForName= new HBox(20);
		Text textId = new Text("ID: " + user.getId());
		Text textName= new Text("Name: " + user.getName());
		paneForName.getChildren().addAll(textId,textName);
		paneForName.setPadding(new Insets(5.0));
		
		//MS:paneForControl: VBox(labelValue,tfValue,btnAdd,btnDeduct)
		VBox paneForControl= new VBox(20);
		Label labelValue= new Label("Value:");
		TextField tfValue= new TextField();
		Button btnAddStock= new Button("Add quantity");
		Button btnDeduct= new Button("Deduct quantity");
		Button btnDiscontinue= new Button("Discontinue product");
		btnAddStock.setPrefWidth(200);
		btnDeduct.setPrefWidth(200);
		btnDiscontinue.setPrefWidth(200);
		paneForControl.getChildren().addAll(labelValue,tfValue,btnAddStock,btnDeduct, btnDiscontinue);
		paneForControl.setPadding(new Insets(50.0));
		paneForControl.setAlignment(Pos.TOP_CENTER);
		
		//paneForProduct: Pane(top:textGuide,center:productTable)
		BorderPane paneForProduct= new BorderPane();
		Text textGuide= new Text("Click to select a product to manage stock");
		TableView<Product> table= createTable(products);

		paneForProduct.setTop(textGuide);
		paneForProduct.setCenter(table);
		paneForControl.setPadding(new Insets(5.0));
	
		//arrange nodes into one pane
		BorderPane paneManage= new BorderPane();
		paneManage.setTop(paneForNavi);
		paneManage.setCenter(paneForProduct);
		paneManage.setBottom(paneForName);
		paneManage.setRight(paneForControl);
		paneManage.setPadding(new Insets(20.0));
		
		//Event handling section of code
		btnStock.setOnAction(e->{
			if(!paneManage.getCenter().equals(paneForProduct)) 
				paneManage.setCenter(paneForProduct);
		});
		
		btnAddStock.setOnAction(e->{
			int index= table.getSelectionModel().getSelectedIndex();
			int value= Integer.parseInt(tfValue.getText());
			addStock(products,index,value);
			table.refresh();
		});
		
		btnDeduct.setOnAction(e->{
			int index = table.getSelectionModel().getSelectedIndex();
			int value= Integer.parseInt(tfValue.getText());
			deductStock(products,index,value);
			table.refresh();
		});
		
		btnDiscontinue.setOnAction(e->{
			int index = table.getSelectionModel().getSelectedIndex();
			discontinueProduct(products,index);
			table.refresh();
		});
		btnHome.setOnAction(naviHome);
		btnProduct.setOnAction(naviAddProduct);
		btnStock.setOnAction(naviManage);
		
		return new Scene(paneManage, 1024, 768);
    }
    
	private static TableView<Product> createTable(ObservableList<Product> products){
		TableView<Product> table= new TableView<>();
		
		//number column
		TableColumn<Product,Integer> colNumber= new TableColumn<>("Number");	
		colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
		
		//Name column
		TableColumn<Product,String> colName= new TableColumn<>("Name");
		colName.setMinWidth(120);
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));

		//Price column
		TableColumn<Product,Double> colPrice = new TableColumn<>("Price");
		colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		//Quantity column
		TableColumn<Product,Integer> colQuantity= new TableColumn<>("Quantity");
		colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		//Status column
		TableColumn<Product,Boolean> colStatus= new TableColumn<>("Status");
		colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		//Value Column
		TableColumn<Product,String> colValue= new TableColumn<>("Value(RM)");
		colValue.setCellValueFactory(data->{
			return new SimpleStringProperty("" +data.getValue().getInventoryValue());
		});
		
		//Others Column (parent column of other1, 2 and 3)
		TableColumn<Product,String> colOthers= new TableColumn<>("Other attributes");
		
		//Different products have different additional attributes, therefore base on different type of product,
		//display different attributes name and its value.
		//Refrigerator: Door Design, Color: , Capacity
		//TV: Screentype, resolution, displaySize
		//Other1 Column
		TableColumn<Product,String> colOther1 = new TableColumn<>("1");
		colOther1.setMinWidth(150);
		colOther1.setCellValueFactory(data->{
			Product item= data.getValue();
			if(item instanceof Refrigerator) {
				return new SimpleStringProperty("Door: "+((Refrigerator) item).getDoorDesign());
			}
			else if(item instanceof Tv) {
				return new SimpleStringProperty("Screen: " + ((Tv) item).getScreenType());
			} 
			else if(item instanceof Oven)	
				return new SimpleStringProperty("Type: " + ((Oven)item).getType());
			else {
				return null;
			}
		});
		
		//Other2 column: for 2nd additional attributes
		TableColumn<Product,String> colOther2 = new TableColumn<>("2");
		colOther2.setPrefWidth(150);
		colOther2.setCellValueFactory(data->{
			Product item= data.getValue();
			if(item instanceof Refrigerator) {
				return new SimpleStringProperty("Color: "+((Refrigerator) item).getColor());
			}
			else if(item instanceof Tv) {
				return new SimpleStringProperty("Resolution: " + ((Tv) item).getResolution());
			} 
			else if(item instanceof Oven)	
				return new SimpleStringProperty("Color: " + ((Oven)item).getColor());
			else {
				return null;
			}
		});
		
		//Other3 column
		TableColumn<Product,String> colOther3 = new TableColumn<>("3");
		colOther3.setPrefWidth(100);
		colOther3.setCellValueFactory(data->{
			Product item= data.getValue();
			if(item instanceof Refrigerator) {
				return new SimpleStringProperty("Capacity: "+((Refrigerator) item).getCapacity()+ "L");
			}
			else if(item instanceof Tv) {
				return new SimpleStringProperty("Size: " + ((Tv) item).getDisplaySize());
			}
			else if(item instanceof Oven)	
				return new SimpleStringProperty("Power: " + ((Oven)item).getPower()+"W");
			else {
				return null;
			}
		});
		
		//put colOther1,2,3 under colOthers
		colOthers.getColumns().addAll(colOther1,colOther2,colOther3);
		
		//after data associated with the columns, add the data to the table
		table.setItems(products);
		table.getColumns().addAll(colNumber,colName,colPrice, colQuantity,colStatus, colValue, colOthers);
		
		return table;
	}
	
	//this method display content of product array and let user select which product to update
	public static int viewProduct(ObservableList<Product> products,Scanner input) {
		System.out.printf("%-5s %-15s %n","Index","Name");
		for(int i=0;i<products.size();i++) {
			System.out.printf("%-5s %-15s %n",(i+1),products.get(i).getName());
		}
		System.out.print("Enter the product index that you want to update:");
		
		int idx=input.nextInt()-1;
		
		if(idx>=0 && idx<products.size()) return idx;
		else {
			System.out.println("product index out of range.");
			return -1;
		}
	}
	
	public static void addStock(ObservableList<Product> prod, int index, int value) {
		if(value>=0)
			prod.get(index).addQuantity(value);
		else System.out.println("Cannot have negative value to add.");
	}
	
	public static void deductStock(ObservableList<Product> prod, int index, int value) {
		
		if(value>=prod.get(index).getQuantity())
			prod.get(index).setQuantity(0);
		else if(value>=0)
			prod.get(index).deductQuantity(value);
		else System.out.println("Cannot have negative value to deduct");
	}
	
	public static void discontinueProduct(ObservableList<Product> prod, int index) {
		prod.get(index).setStatus(false);
		System.out.println("discontinue successfully");
	}
	
	public static void addRefrigerator(ObservableList<Product> products, Scanner in) {
		// refrigerator instance variable: name, doordesign, color, capacity,quantity, price, item number
		String name,doorDesign,color,dummy;
		double capacity,price;
		int quantity, number;
		
		//clear input buffer
		dummy=in.nextLine();
		System.out.println("Enter details for the new refrigerator");
		System.out.print("Enter name:");
        name=in.nextLine();
        System.out.print("Enter door design:");
        doorDesign=in.nextLine();
        System.out.print("Enter color:");
        color=in.next();
        //clear input buffer
        dummy=in.nextLine();
        
        System.out.print("Enter capacity (in Litres):");
        capacity = in.nextDouble();
        dummy=in.nextLine();
        
        System.out.print("Enter quantity:");
        quantity=in.nextInt();
        dummy=in.nextLine();
        
        System.out.print("Enter price:");
        price = in.nextDouble();
        dummy=in.nextLine();
        
        System.out.print("Enter item number:");
        number = in.nextInt();
        dummy=in.nextLine();
        
        Product p1=new Refrigerator(number,name,price,quantity,doorDesign,color,capacity);
        products.add(p1);
	}
	
	public static void addTv(ObservableList<Product> products, Scanner in) {
		// refrigerator instance variable: name > screen type > resolution >
		//displaysize > quantity> price > item number
		String name,sType,dummy,res;
		double price;
		int quantity, displaySize, number;
		
		//clear input buffer
		dummy=in.nextLine();
		System.out.println("Enter details for the new TV");
		System.out.print("Enter name:");
        name=in.nextLine();
        System.out.print("Enter screen type:");
        sType=in.next();
        dummy=in.nextLine();
        System.out.print("Enter resolution (k):");
        res=in.nextLine();
        //clear input buffer
        dummy=in.nextLine();
        
        System.out.print("Enter display size:");
        displaySize = in.nextInt();
        dummy=in.nextLine();
        
        System.out.print("Enter quantity:");
        quantity=in.nextInt();
        dummy=in.nextLine();
        
        System.out.print("Enter price:");
        price = in.nextDouble();
        dummy=in.nextLine();
        
        System.out.print("Enter item number:");
        number = in.nextInt();
        dummy=in.nextLine();
        
        Product p1=new Tv(number,name,price,quantity,sType,res,displaySize);
        products.add(p1);
	}
	
	public static void displayProduct(ObservableList<Product> products,Scanner input) {
		//System.out.printf("%-5s %-8s %-15s %-10s %-7s","Idx","Number","Name","Price","Status");
		System.out.println("Products List\n=====================================\n");
		for(int i=0;i<products.size();i++) {
			//System.out.printf("%-5d %-8d %-15s %-10.2f %-7b",(i+1),products.get(i).getNumber(),products.get(i).getName(),
			//		products.get(i).getPrice(),products.get(i).isStatus());
			System.out.println(products.get(i).toString());
			System.out.println("=====================================\n");
		}
	}
	
	 public static void showAlert(String message) {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Information");
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	 }
	
}
