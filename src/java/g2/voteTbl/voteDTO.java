/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g2.voteTbl;

/**
 *
 * @author APC
 */
public class voteDTO {
    private int vote_id;
    private int user_id;
    private int post_id;
    private int vote_type;

    public voteDTO() {
    }
//constructor -- mod_id -null
    
    public voteDTO(int vote_id, int user_id, int post_id, int vote_type) {
        this.vote_id = vote_id;
        this.user_id = user_id;
        this.post_id = post_id;
        this.vote_type = vote_type;
    }

    public int getVote_id() {
        return vote_id;
    }

    public void setVote_id(int vote_id) {
        this.vote_id = vote_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getVote_type() {
        return vote_type;
    }

    public void setVote_type(int vote_type) {
        this.vote_type = vote_type;
    }
    
    
    
}
