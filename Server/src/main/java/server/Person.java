package server;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private boolean isLogged;
    private List<Person> friends= new ArrayList();

    Person(String name,boolean isLogged){
        this.name=name;
        this.isLogged=isLogged;
    }
    Person(){
        this.name=null;
        this.isLogged=false;
        this.friends=null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getFriends() {
        return friends;
    }

    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
    public void addFriend(Person friend){
        friends.add(friend);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", friends=" + friends +
                '}';
    }
}