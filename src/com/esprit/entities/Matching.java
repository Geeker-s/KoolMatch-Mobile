/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author wassimromdhane
 */
public class Matching {

    private int id_match;
    private int id_user1;
    private int id_user2;

    public Matching() {
    }

    public Matching(int id_match) {
        this.id_match = id_match;
    }

    public Matching(int id_user1, int id_user2) {
        this.id_user1 = id_user1;
        this.id_user2 = id_user2;
    }

    public int getId_match() {
        return id_match;
    }

    public int getId_user1() {
        return id_user1;
    }

    public int getId_user2() {
        return id_user2;
    }

    public void setid_match(int id_match) {
        this.id_match = id_match;
    }

    public void setId_user1(int id_user1) {
        this.id_user1 = id_user1;
    }

    public void setId_user2(int id_user2) {
        this.id_user2 = id_user2;
    }

    @Override
    public String toString() {
        return "matching{" + "\n\tid_match=" + id_match + "\n\tid_user1=" + id_user1 + "\n\tid_user2=" + id_user2 + '}' + "\n\t";
    }

}
