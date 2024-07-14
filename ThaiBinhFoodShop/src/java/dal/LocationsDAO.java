/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 *
 * @author MTDT
 */
public class LocationsDAO extends DBContext {

    public List getProvince() {
        List list = new ArrayList<>();
        String sql = "SELECT DISTINCT province  FROm Locations";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String province = rs.getString("province");
                list.add(province);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List getDistrict(String province) {
        List list = new ArrayList<>();
        String sql = "SELECT DISTINCT district FROM Locations WHERE province = N'"+ province +"'";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
               String district = rs.getString("district");
                list.add(district);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List getWard(String district) {
        List list = new ArrayList<>();
        String sql = "SELECT ward FROM Locations WHERE district = N'"+ district+"'";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
               String ward = rs.getString("ward");
                list.add(ward);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
