package ua.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by amalinkovskiy on 5/15/2017.
 */
public class Users extends ForwardingSet<UserData> {

    private Set<UserData> delegate;

    public Users(Users users) {
        this.delegate = new HashSet<UserData>(users.delegate);
    }

    public Users() {
        this.delegate = new HashSet<>();
    }

    public Users(List<UserData> users) {
        this.delegate = new HashSet<UserData>(users);

    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }

    public Users withAdded(UserData user){
        Users users = new Users(this);
        users.add(user);
        return users;
    }

    public Users without(UserData user){
        Users users = new Users(this);
        users.remove(user);
        return users;
    }
}
