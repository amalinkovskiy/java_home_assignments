package ua.stqa.pft.mantis.tests.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by amalinkovskiy on 6/2/2017.
 */
public class Users extends ForwardingSet<User> {

    private Set<User> delegate;

    public Users(Users users) {
        this.delegate = new HashSet<User>(users.delegate);
    }

    public Users() {
        this.delegate = new HashSet<>();
    }

    public Users(List<User> users) {
        this.delegate = new HashSet<User>(users);

    }

    @Override
    protected Set<User> delegate() {
        return delegate;
    }

}
