/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape;

/**
 *
 * @author ptalonen
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {
    

    public static void main(String[] args) throws Exception {


        Connection conn = getConnection();


        System.out.println("Hello world!");

        Spark.get("*", (req, res) -> {

            List<Kysymys> kysymykset = new ArrayList<>();

           // avaa yhteys tietokantaan
           // tee kysely
            PreparedStatement stmt
                    = conn.prepareStatement("SELECT * FROM Kysymys");
            ResultSet tulos = stmt.executeQuery();

            // käsittele kyselyn tulokset
            while (tulos.next()) {
                kysymykset.add(new Kysymys(tulos.getInt("id"), tulos.getString("kurssi"), tulos.getString("aihe"), tulos.getString("kysymysteksti")));
            }
            // sulje yhteys tietokantaan
            conn.close();

            HashMap map = new HashMap<>();

            map.put("lista", kysymykset);

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        Spark.post("/create", (req, res) -> {
            // avaa yhteys tietokantaan

            // tee kysely
            PreparedStatement stmt
                    = conn.prepareStatement("INSERT INTO Kysymys (kurssi, aihe, kysymysteksti) VALUES (?)");
            stmt.setString(1, req.queryParams("kurssi"));

            stmt.executeUpdate();

            // sulje yhteys tietokantaan
            conn.close();

            res.redirect("/");
            return "";
        });

        Spark.post("/delete/:kurssi", (req, res) -> {
            // avaa yhteys tietokantaan
            // tee kysely
            String kurssi = req.params(":kurssi");


            PreparedStatement stmt
            = conn.prepareStatement("DELETE FROM Kysymys WHERE kurssi = (?)");


            stmt.executeUpdate();
            ResultSet resultSet = stmt.executeQuery();
            // sulje yhteys tietokantaan
            conn.close();

            res.redirect("/");
            return "";
        });


    }

    public static Connection getConnection() throws Exception {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        if (dbUrl != null && dbUrl.length() > 0) {
            return DriverManager.getConnection(dbUrl);
        }

    return DriverManager.getConnection("jdbc:sqlite:kysymykset.db");
    }




                


}
