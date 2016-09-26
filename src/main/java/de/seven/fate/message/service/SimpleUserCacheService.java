package de.seven.fate.message.service;

import de.seven.fate.cache.UserCacheService;
import de.seven.fate.message.enums.AttributeName;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserCacheService implements UserCacheService{

    @Override
    public boolean exist(String userName) {
        return false;
    }

    @Override
    public <O> O getAttribute(String userName, AttributeName attributeName) {
        return null;
    }

    @Override
    public void setAttribute(String userName, AttributeName attributeName, Object attributeValue) {

    }

    @Override
    public void removeAttribute(String userName, AttributeName attributeName) {

    }

    @Override
    public void removeAttributes(String userName) {

    }

    @Override
    public void remove(String userName) {

    }

    @Override
    public void clear() {

    }
}
