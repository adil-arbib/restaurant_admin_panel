package com.team.restaurant_admin_panel.controllers.statistics;

import com.team.restaurant_admin_panel.App;
import com.team.restaurant_admin_panel.models.plat.Plat;
import com.team.restaurant_admin_panel.models.serveur.Serveur;
import com.team.restaurant_admin_panel.models.serveur.ServeurDAO;
import com.team.restaurant_admin_panel.models.statistics.CustomServeur;
import com.team.restaurant_admin_panel.models.statistics.Statistics;
import com.team.restaurant_admin_panel.utils.TimeConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

public class StatisticsController implements Initializable{

    @FXML
    Label serveurNumber;
    @FXML
    Label manager;
    @FXML
    Label CuisinierNumber;


    @FXML
    Label CalendarLabel;
    @FXML
    TableView table;
    @FXML
    TableColumn<CustomServeur,String> NomServeur;
    @FXML
    TableColumn<CustomServeur,String> PrenomServeur;
    @FXML
    TableColumn<CustomServeur,Integer> TotalTable;
    ObservableList<CustomServeur> list= FXCollections.observableArrayList();

    @FXML
    Label lastMonthOrder;

    @FXML
    BarChart barChart;
    @FXML
    CategoryAxis xlabel;
    @FXML
    NumberAxis ylabel;
    @FXML
    PieChart CategoryPop;
    @FXML
    Label ProfitYear;
    @FXML
    Label LastYearProfit;
    @FXML
    Label Orders;
    @FXML
    Label BestMonth;
    @FXML
    Label ProfitMonth;
    @FXML
    Label LessDish;
    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillCalendar();
        barChart.getStylesheets().add(App.class.getResource("css/charts.css").toExternalForm());
        try {
            fillRestaurantStaff();
            fillTable();
            fillbarChart();
            fillPieChart2();
            fillEntity1();
            fillEntity2();
            fillEntity3();
            fillEntity4();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public void fillCalendar(){
        CalendarLabel.setText(LocalDate.now().toString());
    }

     public void fillbarChart() throws SQLException {
         xlabel.setLabel("Nom d'article");
         ylabel.setLabel("Occurence");
         XYChart.Series series = new XYChart.Series();
         series.setName("Basé sur catégorie");
         HashMap<Plat,Integer> listPlat= (HashMap<Plat, Integer>) Statistics.AllPlatOccurence();
         for (Plat i : listPlat.keySet()){
             series.getData().add(new XYChart.Data(i.getNom(),listPlat.get(i) ));
         }
         barChart.getData().add(series);
     }
    public void fillPieChart2() throws SQLException {
        HashMap<String, Integer> l = new HashMap<String, Integer>();
        l = Statistics.CatPopularity();
        ObservableList<PieChart.Data> pieChartData = null;
        ArrayList<String> nom=new ArrayList<String>();
        ArrayList<Integer> demande=new ArrayList<Integer>();

                for (String k : l.keySet()) {
                    nom.add(k);
                    demande.add(l.get(k));
                }
        PieChart.Data data[] = new PieChart.Data[l.size()];
                for(int i=0;i<l.size();i++){
                data[i]=new PieChart.Data(nom.get(i), demande.get(i));
                }
        pieChartData = FXCollections.observableArrayList(data);

        CategoryPop.setData(pieChartData);
    }
    public void fillEntity1() throws SQLException {
        Orders.setText(Statistics.getTotalMonthReservations(TimeConverter.getCurrentMonth())+" réservations");
        lastMonthOrder.setText(Statistics.getTotalMonthReservations(TimeConverter.getLastMonth())+" réservations");
        lastMonthOrder.setStyle("-fx-text-fill: green");
    }
    public void fillEntity2() throws SQLException, ParseException {
        float currentYear=Statistics.ProfitYear(TimeConverter.getCurrentYear());
       float lastYear=Statistics.ProfitYear(TimeConverter.getLastYear());

        ProfitYear.setText(Statistics.ProfitYear(TimeConverter.getCurrentYear())+" DH ");

            float pourcentage = ((lastYear - currentYear) / lastYear) * 100;
            String p = String.format("%.02f", pourcentage);
            String txt = pourcentage > 0 ? "+" + p + "%" : "" + p + "%";
            LastYearProfit.setText(txt);
            String c = pourcentage > 0 ? "-fx-text-fill: green" : "-fx-text-fill: red";
            LastYearProfit.setStyle(c);

    }
    public void fillEntity3() throws SQLException {
        String best_month="";
        //ArrayList of profit that year and get the one with the highest value
        String year=TimeConverter.getCurrentYear().toString();
        ArrayList<String> months= new ArrayList<>(Arrays.asList(year+"-01",year+"-02",year+"-03",year+"-04",year+"-05",year+"-06",
                year+"-07",year+"-08",year+"-09",year+"-10",year+"-11",year+"-12"));
        ArrayList<Float> annualProfit= new ArrayList<>();
        for (String m: months) {
            annualProfit.add(Statistics.monthlyProfit(m));
        }
        float maxprofit=Collections.max(annualProfit);

        ProfitMonth.setText(maxprofit+"DH");
        // getting the month when profit = max profit
        for (String m:months) {
            if(maxprofit==Statistics.monthlyProfit(m))
                best_month=m;
        }
        BestMonth.setText(best_month);
    }

    public void fillEntity4() throws SQLException {
        LessDish.setText(Statistics.lessOrdered().getNom());
    }

  public void fillTable() throws SQLException {

      NomServeur.setCellValueFactory(new PropertyValueFactory<CustomServeur,String>("Nom"));
      PrenomServeur.setCellValueFactory(new PropertyValueFactory<CustomServeur,String>("Prenom"));
      TotalTable.setCellValueFactory(new PropertyValueFactory<CustomServeur,Integer>("Total"));

       ArrayList<Serveur> listServeur= ServeurDAO.getAll();
        for (Serveur s:listServeur) {
            int i = Statistics.numberTablesServerd(s.getId(), TimeConverter.getCurrentMonth());
            list.add(new CustomServeur(s.getNom(), s.getPrenom(), i));
        }
       table.setItems(list);

    }
    public void fillRestaurantStaff() throws SQLException {
        serveurNumber.setText(""+Statistics.numberofServeurs());
        manager.setText(""+Statistics.numberofmanager());
        CuisinierNumber.setText(""+Statistics.numberofcuisinier());

    }


}
