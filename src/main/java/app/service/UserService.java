package app.service;

import app.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhujay on 16/6/15.
 */
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

}
