/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Kelas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLDataException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Category extends Koneksi{
    
    private int categoryid;
    private String categoryname;
     private final Connection Koneksi;
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    
    public Category(){
        Koneksi = super.configDB();
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
       public void TambahCategory(){
        query = "INSERT INTO category VALUES (?,?)";
        try {
            ps = Koneksi.prepareStatement(query);
            ps.setInt(1, categoryid);
            ps.setString(2, categoryname);

            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null,"Data Berhasil Ditambahkan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditambahkan");
        }
    }
public void UbahCategory(){
        query = "UPDATE category SET categoryname=? WHERE categoryid=?";
        try {
            ps = Koneksi.prepareStatement(query);
            ps.setString(1, categoryname);
            ps.setInt(2, categoryid);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null,"Data Berhasil Diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah");
        }
    }
public void HapusCategory(){
        query = "DELETE FROM category WHERE categoryid = ?";
        try {
            ps = Koneksi.prepareStatement(query);
            ps.setInt(1, categoryid);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
        }
    
    }
public ResultSet TampilCategory(){
        query = "SELECT * FROM category";
        
        try{
            st = Koneksi.createStatement();
            rs = st.executeQuery(query);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan");
        }
        return rs;
}

public ResultSet dataComboBox(){
        try {
            query = "SELECT categoryName FROM category";
            
            st = Koneksi.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return rs;
    }
    
    
    public ResultSet konversi(){
        try {
            query = "SELECT categoryId FROM category WHERE categoryName = ?";
            
            ps = Koneksi.prepareStatement(query);
            ps.setString(1, this.categoryname);
            
            rs = ps.executeQuery();
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return rs;
    }

    public ResultSet autoID(){
        try {
            query = "SELECT categoryId AS ID FROM category ORDER BY categoryId DESC LIMIT 1";
            
            st = Koneksi.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Error : " + sQLException.getMessage());
        }
        return rs;
    }
}




