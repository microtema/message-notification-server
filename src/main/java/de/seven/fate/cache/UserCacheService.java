package de.seven.fate.cache;

import de.seven.fate.message.enums.AttributeName;
import org.springframework.stereotype.Service;

@Service
public interface UserCacheService {

    /**
     * @param userName
     * @return true is user cache exist
     */
    boolean exist(String userName);

    /**
     * @param userName
     * @param attributeName
     * @param <O>
     * @return null if cache is not registered or not found
     */
    <O> O getAttribute(final String userName, AttributeName attributeName);


    /**
     * set given attribute in user related cache if registered
     *
     * @param userName
     * @param attributeName
     * @param attributeValue
     */
    void setAttribute(final String userName, AttributeName attributeName, Object attributeValue);


    /**
     * set given attribute in user related cache if registered
     *
     * @param userName
     * @param attributeName
     */
    void removeAttribute(final String userName, AttributeName attributeName);

    /**
     * remove all attributes in user related cache if registered
     *
     * @param userName
     */
    void removeAttributes(final String userName);

    /**
     * remove user cache by given name
     *
     * @param userName
     */
    void remove(final String userName);

    /**
     * clear all cache
     */
    void clear();
}