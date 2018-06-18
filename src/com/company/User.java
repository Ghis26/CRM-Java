package com.company;

/**
 * La classe com.company.User est la classe parente des autres utilisateurs.
 */

public class User {
    protected String login;
    protected String password;

    public User(){
        login   = "";
        password = "";
    }

    public String getLogin()
    {
        return login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void disconnect(){
        System.out.println("A bientôt !");
    }
}
