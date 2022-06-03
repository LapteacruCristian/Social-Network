package server;

import java.util.ArrayList;
import java.util.List;

public class SocialNetwork {
    private List<Person> persons= new ArrayList();

    public String register(Person newPerson){
        for(Person p : persons){
            if(p.getName().equals(newPerson.getName())){
                return "Inregistrare ESUATA: exista deja o Persoana cu un astfel de nume.";
            }

        }
        this.persons.add(newPerson);
        return "Inregistrare cu SUCCES: "+newPerson.getName();
    }
    public boolean login(String name){
        for(Person p:persons){
            if(p.getName().equals(name)){
                if(!p.isLogged()){
                    p.setLogged(true);
                    return true;
                }

            }
        }
        return false;
    }
    public boolean addFriend(Person person, String names){
        List<String> name=new ArrayList<>();
        name.add(String.valueOf(names.split(" ")));
        for(Person p:persons){
            for(String s:name){
                if(p.getName().equals(s) && s!=person.getName()){
                    person.addFriend(p);
                    return true;
                }
            }
        }
        return false;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "SocialNetwork{" +
                "persons=" + persons +
                '}';
    }
}