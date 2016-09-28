package com.zhao.mapp.greendao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.zhao.mapp.greendao.Users;

import com.zhao.mapp.greendao.UsersDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig usersDaoConfig;

    private final UsersDao usersDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        usersDaoConfig = daoConfigMap.get(UsersDao.class).clone();
        usersDaoConfig.initIdentityScope(type);

        usersDao = new UsersDao(usersDaoConfig, this);

        registerDao(Users.class, usersDao);
    }
    
    public void clear() {
        usersDaoConfig.getIdentityScope().clear();
    }

    public UsersDao getUsersDao() {
        return usersDao;
    }

}
