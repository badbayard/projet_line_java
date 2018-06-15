package app;

import core.Case;
import core.Niveau;
import gui.CaseCouleur;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import utilities.Pair;

import java.util.Observable;
import java.util.Observer;

public class VueControleur extends Application {
    private Modele m;
    private CaseCouleur[][] grille_graphique;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);
        StackPane stack = new StackPane();
        GridPane grid = new GridPane();

        m = new Modele(Niveau.MOYEN);

        initGrilleGraphique(grid);
        stack.getChildren().add(grid);

        m.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                if (m.getPartie().estPartieFinie()) {
                    System.out.println("Partie finie !");
                    Rectangle rect = new Rectangle(120,70, Color.rgb(100,100,100,0.5));
                    Text text = new Text("GG !");
                    text.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
                    text.setFill(Color.WHITE);
                    text.setTextAlignment(TextAlignment.CENTER);
                    stack.getChildren().add(rect);
                    stack.getChildren().add(text);
                }
            }
        });

        root.getChildren().add(stack);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Lines");
        primaryStage.getIcons().add(new Image("file:assets/lines_ico.PNG"));
        primaryStage.show();
    }

    private void initGrilleGraphique(GridPane grid) {
        grille_graphique = new CaseCouleur[m.getTaillePartie()][m.getTaillePartie()];
        for (int i = 0; i < m.getTaillePartie(); i++) {
            for (int j = 0; j < m.getTaillePartie(); j++) {
                final int I = i;
                final int J = j;
                grille_graphique[I][J] = new CaseCouleur(m.getCase(I, J).getSymb().getCouleur());
                grid.add(grille_graphique[I][J], J, I);

                if (!m.getCase(I, J).estLibre()) {
                    grille_graphique[I][J].dessinerSymbole();
                }

                grille_graphique[I][J].setOnDragDetected(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Dragboard db = grille_graphique[I][J].startDragAndDrop(TransferMode.MOVE);
                        ClipboardContent content = new ClipboardContent();
                        content.putString(m.getCase(I, J).getSymb().getNomSymbole());
                        db.setContent(content);
                        m.startDD(I, J);
                        event.consume();
                    }
                });

                grille_graphique[I][J].setOnDragOver(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
                        Dragboard db = event.getDragboard();
                        event.acceptTransferModes(TransferMode.MOVE);
                        m.enterDD(I, J);
                        event.consume();
                    }
                });

                grille_graphique[I][J].setOnDragDropped(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent event) {
                        Dragboard db = event.getDragboard();
                        m.stopDD(I, J);
                        event.setDropCompleted(true);
                        event.consume();
                    }
                });
            }
        }
        grid.setGridLinesVisible(true);

        m.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                for (int i = 0; i < m.getTaillePartie(); i++) {
                    for (int j = 0; j < m.getTaillePartie(); j++) {
                        grille_graphique[i][j].update(m.getCase(i, j));
                    }
                }
            }
        });
    }
}
