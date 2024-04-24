/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2.topicTbl;

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
public class topicDAO {

    public List<topicDTO> getTopicByName(String searchTopicValue)
            throws SQLException, ClassNotFoundException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<topicDTO> list_topic = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT * FROM topicTbl WHERE title LIKE ?";
                stm = con.prepareStatement(query);
                stm.setString(1,"%"+ searchTopicValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int topic_id = rs.getInt("topic_id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    list_topic.add(new topicDTO(topic_id, title, content));
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
        return list_topic;
    }

    public List<topicDTO> getTopicData() throws SQLException, ClassNotFoundException, Exception {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<topicDTO> list_topic = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String query = "SELECT * FROM topicTbl";
                stm = con.prepareStatement(query);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int topic_id = rs.getInt("topic_id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    list_topic.add(new topicDTO(topic_id, title, content));
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
        return list_topic;
    }
}
