/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2.postTbl;

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
public class postDAO {

    public postDTO insertPost(int author_id, int topic_id, String post_title, String post_content, String image) throws SQLException, ClassNotFoundException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        postDTO insert_post = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "INSERT INTO postTbl(user_id,topic_id,title,content,isHidden,isDelete,image,voteSum) VALUES (?,?,?,?,'0','0',null,'0')";
                stm = con.prepareStatement(query);
                stm.setInt(1, author_id);
                stm.setInt(2, topic_id);
                stm.setString(3, post_title);
                stm.setString(4, post_content);
                stm.executeUpdate();
                //image
                insert_post = new postDTO(author_id, topic_id, post_title, post_content, false, false, null, 0);
                //voteSum
                // sql check ok -- testing createPost.jsp
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return insert_post;
    }

    public postDTO getPostById(int view_post_id) throws SQLException, ClassNotFoundException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        postDTO view_post = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT * FROM postTbl WHERE post_id = ?";
                stm = con.prepareCall(query);
                stm.setInt(1, view_post_id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    //int post_id = rs.getInt("post_id");
                    int user_id = rs.getInt("user_id");
                    int topic_id = rs.getInt("topic_id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    boolean isHidden = rs.getBoolean("isHidden");
                    boolean isDelete = rs.getBoolean("isDelete");
                    String image = rs.getString("image");
                    int voteSum = rs.getInt("voteSum");
                    view_post = new postDTO(view_post_id, user_id, topic_id, title, content, isHidden, isDelete, image, voteSum);
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

        return view_post;
    }

    public List<postDTO> getPostByTopic(int topic_id) throws SQLException, ClassNotFoundException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<postDTO> list_post = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT * FROM postTbl WHERE topic_id = ?";
                stm = con.prepareCall(query);
                stm.setInt(1, topic_id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int post_id = rs.getInt("post_id");
                    int user_id = rs.getInt("user_id");
                    //topic_id
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    boolean isHidden = rs.getBoolean("isHidden");
                    boolean isDelete = rs.getBoolean("isDelete");
                    String image = rs.getString("image");
                    int voteSum = rs.getInt("voteSum");
                    list_post.add(new postDTO(post_id, user_id, topic_id, title, content, isHidden, isDelete, image, voteSum));
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

        return list_post;
    }

    public List<postDTO> getPostData() throws SQLException, ClassNotFoundException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<postDTO> list_post = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT * FROM postTbl";
                stm = con.prepareStatement(query);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int post_id = rs.getInt("post_id");
                    int user_id = rs.getInt("user_id");
                    int topic_id = rs.getInt("topic_id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    boolean isHidden = rs.getBoolean("isHidden");
                    boolean isDelete = rs.getBoolean("isDelete");
                    String image = rs.getString("image");
                    int voteSum = rs.getInt("voteSum");
                    list_post.add(new postDTO(post_id, user_id, topic_id, title, content, isHidden, isDelete, image, voteSum));
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
        return list_post;
    }

    //get PostData by user_id
    public List<postDTO> getPostData(int userID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<postDTO> list_post = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT * FROM postTbl WHERE user_id = ?";
                stm = con.prepareStatement(query);
                stm.setInt(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int post_id = rs.getInt("post_id");
                    int user_id = rs.getInt("user_id");
                    int topic_id = rs.getInt("topic_id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    boolean isHidden = rs.getBoolean("isHidden");
                    boolean isDelete = rs.getBoolean("isDelete");
                    String image = rs.getString("image");
                    int voteSum = rs.getInt("voteSum");
                    list_post.add(new postDTO(post_id, user_id, topic_id, title, content, isHidden, isDelete, image, voteSum));
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
        return list_post;
    }
}
