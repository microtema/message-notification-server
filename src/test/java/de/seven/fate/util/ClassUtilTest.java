package de.seven.fate.util;

import de.seven.fate.message.bo.MessageBO;
import de.seven.fate.message.converter.Message2MessageBOConverter;
import de.seven.fate.message.domain.Message;
import de.seven.fate.message.enums.UserName;
import de.seven.fate.message.resource.MessageResource;
import org.junit.Test;

import java.lang.annotation.Annotation;

import static org.junit.Assert.*;

/**
 * Created by Mario on 08.10.2016.
 */
public class ClassUtilTest {

    ClassUtil sut;

    @Test
    public void getGenericType() throws Exception {

        assertSame(MessageBO.class, ClassUtil.getGenericType(Message2MessageBOConverter.class, 0));
        assertSame(Message.class, ClassUtil.getGenericType(Message2MessageBOConverter.class, 1));
    }

    @Test
    public void newInstance() throws Exception {

        assertNotNull(ClassUtil.newInstance(Message.class));
    }

    @Test
    public void getIndexOfParameter() throws Exception {

        Annotation[][] parameterAnnotations = MessageResource.class.getDeclaredMethod("getMessages", String.class).getParameterAnnotations();

        int indexOfParameter = ClassUtil.getIndexOfParameter(parameterAnnotations, UserName.class);

        assertEquals(0, indexOfParameter);
    }

}