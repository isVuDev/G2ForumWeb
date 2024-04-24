/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2.userTbl;

import g2.utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nam
 */
public class userDAO {

    public userDTO checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        userDTO acc = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "select * from userTbl where username=? and password=?";
                stm = con.prepareStatement(query);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int userID = rs.getInt("user_id");
                    String email = rs.getString("email");
                    Date birthdate = rs.getDate("birthdate");
                    boolean isMod = rs.getBoolean("isMod");
                    boolean isDelete = rs.getBoolean("isDelete");
                    boolean isBanned = rs.getBoolean("isBanned");
                    byte[] avatar = rs.getBytes("avatar");
                    acc = new userDTO(userID, username, password, email, birthdate, isMod, isDelete, isBanned, avatar);
                    // public userDTO(int user_id, String username, String password, String email, Date birthDate, boolean isMod, boolean isDelete,boolean isBanned, byte[] avatar) {

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return acc;
    }

    public boolean checkAccountExisted(String username) throws SQLException, ClassNotFoundException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "select * from userTbl where username = ? ";
                stm = con.prepareStatement(query);
                stm.setString(1, username);
                rs = stm.executeQuery();
                return rs.next();
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;//exception
    }

    public boolean checkEmailExisted(String email) throws SQLException, ClassNotFoundException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "select * from userTbl where email = ? ";
                stm = con.prepareStatement(query);
                stm.setString(1, email);
                rs = stm.executeQuery();

                return rs.next();
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;//exception
    }

    public boolean signup(String username, String email, String password) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "insert into userTbl(username,email,password) values(?,?,?)";
                stm = con.prepareStatement(query);
                stm.setString(1, username);
                stm.setString(2, email);
                stm.setString(3, password);
                int affectedRows = stm.executeUpdate();
                return affectedRows == 1;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public userDTO getUser(String username) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        userDTO acc = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "select * from userTbl where username=?";
                stm = con.prepareStatement(query);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int userID = rs.getInt("user_id");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    Date birthdate = rs.getDate("birthdate");
                    boolean isMod = rs.getBoolean("isMod");
                    boolean isDelete = rs.getBoolean("isDelete");
                    boolean isBanned = rs.getBoolean("isBanned");
                    byte[] avatar = rs.getBytes("avatar");
                    acc = new userDTO(userID, username, password, email, birthdate, isMod, isDelete, isBanned, avatar);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }

        }
        return acc;
    }

    public List<userDTO> getUsers(String searchValue)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<userDTO> users = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT * FROM userTbl WHERE username like ?";
                stm = con.prepareStatement(query);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int userID = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    Date birthdate = rs.getDate("birthdate");
                    boolean isMod = rs.getBoolean("isMod");
                    boolean isDelete = rs.getBoolean("isDelete");
                    boolean isBanned = rs.getBoolean("isBanned");
                    byte[] avatar = rs.getBytes("avatar");
                    users.add(new userDTO(userID, username, password, email, birthdate, isMod, isDelete, isBanned, avatar));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return users;
    }

    public boolean deleteUser(String userName) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "DELETE FROM userTbl WHERE username = ?";
                stm = con.prepareStatement(query);
                stm.setString(1, userName);
                int affectedRows = stm.executeUpdate();
                return affectedRows == 1;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean banUser(String username, boolean isBanned) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "UPDATE userTbl SET isBanned = ? WHERE username = ?";
                stm = con.prepareStatement(query);
                stm.setBoolean(1, isBanned);
                stm.setString(2, username);
                int affectedRows = stm.executeUpdate();
                return affectedRows == 1;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateUser(String userName, String password, String email,
            Date birthdate, byte[] avatar) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "UPDATE userTbl SET password = ?, email = ?,"
                        + " birthdate = ?, avatar = ? WHERE username = ?";
                stm = con.prepareStatement(query);
                stm.setString(1, password);
                stm.setString(2, email);
                stm.setDate(3, birthdate);
                stm.setBytes(4, avatar);
                stm.setString(5, userName);
                int affectedRows = stm.executeUpdate();
                return affectedRows == 1;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public List<userDTO> getMods(boolean isMod)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<userDTO> mods = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT * FROM userTbl WHERE isMod = ?";
                stm = con.prepareStatement(query);
                stm.setBoolean(1,isMod);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int userID = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    Date birthdate = rs.getDate("birthdate");
                    boolean isDelete = rs.getBoolean("isDelete");
                    boolean isBanned = rs.getBoolean("isBanned");
                    byte[] avatar = rs.getBytes("avatar");
                    mods.add(new userDTO(userID, username, password, email, birthdate, isMod, isDelete, isBanned, avatar));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return mods;
    }

    public userDTO getUserByID(int user_id)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        userDTO user = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT * FROM userTbl WHERE user_id like ?";
                stm = con.prepareStatement(query);
                stm.setInt(1,user_id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int userID = rs.getInt("user_id");
                    String username = rs.getString("username");                 
                    String email = rs.getString("email");
                    Date birthdate = rs.getDate("birthdate");
                    boolean isMod = rs.getBoolean("isMod");
                    boolean isDelete = rs.getBoolean("isDelete");
                    boolean isBanned = rs.getBoolean("isBanned");
                    byte[] avatar = rs.getBytes("avatar");
                    user = new userDTO(userID, username, null, email, birthdate, isMod, isDelete, isBanned, avatar);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return user;
    }
}
