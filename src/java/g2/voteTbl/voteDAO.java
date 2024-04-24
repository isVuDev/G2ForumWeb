/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2.voteTbl;

import g2.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author APC
 */
public class voteDAO {
    public List<voteDTO> getVoteData() throws SQLException, ClassNotFoundException, Exception {
        //getVoteData with mod_id
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<voteDTO> list_vote = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String query = "SELECT * FROM voteTbl";
                stm = con.prepareStatement(query);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int vote_id = rs.getInt("vote_id");
                    int user_id = rs.getInt("user_id");
                    int post_id = rs.getInt("post_id");
                    int vote_type = rs.getInt("vote_type");
                    list_vote.add(new voteDTO(vote_id, user_id, post_id, vote_type));
                }
            }
           
        } finally {
            if(rs!= null) rs.close();
            if(stm!= null) stm.close();
            if(con!= null) con.close();
        }
        return list_vote;
    }
    
    public boolean sumbitVote(int user_id,int post_id,int vote_type)throws SQLException, ClassNotFoundException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String query = "INSERT INTO voteTbl (user_id,post_id,vote_type) "
                        + "VALUES (?,?,?)";
                stm = con.prepareStatement(query);
                stm.setInt(1, user_id);
                stm.setInt(2, post_id);
                stm.setInt(3, vote_type);
                if (stm.executeUpdate()>0){
                    result = true;
                }              
            }
           
        } finally {
            if(stm!= null) stm.close();
            if(con!= null) con.close();
        }
        return result;
    }
    //check if user has voted before updating to database
    public boolean hasVoted(int postId, int userId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            String query = "SELECT * FROM voteTbl WHERE post_id = ? AND user_id = ?";
            stmt = con.prepareStatement(query);
            stmt.setInt(1, postId);
            stmt.setInt(2, userId);
            rs = stmt.executeQuery();
            return rs.next(); // If a row is found, user has already voted
        
        } finally {
            // Close resources
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
        
    public boolean removeVote(int postId, int userId) throws SQLException,ClassNotFoundException {
        Connection connection = null;
        PreparedStatement stmt = null;
        boolean result = false;

        try {
            connection = DBUtils.getConnection();
            String query = "DELETE FROM voteTbl WHERE post_id = ? AND user_id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, postId);
            stmt.setInt(2, userId);
            if (stmt.executeUpdate()>0){
                result = true;
            }
        } finally {
            // Close resources
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        } return result; 
    }
    
    public int calculateVotesByPostID(int postId) throws SQLException,ClassNotFoundException {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int voteSum = 0;

        try {
            connection = DBUtils.getConnection();
            String query = "SELECT SUM(vote_type) AS total_votes FROM voteTbl WHERE post_id = ? GROUP BY post_id";
                       
            
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, postId);
            rs = stmt.executeQuery();
            if (rs.next()) {
               voteSum = rs.getInt("total_votes");
            }
        } finally {
            // Close resources
            if (rs != null){
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
            
        } return voteSum; 
    }

    
}
