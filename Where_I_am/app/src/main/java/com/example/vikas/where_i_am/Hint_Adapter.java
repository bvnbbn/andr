package com.example.vikas.where_i_am;

/**
 * Created by vikas on 7/4/17.
 */

public class Hint_Adapter {
    private String name,hint;

    public Hint_Adapter() {
    }

    public Hint_Adapter(String name,String hint)
    {
        this.name=name;
        this.hint = hint;

    }

    public String getHint()
    {

        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getName()
    {

        return name;
    }

    public void setName(String name) {
        this.name =name;
    }

}
